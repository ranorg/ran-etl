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
 * @Source : ASNUTCTime.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 10, 2009
 * @Time : 11:51:21 PM
 * @Version : $0.01
 **/
public class ASNUTCTime extends ASNCharacterString
{
    private static final long serialVersionUID = 3506653596132176630L;

    {
        type = "UTCTime";
        tagUniv = new Tag( Tag.TAG_VALUE_UTC_TIME );
    }

    /**
     * 
     */
    public ASNUTCTime()
    {
        super( "" );
    }

    /**
     * @param name
     */
    public ASNUTCTime( String name )
    {
        super( name );
    }

}
