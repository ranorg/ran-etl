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
 * @Source 	 	 : ASNCharacterString.java
 * @Description : A general purpose tree node implementation can be used to represent any tree data.
 * @Timeline 	 : Jan 13, 2013 11:30:14 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */
package ink.jasn.ca.type.base;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.grammar.AsnConstraint;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

public class ASNCharacterString extends ASNPrimitive
{
	/**
     * 
     */
	private static final long serialVersionUID = -9189946131480957690L;
	public AsnConstraint constraint;
	public final String BUILTINTYPE = "CHARACTER STRING";

	{
		type = "CHARACTER STRING";
	}

	/**
     * 
     */
	public ASNCharacterString()
	{
		super("");
	}

	/**
	 * @param name
	 */
	public ASNCharacterString(String name)
	{
		super(name);
	}

	/**
	 * @param name
	 * @param tag
	 */
	public ASNCharacterString(String name, Tag tag)
	{
		super(name, tag);
	}

	/**
	 * @param name
	 * @param tag
	 * @param optional
	 */
	public ASNCharacterString(String name, Tag tag, boolean optional)
	{
		super(name, tag, optional);
	}

	@Override
	public CadedDataTreeNode<Object> decode(ASNDataReader asnDataReader) throws Exception
	{
		CadedDataTreeNode<Object> mvtNode = new CadedDataTreeNode<Object>(name);
		mvtNode.setData(asnDataReader.decodeString());
		return mvtNode;
	}

	@Override
	public CadedDataTreeNode<Object> encode(ASNDataWriter asnDataWriter) throws Exception
	{
		return null;
	}

}
