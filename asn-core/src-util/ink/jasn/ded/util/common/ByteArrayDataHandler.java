/*
 * Copyright (c) 2009 CONNECTiVA Systems, Inc. J2, Block-GP, Sector-V, Kolkata, 700091, INDIA All
 * Rights Reserved. This software is the confidential and proprietary information of CONNECTiVA
 * Systems, Inc. ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you entered into with
 * CONNECTiVA.
 */

/**
 * Package declaration
 */

package ink.jasn.ded.util.common;

/**
 * Packages and classes import
 */
import java.nio.ByteBuffer;

/**
 * @Source : ByteArrayDataHandler.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Sep 7, 2009
 * @Time : 12:45:03 AM
 * @Version : $1.00
 */

public class ByteArrayDataHandler
{
    private ByteBuffer buffer;

    /**
     * @param bytes
     */
    public ByteArrayDataHandler( byte[] bytes )
    {
        buffer = ByteBuffer.wrap( bytes );
    }

    /**
     * @return
     */
    public byte readByte()
    {
        return buffer.get();
    }

    /**
     * @param size
     * @return
     */
    public byte[] readBytes( int size )
    {
        byte[] retByte = new byte[size];
        buffer.get( retByte );
        return retByte;
    }

    /**
     * @param byteArr
     */
    public void readBytes( byte[] byteArr )
    {
        buffer.get( byteArr );
    }

    /**
     * Internal integer read routine
     * @param length
     * @return
     * @throws Exception
     */
    public long readBerInteger( int length ) throws Exception
    {
        long value = 0;
        byte x;

        if( length == -1 )
            throw new Exception( "Unknown integer value: illegal integer length" );
        if( length == 0 )
            return value;

        x = readByte();
        value = x; // sign extended

        while( --length > 0 )
        {
            x = readByte();
            value = (value << 8) | (0x00FF & x);
        }

        return value;
    }

    /**
     * Read a primitive BER integer object as an unsigned integer
     * @param length
     * @return
     * @throws Exception
     */
    public long readBerUnsignedInteger( int length ) throws Exception
    {
        long value = 0;
        byte x;

        if( length == -1 )
            throw new Exception( "Unknown integer value: illegal integer length" );
        if( length == 0 )
            return value;
        while( length-- > 0 )
        {
            x = readByte();
            value = (value << 8) | (0x00FF & x);
        }

        return value;
    }
}
