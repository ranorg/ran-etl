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
 * @Source : GraphicString.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 28, 2009
 * @Time : 11:57:36 PM
 * @Version : $0.01
 **/
public class ASNGraphicString extends ASNCharacterString
{
    private static final long serialVersionUID = -7824293286595948250L;

    {
        type = "GraphicString";
        tagUniv = new Tag( Tag.TAG_VALUE_GRAPHIC_STRING );
    }

    /**
     * 
     */
    public ASNGraphicString()
    {
        
    }

    /**
     * @param name
     */
    public ASNGraphicString( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNGraphicString( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNGraphicString( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }
}
