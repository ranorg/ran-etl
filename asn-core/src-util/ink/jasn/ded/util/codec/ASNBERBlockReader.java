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
 * @Source 	 	 : ASNBERBlockReader.java
 * @Description : SHORT DESCRIPTION ABOUT THE FILE
 * @Timeline 	 : Oct 24, 2013 4:36:28 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */

package ink.jasn.ded.util.codec;

import ink.jasn.ca.type.base.Tag;
import ink.jasn.com.exception.ASNEOFException;
import ink.jasn.com.exception.ASNErrorCodes;
import ink.jasn.com.exception.ASNException;

import java.io.File;
import java.io.IOException;

public class ASNBERBlockReader extends ASNBERReader
{

	private int blockSize;

	private int tempBlockSize;

	private boolean fillerCheck;

	/**
	 * 
	 */
	public ASNBERBlockReader()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param fileName
	 * @throws IOException
	 */
	public ASNBERBlockReader(String fileName) throws IOException
	{
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param file
	 * @throws IOException
	 */
	public ASNBERBlockReader(File file) throws IOException
	{
		super(file);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param blockSize
	 *           the blockSize to set
	 */
	public void setBlockSize(int blockSize)
	{
		this.blockSize = blockSize;
		this.tempBlockSize = blockSize;
	}

	/**
	 * @param fillerCheck
	 *           the fillerCheck to set
	 */
	public void setFillerCheck(boolean fillerCheck)
	{
		this.fillerCheck = fillerCheck;
	}

	/**
	 * Read ASN.1 BER Tag from provided byte buffer stream. Decoding TAG follow [
	 * ITU-T Standard X.690, 8.1.2 ].
	 */
	@Override
	protected Tag readTag() throws Exception
	{
		int tClass, tValue;
		byte b;
		if (tempBlockSize <= 0)
			tempBlockSize = blockSize;

		b = readByte();
		tempBlockSize -= 1;

		if ((b & BIT_ALL_MASK) == 0xFF)
		{
			if (fillerCheck)
			{
				checkFillerInBlock();
				b = readByte();
				if (b == -1)
					throw new ASNEOFException(ASNErrorCodes.DATA_FILE_READ_ERROR,
								new Object[] { "END OF FILE reached." });
				tempBlockSize -= 1;
			}
		}

		tClass = b & BIT_8_AND_7_MASK;
		constructed = (0 != (b & BIT_6_MASK));
		tValue = b & BIT_5_TO_1_MASK; /*
												 * ITU-T Standard X.690, 8.1.2.3 TagID<32
												 * Short Form of Tag
												 */

		if (tValue == BIT_5_TO_1_MASK) // Check for the Long Form
		{
			/* ITU-T Standard X.690, 8.1.2.4.X Multi-byte tag TagID>=32 */
			tValue = 0;
			do
			{
				b = readByte();
				tempBlockSize -= 1;
				tValue = (tValue << 7) | (BIT_7_TO_1_MASK & b);
			}
			while (0 != (BIT_8_MASK & b));
		}
		return new Tag(tClass, constructed, tValue);
	}

	/**
	 * @throws ASNException
	 * 
	 */
	private void checkFillerInBlock() throws ASNException
	{
		byte b;
		while (tempBlockSize > 0)
		{
			b = readByte();
			tempBlockSize -= 1;
			if ((b & BIT_ALL_MASK) != BIT_ALL_MASK)
			{
				throw new ASNException(ASNErrorCodes.CODEC_DATA_CORRUPTION,
							new Object[] { "in the data block" });
			}
		}
		tempBlockSize = blockSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ink.jasn.ded.util.codec.ASNBERReader#readLength()
	 */
	@Override
	protected int readLength() throws Exception
	{
		byte h, x;
		int length;

		h = readByte();
		tempBlockSize -= 1;
		if (0 == (BIT_8_MASK & h))
			return h; /* definite short form */

		h &= BIT_7_TO_1_MASK; /* definite long form */
		if (h == 0)
			return INDEFINITE_LENGTH; /* indefinite long form */

		length = 0;
		/* definite long form */
		while (h-- > 0)
		{
			x = readByte();
			tempBlockSize -= 1;
			length = (length << 8) | (BIT_ALL_MASK & x);
		}
		return length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ink.jasn.ded.util.codec.ASNBERReader#readContent(int)
	 */
	@Override
	protected byte[] readContent(int length) throws Exception
	{
		tempBlockSize -= length;
		return super.readContent(length);
	}

	public static void main(String[] args)
	{
		System.out.println("ASNBERBlockReader.main()");
	}

}
