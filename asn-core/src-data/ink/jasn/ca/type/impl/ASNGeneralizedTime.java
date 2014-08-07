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
 * @Source : ASNGeneralizedTime.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 10, 2009
 * @Time : 11:49:00 PM
 * @Version : $0.01
 **/
public class ASNGeneralizedTime extends ASNCharacterString
{
    private static final long serialVersionUID = 7327514656184524000L;

    {
        type = "GeneralizedTime";
        tagUniv = new Tag( Tag.TAG_VALUE_GENERALIZED_TIME );
    }

    /**
     * 
     */
    public ASNGeneralizedTime()
    {
        super( "" );
    }

    /**
     * @param name
     */
    public ASNGeneralizedTime( String name )
    {
        super( name );
        // TODO Auto-generated constructor stub
    }

}
