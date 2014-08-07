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
 * @Source 	 	 : Tag.java
 * @Description : ASN.1 Tag representation class
 * @Timeline 	 : May 11, 2013 09:00:48 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */
package ink.jasn.ca.type.base;

import ink.jasn.ca.type.common.Specifier;
import ink.jasn.ded.util.constant.ASNTagParamaters;

public class Tag extends Specifier<Integer, Boolean, Integer> implements
			ASNTagParamaters, Comparable<Tag>
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 747493380886943913L;
	/**
	 * is it explicit or implicit?
	 */
	private boolean				explicit;

	/**
	 * 
	 */
	public Tag()
	{
		super();
	}

	/**
	 * Constructs an ASN.1 Tag instance.
	 * 
	 * @param clazz
	 *           the tag's class.
	 * @param value
	 *           the tag's value.
	 * @param explicit
	 *           whether this tag is explicit or implicit. Default is explicit.
	 * @param type
	 *           whether this tag is constructed or not. Default is not
	 *           constructed.
	 */
	public Tag(int clazz, boolean type, int value, boolean explicit)
	{
		super(clazz, type, value);
		this.explicit = explicit;
	}

	/**
	 * Convenience constructor. Constructs an ASN.1 Tag instance. The resulting
	 * tag is of a non-constructed type.
	 * 
	 * @param clazz
	 *           the tag's class.
	 * @param value
	 *           the tag's value.
	 * @param explicit
	 *           whether this tag is explicit or implicit. Default is explicit.
	 */
	public Tag(int clazz, int value, boolean explicit)
	{
		this(clazz, false, value, explicit);
	}

	public Tag(int clazz, boolean type, int value)
	{
		super(clazz, type, value);
	}

	/**
	 * Convenience constructor. Constructs an ASN.1 Tag instance. The resulting
	 * tag is explicit, of a non-constructed type.
	 * 
	 * @param clazz
	 *           the tag's class.
	 * @param value
	 *           the tag's value.
	 */
	public Tag(int clazz, int value)
	{
		this(clazz, false, value, true);
	}

	/**
	 * Convenience constructor. Constructs an ASN.1 Tag instance. The resulting
	 * tag is of class UNIVERSAL and is of a non-constructed type.
	 * 
	 * @param value
	 *           the tag's value.
	 * @param explicit
	 *           whether this tag is explicit or implicit. Default is explicit.
	 */
	public Tag(int value, boolean explicit)
	{
		this(TAG_CLASS_UNIVERSAL, false, value, explicit);
	}

	/**
	 * Convenience constructor. Constructs an ASN.1 Tag instance. The resulting
	 * tag is of class UNIVERSAL, is explicit, and is of a non-constructed type.
	 * 
	 * @param value
	 *           the tag's value.
	 */
	public Tag(int value)
	{
		this(TAG_CLASS_UNIVERSAL, false, value, true);
	}

	/**
	 * @param tag
	 */
	public Tag(Tag tag)
	{
		this(tag.name, tag.type, tag.value, tag.explicit);
	}

	/**
	 * @return a cloned instance of this.
	 */
	public Tag clone()
	{
		return new Tag(this);
	}

	public int compareTo(Tag o)
	{
		return (this.value - o.value);
	}

	/**
	 * @param tag
	 * @return
	 */
	public boolean match(Tag tag)
	{
		// if( !(name == tag.name()))
		// return false;

		boolean match = value.equals(tag.value());
		// System.out.println( "MATCH --->>>"+ match+" G-TAG :" + this.value +
		// " D-TAG :" +
		// tag.value );

		return match;
	}

	@Override
	public Integer defaultValue()
	{
		return value;
	}

	public boolean isOfClass(int clazz)
	{
		return (name == clazz);
	}

	/**
	 * s * @return
	 */
	public boolean isConstructed()
	{
		return type;
	}

	/**
	 * @param clazz
	 */
	public void setClass(int clazz)
	{
		name = clazz;
	}

	/**
	 * @param tagType
	 */
	public void setType(boolean tagType)
	{
		type = tagType;
	}

	/**
	 * @param tagValue
	 */
	public void setValue(int tagValue)
	{
		value = tagValue;
	}

	/**
	 * @param explicit
	 *           the explicit to set
	 */
	public void setExplicit()
	{
		this.explicit = true;
	}

	/**
	 * @param explicit
	 *           the explicit to set
	 */
	public void setExplicit(boolean explicit)
	{
		this.explicit = explicit;
	}

	/**
	 * @return
	 */
	public boolean isExplicit()
	{
		return explicit;
	}
	
	/* (non-Javadoc)
	 * @see ink.jasn.ca.type.common.Specifier#toString()
	 */
	@Override
	public String toString()
	{
		return "Name :"+name+" Type :"+type+" Value :"+value;
	}
}
