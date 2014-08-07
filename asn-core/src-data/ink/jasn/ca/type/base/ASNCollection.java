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
 * @Source 	 	 : ASNCollection.java
 * @Description : A general purpose tree node implementation can be used to represent any tree data.
 * @Timeline 	 : Jan 13, 2013 11:30:14 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */
package ink.jasn.ca.type.base;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ded.util.codec.ASNDataReader;

public abstract class ASNCollection extends ASNConstructed
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2659737881192942043L;

	/**
	 * @param string
	 */
	public ASNCollection(String string)
	{
		super(string);
	}

	/**
	 * @param name
	 * @param tag
	 */
	public ASNCollection(String name, Tag tag)
	{
		super(name, tag);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CadedDataTreeNode<Object> decode(ASNDataReader asnDataReader) throws Exception
	{
		CadedDataTreeNode<Object> mvtNode = asnDataReader.decodeCollection(this);
		return mvtNode;
	}

}
