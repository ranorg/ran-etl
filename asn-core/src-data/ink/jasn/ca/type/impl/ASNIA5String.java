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
 * @Source : IA5String.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 28, 2009
 * @Time : 11:54:24 PM
 * @Version : $0.01
 **/
public class ASNIA5String extends ASNCharacterString
{
    private static final long serialVersionUID = -4848167045446500522L;

    {
        type = "IA5STRING";
        tagUniv = new Tag( Tag.TAG_VALUE_IA5_STRING );
    }

    /**
     * 
     */
    public ASNIA5String()
    {

    }

    /**
     * @param name
     */
    public ASNIA5String( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNIA5String( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNIA5String( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }

}
