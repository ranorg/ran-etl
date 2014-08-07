package ink.jasn.ded.util.codec;

/***
 * Packages and Classes Import :
 * @-----------------------------
 */
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.com.exception.ASNErrorCodes;
import ink.jasn.com.exception.ASNException;
import ink.jasn.ded.util.common.ByteArrayDataHandler;
import ink.jasn.ded.util.common.DecodeUtil;

import java.io.IOException;
import java.util.BitSet;
import java.util.Date;

/**
 * @Source : ASNDERReader.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 18, 2009
 * @Time : 2:02:04 PM
 * @Version : $0.01
 **/
public class ASNDERReader extends ASNBERReader
{
	/**
	 * Default constructor
	 */
	public ASNDERReader()
	{
		super();
	}

	/**
	 * Constructor with file name as a parameter
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public ASNDERReader(String fileName) throws IOException
	{
		super(fileName);
	}

	/**
     *
     */
	@Override
	public int readLength() throws Exception
	{
		int result;
		int byteCount = 0;

		int limit = read();
		byteCount++;
		if (limit == 0x80) // NON-definite Length encoding
		{
			throw new ASNException(ASNErrorCodes.CODEC_UNSUPPOTED_ENCODING, new Object[] {
					"Indefinite length enconding", "DER" });
		}

		if ((limit & 0x80) == 0)
		{
			result = limit;
		}
		else
		{
			limit &= 0x7F;
			if (limit > 4)
				throw new ASNException(ASNErrorCodes.CODEC_INVALID_LENGTH_SIZE);
			result = 0;
			while (limit-- > 0)
			{
				result = (result << 8) | (read() & 0xFF);
				byteCount++;
			}
		}
		return result;
	}

	/**
	 * The encoding of a boolean value is primitive. The contents octets shall
	 * consist of a single octet. If the boolean value is FALSE the octet will be
	 * zero. If the boolean value is TRUE the octet will have all eight bits set
	 * to one.
	 */
	public Boolean decodeBoolean() throws Exception
	{
		int length = getLength();
		if (length != 1)
			throw new ASNException(ASNErrorCodes.CODEC_INVALID_LENGTH, new Object[] {
					"BOOLEAN", length });
		int x = BIT_ALL_MASK & readByte();
		if (x == 0)
			return false;
		else if (x == 1)
			return true;
		else
			throw new ASNException(ASNErrorCodes.CODEC_BADLY_ENCODED_DATA, new Object[] {
					"BOOLEAN", "DER" });
	}

	/**
	 * The encoding of a null value shall be primitive. The contents octets shall
	 * not contain any octets.
	 */
	public ASNType decodeNull() throws Exception
	{
		byte[] content = getContent();
		int lengthx = content.length;
		if (lengthx != 0)
			throw new ASNException(ASNErrorCodes.CODEC_INVALID_LENGTH, new Object[] {
					"NULL", lengthx });
		return null;
	}

	/**
	 * The decoding of a bitstring value shall be either primitive or constructed
	 * 
	 * @return String
	 */
	public String decodeBitString() throws Exception
	{
		StringBuilder valueToReturn = new StringBuilder("");
		long endOffset = count + length;
		while (count < endOffset)
		{
			slide();
			if (tag.isConstructed())
				valueToReturn.append(decodeBitString());
			else
				valueToReturn.append(decodeDERBitString(content, length));
		}
		return valueToReturn.toString();
	}

	/**
	 * Supporting method for BIT STRING decoder
	 * 
	 * @param value
	 * @param size
	 * @return String
	 * @throws Exception
	 */
	private String decodeDERBitString(byte[] value, int size) throws Exception
	{
		if (size < 0)
			throw new ASNException(ASNErrorCodes.CODEC_DATA_CORRUPTION,
						new Object[] { "BIT STRING" });
		ByteArrayDataHandler badh = new ByteArrayDataHandler(value);
		BitSet set = new BitSet();
		int i, j, m, unused = badh.readByte();
		--size;
		int counter = 0;
		for (i = 1; i < size; ++i)
		{
			m = badh.readByte();
			for (j = 0; j < 8; ++j)
			{
				if (0 != (m & (BIT_8_MASK >> j)))
					set.set(counter);
				++counter;
			}
		}
		m = badh.readByte();
		for (j = 0; j < 8 - unused; ++j)
		{
			if (0 != (m & (BIT_8_MASK >> j)))
				set.set(counter);
			++counter;
		}

		for (j = 8 - unused; j < 8; ++j)
		{
			if (0 != (m & (BIT_8_MASK >> 0)))
				set.set(counter);
			++counter;
		}
		return set.toString();
	}

