/*
 * 
 * Copyright 2013 ZCrypto, All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, without modification, 
 * are permitted subject to the following conditions:
 *
 * 1. Any use in source and binary forms must have a valid license from ZCrypto.
 *
 * 2. Redistributions of source code must retain the above copyright notice together with a valid license 
 *	   agreement and this list of conditions and the following disclaimer.
 *
 *	3. Redistributions in binary form must reproduce the above copyright notice together with a valid license 
 *	   agreement and this list of conditions and the following disclaimer in the documentation and/or other 
 * 	materials provided with the distribution.
 *
 * 4. Any short of use to endorse or promote products are not permitted without any prior written permission
 *		from ZCrypto.
 *
 * @Vertical 	 : Telecommunication   
 *	@Project  	 : Compiler for ASN with Data Encoder and Decoder (CA-DED)
 * @Source 	 	 : ASNEOFException.java
 * @Description : SHORT DESCRIPTION ABOUT THE FILE
 * @Timeline 	 : Oct 25, 2013 4:36:10 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */

package ink.jasn.com.exception;

public class ASNEOFException extends ASNException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7600050891042296844L;

	/**
	 * @param errorCode
	 */
	public ASNEOFException(int errorCode)
	{
		super(errorCode);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public ASNEOFException(int errorCode, Exception cause)
	{
		super(errorCode, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param fillers
	 */
	public ASNEOFException(int errorCode, Object[] fillers)
	{
		super(errorCode, fillers);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param fillers
	 * @param cause
	 */
	public ASNEOFException(int errorCode, Object[] fillers, Exception cause)
	{
		super(errorCode, fillers, cause);
		// TODO Auto-generated constructor stub
	}

}
