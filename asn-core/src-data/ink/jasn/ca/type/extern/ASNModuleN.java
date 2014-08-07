/*
 * Copyright (c) 2009 CONNECTiVA Systems, Inc.
 * J2, Block-GP, Sector-V, Kolkata, 700091, INDIA
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * CONNECTiVA Systems, Inc. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with CONNECTiVA.
 *
 */

/**
* Package declaration
*/
package ink.jasn.ca.type.extern;

import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.grammar.AsnTypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Packages and classes import
 */

/**
 * @Source  : ASNModule.java
 * @Author  : Nirmal Kumar Biswas
 * @Date    : Sep 10, 2009
 * @Time    : 8:38:30 PM
 * @Version : $1.00
 **/

public class ASNModuleN
{

    public ASNModuleIdentifier moduleIdentifier; // Name of Module
    public List<String> exportSymbolList;
    public List<String> importSymbolList;
    public List<String> importSymbolFromModuleList;
    
    public AsnTypes asnTypes;
    public List<ASNType> asnValues;
    public boolean tag;
    public boolean extensible;
    public String tagDefault;
    public boolean exported;
    public boolean imported;

    // Default Constructor
    public ASNModuleN()
    {
        exportSymbolList = new ArrayList<String>();
        importSymbolList = new ArrayList<String>();
        importSymbolFromModuleList = new ArrayList<String>();
        
        asnTypes = new AsnTypes();
        asnValues = new ArrayList<ASNType>();
        
        tagDefault = "";
    }

    // To String Method
    public String toString()
    {
        String ts = "";
        Iterator ii;
        ts += "MODULE NAME ::= \n";
        ts += moduleIdentifier + "\n";
        ts += "IMPORT SYMBOL LIST" + "\n";
        ii = importSymbolList.iterator();
        while( ii.hasNext() )
        {
            ts += ii.next() + "\n";
        }
        ts += "IMPORT SYMBOLS FROM MODULE \n";
        ii = importSymbolFromModuleList.iterator();
        while( ii.hasNext() )
        {
            ts += ii.next() + "\n";
        }
        ts += "EXPORT SYMBOL LIST \n";
        ii = exportSymbolList.iterator();
        while( ii.hasNext() )
        {
            ts += ii.next() + "\n";
        }
        ts += "ASN TYPES LIST \n";
        ts += asnTypes + "\n";

        ts += "ASN VALUES LIST \n";
        ii = asnValues.iterator();
        while( ii.hasNext() )
        {
            ts += ii.next() + "\n";
        }
        return ts;
    }
}
