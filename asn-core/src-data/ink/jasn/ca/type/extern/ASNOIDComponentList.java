/*
 * Copyright (c) 2009 CONNECTiVA Systems, Inc. J2, Block-GP, Sector-V, Kolkata, 700091, INDIA All
 * Rights Reserved. This software is the confidential and proprietary information of CONNECTiVA
 * Systems, Inc. ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you entered into with
 * CONNECTiVA.
 */

/**
 * Package declaration
 */

package ink.jasn.ca.type.extern;

import ink.jasn.ca.type.base.ASNDefinedValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Packages and classes import
 */

/**
 * @Source : ASNOIDComponentList.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Sep 10, 2009
 * @Time : 8:47:44 PM
 * @Version : $1.00
 */

public class ASNOIDComponentList
{
    public boolean isDefinitive;
    public ASNDefinedValue defval;
    public List<String> components;

    // Default Constructor
    public ASNOIDComponentList()
    {
        components = new ArrayList<String>();
    }

    // toString Method
    public String toString()
    {
        String ts = "";
        if( isDefinitive )
            ts += defval;
        if( components != null )
        {
            java.util.Iterator i = components.iterator();
            while( i.hasNext() )
            {
                ts += (i.next());
            }
        }
        return ts;
    }
}
