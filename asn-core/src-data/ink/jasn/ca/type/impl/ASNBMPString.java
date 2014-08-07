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
 * @Source : BMPString.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 28, 2009
 * @Time : 11:59:58 PM
 * @Version : $0.01
 **/
public class ASNBMPString extends ASNCharacterString
{
    private static final long serialVersionUID = 6828538209186726947L;
    private final String TYPE_STRING = "BMPString";

    {
        type = TYPE_STRING;
        tagUniv = new Tag( Tag.TAG_VALUE_BMP_STRING );
    }

    /**
     * 
     */
    public ASNBMPString()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param name
     */
    public ASNBMPString( String name )
    {
        super( name );
        type = TYPE_STRING;
    }

    /**
     * @param name
     * @param tag
     */
    public ASNBMPString( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNBMPString( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }
}
