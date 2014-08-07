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
import ink.jasn.ca.type.extern.ASNNamedNumber;
import ink.jasn.ca.type.grammar.AsnConstraint;
import ink.jasn.ca.type.grammar.AsnNamedNumberList;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

/**
 * @Source : ISNInteger.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Jul 29, 2009
 * @Time : 11:25:00 AM
 * @Version : $0.01
 **/
public class ASNInteger extends ASNPrimitive
{
    private static final long serialVersionUID = -4420509501567771363L;

    public ASNNamedNumber asnNamedNumber;
    public AsnNamedNumberList namedNumberList;
    public AsnConstraint constraint;

    {
        type = "INTEGER";
        tagUniv = new Tag( Tag.TAG_VALUE_INTEGER );
    }

    /**
     * 
     */
    public ASNInteger()
    {
        super( "" );
    }

    /**
     * @param name
     */
    public ASNInteger( String name )
    {
        super( name );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNInteger( String name, Tag tag )
    {
        super( name, tag );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNInteger( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
    }

    @Override
    public CadedDataTreeNode<Object> decode( ASNDataReader asnDataReader ) throws Exception
    {
        CadedDataTreeNode<Object> mvtNode = new CadedDataTreeNode<Object>( name );
        mvtNode.setData( asnDataReader.decodeInteger() );
        return mvtNode;
    }

    @Override
    public CadedDataTreeNode<Object> encode( ASNDataWriter asnDataWriter ) throws Exception
    {
        return null;
    }
}
