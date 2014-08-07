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
 * @Source 	 	 : GenericASNParser.java
 * @Description : A general purpose implementation of ASN parse 
 * @Timeline 	 : Sep 23, 2013 11:30:14 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */

package ink.jasn.ca.basis.helper;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNMaster;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.io.DataReaderFactory;
import ink.jasn.ded.util.io.Slider;

import java.io.IOException;

public class GenericASNParser implements Slider<CadedDataTreeNode<Object>>
{
	private ASNMaster asnMaster;

	private ASNDataReader asnDataReader;

	public GenericASNParser()
	{

	}

	public GenericASNParser(ASNMaster masterASN)
	{
		asnMaster = masterASN;
	}

	public void initialize(ASNMaster masterASN)
	{
		asnMaster = masterASN;
	}

	public void configure(ASNDataReader asnDataReader)
	{
		this.asnDataReader = asnDataReader;
	}

	public void configure(String decoderName, String fileName)
	{
		try
		{
			asnDataReader = DataReaderFactory.getASNDataReader(decoderName, fileName);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void prepare(String dataFileName) throws IOException
	{
		asnDataReader.fixNewFile(dataFileName);
	}

	public boolean hasMoreVTN()
	{
		return asnDataReader.hasMoreData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.connectiva.gpf.io.common.Slider#slide()
	 */
	public CadedDataTreeNode<Object> slide() throws Exception
	{
		CadedDataTreeNode<Object> mvtNode = asnMaster.process(asnDataReader);
		return mvtNode;
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	public void close() throws Exception
	{
		asnDataReader.close();
	}
}
