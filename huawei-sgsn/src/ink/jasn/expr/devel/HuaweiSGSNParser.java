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
import ink.jasn.ded.util.constant.StaticParameters;

import java.io.FileWriter;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import com.ranorg.etl.core.ETL;
import com.ranorg.etl.exception.ETLException;
import com.ranorg.etl.misc.ETLConstants;
import com.ranorg.etl.record.DataRecord;
import com.ranorg.etl.record.DataRecord.Field;
import com.ranorg.etl.record.Record;

public class HuaweiSGSNParser extends ASNETLParser {

	private static final Logger log = Logger.getLogger(HuaweiSGSNParser.class);

	public HuaweiSGSNParser() {
	}

	@Override
	public String getSourceName() {
		return "HuaweiSGSN";
	}

	@Override
	public String getGramarFile() {
		return "CDRF_R6_Org.asn";
	}

	@Override
	protected void parseAndStore(GenericASNParser genPer,
			ArrayBlockingQueue<Record> recordStore) throws ETLException {
		while (genPer.hasMoreVTN()) {
			CadedDataTreeNode<Object> mvtN;
			try {
				mvtN = genPer.slide();
				String recordName = mvtN.getChildren().get(0).getName();
				DataRecord dr = createBlankRecordFromFieldNames(getFieldNames(recordName));
				if (dr != null) {
					for (Field fld : dr.getFields()) {
						if (fld.getName().equals(ETLConstants.RECORD_NAME)) {
							fld.setValue(recordName);
							continue;
						}
						fillRecordFields(mvtN.getChildren().get(0), fld);
					}
					recordStore.put(dr);
				} else {
					log.warn("Unknown record type : " + recordName);
				}
			} catch (Exception e1) {
				throw new ETLException(e1);
			}
		}
	}

	public static void main(String[] args) throws ETLException {
		ETL.main(null);
	}

	public static void main1(String[] args) {
		ASNGrammarReader agr = new ASNGrammarReader(Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("CDRF_R6_Org.asn"));

		ASNMaster masterNode;

		try {
			FileWriter fwr = new FileWriter("/home/eamitmo/Development/ran-workspace/huawei-sgsn/data/ModelSytaxTreeF.TXT");
			masterNode = agr.parseGrammar();
			fwr.write(masterNode.toStringFull());
			fwr.flush();
			fwr.close();

			GenericASNParser genPer = new GenericASNParser(masterNode);

			genPer.configure(StaticParameters.ASN_BER,
					"/home/eamitmo/Development/ran-workspace/huawei-sgsn/data/S-CDR.dat");
			fwr = new FileWriter("/home/eamitmo/Development/ran-workspace/huawei-sgsn/data/ModelValueTreeF.TXT");

			int i = 0;
			while (genPer.hasMoreVTN()) {
				CadedDataTreeNode<Object> mvtN = genPer.slide();
				fwr.write(mvtN.toString());
				i++;
			}
			fwr.flush();
			fwr.close();

			System.out.println("TestAParser.main()---> FINISH !!!---> " + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
