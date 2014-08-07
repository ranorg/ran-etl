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

import java.util.ArrayList;
import java.util.List;

/**
 * @Source : ASNSet.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 2, 2009
 * @Time : 12:15:20 PM
 * @Version : $0.01
 **/
public class ASNSet extends ASNCollection
{
	private static final long serialVersionUID = 5689792333301204094L;
	public AsnElementTypeList elementTypeList;
	public boolean isSequence;
	public final String BUILTINTYPE1 = "SET";

	private List<Integer> availableChildIndexes;

	{
		type = "SET";
		tagUniv = new Tag(Tag.TAG_VALUE_SET);
	}

	/**
     * 
     */
	public ASNSet()
	{
		super("");
	}

	/**
	 * @param name
	 * @param tag
	 */
	public ASNSet(String name, Tag tag)
	{
		super(name, tag);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.connectiva.gpf.datadef.asn.media.ASNConstructed#checkCompleteness()
	 */
	@Override
	public boolean checkCompleteness() throws Exception
	{
		boolean check = true;
		AbstractSyntaxTreeNode mstNode = null;
		for (Integer index : availableChildIndexes)
		{
			mstNode = childByIndex(index);
			if (!mstNode.isOptional())
			{
				check = false;
				throw new ASNException(ASNErrorCodes.TNODE_COMPLETENESS_CHECK_FAILED,
							new Object[] { type, name, mstNode.getName() });
			}
		}
		return check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ink.jasn.ca.type.base.ASNType#decide(ink.jasn.ca.type.base.Tag)
	 */
	@Override
	public ASNType decide(Tag aTag) throws Exception
	{
		Tag mTag, tempTag;
		ASNType asnTypeNode = null;
		String firstDefinedName;
		boolean lOptional;
		for (Integer index : availableChildIndexes)
		{
			asnTypeNode = (ASNType) childByIndex(index);
			mTag = asnTypeNode.getTag();

			// System.out.println("ASNSet.decide()==============>"+asnTypeNode.toString()+"------------"+mTag);

			if (mTag != null && mTag.match(aTag))
			{
				availableChildIndexes.remove(index);
				return asnTypeNode;
			}
			firstDefinedName = asnTypeNode.getName();
			lOptional = asnTypeNode.isOptional();
			while (asnTypeNode.isDefined())
			{
				try
				{
					asnTypeNode = asnTypeNode.decide(null);
				}
				catch (Exception e)
				{
					availableChildIndexes.remove(index);
					throw e;
				}

				if (asnTypeNode != null)
				{
					tempTag = asnTypeNode.getTag();
					asnTypeNode.setName(firstDefinedName);

					if (tempTag != null)
					{
						if (aTag.match(tempTag))
						{
							availableChildIndexes.remove(index);
							return asnTypeNode;
						}
						else
							mTag = tempTag;
					}

					if (lOptional == false && asnTypeNode.isOptional())
						lOptional = true;
				}
			}

			if (asnTypeNode instanceof ASNChoice)
			{
				asnTypeNode = asnTypeNode.decide(aTag);
				if (asnTypeNode != null)
				{
					availableChildIndexes.remove(index);
					return asnTypeNode;
				}
			}

			if (asnTypeNode != null && aTag.match(asnTypeNode.getTagUniv())
						&& mTag.match(aTag))
			{
				availableChildIndexes.remove(index);
				return asnTypeNode;
			}
		}
		throw new ASNException(ASNErrorCodes.TNODE_ERROR_DECIDING_CHILD, new Object[] {
				name, aTag });
	}

	@Override
	public CadedDataTreeNode<Object> encode(ASNDataWriter asnDataWriter) throws Exception
	{
		return null;
	}

	@Override
	public void reset()
	{
		int length = getChildren().size();
		if (availableChildIndexes == null)
			availableChildIndexes = new ArrayList<Integer>(length);
		else
			availableChildIndexes.clear();

		for (int i = 0; i < length; i++)
		{
			availableChildIndexes.add(i);
		}
	}

}