	/**
	 * The decoding of a octetstring value shall be either primitive or
	 * constructed
	 * 
	 * @return String
	 */
	public String decodeOctetString() throws Exception
	{
		StringBuilder valueToReturn = new StringBuilder("");
		long endOffset = count + length;
		while (count < endOffset)
		{
			slide();
			if (tag.isConstructed())
				valueToReturn.append(decodeOctetString());
			else
				valueToReturn.append(decodeDEROctetString(content, length));
		}
		return valueToReturn.toString();
	}

	/**
	 * Supporting mothod for BER decoding of octet string type.
	 * 
	 * @param value
	 * @param size
	 * @return String
	 * @throws Exception
	 */
	private String decodeDEROctetString(byte[] value, int size) throws Exception
	{
		if (size < 0)
			throw new ASNException(ASNErrorCodes.CODEC_DATA_CORRUPTION,
						new Object[] { "OCTET STRING" });
		return DecodeUtil.toHexString(content);
	}

	/**
	 * The encoding of a real value shall be primitive.
	 */
	public Double decodeReal() throws Exception
	{
		if (length == -1)
			throw new ASNException(ASNErrorCodes.CODEC_INVALID_LENGTH, new Object[] {
					"REAL", length });
		if (length == 0)
			return 0.0;

		ByteArrayDataHandler badh = new ByteArrayDataHandler(content);

		byte initByte = badh.readByte();
		int typeSig = initByte & BIT_8_MASK;
		int operByte, sign, base, factor, exponentSize;
		int mantissa, exponent;
		/**
		 * Bit 8 of the first contents octet is 1
		 */
		if (typeSig == 1)
		{
			operByte = initByte & BIT_7_MASK;
			sign = (operByte == 1) ? -1 : 1;
			operByte = initByte & BIT_6_AND_5_MASK;
			switch (operByte)
			{
				case 0:
					base = 2;
					break;
				case 1:
					base = 8;
					break;
				case 2:
					base = 16;
					break;
				default:
					throw new ASNException(ASNErrorCodes.CODEC_UNSUPPORTED_TYPE,
								new Object[] { "REAL" });
			}
			factor = initByte & BIT_4_AND_3_MASK;

			exponentSize = initByte & BIT_2_AND_1_MASK;
			if (exponentSize == 3)
			{
				exponentSize = BIT_4_TO_1_MASK & badh.readByte();
				length--;
			}
			else
			{
				exponentSize++;
			}
			exponent = (int) badh.readBerInteger(exponentSize);
			length -= exponentSize;

			/*
			 * Read the mantessa and recompute the actual mantessa value based upon
			 * the formula in 8.5.6: M = sign * N * 2**F
			 */
			mantissa = (int) badh.readBerUnsignedInteger(length);
			if (factor != 0)
				mantissa <<= factor;
			mantissa = sign * mantissa;

			/*
			 * Now compute the result and return it.
			 */
			return mantissa * Math.pow((double) base, (double) exponent);
		}

		typeSig = initByte & BIT_8_AND_7_MASK;
		/**
		 * Bit 8 and 7 of the first content octet is 0
		 */
		if (typeSig == 0)
		{

		}

		/**
		 * Bit 8 is 0 and Bit 7 is 1 for the first content octet
		 */
		if (typeSig == 1)
		{
			operByte = initByte & BIT_1_MASK;
			if (operByte == 0)
				return Double.POSITIVE_INFINITY;
			if (operByte == 1)
				return Double.NEGATIVE_INFINITY;
		}

		return null;
	}

	@Override
	public Date decodeGeneralizedTime() throws Exception
	{
		return super.decodeGeneralizedTime();
	}

	@Override
	public Date decodeUTCTime() throws Exception
	{
		return super.decodeUTCTime();
	}

}
