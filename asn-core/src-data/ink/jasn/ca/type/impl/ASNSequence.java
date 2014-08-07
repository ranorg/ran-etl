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
import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNCollection;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ca.type.grammar.AsnElementTypeList;
import ink.jasn.com.exception.ASNErrorCodes;
import ink.jasn.com.exception.ASNException;
import ink.jasn.ded.util.codec.ASNDataWriter;

/**
 * @Source : ASNSequence.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 2, 2009
 * @Time : 12:06:21 PM
 * @Version : $0.01
 **/
public class ASNSequence extends ASNCollection
{
    private static final long serialVersionUID = -2328579904772363103L;
    public AsnElementTypeList elementTypeList;
    public boolean isSequence;
    public final String BUILTINTYPE = "SEQUENCE";

    private int lastChildIndex;

    {
        type = "SEQUENCE";
        tagUniv = new Tag( Tag.TAG_VALUE_SEQUENCE );
    }

    /**
     * 
     */
    public ASNSequence()
    {
        super( "" );
    }

    /**
     * @param name
     * @param tag
     */
    public ASNSequence( String name, Tag tag )
    {
        super( name, tag );
    }

    /*
     * (non-Javadoc)
     * @see ink.jasn.ca.type.base.ASNType#checkCompleteness()
     */
    @Override
    public boolean checkCompleteness() throws Exception
    {
        boolean check = true;
        AbstractSyntaxTreeNode mstNode = null;
        if( lastChildIndex > 0 && lastChildIndex < count() )
            for( int i = lastChildIndex; i < count(); i++ )
            {
                mstNode = childByIndex( i );
                if( !mstNode.isOptional() )
                {
                    check = false;
                    throw new ASNException( ASNErrorCodes.TNODE_COMPLETENESS_CHECK_FAILED,
                                            new Object[] { type, name, mstNode.getName() } );
                }
            }
        return check;
    }

    /*
     * (non-Javadoc)
     * @see
     * ink.jasn.ca.type.base.ASNType#decide(ink.jasn.ca.type.base.Tag)
     */
    @Override
    public ASNType decide( Tag aTag ) throws Exception
    {
        Tag mTag, tempTag = null;
        ASNType asnTypeNode = null;
        String firstDefinedName;
        boolean lOptional = false;
        
        if( lastChildIndex < count() )
        {
            for( int i = lastChildIndex; i < count(); i++ )
            {
                asnTypeNode = (ASNType) childByIndex( i );
                mTag = asnTypeNode.getTag();
                if( mTag != null && mTag.match( aTag ) )
                {
                    lastChildIndex = ++i;
                    return asnTypeNode;
                }
                firstDefinedName = asnTypeNode.getName();
                lOptional = asnTypeNode.isOptional();
                while( asnTypeNode.isDefined() )
                {
                    try
                    {
                        asnTypeNode = asnTypeNode.decide( null );
                    } catch( Exception e )
                    {
                        lastChildIndex = ++i;
                        throw e;
                    }
                    tempTag = asnTypeNode.getTag();
                    asnTypeNode.setName( firstDefinedName );

                    if( tempTag != null )
                    {
                        if( aTag.match( tempTag ) )
                        {
                            lastChildIndex = ++i;
                            return asnTypeNode;
                        } else
                            mTag = tempTag;
                    }

                    if( lOptional == false && asnTypeNode.isOptional() )
                        lOptional = true;
                }
                //System.out.println("ASNTYPE NODE ->"+asnTypeNode.getName()+" TG > "+asnTypeNode.getTag()+" TYPE >"+asnTypeNode.getType());
                if( asnTypeNode instanceof ASNChoice )
                {
                    asnTypeNode = asnTypeNode.decide( aTag );
                    if( asnTypeNode != null )
                    {
                        lastChildIndex = ++i;
                        return asnTypeNode;
                    }
                }
                if( asnTypeNode != null && aTag.match( asnTypeNode.getTagUniv() ) )
                {
                    lastChildIndex = ++i;
                    return asnTypeNode;
                }
                if( lOptional )
                    continue;
                else
                {
                    throw new ASNException( ASNErrorCodes.TNODE_MISSING_MANDATORY_FIELD,
                                            new Object[] { asnTypeNode.getName() } );
                }
            }
        } else
        {
            System.out.println("TAG : "+aTag);
           // System.out.println("MST : "+this.toStringFull());
            System.out.println( "ASNSequence.decide()  LCI > "+lastChildIndex );
            throw new ASNException( ASNErrorCodes.TNODE_ERROR_DECIDING_CHILD, new Object[] { name, aTag } );
        }
        return asnTypeNode;
    }

    @Override
    public CadedDataTreeNode<Object> encode( ASNDataWriter asnDataWriter ) throws Exception
    {
        return null;
    }

    @Override
    public void reset()
    {
        //System.out.println( "ASNSequence.reset()->"+name );
        
        lastChildIndex = 0;
    }

}
