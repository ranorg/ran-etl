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
 * @Source : RelativeObjectIdentifier.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 29, 2009
 * @Time : 12:46:20 PM
 * @Version : $0.01
 **/
public class ASNRelativeObjectIdentifier extends ASNPrimitive
{
    private static final long serialVersionUID = 5650419804721854446L;

    {
        type = "RELATIVE-OID";
        tagUniv = new Tag( Tag.TAG_VALUE_RELATIVE_OID );
    }

    /**
     * 
     */
    public ASNRelativeObjectIdentifier()
    {
        super( "" );
    }

    /**
     * @param name
     */
    public ASNRelativeObjectIdentifier( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNRelativeObjectIdentifier( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNRelativeObjectIdentifier( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }

    @Override
    public CadedDataTreeNode<Object> decode( ASNDataReader asnDataReader ) throws Exception
    {
        CadedDataTreeNode<Object> mvtNode = new CadedDataTreeNode<Object>( name );
        mvtNode.setData( asnDataReader.decodeRelativeObjectIdentifier() );
        return mvtNode;
    }

    @Override
    public CadedDataTreeNode<Object> encode( ASNDataWriter asnDataWriter ) throws Exception
    {
        return null;
    }
}
