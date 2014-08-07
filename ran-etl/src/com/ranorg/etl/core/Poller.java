package com.ranorg.etl.core;

import static com.ranorg.etl.misc.ETLConstants.OUTFILE_FIELD_DELIMETER;
import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.ranorg.etl.db.DAO;
import com.ranorg.etl.exception.ETLException;
import com.ranorg.etl.exception.NoMoreRecordException;
import com.ranorg.etl.misc.ETLConstants;
import com.ranorg.etl.misc.FileStatus;
import com.ranorg.etl.record.DataRecord;
import com.ranorg.etl.record.Record;

public class Poller implements Runnable {

	private static final Logger log = Logger.getLogger(Poller.class);
	private WatchService watchDog = null;
	private File watchDirFile = null;;
	private Path watchDirPath = null;
	private WatchKey registration = null;
	private boolean stop = false;
	private FileLock lock = null;
	private FileChannel lockChannel = null;
	private ResourceBundle rb = null;

	private Map<String, FileWriter> fileWriterMap = null;

	public void init() throws IOException {
		try {
			rb = ResourceBundle.getBundle("etl");
			watchDog = FileSystems.getDefault().newWatchService();
			watchDirFile = new File(rb.getString(ETLConstants.INPUT_DIR));
			if (!watchDirFile.exists()) {
				watchDirFile.mkdir();
				log.debug("inputDir created.");
			}

			File outDir = new File(rb.getString(ETLConstants.OUTPUT_DIR));
			if (!outDir.exists()) {
				outDir.mkdir();
				log.debug("outputDir created.");
			}
			/*
			 * if (rb.getString(ETLConstants.BACKUP_DIR) != null) { File
			 * backupDir = new File(rb.getString(ETLConstants.BACKUP_DIR)); if
			 * (!backupDir.exists()) { backupDir.mkdirs();
			 * log.debug("backupDir created."); } }
			 */
			watchDirPath = watchDirFile.toPath();
			registration = watchDirPath.register(watchDog,
					StandardWatchEventKinds.ENTRY_CREATE/*
														 * ,
														 * StandardWatchEventKinds
														 * . ENTRY_MODIFY,
														 * StandardWatchEventKinds
														 * .ENTRY_DELETE
														 */);
		} catch (IOException e) {
			throw e;
		} catch (UnsupportedOperationException e) {
			throw new IOException(e);
		} finally {
		}
	}

	private void listen() {
		WatchKey event = null;
		while (!stop) {
			try {
				log.debug("Waiting for input file..............in "
						+ watchDirFile.getAbsolutePath());
				event = watchDog.take();
			} catch (InterruptedException e) {

			} catch (ClosedWatchServiceException e) {
				e.printStackTrace();
				stop = true;
			}
			if (event != null && event.isValid()) {
				List<WatchEvent<?>> events = event.pollEvents();

				for (WatchEvent<?> what : events) {
					if (what.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
						String fileName = what.context().toString();
						parseFile(fileName);
					}
				}
				event.reset();
			}
		}
		if (event != null && event.isValid())
			event.cancel();

	}

	private void parseFile(String fileName) {
		log.debug("File received.............." + fileName);

		String inputFile = rb.getString(ETLConstants.INPUT_DIR)
				+ File.separator + fileName;
		FileStatus fileStatus = new FileStatus();
		String status = FileStatus.INITIALIZED;
		DAO du = null;
		try {
			du = getDBUtil();
			fileStatus.setFileName(fileName);
			fileStatus.setStatus(status);
			fileStatus.setStartDate(new Date());
			du.insertStatus(fileStatus);
			log.debug("Status inserted..." + inputFile);
			Parser parser = loadParser();
			parser.initialize(inputFile);
			Transformer transformer = loadTransformer();
			transformer.initialize();
			fileStatus.setSource(parser.getSourceName());
			fileStatus.setStatus(FileStatus.RUNNING);
			fileStatus.setEndDate(new Date());
			du.updateStatus(fileStatus);
			fileWriterMap = new HashMap<String, FileWriter>();
			int i = 0;
			boolean hasNext = true;
			while (hasNext) {
				try {
					Record record = parser.getNextRecord();
					if (record instanceof DataRecord) {
						DataRecord dataRecord = (DataRecord) record;
						dataRecord.getFields(ETLConstants.FILE_NAME).setValue(
								fileName);
						i++;
						transformer.transform(dataRecord);
						writeRecord(dataRecord, parser, fileName);
					}

				} catch (NoMoreRecordException e) {
					hasNext = false;
				}
			}
			fileStatus.setParseCount(i);
			status = FileStatus.SUCCESSFUL;
			log.info("Finished Successfully: Record Count=" + i + " File="
					+ fileName);
		} catch (Exception e) {
			status = FileStatus.FAIL;
			fileStatus.setErrorMessage(e.getMessage());
			log.error("Error during parsing..." + fileName, e);
		} finally {
			closeWriter(inputFile);
			new File(rb.getString(ETLConstants.INPUT_DIR) + File.separator
					+ fileName).delete();
			/*
			 * try { backUp(rb.getString(ETLConstants.INPUT_DIR) +
			 * File.separator + fileName, rb.getString(ETLConstants.BACKUP_DIR)
			 * + File.separator + fileName);
			 * log.debug("File backedup.............." + fileName); } catch
			 * (IOException e) { status = FileStatus.FAIL; String msg =
			 * "Error during file back up.............." + fileName;
			 * fileStatus.setErrorMessage(msg); log.debug(msg, e); }
			 */
			try {
				fileStatus.setStatus(status);
				fileStatus.setEndDate(new Date());
				if (du != null)
					du.updateStatus(fileStatus);
				log.debug("Status updated..." + inputFile);
			} catch (Exception e1) {
				log.error(
						"Error during status update.............." + fileName,
						e1);
			}
		}
	}

