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
 * @Source : TeletexString.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 28, 2009
 * @Time : 11:55:12 PM
 * @Version : $0.01
 **/
public class ASNTeletexString extends ASNCharacterString
{
    private static final long serialVersionUID = -3545769932523752051L;

    {
        type = "TeletexString";
        tagUniv = new Tag( Tag.TAG_VALUE_TELETEX_STRING );
    }

    /**
     * 
     */
    public ASNTeletexString()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param name
     */
    public ASNTeletexString( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNTeletexString( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNTeletexString( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }
}
