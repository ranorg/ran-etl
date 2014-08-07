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
 * @Source : PrintableString.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 28, 2009
 * @Time : 11:51:45 PM
 * @Version : $0.01
 **/
public class ASNPrintableString extends ASNCharacterString
{
    private static final long serialVersionUID = 2635156660525419198L;

    {
        type = "PrintableString";
        tagUniv = new Tag( Tag.TAG_VALUE_PRINTABLE_STRING );
    }

    /**
     * 
     */
    public ASNPrintableString()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param name
     */
    public ASNPrintableString( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNPrintableString( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNPrintableString( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }

}
