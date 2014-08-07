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
package ink.jasn.com.exception;

/**
 * Packages and classes import
 */

/**
 * @Source  : ASNException.java
 * @Author  : Nirmal Kumar Biswas
 * @Date    : Oct 28, 2009
 * @Time    : 12:33:04 PM
 * @Version : $1.00
 **/

public class ASNException extends Exception
{
    private static final long serialVersionUID = -880442746106793374L;

    public ASNException( int errorCode )
    {
        super( MessageLookup.lookup( String.valueOf( errorCode ) ) );
    }

    public ASNException( int errorCode, Exception cause )
    {
        super( MessageLookup.lookup( String.valueOf( errorCode ) ), cause );
    }


    public ASNException( int errorCode, Object[] fillers )
    {
        super( MessageLookup.lookup( String.valueOf( errorCode ), fillers ) );
    }

    public ASNException( int errorCode, Object[] fillers, Exception cause )
    {
        super( MessageLookup.lookup( String.valueOf( errorCode ), fillers ), cause );
    }

}
