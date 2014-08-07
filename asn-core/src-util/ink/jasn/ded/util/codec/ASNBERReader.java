package ink.jasn.ded.util.codec;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNCollection;
import ink.jasn.ca.type.base.ASNContainer;
import ink.jasn.ca.type.base.ASNDefinedType;
import ink.jasn.ca.type.base.ASNSelection;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ca.type.impl.ASNAny;
import ink.jasn.ca.type.impl.ASNEnumerated;
import ink.jasn.com.exception.ASNErrorCodes;
import ink.jasn.com.exception.ASNException;
import ink.jasn.ded.util.common.ByteArrayDataHandler;
import ink.jasn.ded.util.common.ByteMask;
import ink.jasn.ded.util.common.DecodeUtil;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.BitSet;
import java.util.Date;

/**
 * An implementation ASN.1 decoding named "Basic Encoding Rule" [ BER decoding ] depending upon the
 * ITU-T Standard X.690-0207, 8.Z ;( Z=1(1)26) . In this encoding rule the value may consist with
 * four components in the following order
 * <ol>
 * <li>ASN.1 tag , class type and data type <em>identifier octets</em> X.690-0207, 8.1.2;
 * <li>Size of the content <em>length octets</em> X.690-0207, 8.1.3;
 * <li>Actual value <em>contents octets</em> X.690-0207, 8.1.4;
 * <li>An end of value marker <em>end-of-contents octets</em> X.690-0207, 8.1.5.
 * </ol>
 * The end of content may or may not present depending upon the encoding of the length octets. All
 * the decoding available in this class does not consider any constraints and extensibility.
 */

/**
 * @Source : ASNBERReader.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 18, 2009
 * @Time : 1:59:15 PM
 * @Version : $0.01
 */
public class ASNBERReader extends ASNDataReader implements ByteMask
{
	protected static final int INDEFINITE_LENGTH = -1;

	/**
	 * Default constructor
	 */
	public ASNBERReader()
	{
		super();
	}

