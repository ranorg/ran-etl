
/**
 * Copyright Connectiva Systems, Inc. All Rights Reserved. This software is the
 * proprietary information of Connectiva Systems, Inc. Use is subject to license
 * terms.
 *
 * Project: ParserGenX	
***/

/***
 * Package Declaration :
 *---------------------*/
 package ink.jasn.ca.type.common;

import ink.jasn.ca.type.base.Tag;

/***
 * Packages and Classes Import :
 *-----------------------------*/

/**
 * Source  : ASNDataFrame.java
 * Author  : Nirmal Kumar Biswas
 * Date    : Apr 8, 2009
 * Time    : 1:49:10 PM
 * Version : $0.01
 **/
public class ASNDataFrame extends DataFrame
{
	private static final long serialVersionUID = 1L;

	private Tag tag;
	
	private Marker marker;
	
	private byte[] data;
    
    private Object value; 
	
	/**
	 * 
	 */
	public ASNDataFrame() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param tag
	 */
	public ASNDataFrame(Tag tag) {
		this.tag = tag;
	}


	/**
	 * @param tag
	 * @param length
	 * @param value
	 */
	public ASNDataFrame(Tag tag, int length, byte[] data) {
		this(tag,length);
		this.data = data;
	}
    
    /**
     * @param tag
     * @param length
     * @param value
     */
    public ASNDataFrame(Tag tag, int length, Object value) {
        this(tag,length);
        this.value = value;
    }


	/**
	 * @param tag
	 * @param length
	 */
	public ASNDataFrame(Tag tag, int length) {
		this(tag);
		marker=new Marker(length);
	}

    /**
     * @return the data
     */
    public byte[] getData()
    {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData( byte[] data )
    {
        this.data = data;
    }

    /**
     * @return the length
     */
    public int getLength()
    {
        return marker.value();
    }

    /**
     * @param length the length to set
     */
    public void setLength( int length )
    {
        if(marker==null)
            marker=new Marker();
        marker.value( length );
    }

    /**
     * @return the tag
     */
    public Tag getTag()
    {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag( Tag tag )
    {
        this.tag = tag;
    }

    /**
     * @return the value
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue( Object value )
    {
        this.value = value;
    }

    /**
     * @return the marker
     */
    public Marker getMarker()
    {
        return marker;
    }
    
}
