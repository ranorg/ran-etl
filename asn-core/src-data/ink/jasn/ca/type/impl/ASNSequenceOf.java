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
import ink.jasn.ca.type.base.ASNContainer;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ca.type.grammar.AsnConstraint;

/**
 * @Source : ASNSequenceOf.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 3, 2009
 * @Time : 10:17:33 PM
 * @Version : $0.01
 **/
public class ASNSequenceOf extends ASNContainer
{
    private static final long serialVersionUID = -7276063533062786913L;
    public AsnConstraint constraint;
    // public boolean isDefinedType;
    // public boolean isSizeConstraint;
    // public String typeName; // Name of the defined type

    public final String BUILTINTYPE = "SEQUENCE OF";
    {
        type = "SEQUENCE OF";
        tagUniv = new Tag( Tag.TAG_VALUE_SEQUENCE_OF );
    }

    /**
     * 
     */
    public ASNSequenceOf()
    {
        super( "" );
    }

    /**
     * @param name
     * @param tag
     * @param optional
     */
    public ASNSequenceOf( String name, Tag tag, boolean optional )
    {
        super( name, tag, optional );
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

    }

}
