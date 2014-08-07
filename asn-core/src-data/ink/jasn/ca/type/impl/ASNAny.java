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
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;


/**
 * @Source : ASNAny.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 8, 2009
 * @Time : 3:04:21 PM
 * @Version : $0.01
 **/

public class ASNAny extends ASNType
{
    private static final long serialVersionUID = 1327156417938445563L;
    public boolean isDefinedBy;
    public String definedByType;
    public final String BUILTINTYPE = "ANY";

    private final String TYPE_STRING = "ANY";

    {
        type = TYPE_STRING;
    }

    public ASNAny()
    {

    }

    /**
     * @param name
     */
    public ASNAny( String name )
    {
        super( name );
    }

    @Override
    public CadedDataTreeNode<Object> decode( ASNDataReader asnDataReader ) throws Exception
    {
        return null;
    }

    @Override
    public CadedDataTreeNode<Object> encode( ASNDataWriter asnDataWriter ) throws Exception
    {
        return null;
    }

    @Override
    public boolean checkCompleteness()
    {
        return false;
    }

    @Override
    public ASNType decide( Tag aTag ) throws Exception
    {
        return (ASNType) childByIndex( 0 );
    }

    @Override
    public void reset()
    {

    }

}
