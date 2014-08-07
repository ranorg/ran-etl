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
 * @Source 	 	 : ASNModelViewNode.java
 * @Description : A general purpose tree node implementation can be used to represent any tree data.
 * @Timeline 	 : Jan 13, 2013 11:30:14 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */

package ink.jasn.ca.type.base;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

public class ASNModelViewNode extends ASNType
{
	private static final long	serialVersionUID	= -8822451868772849732L;

	public ASNModelViewNode()
	{
		super();
	}

	public ASNModelViewNode(String name, String type)
	{
		super(name);
		this.setType(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ink.jasn.ca.type.base.ASNType#checkCompleteness()
	 */
	@Override
	public boolean checkCompleteness() throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ink.jasn.ca.type.base.ASNType#decide(ink.jasn.ca.type.base.Tag)
	 */
	@Override
	public ASNType decide(Tag tag) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ink.jasn.ca.type.base.ASNType#decode(ink.jasn.ded.util.codec.ASNDataReader
	 * )
	 */
	@Override
	public CadedDataTreeNode<Object> decode(ASNDataReader asnDataReader) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ink.jasn.ca.type.base.ASNType#encode(ink.jasn.ded.util.codec.ASNDataWriter
	 * )
	 */
	@Override
	public CadedDataTreeNode<Object> encode(ASNDataWriter asnDataWriter) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

}
