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
 * @Source : Real.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 29, 2009
 * @Time : 12:42:13 PM
 * @Version : $0.01
 **/
public class ASNReal extends ASNPrimitive
{
    private static final long serialVersionUID = 7914515994512679532L;

    {
        type = "REAL";
        tagUniv = new Tag( Tag.TAG_VALUE_REAL );
    }

    /**
     * 
     */
    public ASNReal()
    {
        super( "" );
    }

    /**
     * @param name
     */
    public ASNReal( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNReal( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNReal( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }

    @Override
    public CadedDataTreeNode<Object> decode( ASNDataReader asnDataReader ) throws Exception
    {
        CadedDataTreeNode<Object> mvtNode = new CadedDataTreeNode<Object>( name );
        mvtNode.setData( asnDataReader.decodeReal() );
        return mvtNode;
    }

    @Override
    public CadedDataTreeNode<Object> encode( ASNDataWriter asnDataWriter ) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }
}
