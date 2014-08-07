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
 * @Source : VisibleString.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 28, 2009
 * @Time : 11:52:40 PM
 * @Version : $0.01
 **/
public class ASNVisibleString extends ASNCharacterString
{
    private static final long serialVersionUID = 4063733135831189057L;

    {
        type = "VisibleString";
        tagUniv = new Tag( Tag.TAG_VALUE_VISIBLE_STRING );
    }

    /**
     * 
     */
    public ASNVisibleString()
    {

    }

    /**
     * @param name
     */
    public ASNVisibleString( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNVisibleString( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNVisibleString( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }
}
