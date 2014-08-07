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
 * @Source 	 	 : JavaCodeGenerationVisitor.java
 * @Description : SHORT DESCRIPTION ABOUT THE FILE
 * @Timeline 	 : Jul 22, 2013 3:09:44 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */

package ink.jasn.ca.gen.code;

import ink.jasn.ca.basis.central.AbstractSyntaxTreeNode;
import ink.jasn.ca.basis.central.Visitor;

public class JavaCodeGenerationVisitor implements Visitor, ClassString
{

	/**
	 * 
	 */
	public JavaCodeGenerationVisitor()
	{
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ink.jasn.ca.basis.central.Visitor#visit(ink.jasn.ca.basis.central.
	 * AbstractSyntaxTreeNode)
	 */
	@Override
	public void visit(AbstractSyntaxTreeNode mstNode)
	{
		StringBuilder strBuilder = new StringBuilder(PUBLIC_CLASS_DECLARATION
					+ SINGLE_SPACE + mstNode.getName() + SINGLE_SPACE + EXTENDS_SUPER
					+ mstNode.getParent().getName());
		strBuilder.append(NEW_LINE);
		strBuilder.append(LEFT_BRACE);
		strBuilder.append(NEW_LINE);
		strBuilder.append(TAB_SPACE + DEFAULT_CONSTRUCTOR_DECLARATION + SINGLE_SPACE
					+ mstNode.getName() + METHOD_BRACES);
		strBuilder.append(NEW_LINE);
		strBuilder.append(LEFT_BRACE);
		strBuilder.append(NEW_LINE);
		strBuilder.append(RIGHT_BRACE);
		for (AbstractSyntaxTreeNode mstN : mstNode.getChildren())
		{
			visit(mstN);
		}

	}

}
