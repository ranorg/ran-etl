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
 * @Source 	 	 : ASNGrammarReader.java
 * @Description : ASN.1 Syntax file reader
 * @Timeline 	 : May 11, 2013 09:00:48 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */
package ink.jasn.ca.basis.parser;

import ink.jasn.ca.basis.helper.ModelSyntaxTreeOrganizer;
import ink.jasn.ca.type.base.ASNMaster;
import ink.jasn.ca.type.grammar.ASNModule;
import ink.jasn.com.exception.ASNErrorCodes;
import ink.jasn.com.exception.ASNException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;

import antlr.RecognitionException;
import antlr.TokenStreamException;

public class ASNGrammarReader {
	private String grammarStxFileName;

	private InputStream is;

	/**
	 * Default constructor
	 */
	public ASNGrammarReader() {

	}

	/**
	 * Constructor with grammar file name
	 * 
	 * @param grammarFileName
	 */
	public ASNGrammarReader(InputStream is) {
		super();
		this.is = is;
	}

	/**
	 * Constructor with grammar file name
	 * 
	 * @param grammarFileName
	 */
	public ASNGrammarReader(String grammarFileName) {
		super();
		this.grammarStxFileName = grammarFileName;
	}

	/**
	 * Parse an ASN.1 grammar file.
	 */
	public ASNMaster parseGrammar() throws Exception {
		if (grammarStxFileName == null && is == null)
			throw new ASNException(ASNErrorCodes.GRAMMAR_FILE_NOT_SET);
		return readASNSyntaxFile(false);
	}

	/**
	 * Parse an ASN.1 grammar file.
	 */
	public ASNMaster parseSyntax(String grammarFile) throws Exception {
		grammarStxFileName = grammarFile;
		return readASNSyntaxFile(false);
	}

	/**
	 * Read ASN.1 grammar file
	 * 
	 * @return
	 * @throws Exception
	 */
	private ASNMaster readASNSyntaxFile(boolean mode) throws Exception {
		ASNMaster asnMasterNode = null;

		if (is == null) {
			try {
				is = new FileInputStream(grammarStxFileName);
			} catch (FileNotFoundException e) {
				throw new ASNException(ASNErrorCodes.GRAMMAR_FILE_NOT_FOUND,
						new Object[] { e.getMessage() });
			}
		}
		ASNLexer lex = new ASNLexer(is);
		ASNParser asn = new ASNParser(lex);
		ASNModule module = new ASNModule();

		try {
			asn.module_definition(module);
		} catch (RecognitionException e) {
			throw new ASNException(ASNErrorCodes.GRAMMAR_SYNTAX_ERROR,
					new Object[] { e.getMessage() });
		} catch (TokenStreamException e) {
			throw new ASNException(ASNErrorCodes.GRAMMAR_STREAM_ERROR,
					new Object[] { e.getMessage() });
		}

		ModelSyntaxTreeOrganizer msto = new ModelSyntaxTreeOrganizer(
				module.asnTypes.typeList);
		asnMasterNode = msto.organize(mode);

		asnMasterNode.setName(module.moduleIdentifier.name);
		asnMasterNode.setGrammarFileName(grammarStxFileName);
		return asnMasterNode;
	}

	/**
	 * Testing of possibileities
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ASNGrammarReader ar = new ASNGrammarReader(
				"D:/GAMMATZ/TMOD/T_Mobile_rating_enable.asn");

		// ASNGrammarReader ar = new ASNGrammarReader(
		// "D:/GENERATOR/TestZONE/asn parsers/Ericsson_EMM_NGNAdapter/grammer/EMM_NGN.asn"
		// );

		FileWriter fw = new FileWriter("D:/GAMMATZ/TMOD/MSTO.TXT");

		ASNMaster master = ar.readASNSyntaxFile(false);

		fw.write(master.toString());
		fw.close();
		// System.out.println( ar.readASNSyntaxFile(true) );
	}

}
