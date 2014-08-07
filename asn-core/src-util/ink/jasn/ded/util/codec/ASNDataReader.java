package ink.jasn.ded.util.codec;

import ink.jasn.ca.type.base.Tag;
import ink.jasn.com.exception.ASNErrorCodes;
import ink.jasn.com.exception.ASNException;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Source : ASNDataReader.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 18, 2009
 * @Time : 1:11:54 PM
 * @Version : $0.01
 **/
public abstract class ASNDataReader implements ASNDataDecoder
{
	protected Tag tag;

	protected int length;

	protected byte[] content;

	private long total;

	protected long count;

	protected boolean constructed;

	private InputStream iStream;

	private BufferedInputStream buffIn;

	/**
	 * Default Constructor
	 */
	public ASNDataReader()
	{

	}

	/**
	 * @param is
	 * @throws IOException
	 */
	protected ASNDataReader(InputStream is) throws IOException
	{
		iStream = is;
		total = iStream.available();
		buffIn = new BufferedInputStream(iStream);
	}

	/**
	 * @param fileName
	 * @throws IOException
	 */
	public void fixNewFile(String fileName) throws IOException
	{
		close();
		iStream = new FileInputStream(fileName);
		total = iStream.available();
		buffIn = new BufferedInputStream(iStream);
	}

	/**
	 * @return
	 * @throws IOException
	 */
	protected int read() throws IOException
	{
		int i = buffIn.read();
		count++;
		return i;
	}

	/**
	 * @return the iStream
	 */
	public InputStream getiStream()
	{
		return iStream;
	}

	/**
	 * @param iStream
	 *           the iStream to set
	 * @throws IOException
	 */
	public void setiStream(InputStream iStream) throws IOException
	{
		close();
		this.iStream = iStream;
		total = iStream.available();
		count = 0;
		buffIn = new BufferedInputStream(iStream);
	}

	/**
	 * @return
	 * @throws IOException
	 */
	protected byte readByte() throws ASNException
	{
		int i;
		try
		{
			i = buffIn.read();
		}
		catch (IOException e)
		{
			throw new ASNException(ASNErrorCodes.DATA_FILE_READ_ERROR, e);
		}
		count++;
		return (byte) i;
	}

	/**
	 * @param bt
	 * @return
	 * @throws IOException
	 */
	protected int read(byte[] bt) throws ASNException
	{
		int i;
		try
		{
			i = buffIn.read(bt);
		}
		catch (IOException e)
		{
			throw new ASNException(ASNErrorCodes.DATA_FILE_READ_ERROR, e);
		}
		count += bt.length;
		return i;
	}

	public void reset() throws IOException
	{
		count = 0;
		total = 0;
		close();
	}

	/**
	 * @throws IOException
	 */
	public void close() throws IOException
	{
		if (buffIn != null)
			buffIn.close();
		if (iStream != null)
			iStream.close();
		count = 0;
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public boolean hasMoreData()
	{
		return ((total - count) > 0) ? true : false;
	}

	/**
	 * @return the tag
	 */
	public Tag getTag()
	{
		return tag;
	}

	/**
	 * @return the length
	 */
	public int getLength()
	{
		return length;
	}

	/**
	 * @return the content
	 */
	public byte[] getContent()
	{
		return content;
	}

	/**
	 * @return {@link Tag}
	 * @throws Exception
	 */
	protected abstract Tag readTag() throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	protected abstract int readLength() throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	protected abstract byte[] readContent(int length) throws Exception;

	/**
	 * TLV [ILC] Slider in a ASN.1 data channel to fetch serial data as [ T | L |
	 * T | L |.....| T | L | V ] format consecutively. Where T stands for TAG in
	 * data L stands for LENGTH for the DATA and V represents the DATA itself or
	 * VALUE.
	 * 
	 * @throws Exception
	 */
	public Boolean slide() throws Exception
	{
		tag = readTag();
		length = readLength();
		if (!constructed)
			content = readContent(length);

		// FileWriter f=new FileWriter("d:/TLV.TXT");

		// f.write( tag.value()+"\t:"+length+"\t:"+((content!=null)?new
		// String(content,"UTF-8"):null)+" \t POSITION : "+count );
		// f.flush();f.close();
		//		System.out.println("POSITION-> " + count+"\t :TAG-> " + tag.value() + "\t: LENGTH-> " + length
		//					+ "\t: VALUE-> "
		//					+ ((content != null) ? new String(content, "UTF-8") : null));
		return true;
	}

	/**
	 * TLV [ILC] Slider in a ASN.1 data channel to fetch serial data as [ T | L |
	 * T | L |.....| T | L | V ] format consecutively. Where T stands for TAG in
	 * data L stands for LENGTH for the DATA and V represents the DATA itself or
	 * VALUE.
	 * 
	 * @throws Exception
	 */
	public Boolean shift() throws Exception
	{
		tag = readTag();
		length = readLength();
		content = null;
		if (!constructed)
			content = readContent(length);

		// FileWriter f=new FileWriter("d:/TLV.TXT");

		// f.write( tag.value()+"\t:"+length+"\t:"+((content!=null)?new
		// String(content,"UTF-8"):null)+" \t POSITION : "+count );
		// f.flush();f.close();
		System.out.println("sft-out|=> TAG-> " + tag.value() + "\t: LENGTH-> " + length
					+ "\t: VALUE-> "
					+ ((content != null) ? new String(content, "UTF-8") : null)
					+ " \t: POSITION-> " + count);
		return true;
	}

	public boolean checkFiller()
	{
		if (content == null)
			return false;
		for (int i = 0; i < length; i++)
		{
			if (content[i] != 0xFF && content[i] != 0x00)
				return false;
		}
		return true;
	}
}
