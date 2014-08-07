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

package ink.jasn.com.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Packages and classes import
 */

/**
 * @Source : MessageLookup.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Oct 28, 2009
 * @Time : 12:36:36 PM
 * @Version : $1.00
 */

public class MessageLookup
{
    private static ResourceBundle bundle = ResourceBundle.getBundle( "ink.jasn.com.exception.ASNErrorCodes",
                                                                     Locale.getDefault() );

    public static String lookup( String errorCode )
    {
        try
        {
            String message = bundle.getString( errorCode );
            return ASNErrorCodes.CodePrefix + errorCode + ": " + message;
        } catch( Exception e )
        {
            return ASNErrorCodes.CodePrefix + ASNErrorCodes.LOOKUP_BAD_KEY
                   + "BAD-MESSAGE-LOOKUP !!! V1 [ Undefined lookup key provided in message lookup for ASN exception. ]";
        }

    }

    public static String lookup( String errorCode, Object[] fillers )
    {
        String message = bundle.getString( errorCode );

        try
        {
            MessageFormat msgFormatter = new MessageFormat( message );
            return ASNErrorCodes.CodePrefix + errorCode + ": " + msgFormatter.format( fillers );
        } catch( Exception e )
        {
            return ASNErrorCodes.CodePrefix + ASNErrorCodes.LOOKUP_BAD_KEY
                   + "BAD-MESSAGE-LOOKUP !!! V2 [ Undefined lookup key provided in message lookup for ASN exception. ]";

        }
    }

    public static void main( String[] args )
    {
        System.out.println( "MessageLookup.main()--- >" + lookup(""+ ASNErrorCodes.CODEC_BADLY_ENCODED_DATA) );
    }
}
