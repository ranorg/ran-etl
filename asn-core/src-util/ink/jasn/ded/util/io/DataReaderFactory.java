package ink.jasn.ded.util.io;

import ink.jasn.ded.util.codec.ASNBERBlockReader;
import ink.jasn.ded.util.codec.ASNBERReader;
import ink.jasn.ded.util.codec.ASNDERReader;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.constant.StaticParameters;

import java.io.IOException;

/**
 * @Source : DataReaderFactory.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 12, 2009
 * @Time : 5:45:36 PM
 * @Version : $0.01
 **/

public final class DataReaderFactory
{

	public static ASNDataReader getASNDataReader(String type)
	{
		if (type.equals(StaticParameters.ASN_BER))
		{
			return new ASNBERReader();
		}
		else if (type.equals(StaticParameters.ASN_BER_BLOCK))
		{
			return new ASNBERBlockReader();
		}
		else if (type.equals(StaticParameters.ASN_DER))
		{
			return new ASNDERReader();
		}
		else
		{
			if (type.equals(StaticParameters.ASN_PER)
						|| type.equals(StaticParameters.ASN_CER)
						|| type.equals(StaticParameters.ASN_CER))
			{
				System.out.println("READER:: The decoding type : " + type
							+ "is currently not supported.");
			}
			else
			{
				System.out.println("READER:: The decoding type : " + type
							+ "is invalid decoding type.");
			}
		}
		return null;

	}

	public static ASNDataReader getASNDataReader(String type, String fileName)
				throws IOException
	{
		if (type.equals(StaticParameters.ASN_BER))
		{
			return new ASNBERReader(fileName);
		}
		else if (type.equals(StaticParameters.ASN_BER_BLOCK))
		{
			return new ASNBERBlockReader(fileName);
		}
		else if (type.equals(StaticParameters.ASN_DER))
		{
			return new ASNDERReader(fileName);
		}
		else
		{
			if (type.equals(StaticParameters.ASN_PER)
						|| type.equals(StaticParameters.ASN_CER)
						|| type.equals(StaticParameters.ASN_CER))
			{
				System.out.println("READER:: The decoding type : " + type
							+ "is currently not supported.");
			}
			else
			{
				System.out.println("READER:: The decoding type : " + type
							+ "is invalid decoding type.");
			}
		}
		return null;

	}
}
