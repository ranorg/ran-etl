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
 * @Source 	 	 : TestComment.java
 * @Description : SHORT DESCRIPTION ABOUT THE FILE
 * @Timeline 	 : Jun 11, 2013 10:01:48 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */
package ink.jasn.ca.type.base;

import ink.jasn.ca.basis.central.AbstractSyntaxTreeNode;
import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.impl.ASNChoice;
import ink.jasn.com.exception.ASNErrorCodes;
import ink.jasn.com.exception.ASNException;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

public class ASNMaster extends ASNType
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2930396476166817946L;

	private CadedDataTreeNode<Object>	mvtNode;

	private String								grammarFileName;

	private ASNDataReader					asdr;

	private boolean							hasMoreAvailableVTN	= true;

	{
		type = "Master";
	}

	public ASNMaster()
	{

	}

	/**
	 * @param name
	 */
	public ASNMaster(String name)
	{
		super(name);
	}

	/**
	 * @return the grammarFileName
	 */
	public String getGrammarFileName()
	{
		return grammarFileName;
	}

	/**
	 * @param grammarFileName
	 *           the grammarFileName to set
	 */
	public void setGrammarFileName(String grammarFileName)
	{
		this.grammarFileName = grammarFileName;
	}

	public CadedDataTreeNode<Object> process(ASNDataReader asnDataReader)
				throws Exception
	{
		hasMoreAvailableVTN = true;
		asdr = asnDataReader;
		CadedDataTreeNode<Object> decodedMVTNode = null;
		try
		{
			asdr.slide();
			Tag lTag = asdr.getTag();
			ASNType asnType = decide(lTag);

			if (asnType != null)
				decodedMVTNode = asnType.decode(asdr);
			else
				throw new ASNException(ASNErrorCodes.CODEC_PARSING_FAILED);
			
			if (mvtNode == null)
				return decodedMVTNode;
			else
				mvtNode.addChild(decodedMVTNode);
		}
		catch (Exception e)
		{
			hasMoreAvailableVTN = false;
			throw e;
		}
		return mvtNode;
	}

	@Override
	public ASNType decide(Tag aTag) throws Exception
	{
		ASNType asnTypeNode = null;
		String firstDefinedName;
		Tag tempTag, mTag;
		for (AbstractSyntaxTreeNode mstNode : getChildren())
		{
			asnTypeNode = (ASNType) mstNode;
			mTag = asnTypeNode.getTag();

			if (mTag != null && mTag.match(aTag))
				return asnTypeNode;
			firstDefinedName = asnTypeNode.getName();

			while (asnTypeNode.isDefined())
			{
				try
				{
					asnTypeNode = asnTypeNode.decide(null);
				}
				catch (Exception e)
				{
					throw e;
				}
				tempTag = asnTypeNode.getTag();
				asnTypeNode.setName(firstDefinedName);

				if (tempTag != null)
				{
					if (aTag.match(tempTag))
						return asnTypeNode;
					else
						mTag = tempTag;
				}
			}

			if (asnTypeNode instanceof ASNChoice)
			{
				mvtNode = new CadedDataTreeNode<Object>(asnTypeNode.getName());
				asnTypeNode = asnTypeNode.decide(aTag);
				if (asnTypeNode != null)
					return asnTypeNode;
			}

			if (asnTypeNode != null && aTag.match(asnTypeNode.getTagUniv()))
				return asnTypeNode;
		}
		return null;
	}

	public boolean hasMoreVTN()
	{
		return (hasMoreAvailableVTN & asdr.hasMoreData());
	}

	@Override
	public CadedDataTreeNode<Object> decode(ASNDataReader asnDataReader) throws Exception
	{
		return null;
	}

	@Override
	public CadedDataTreeNode<Object> encode(ASNDataWriter asnDataWriter) throws Exception
	{
		return null;
	}

	@Override
	public boolean checkCompleteness()
	{
		return false;
	}

	@Override
	public void reset()
	{

	}

	public ASNMaster selfCopy()
	{
		return new ASNMaster(name);
	}
}
