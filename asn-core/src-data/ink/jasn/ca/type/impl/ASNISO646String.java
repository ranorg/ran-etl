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
 * @Source : ISO646String.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 28, 2009
 * @Time : 11:53:23 PM
 * @Version : $0.01
 **/
public class ASNISO646String extends ASNCharacterString
{
    private static final long serialVersionUID = -3952961267374453506L;

    {
        type = "ISO646String";
        tagUniv = new Tag( Tag.TAG_VALUE_ISO646_STRING );
    }

    /**
     * 
     */
    public ASNISO646String()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param name
     */
    public ASNISO646String( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNISO646String( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNISO646String( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }
}
