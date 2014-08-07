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

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNPrimitive;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ca.type.grammar.AsnConstraint;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;


/**
 * @Source : OctetString.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 29, 2009
 * @Time : 12:43:59 PM
 * @Version : $0.01
 **/
public class ASNOctetString extends ASNPrimitive
{
    private static final long serialVersionUID = -8679941282162638709L;
    public AsnConstraint constraint;

    {
        type = "OCTET STRING";
        tagUniv = new Tag( Tag.TAG_VALUE_OCTET_STRING );
    }

    /**
     * 
     */
    public ASNOctetString()
    {
        super( "" );
    }

    /**
     * @param name
     */
    public ASNOctetString( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNOctetString( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNOctetString( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }

    @Override
    public CadedDataTreeNode<Object> decode( ASNDataReader asnDataReader ) throws Exception
    {
        CadedDataTreeNode<Object> mvtNode = new CadedDataTreeNode<Object>( name );
        mvtNode.setData( asnDataReader.decodeOctetString() );
        return mvtNode;
    }

    @Override
    public CadedDataTreeNode<Object> encode( ASNDataWriter asnDataWriter ) throws Exception
    {
        return null;
    }
}
