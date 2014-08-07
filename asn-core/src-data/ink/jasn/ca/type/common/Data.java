
/**
 * Copyright Connectiva Systems, Inc. All Rights Reserved. This software is the
 * proprietary information of Connectiva Systems, Inc. Use is subject to license
 * terms.
 *
 * Project: ParserGenX	
***/

/***
 * Package Declaration :
 *---------------------*/
 package ink.jasn.ca.type.common;

/***
 * Packages and Classes Import :
 *-----------------------------*/

/**
 * Source  : Data.java
 * Author  : Nirmal Kumar Biswas
 * Date    : Apr 8, 2009
 * Time    : 1:42:26 PM
 * Version : $0.01
 **/
public class Data<D>
{
    private D data;
    
    public Data(D data)
    {
        this.data=data;
    }
    /*
     * (non-Javadoc)
     * @see com.connectiva.pgf.datadefinition.common.Operational#getValue()
     */
    public D getValue()
    {
        return data;
    }

    /*
     * (non-Javadoc)
     * @see com.connectiva.pgf.datadefinition.common.Operational#setValue(java.lang.Object)
     */
    public void setValue( D o)
    {
        this.data=o;
    }    
}
