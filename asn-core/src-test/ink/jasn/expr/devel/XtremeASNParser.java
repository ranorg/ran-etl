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
import ink.jasn.ca.basis.parser.ASNGrammarReader;
import ink.jasn.ca.type.base.ASNMaster;
import ink.jasn.ded.util.constant.StaticParameters;

import java.io.FileNotFoundException;
import java.io.FileWriter;

import antlr.RecognitionException;
import antlr.TokenStreamException;

public class XtremeASNParser
{

	/**
	 * 
	 */
	public XtremeASNParser()
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)
	{
		ASNGrammarReader agr = new ASNGrammarReader(
					"/home/enirbis/Extra/ProjectOASN/CDRF_R6_Org.asn");

		ASNMaster masterNode;

		try
		{
			FileWriter fwr = new FileWriter(
						"/home/enirbis/Extra/ProjectOASN/ModelSytaxTreeF.TXT");
			masterNode = agr.parseGrammar();
			fwr.write(masterNode.toStringFull());
			fwr.flush();
			fwr.close();

			GenericASNParser genPer = new GenericASNParser(masterNode);

			genPer.configure(StaticParameters.ASN_BER,
						"/home/enirbis/Extra/ProjectOASN/Huawei-MSC/data/H20130904102649.dat");
			fwr = new FileWriter("/home/enirbis/Extra/ProjectOASN/ModelValueTreeF.TXT");

			int i = 0;
			while (genPer.hasMoreVTN())
			{
				CadedDataTreeNode<Object> mvtN = genPer.slide();
				fwr.write(mvtN.toString());
				i++;
			}
			fwr.flush();
			fwr.close();

			System.out.println("TestAParser.main()---> FINISH !!!---> " + i);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (RecognitionException e)
		{
			e.printStackTrace();
		}
		catch (TokenStreamException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
