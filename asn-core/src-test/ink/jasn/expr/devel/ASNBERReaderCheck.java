/**
 * 
 */
package ink.jasn.expr.devel;

import ink.jasn.ded.util.codec.ASNBERBlockReader;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.io.DataReaderFactory;

import java.io.IOException;

/*
 * This is a proprietary information of ZCrypto.
 * All the copyright is reserved for ZCrypto. (2013)
 *
 * Any information uses from ZCrypto are subject to the term and condition 
 * together with a valid license agreement made in-between.
 *
 * @Source : ASNBERReaderCheck.java
 * @Description :
 * @Timeline : Jul 20, 2013 11:37:11 AM
 * @Author : iNK
 * @Version : #1.00
 */

public class ASNBERReaderCheck
{

	private ASNDataReader	dr;

	/**
	 * 
	 */
	public ASNBERReaderCheck()
	{
		ASNBERBlockReader asnbbr=(ASNBERBlockReader) DataReaderFactory.getASNDataReader("BER_BLOCK");
		asnbbr.setBlockSize(2048);
		asnbbr.setFillerCheck(true);
		dr = asnbbr;
	}

	/**
	 * @throws Exception
	 * 
	 */
	private void parseWithBer(String fileName) throws Exception
	{
		try
		{
			dr.fixNewFile(fileName);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (dr.hasMoreData())
		{
			dr.shift();

		}
	}

	public static void main(String[] args)
	{
		try
		{
			new ASNBERReaderCheck()
						.parseWithBer("/home/enirbis/Extra/ProjectOASN/Finalized/ZTE-MSC/data/ZTE_MSC/B20130904000000.dat");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
