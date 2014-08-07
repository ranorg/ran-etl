package ink.jasn.ca.basis.parser;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.basis.helper.GenericASNParser;
import ink.jasn.ca.type.base.ASNMaster;
import ink.jasn.ded.util.constant.StaticParameters;

import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import com.ranorg.etl.core.Parser;
import com.ranorg.etl.exception.ETLException;
import com.ranorg.etl.exception.NoMoreRecordException;
import com.ranorg.etl.record.DataRecord;
import com.ranorg.etl.record.DataRecord.Field;
import com.ranorg.etl.record.EndRecord;
import com.ranorg.etl.record.FieldFactory;
import com.ranorg.etl.record.RBFieldFactory;
import com.ranorg.etl.record.Record;

public abstract class ASNETLParser extends Parser {

	private static final Logger log = Logger.getLogger(ASNETLParser.class);
	protected GenericASNParser genPer = null;
	protected ArrayBlockingQueue<Record> recordStore = new ArrayBlockingQueue<Record>(
			1000);
	private FieldFactory fieldFactory = null;

	@Override
	public void initialize(String inputFile) throws ETLException {
		try {
			InputStream is = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(getGramarFile());
			if (is == null)
				throw new ETLException(
						"Couldn't find gramar file in class path.");
			ASNGrammarReader agr = new ASNGrammarReader(is);
			ASNMaster masterNode = agr.parseGrammar();
			genPer = new GenericASNParser(masterNode);
			configureParser(inputFile);
			fieldFactory = new RBFieldFactory();
			fieldFactory.initialize();
			startProducer();
		} catch (Exception e) {
			throw new ETLException(e);
		}
	}

	public void configureParser(String inputFile) throws Exception {
		genPer.configure(StaticParameters.ASN_BER, inputFile);
	}

	private Thread producerThread = null;

	private void startProducer() {
		producerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					parseAndStore(genPer, recordStore);
				} catch (ETLException e) {
					log.error("", e);
				} finally {
					try {
						recordStore.put(new EndRecord());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		});
		producerThread.start();
	}

	private void stopProducer() {
		try {
			// producerThread.interrupt();
			producerThread.join();
		} catch (Exception e) {
			log.error("", e);
		}
	}

	protected abstract void parseAndStore(GenericASNParser genPer,
			ArrayBlockingQueue<Record> recordStore) throws ETLException;

	@Override
	public Record getNextRecord() throws NoMoreRecordException, ETLException {
		Record ret = null;
		try {
			ret = recordStore.take();
			if (ret instanceof EndRecord) {
				throw new NoMoreRecordException();
			} else
				return ret;
		} catch (NoMoreRecordException e) {
			stopProducer();
			throw e;
		} catch (Exception e) {
			stopProducer();
			throw new ETLException(e);
		}
	}

	protected DataRecord createBlankRecordFromFieldNames(
			LinkedHashSet<String> list) {
		if (list != null) {
			DataRecord dr = new DataRecord();
			for (String name : list) {
				dr.addField(new Field(name, ""));
			}
			return dr;
		}
		return null;
	}

	protected void fillRecordFields(CadedDataTreeNode<Object> mvtN, Field fld) {
		for (CadedDataTreeNode<Object> ch : mvtN.getChildren()) {
			if (ch.getName().equals(fld.getName())) {
				if (ch.getNumberOfChildren() == 0) {
					fld.setValue(ch.getValue());
				} else {
					for (CadedDataTreeNode<Object> ch1 : ch.getChildren()) {
						fillChildValue(ch1, fld);
					}
				}
				break;
			}
		}
	}

	protected void fillChildValue(CadedDataTreeNode<Object> ch, Field fld) {
		if (ch.getNumberOfChildren() == 0) {
			fld.appendValue(ch.getValue());
			return;
		}

		for (CadedDataTreeNode<Object> ch1 : ch.getChildren()) {
			fillChildValue(ch1, fld);
		}
	}

	@Override
	public LinkedHashSet<String> getFieldNames(String type) {
		return fieldFactory.getFieldNames(type);
	}

	public abstract String getGramarFile();

}
