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
 * @Source : T61String.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 28, 2009
 * @Time : 11:55:59 PM
 * @Version : $0.01
 **/
public class ASNT61String extends ASNCharacterString
{
    private static final long serialVersionUID = 2718842311987847446L;

    {
        type = "T61String";
        tagUniv = new Tag( Tag.TAG_VALUE_T61_STRING );
    }

    /**
    * 
    */
    public ASNT61String()
    {

    }

    /**
     * @param name
     */
    public ASNT61String( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNT61String( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNT61String( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }
}
