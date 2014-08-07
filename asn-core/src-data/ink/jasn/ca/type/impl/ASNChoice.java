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
 * @Source 	 	 : ASNChoice.java
 * @Description : ASN Choice Data Type Definition
 * @Timeline 	 : sep 17, 2013 11:30:14 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */
package ink.jasn.ca.type.impl;

import ink.jasn.ca.basis.central.AbstractSyntaxTreeNode;
import ink.jasn.ca.type.base.ASNSelection;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ca.type.grammar.AsnElementTypeList;

public class ASNChoice extends ASNSelection
{
	private static final long serialVersionUID = -1510957782059297791L;
	public AsnElementTypeList elementTypeList;
	public final String BUILTINTYPE = "CHOICE";

	public final String TYPE_STRING = "CHOICE";

	{
		type = "CHOICE";
	}

	/**
    * 
    */
	public ASNChoice()
	{
		super("");
		type = TYPE_STRING;
	}

	/**
	 * @param name
	 */
	public ASNChoice(String name)
	{
		super(name);
	}

	@Override
	public void reset()
	{

	}

	@Override
	public ASNType decide(Tag aTag) throws Exception
	{
		ASNType asnTypeNode = null;
		Tag mTag, tempTag;
		String firstDefinedName;
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
				if (tempTag != null)
				{
					if (aTag.match(tempTag))
						return asnTypeNode;
					else
						mTag = tempTag;
				}
				asnTypeNode.setName(firstDefinedName);
			}

			if (asnTypeNode.getTag() != null && asnTypeNode.getTag().match(aTag))
				return asnTypeNode;
			else if (!asnTypeNode.isLeaf() && (asnTypeNode instanceof ASNChoice))
				return asnTypeNode = asnTypeNode.decide(aTag);

		}
		return null;
		// throw new ASNException( ASNErrorCodes.TNODE_ERROR_DECIDING_CHILD, new
		// Object[] { name, aTag } );
	}
}
