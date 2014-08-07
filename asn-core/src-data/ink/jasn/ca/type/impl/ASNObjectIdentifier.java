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
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;


/**
 * @Source : ObjectIdentifier.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 29, 2009
 * @Time : 12:44:56 PM
 * @Version : $0.01
 **/
public class ASNObjectIdentifier extends ASNPrimitive
{
    private static final long serialVersionUID = 1388228381999249858L;

    {
        type = "OBJECT IDENTIFIER";
        tagUniv = new Tag( Tag.TAG_VALUE_OBJECT_IDENTIFIER );
    }

    /**
     * 
     */
    public ASNObjectIdentifier()
    {
        super( "" );
    }

    /**
     * @param name
     */
    public ASNObjectIdentifier( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNObjectIdentifier( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNObjectIdentifier( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }

    @Override
    public CadedDataTreeNode<Object> decode( ASNDataReader asnDataReader ) throws Exception
    {
        CadedDataTreeNode<Object> mvtNode = new CadedDataTreeNode<Object>( name );
        mvtNode.setData( asnDataReader.decodeObjectIdentifier() );
        return mvtNode;
    }

    @Override
    public CadedDataTreeNode<Object> encode( ASNDataWriter asnDataWriter ) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }
}
