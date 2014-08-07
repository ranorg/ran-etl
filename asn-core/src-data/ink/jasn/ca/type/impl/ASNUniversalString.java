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
 * @Source : UniversalString.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 28, 2009
 * @Time : 11:59:09 PM
 * @Version : $0.01
 **/
public class ASNUniversalString extends ASNCharacterString
{
    private static final long serialVersionUID = -6765945484066662010L;

    {
        type = "UniversalString";
        tagUniv = new Tag( Tag.TAG_VALUE_UNIVERSAL_STRING );
    }

    /**
     * 
     */
    public ASNUniversalString()
    {

    }

    /**
     * @param name
     */
    public ASNUniversalString( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNUniversalString( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNUniversalString( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }
}