	/**
	 * Constructor with file name as a parameter
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public ASNBERReader(String fileName) throws IOException
	{
		super(new FileInputStream(fileName));
	}

	/**
	 * Constructor with {@link File} as a parameter
	 * 
	 * @param file
	 * @throws IOException
	 */
	public ASNBERReader(File file) throws IOException
	{
		super(new FileInputStream(file));
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
		b = readByte();
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
				tValue = (tValue << 7) | (BIT_7_TO_1_MASK & b);
			}
			while (0 != (BIT_8_MASK & b));
		}
		return new Tag(tClass, constructed, tValue);
	}

	/**
	 * Read ASN.1 BER length for the content in the encoded data stream. Decoding
	 * LENGTH has been written by following [ ITU-T Standard X.690, 8.1.3 ].
	 * There are two kind length form
	 * <ul>
	 * <li>the definite form [ ITU-T Standard X.690,8.1.3.3 ]; and
	 * <li>the indefinite form [ ITU-T Standard X.690,8.1.3.6 ].
	 * <ul>
	 * For the definite form, the length octets shall consist of one or more
	 * octets, and shall represent the number of octets in the contents octets
	 * using either the short form or the long form.<br>
	 * <b>In the short form, the length octets shall consist of a single octet in
	 * which bit 8 is zero and bits 7 to 1 encode the number of octets in the
	 * contents octets (which may be zero), as an unsigned binary integer with
	 * bit 7 as the most significant bit.</b><br>
	 * <b>In the long form, the length octets shall consist of an initial octet
	 * and one or more subsequent octets. The initial octet shall be encoded as
	 * follows:
	 * <ol>
	 * <li>bit 8 shall be one;
	 * <li>bits 7 to 1 shall encode the number of subsequent octets in the length
	 * octets, as an unsigned binary integer with bit 7 as the most significant
	 * bit;
	 * <li>the value 11111111 shall not be used.
	 * </ol>
	 * Bits 8 to 1 of the first subsequent octet, followed by bits 8 to 1 of the
	 * second subsequent octet, followed in turn by bits 8 to 1 of each further
	 * octet up to and including the last subsequent octet, shall be the encoding
	 * of an unsigned binary integer equal to the number of octets in the
	 * contents octets, with bit 8 of the first subsequent octet as the most
	 * significant bit.</b> <br>
	 * <b>For the indefinite form, the length octets indicate that the contents
	 * octets are terminated by end-of-contents octets, and shall consist of a
	 * single octet. The single octet shall have bit 8 set to one, and bits 7 to
	 * 1 set to zero</b>
	 */
	@Override
	protected int readLength() throws Exception
	{
		byte h, x;
		int length;

		h = readByte();
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
			length = (length << 8) | (BIT_ALL_MASK & x);
		}
		return length;
	}

	/**
	 * Read the whole content as specified by length in a byte array. This
	 * content will always be of primitive form.
	 */
	@Override
	protected byte[] readContent(int length) throws Exception
	{
		byte[] result = new byte[length];

		int actualLength = read(result);
		if (actualLength == -1)
		{
			throw new EOFException();
		}
		if (actualLength != length)
		{
			throw new ASNException(ASNErrorCodes.CODEC_CONTENT_LENGTH_MISMATCH,
						new Object[] { actualLength, length });
		}
		return result;
	}

	/**
	 * The decoding of a octet string value shall be either primitive or
	 * constructed
	 * 
	 * @return String
	 */
	public String decodeOctetString() throws Exception
	{
		StringBuilder valueToReturn = new StringBuilder("");
		if (tag.isConstructed())
		{
			long endOffset = count + length;
			while (count < endOffset)
			{
				slide();
				if (tag.isConstructed())
					valueToReturn.append(decodeOctetString());
				else
					valueToReturn.append(decodeBEROctetString(content, length));
			}
		}
		else
		{
			valueToReturn.append(decodeBEROctetString(content, length));
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
	private String decodeBEROctetString(byte[] value, int size) throws Exception
	{
		if (size < 0)
			throw new ASNException(ASNErrorCodes.CODEC_DATA_CORRUPTION,
						new Object[] { "Octect String" });
		return DecodeUtil.toHexString(content);
	}

	/**
	 * The decoding of a bitstring value shall be either primitive or constructed
	 * 
	 * @return String
	 */
	public String decodeBitString() throws Exception
	{
		StringBuilder valueToReturn = new StringBuilder("");
		if (tag.isConstructed())
		{
			long endOffset = count + length;
			while (count < endOffset)
			{
				slide();
				if (tag.isConstructed())
					valueToReturn.append(decodeBitString());
				else
					valueToReturn.append(decodeBERBitString(content, length));
			}
		}
		else
		{
			valueToReturn.append(decodeBERBitString(content, length));
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
	private String decodeBERBitString(byte[] value, int size) throws Exception
	{
		if (size < 0)
			throw new ASNException(ASNErrorCodes.CODEC_DATA_CORRUPTION,
						new Object[] { "Bit String" });
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
		return set.toString();
	}

	/**
	 * The encoding of a boolean value is primitive. The contents octets shall
	 * consist of a single octet. If the boolean value is FALSE the octet will be
	 * zero. If the boolean value is TRUE the octet will have any non-zero value.
	 */
	public Boolean decodeBoolean() throws Exception
	{
		int length = getLength();
		if (length != 1)
			throw new ASNException(ASNErrorCodes.CODEC_INVALID_LENGTH, new Object[] {
					"BOOLEAN", length });

		byte x = content[0];
		return (x != 0);
	}

	public String decodeEnumerated(ASNEnumerated enumeratedType) throws Exception
	{
		byte[] content = getContent();
		BigInteger key = new BigInteger(content);
		return enumeratedType.asnNamedNumber.get(key);
	}

	public Date decodeGeneralizedTime() throws Exception
	{
		byte[] content = getContent();
		return DecodeUtil.toGeneralizedTime(content);
	}

	/**
	 * The encoding of an integer value shall be primitive. The contents octets
	 * shall consist of one or more octets. If the contents octets of an integer
	 * value encoding consist of more than one octet, then the bits of the first
	 * octet and bit 8 of the second octet:
	 * <ul>
	 * <li>shall not all be ones; and
	 * <li>shall not all be zero.
	 * <ul>
	 * The contents octets shall be a two's complement binary number equal to the
	 * integer value, and consisting of bits 8 to 1 of the first octet, followed
	 * by bits 8 to 1 of the second octet, followed by bits 8 to 1 of each octet
	 * in turn up to and including the last octet of the contents octets.
	 */
	public BigInteger decodeInteger() throws Exception
	{
		BigInteger value = new BigInteger("0");
		// int checker;
		// byte x;

		if (length == -1)
			throw new ASNException(ASNErrorCodes.CODEC_INVALID_LENGTH, new Object[] {
					"INTEGER", length });
		if (length == 0)
			return value;
		// if( length < 2 )
		// {
		// value = new BigInteger( 1, content );
		// } else
		// {
		// ByteArrayDataHandler badh = new ByteArrayDataHandler( content );
		// x = badh.readByte();
		// checker = x & BIT_ALL_MASK;
		// x = badh.readByte();
		// checker += x & BIT_8_MASK;
		// if( checker == 0 || checker == 2 )
		// throw new Exception( "invalidly encoded data found for integer type."
		// );
		// value = new BigInteger( 1, content );
		// }
		value = new BigInteger(1, content);
		return value;
	}

	/**
	 * The encoding of a null value shall be primitive. The contents octets shall
	 * not contain any octets.
	 */
	public ASNType decodeNull() throws Exception
	{
		// byte[] content = getContent();
		// int lengthx = content.length;

		if (length != 0)
			throw new ASNException(ASNErrorCodes.CODEC_INVALID_LENGTH, new Object[] {
					"NULL", length });
		return null;
	}

	public String decodeObjectIdentifier() throws Exception
	{
		String value = "";
		int i;
		int v, b, c;
		int length = getLength();

		if (length == -1)
			throw new Exception("Expected primitive type");
		byte[] bstr = getContent();
		/*
		 * Calculate the OID length
		 */
		length = 2;
		for (i = 1; i < bstr.length; ++i)
		{
			if (bstr[i] >= 0)
				++length;
		}

		/*
		 * Parse the OID
		 */
		b = BIT_ALL_MASK & bstr[0];
		v = b / 40;
		value += v;
		v = b % 40;
		value += " " + v;

		v = 0;
		length = 2;
		for (i = 1; i < bstr.length; ++i)
		{
			v = v * 128 + (BIT_7_TO_1_MASK & bstr[i]);
			c = bstr[i] & BIT_8_MASK;
			if (c == 0)
			{
				value += " " + v;
				v = 0;
			}
		}

		/*
		 * Return the OID as an object
		 */
		return String.valueOf(value);
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

	public String decodeString() throws Exception
	{
		byte[] content = getContent();
		String result = new String(content, "UTF8");
		return result;
	}

	public Date decodeUTCTime() throws Exception
	{
		byte[] content = getContent();
		return DecodeUtil.toUTCTime(content);
	}

	public String decodeRelativeObjectIdentifier() throws Exception
	{
		String value = "";
		int i, size;
		int v, c;

		if (length == -1)
			throw new ASNException(ASNErrorCodes.CODEC_INVALID_LENGTH, new Object[] {
					"Relative Object Identifier", length });

		/*
		 * Calculate the relative OID length
		 */
		size = 0;
		for (i = 0; i < content.length; ++i)
		{
			if (content[i] >= 0)
				++size;
		}

		/*
		 * Parse the OID
		 */
		v = 0;
		length = 0;
		for (i = 0; i < content.length; ++i)
		{
			v = v * 128 + (BIT_7_TO_1_MASK & content[i]);
			c = content[i] & BIT_8_MASK;
			if (c == 0)
			{
				value += " " + v;
				v = 0;
			}
		}
		/*
		 * Return the OID as an object
		 */
		return value;
	}

	/**
	 * Any currently not in used according to our defined framework and data type
	 * or metadata definition.
	 */
	public CadedDataTreeNode<Object> decodeAny(ASNAny anyType) throws Exception
	{
		ASNType asnType = anyType.decide(tag);
		CadedDataTreeNode<Object> mvtNode = asnType.decode(this);
		return mvtNode;
	}

	/**
     * 
     */
	public CadedDataTreeNode<Object> decodeCollection(ASNCollection collectionType)
				throws Exception
	{
		CadedDataTreeNode<Object> mvtNode = new CadedDataTreeNode<Object>(
					collectionType.getName());
		CadedDataTreeNode<Object> childVTN = null;
		Tag lTag = getTag();
		int length = getLength();

		if (lTag.isOfClass(Tag.TAG_CLASS_UNIVERSAL))
		{
			collectionType.getTagUniv().match(lTag);
		}

		collectionType.reset();
		// System.out.println( "ASNBERReader.decodeCollection()===>"
		// +collectionType.getName()+" ---> CODE : "+collectionType.hashCode());
		if (length == INDEFINITE_LENGTH)
		{
			Tag berSpecial = new Tag(Tag.TAG_VALUE_BASIC_ENCODING_RULE_RESERVED);
			while (true)
			{
				try
				{
					slide();
					lTag = getTag();
					int l = getLength();
					if (lTag.match(berSpecial) && l == 0)
					{
						break;
					}
					ASNType asnTypeNode = collectionType.decide(lTag);
					if (asnTypeNode != null)
					{
						childVTN = asnTypeNode.decode(this);
						mvtNode.addChild(childVTN);
					}
				}
				catch (Exception e)
				{
					throw new ASNException(ASNErrorCodes.CODEC_DECODING_ERROR, new Object[] {
							collectionType.getName() + "with tag" + lTag, e.getMessage() }, e);
				}
			}
		}
		else
		{
			long endOffset = count + length;
			while (count < endOffset)
			{
				try
				{
					slide();
					lTag = getTag();
//					System.out.println("ASNBERReader.decodeCollection()" + lTag + ":"
//								+ collectionType);
					ASNType asnTypeNode = collectionType.decide(lTag);
//					System.out.println("ASNBERReader.decodeCollection(===>" + asnTypeNode);
					if (asnTypeNode != null)
					{
						childVTN = asnTypeNode.decode(this);
						mvtNode.addChild(childVTN);
					}
				}
				catch (Exception e)
				{
					throw new ASNException(ASNErrorCodes.CODEC_DECODING_ERROR, new Object[] {
							collectionType.getName() + " with tag-" + lTag, e.getMessage() }, e);
				}
			}
		}
		collectionType.checkCompleteness();
		// if (collectionType.getName().equalsIgnoreCase("callEventRecords"))
		// System.out.println("ASNBERReader.decodeContainer(=======>\n" +
		// mvtNode);
		return mvtNode;
	}

	public CadedDataTreeNode<Object> decodeContainer(ASNContainer containerType)
				throws Exception
	{
		CadedDataTreeNode<Object> mvtNode = new CadedDataTreeNode<Object>(
					containerType.getName());
		CadedDataTreeNode<Object> childVTN = null;

		Tag lTag = null;

		int length = getLength();
		containerType.reset();
		if (length == INDEFINITE_LENGTH)
		{
			Tag berSpecial = new Tag(Tag.TAG_VALUE_BASIC_ENCODING_RULE_RESERVED);
			while (true)
			{
				slide();
				lTag = getTag();
				int l = getLength();
				if (lTag.match(berSpecial) && l == 0)
				{
					break;
				}

				ASNType asnTypeNode = containerType.decide(lTag);
//				System.out.println("ASNBERReader.decodeContainer(IDF)===>" + asnTypeNode
//							+ " TAG- " + lTag);
				if (asnTypeNode != null)
					childVTN = asnTypeNode.decode(this);
				mvtNode.addChild(childVTN);
			}
		}
		else
		{
			long endOffset = count + length;
			while (count < endOffset)
			{
				slide();
				lTag = getTag();
				ASNType asnTypeNode = containerType.decide(lTag);
//				System.out.println("ASNBERReader.decodeContainer(DF)===>" + asnTypeNode
//							+ " TAG- " + lTag);
				if (asnTypeNode != null)
					childVTN = asnTypeNode.decode(this);
				mvtNode.addChild(childVTN);
			}
		}
		containerType.checkCompleteness();
		return mvtNode;
	}

	public CadedDataTreeNode<Object> decodeDefined(ASNDefinedType definedType)
				throws Exception
	{
		if (tag != null && tag.value() != null)
		{
			if (tag.isExplicit())
			{
				slide();
				Tag lTag = getTag();
				if (!tag.match(lTag))
				{
					throw new ASNException(ASNErrorCodes.CODEC_INVALID_TAG,
								new Object[] { definedType.getName() });
				}
			}
		}

		ASNType asnTypeNode = definedType.decide(null);
		// if( asnTypeNode.getTag() == null )
		// asnTypeNode.setTag( definedType.getTag() );

		CadedDataTreeNode<Object> mvtNode = asnTypeNode.decode(this);
		return mvtNode;
	}

	public CadedDataTreeNode<Object> decodeSelection(ASNSelection selectionType)
				throws Exception
	{
		CadedDataTreeNode<Object> mvtNode = new CadedDataTreeNode<Object>(
					selectionType.getName());
		CadedDataTreeNode<Object> childVTN = null;

		/** CRZ Started */
		if (selectionType.getTag() != null)
		{
			slide();
		}
		else
		{
			Tag lTag = null;
			ASNType checkerASNNode = selectionType;

			do
			{
				checkerASNNode = (ASNType) checkerASNNode.getParent();
				if (checkerASNNode.getTag() != null)
					lTag = checkerASNNode.getTag();
			}
			while (checkerASNNode.isDefined() && !checkerASNNode.isLeaf());

			if (lTag != null)
			{
				slide();
			}
			else
			{
				checkerASNNode = selectionType;
				if (checkerASNNode.count() == 1)
				{
					do
					{
						checkerASNNode = (ASNType) checkerASNNode.childByIndex(0);
						if (checkerASNNode.getTag() != null)
							lTag = checkerASNNode.getTag();
					}
					while (checkerASNNode.isDefined() && !checkerASNNode.isLeaf());

					if (lTag != null)
					{
						slide();
					}
				}
			}
		}

		/** CRZ ended */

		ASNType asnTypeNode = selectionType.decide(getTag());

		if (asnTypeNode != null)
			childVTN = asnTypeNode.decode(this);
		mvtNode.addChild(childVTN);
		return mvtNode;
	}
}
