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
 * @Source 	 	 : ASNDefinedType.java
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

public class ASNDefinedType extends ASNType
{
	private static final long serialVersionUID = -3560980813397651375L;

	public boolean isModuleReference;

	public String moduleReference;

	private String typeName;

	public AsnConstraint constraint;

	private final String TYPE_STRING = "DEFINED TYPE";

	/**
     * 
     */
	public ASNDefinedType()
	{
		type = TYPE_STRING;
	}

	/**
	 * @param name
	 */
	public ASNDefinedType(String name)
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param tag
	 */
	public ASNDefinedType(String name, Tag tag)
	{
		super(name, tag);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param tag
	 * @param optional
	 */
	public ASNDefinedType(String name, Tag tag, boolean optional)
	{
		super(name, tag, optional);
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName()
	{
		return typeName;
	}

	/**
	 * @param typeName
	 *           the typeName to set
	 */
	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}

	@Override
	public CadedDataTreeNode<Object> decode(ASNDataReader asnDataReader) throws Exception
	{
		CadedDataTreeNode<Object> decodedMVTNode = asnDataReader.decodeDefined(this);
		if (decodedMVTNode != null)
			decodedMVTNode.setName(name);
		return decodedMVTNode;
	}

	@Override
	public CadedDataTreeNode<Object> encode(ASNDataWriter asnDataWriter) throws Exception
	{
		return null;
	}

	@Override
	public boolean isDefined()
	{
		return true;
	}

	@Override
	public boolean isOptional()
	{
		if (optional)
			return optional;
		else
		{
			ASNType asnType;
			try
			{
				asnType = decide(null);
				while (!asnType.isOptional() && asnType.isDefined())
					asnType = asnType.decide(null);
				return asnType.isOptional();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
	}

	@Override
	public boolean checkCompleteness()
	{
		return true;
	}

	@Override
	public ASNType decide(Tag aTag) throws Exception
	{
		return (ASNType) childByIndex(0);
	}

	@Override
	public void reset()
	{
	}
}
