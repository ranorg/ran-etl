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
 * @Source 	 	 : ASNMainParser.java
 * @Description : SHORT DESCRIPTION ABOUT THE FILE
 * @Timeline 	 : Jul 22, 2013 12:51:17 AM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */

package ink.jasn.ca.basis.parser;

import ink.jasn.ca.type.grammar.ASNModule;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import antlr.RecognitionException;
import antlr.TokenStreamException;

public class ASNMainParser
{

	/**
	 * 
	 */
	public ASNMainParser()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws TokenStreamException
	 * @throws RecognitionException
	 */
	public static void main(String[] args) throws IOException, RecognitionException,
				TokenStreamException
	{
		// InputStream is=new
		// FileInputStream("D:/GENERATOR/TestZONE/GGSN/ggsn_r6.asn");
		InputStream is = new FileInputStream(
					"D:/GENERATOR/TestZONE/MSC/TMUK_REnabler_8.4_v1.6.asn");
		// InputStream is=new
		// FileInputStream("D:/GENERATOR/TestZONE/Sample ASN.1/EmployeeASN.asn");

		// InputStream is=new
		// FileInputStream("D:\\GENERATOR\\ASN\\arc\\x509.asn1");

		// InputStream is=new
		// FileInputStream("D:/GENERATOR/BinaryNotes1.5.1-all/BNCompiler/testworkdir/test.asn");

		ASNLexer lex = new ASNLexer(is);
		ASNParser asn = new ASNParser(lex);

		ASNModule module = new ASNModule();

		asn.module_definition(module);

		// System.out.println(module.toString());

		// module = new ASNModule();

		// asn.module_body( module );
		// asn.assignment( module );

		// System.out.println(module.toString());
		// System.out.println("========================");
		// System.out.println(module.tag);
		// System.out.println(module.tagDefault);

		// List<ASNType> tl=module.asnTypes.sequenceSets;
		// System.out.println(tl);

		// System.out.println(module.asnTypes.typeList);

		System.out.println(module);

		// ModelSyntaxTreeOrganizer sto=new
		// ModelSyntaxTreeOrganizer(module.asnTypes.typeList);

		// sto.organize();

		// System.out.println(module.asnValues);
		// System.out.println(module.asnTypes.defineds);

		// System.out.println(module.asnTypes.octetStrings);

	}

}
