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
import ink.jasn.ca.basis.central.AbstractSyntaxTreeNode;
import ink.jasn.ca.type.base.ASNContainer;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ca.type.grammar.AsnConstraint;

import java.util.ArrayList;
import java.util.List;

/**
 * @Source : ASNSetOf.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 10, 2009
 * @Time : 6:14:43 PM
 * @Version : $0.01
 **/
public class ASNSetOf extends ASNContainer
{
    private static final long serialVersionUID = -2312718490027714454L;
    public AsnConstraint constraint;
    // public boolean isDefinedType;
    // public boolean isSizeConstraint;
    // public String typeName; // Name of the defined type
    public final String BUILTINTYPE1 = "SET OF";

    private List<AbstractSyntaxTreeNode> availableChildren;

    {
        type = "SET OF";
        tagUniv = new Tag( Tag.TAG_VALUE_SET_OF );
    }

    /**
     * 
     */
    public ASNSetOf()
    {
        super( "" );
    }

    /**
     * @param name
     */
    public ASNSetOf( String name )
    {
        super( name );
    }

    /*
     * (non-Javadoc)
     * @see com.connectiva.gpf.datadef.asn.media.ASNConstructed#checkCompleteness()
     */
    @Override
    public boolean checkCompleteness()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.connectiva.gpf.datadef.asn.media.ASNConstructed#decide()
     */
    @Override
    public ASNType decide( Tag aTag )
    {
        return (ASNType) childByIndex( 0 );
    }

    @Override
    public void reset()
    {
        if( availableChildren == null )
            availableChildren = new ArrayList<AbstractSyntaxTreeNode>();
        else
            availableChildren.clear();
        availableChildren.addAll( getChildren() );
    }

}
