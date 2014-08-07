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
 * @Source : NumericString.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 28, 2009
 * @Time : 11:50:43 PM
 * @Version : $0.01
 **/
public class ASNNumericString extends ASNCharacterString
{
    private static final long serialVersionUID = -5559020553819672252L;

    {
        type = "NumericString";
        tagUniv = new Tag( Tag.TAG_VALUE_NUMERIC_STRING );
    }

    /**
    * 
    */
    public ASNNumericString()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param name
     */
    public ASNNumericString( String name )
    {
        super( name );
        // TODO Auto-generated constructor stub
    }

    /**
     * @param name
     * @param tag
     */
    public ASNNumericString( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNNumericString( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }
}
