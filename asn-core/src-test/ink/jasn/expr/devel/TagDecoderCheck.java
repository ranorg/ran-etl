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
 * @Source 	 	 : TagDecoderCheck.java
 * @Description : SHORT DESCRIPTION ABOUT THE FILE
 * @Timeline 	 : Oct 9, 2013 11:40:51 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */

package ink.jasn.expr.devel;

import ink.jasn.ca.type.base.Tag;

public class TagDecoderCheck
{

	// ALL BIT MASKING
	public int BIT_ALL_MASK = 0xFF;

	// SINGLE BIT MASKING
	public static int BIT_8_MASK = 0x80;
	public int BIT_7_MASK = 0x40;
	public int BIT_6_MASK = 0x20;
	public int BIT_5_MASK = 0x10;
	public int BIT_4_MASK = 0x08;
	public int BIT_3_MASK = 0x04;
	public int BIT_2_MASK = 0x02;
	public int BIT_1_MASK = 0x01;

	// BIT COUPLE MASKING
	public static int BIT_8_AND_7_MASK = 0xC0;
	public int BIT_6_AND_5_MASK = 0x30;
	public int BIT_4_AND_3_MASK = 0x0C;
	public int BIT_2_AND_1_MASK = 0x03;

	// BIT RANGE MASKING
	public static int BIT_7_TO_1_MASK = 0x7F;
	public int BIT_6_TO_1_MASK = 0x3F;
	public static int BIT_5_TO_1_MASK = 0x1F;
	public int BIT_4_TO_1_MASK = 0x0F;
	public int BIT_3_TO_1_MASK = 0x07;

	/**
	 * 
	 */
	public TagDecoderCheck()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		String dat = "0x80";
		char[] chard = dat.toCharArray();

		Integer ix = Integer.decode(dat);
		byte bb = Byte.decode("0xF");

		System.out.println("TagDecoderCheck.main(=>" + bb);
		System.out.println("TagDecoderCheck===>" + readTag(chard).toXMLString());
	}

	protected static Tag readTag(char[] chard) throws Exception
	{
		int tClass, tValue;
		byte b;
		b = Byte.decode("0x" + chard[0]);
		tClass = b & BIT_8_AND_7_MASK;
		// constructed = (0 != (b & BIT_6_MASK));
		tValue = b & BIT_5_TO_1_MASK; /*
												 * ITU-T Standard X.690, 8.1.2.3 TagID<32
												 * Short Form of Tag
												 */

		if (tValue == BIT_5_TO_1_MASK) // Check for the Long Form
		{
			/* ITU-T Standard X.690, 8.1.2.4.X Multi-byte tag TagID>=32 */
			tValue = 0;
			int i = 0;
			do
			{
				if (++i >= chard.length)
					break;
				b = Byte.decode("0x" + chard[i]);
				tValue = (tValue << 7) | (BIT_7_TO_1_MASK & b);
			}
			while (0 != (BIT_8_MASK & b));
			
			
		}
		System.out.println("TagDecoderCheck.readTag("+tValue);
		return new Tag(tClass, false, tValue);
	}

}
