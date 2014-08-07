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
 * @Source : VideotexString.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 28, 2009
 * @Time : 11:56:58 PM
 * @Version : $0.01
 **/
public class ASNVideotexString extends ASNCharacterString
{
    private static final long serialVersionUID = 1122426799488841753L;

    {
        type = "VideotexString";
        tagUniv = new Tag( Tag.TAG_VALUE_VIDEOTEX_STRING );
    }

    /**
    * 
    */
    public ASNVideotexString()
    {

    }

    /**
     * @param name
     */
    public ASNVideotexString( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNVideotexString( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNVideotexString( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }
}
