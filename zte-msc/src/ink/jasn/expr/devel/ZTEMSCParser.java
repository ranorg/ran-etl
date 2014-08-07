/*
 * 
 * Copyright 2013 ZCrypto, All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, without modification, 
 * are permitted subject to the following conditions:
 *
 * 1. Any use in source and binary forms must have a valid license from ZCrypto.
 *
 * 2. Redistributions of source code must retain the above copyright notice together with a valid license 
 *	   agreement and this list of conditions and the following disclaimer.
 *
 *	3. Redistributions in binary form must reproduce the above copyright notice together with a valid license 
 *	   agreement and this list of conditions and the following disclaimer in the documentation and/or other 
 * 	materials provided with the distribution.
 *
 * 4. Any short of use to endorse or promote products are not permitted without any prior written permission
 *		from ZCrypto.
 *
 * @Vertical 	 : Telecommunication   
 *	@Project  	 : Compiler for ASN with Data Encoder and Decoder (CA-DED)
 * @Source 	 	 : XtremeASNParser.java
 * @Description : SHORT DESCRIPTION ABOUT THE FILE
 * @Timeline 	 : Sep 14, 2013 12:04:58 AM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */

package ink.jasn.expr.devel;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.basis.helper.GenericASNParser;
import ink.jasn.ca.basis.parser.ASNETLParser;
import ink.jasn.ca.basis.parser.ASNGrammarReader;
import ink.jasn.ca.type.base.ASNMaster;
import ink.jasn.com.exception.ASNEOFException;
import ink.jasn.ded.util.codec.ASNBERBlockReader;
import ink.jasn.ded.util.constant.StaticParameters;
import ink.jasn.ded.util.io.DataReaderFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import com.ranorg.etl.core.ETL;
import com.ranorg.etl.exception.ETLException;
import com.ranorg.etl.misc.ETLConstants;
import com.ranorg.etl.record.DataRecord;
import com.ranorg.etl.record.DataRecord.Field;
import com.ranorg.etl.record.Record;

public class ZTEMSCParser extends ASNETLParser {

	private static final Logger log = Logger.getLogger(ZTEMSCParser.class);

	public ZTEMSCParser() {
	}

	@Override
	public String getSourceName() {
		return "ZTEMSC";
	}

	@Override
	public String getGramarFile() {
		return "wcs_asn_v6.2.0.asn";
	}

	@Override
	public void configureParser(String inputFile) throws Exception {
		genPer.configure(StaticParameters.ASN_BER, inputFile);
		ASNBERBlockReader blockReader = (ASNBERBlockReader) DataReaderFactory
				.getASNDataReader(StaticParameters.ASN_BER_BLOCK, inputFile);
		blockReader.setFillerCheck(true);
		blockReader.setBlockSize(2048);
		genPer.configure(blockReader);
	}

	@Override
	protected void parseAndStore(GenericASNParser genPer,
			ArrayBlockingQueue<Record> recordStore) throws ETLException {
		while (genPer.hasMoreVTN()) {
			CadedDataTreeNode<Object> mvtN;
			try {
				Set<String> st = new HashSet<String>();
				mvtN = genPer.slide();
				for (CadedDataTreeNode<Object> childOfCallEventRecord : mvtN
						.getChildren()) {
					String recordName = childOfCallEventRecord.getName();
					st.add(recordName);
					LinkedHashSet<String> flds = getFieldNames(recordName);
					if (flds != null) {
						DataRecord dr = createBlankRecordFromFieldNames(flds);
						if (dr != null) {
							for (Field fld : dr.getFields()) {
								if (fld.getName().equals(
										ETLConstants.RECORD_NAME)) {
									fld.setValue(recordName);
									continue;
								}
								fillRecordFields(childOfCallEventRecord, fld);
							}
							recordStore.put(dr);
						}
					} else {
						log.warn("Unknown record type : " + recordName);
					}
				}
			} catch (ASNEOFException e) {
				// e.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new ETLException(e1);
			}
		}
	}

	public static void main(String[] args) throws ETLException {
		/*
		 * HuaweiMSCParser hsp = new HuaweiMSCParser();
		 * hsp.initialize("/home/eamitmo/Downloads/HUAWEI_MSC/H20130904102647.dat"
		 * ); boolean hasNext = true; while (hasNext) { //
		 * System.out.println("HuaweiMSCParser.main(MM)"); try { Record
		 * dataRecord = hsp.getNextRecord(); //
		 * System.out.println("HuaweiMSCParser.main()" + dataRecord); } catch
		 * (Exception e) { hasNext = false; } }
		 */
		ETL.main(null);
		// main1(null);
	}

	public static void main1(String[] args) {
		ASNGrammarReader agr = new ASNGrammarReader(
				"/home/eamitmo/development/project/ran-project/ran-workspace/zte-msc/src/wcs_asn_v6.2.0.asn");
		int i = 0;
		ASNMaster masterNode;
		FileWriter fwr = null;
		GenericASNParser genPer = null;
		Set<String> set = new HashSet<String>();
		try {
			fwr = new FileWriter(
					"/home/eamitmo/development/project/ran-project/ran-workspace/zte-msc/data/ModelValueTreeF.TXT");
			masterNode = agr.parseGrammar();
			fwr.write(masterNode.toStringFull());
			fwr.flush();
			fwr.close();

			genPer = new GenericASNParser(masterNode);

			ASNBERBlockReader blockReader = (ASNBERBlockReader) DataReaderFactory
					.getASNDataReader(StaticParameters.ASN_BER_BLOCK,
							"/home/eamitmo/development/project/ran-project/Doc/zte-msc_data_spec/B2014032332293.dat");

			// "/home/enirbis/Extra/ProjectOASN/Finalized/ZTE-MSC/data/ZTE_MSC/B20130904000000.dat");

			// /home/enirbis/Extra/ProjectOASN/Finalized/ZTE-MSC/data/B20131023000001.dat

			blockReader.setFillerCheck(true);
			blockReader.setBlockSize(2048);

			// genPer.configure(StaticParameters.ASN_BER,
			// "/home/enirbis/Extra/ProjectOASN/Finalized/ZTE-MSC/data/ZTE_MSC/B20130904000000.dat");
			genPer.configure(blockReader);

			fwr = new FileWriter(
					"/home/eamitmo/development/project/ran-project/ran-workspace/zte-msc/data/ModelValueTreeF.TXT");

			// int i = 0;

			while (genPer.hasMoreVTN()) {
				CadedDataTreeNode<Object> mvtN = genPer.slide();
				for (CadedDataTreeNode<Object> childOfCallEventRecord : mvtN
						.getChildren()) {
					set.add(childOfCallEventRecord.getName());
				}
				fwr.write(mvtN.toString());
				i++;
			}
			System.out.println("ZTEMSCParser.main()" + set);
			System.out.println("TestAParser.main()---> FINISH !!!---> " + i);
		} catch (ASNEOFException e) {
			System.out.println("ZTEMSCParser.main()" + set);
			System.out.println("INKZTEMSCDecoder.main() FINISHED..." + i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fwr.flush();
				fwr.close();
				genPer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
