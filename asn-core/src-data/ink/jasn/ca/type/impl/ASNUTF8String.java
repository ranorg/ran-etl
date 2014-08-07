/**
 * Copyright Connectiva Systems, Inc @2009. All Rights Reserved. This software is the proprietary
 * information of Connectiva Systems, Inc. Use is subject to license terms. 
 * @Project: ParserGenX
 ***/

/***
 * Package Declaration :
 *@PKGS---------------------
 */

package ink.jasn.ca.type.impl;

/***
 * Packages and Classes Import :
 *@IMPRT-----------------------------
 */
import ink.jasn.ca.type.base.ASNCharacterString;
import ink.jasn.ca.type.base.Tag;


/**
 * @Source : UTF8String.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 29, 2009
 * @Time : 12:00:41 AM
 * @Version : $0.01
 **/
public class ASNUTF8String extends ASNCharacterString
{
    private static final long serialVersionUID = 6482138444912021840L;

    {
        type = "UTF8String";
        tagUniv = new Tag( Tag.TAG_VALUE_UTF8_STRING );
    }

    /**
     * 
     */
    public ASNUTF8String()
    {

    }

    /**
     * @param name
     */
    public ASNUTF8String( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNUTF8String( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNUTF8String( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }
}
