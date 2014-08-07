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
 * @Source 	 	 : ASNType.java
 * @Description : Basis for any ASN.1 data type declaration. Holds all the common property needs to express asn.1 type.
 * @Timeline 	 : May 01, 2013 03:09:43 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */

package ink.jasn.ca.type.base;

import ink.jasn.ca.basis.central.AbstractSyntaxTreeNode;
import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.basis.central.Visitor;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

public abstract class ASNType extends AbstractSyntaxTreeNode
{
	private static final long serialVersionUID = -1539787378399664749L;

	/**
     * 
     */
	protected Tag tag;

	/**
     * 
     */
	protected Tag tagUniv;

	/**
     * 
     */
	public ASNType()
	{
		super();
	}

	/**
	 * @param name
	 */
	protected ASNType(String name)
	{
		super(name);
	}

	/**
	 * @param name
	 */
	protected ASNType(String name, boolean optional)
	{
		this(name);
		this.optional = optional;
	}

	/**
     * 
     */
	protected ASNType(String name, Tag tag)
	{
		this(name);
		this.tag = tag;
	}

	/**
     * 
     */
	protected ASNType(String name, Tag tag, boolean optional)
	{
		this(name);
		this.tag = tag;
		this.optional = optional;
	}

	/**
	 * @return
	 */
	@Override
	public boolean isDefined()
	{
		return false;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public abstract boolean checkCompleteness() throws Exception;

	/**
	 * @param tag
	 * @return
	 * @throws Exception
	 */
	public abstract ASNType decide(Tag tag) throws Exception;

	/**
	 * @param asnDataReader
	 * @return
	 * @throws Exception
	 */
	public abstract CadedDataTreeNode<Object> decode(ASNDataReader asnDataReader)
				throws Exception;

	/**
	 * @param asnDataWriter
	 * @return
	 * @throws Exception
	 */
	public abstract CadedDataTreeNode<Object> encode(ASNDataWriter asnDataWriter)
				throws Exception;

	/**
     * 
     */
	public void reset()
	{
		System.out.println("ASNType.reset opearation NULL RESEET CALLED from " + name);
	}

	@Override
	public String toString()
	{
		return this.name;
	}

	public String toStringFull()
	{
		return toString("");
	}

	public String toStringCompact()
	{
		return toStringSpecial("");
	}

	public String toString(String str)
	{
		StringBuilder returnString = new StringBuilder("\n" + str + "[ Name:" + name
					+ "\tType:" + type + "\tReference:" + reference + "\tTag:"
					+ ((tag != null) ? tag.toString() : "empty") + "\tOptional:" + optional
					+ " ]");
		if (getChildren() != null)
		{
			str += "\t|---";
			for (AbstractSyntaxTreeNode asnType : getChildren())
			{
				returnString.append(asnType.toString(str));
			}
		}
		return returnString.toString();
	}

	public String toStringSpecial(String str)
	{
		StringBuilder returnString = new StringBuilder("\n" + str + "[ Name:" + name
					+ "\tType:" + type + " ]");
		if (getChildren() != null)
		{
			str += "\t|---";
			for (AbstractSyntaxTreeNode asnType : getChildren())
			{
				returnString.append(((ASNType) asnType).toStringSpecial(str));
			}
		}
		return returnString.toString();
	}

	/**
	 * @param tag
	 *           the tag to set
	 */
	public void setTag(Tag tag)
	{
		this.tag = tag;
	}

	/**
	 * @return
	 */
	public Tag getTag()
	{
		return tag;
	}

	/**
	 * @return
	 */
	public Tag getTagUniv()
	{
		return tagUniv;
	}

	/**
	 * @param tagUniv
	 */
	public void setTagUniv(Tag tagUniv)
	{
		this.tagUniv = tagUniv;
	}

	/**
     * 
     */
	@Override
	public String toXMLString()
	{
		return null;
	}

	/**
     * 
     */
	@Override
	public void visit(Visitor visitor)
	{
		visitor.visit(this);
	}

	/**
     * 
     */
	@Override
	public boolean isScalar()
	{
		return false;
	}

}
