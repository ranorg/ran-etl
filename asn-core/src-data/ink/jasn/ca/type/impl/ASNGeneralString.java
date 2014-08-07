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
 *@IMPRT----------------------------
 */
import ink.jasn.ca.type.base.ASNCharacterString;
import ink.jasn.ca.type.base.Tag;


/**
 * @Source : GeneralString.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 28, 2009
 * @Time : 11:58:13 PM
 * @Version : $0.01
 **/
public class ASNGeneralString extends ASNCharacterString
{
    private static final long serialVersionUID = -4441454559031534553L;

    {
        type = "GeneralString";
        tagUniv = new Tag( Tag.TAG_VALUE_GENERAL_STRING );
    }

    /**
     * 
     */
    public ASNGeneralString()
    {

    }

    /**
     * @param name
     */
    public ASNGeneralString( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNGeneralString( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNGeneralString( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }
}