	private void closeWriter(String inputFile) {
		for (Map.Entry<String, FileWriter> entry : fileWriterMap.entrySet()) {
			FileWriter fwr = entry.getValue();
			try {
				if (fwr != null) {
					fwr.flush();
					fwr.close();
				}
			} catch (Exception e2) {
				log.debug("Error during file close..." + inputFile, e2);
			}
		}
	}

	private void writeRecord(DataRecord dataRecord, Parser parser,
			String fileName) throws IOException {
		String type = dataRecord.getFields(ETLConstants.RECORD_NAME).getValue();
		FileWriter fwr = fileWriterMap.get(type);
		if (fwr == null) {
			String outputFile = rb.getString(ETLConstants.OUTPUT_DIR)
					+ File.separator + fileName + "_" + type + ".csv";
			fwr = new FileWriter(outputFile);
			String header = getHeader(parser.getAllFieldNames(type));
			fwr.write(header);
			fileWriterMap.put(type, fwr);
		}
		fwr.write("\n" + dataRecord.toString());
	}

	private String getHeader(LinkedHashSet<String> fieldNames) {
		String ret = "";
		String delim = "";
		for (String name : fieldNames) {
			ret += delim + name;
			delim = OUTFILE_FIELD_DELIMETER;
		}
		return ret;
	}

	private DAO getDBUtil() {
		try {
			return (DAO) Class.forName(rb.getString(ETLConstants.DB_UTIL))
					.newInstance();
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (InstantiationException e) {
			log.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	private Parser loadParser() throws ETLException {
		try {
			return (Parser) Class.forName(
					rb.getString(ETLConstants.PARSER_CLASS)).newInstance();
		} catch (Exception e) {
			throw new ETLException(e.toString(), e);
		}
	}

	private Transformer loadTransformer() throws ETLException {
		try {
			String parserClass = rb.getString(ETLConstants.PARSER_CLASS);
			String transformerClass = parserClass.substring(0, parserClass.length()-6)+"Transformer";
			return (Transformer) Class.forName(transformerClass).newInstance();
		} catch (Exception e) {
			throw new ETLException(e.toString(), e);
		}
	}
	
	private void backUp(String infile, String bakfile) throws IOException {
		Path inputFile = new File(infile).toPath();
		Path targetFile = new File(bakfile).toPath();
		Files.move(inputFile, targetFile, ATOMIC_MOVE, REPLACE_EXISTING);
	}

	@Override
	public void run() {
		log.debug("Polling started.....");
		parseIfAny();
		listen();
		close();
		log.debug("Polling ended.....");
	}

	private void parseIfAny() {
		File inputDir = new File(rb.getString(ETLConstants.INPUT_DIR));
		for (String fileName : inputDir.list()) {
			parseFile(fileName);
		}

	}

	private void close() {
		if (registration != null && registration.isValid()) {
			registration.cancel();
		}

		if (watchDog != null) {
			try {
				watchDog.close();

			} catch (IOException e) {

			}
		}
		if (lock != null) {
			try {
				lock.close();
			} catch (IOException e) {
			}
		}
		if (lockChannel != null) {
			try {
				lockChannel.close();
			} catch (IOException e) {
			}
		}

	}
}
