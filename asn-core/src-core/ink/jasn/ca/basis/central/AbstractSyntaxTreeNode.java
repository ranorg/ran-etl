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
 * @Source 	 	 : AbstractSyntaxTreeNode.java
 * @Description : A general purpose tree node implementation can be used to represent any tree data.
 * @Timeline 	 : Jan 13, 2013 11:30:14 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */

package ink.jasn.ca.basis.central;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSyntaxTreeNode implements Serializable, Cloneable
{
	/**
	 * Eclipse generated serial version number
	 */
	private static final long serialVersionUID = 166997694932309895L;

	/**
	 * Name of the NODE.
	 */
	protected String name;

	/**
	 * Alias for the NODE name
	 */
	protected String alias;

	/**
	 * Type of the NODE represented as String.
	 */
	protected String type;

	/**
	 * Reference NODE name to the child sub-tree.
	 */
	protected String reference;

	/**
	 * If the NODE is optional or not.
	 */
	protected boolean optional;

	/**
	 * Index of the last NODE visited in the tree.
	 */
	protected int lastIterationIndex;

	/**
	 * Link to the parent
	 */
	private AbstractSyntaxTreeNode parent;

	/**
	 * List of child NODEs of this.
	 */
	private List<AbstractSyntaxTreeNode> children;

	/**
     * 
     */
	@Override
	public AbstractSyntaxTreeNode clone() throws CloneNotSupportedException
	{
		AbstractSyntaxTreeNode mstn = (AbstractSyntaxTreeNode) super.clone();
		List<AbstractSyntaxTreeNode> childrenC = new ArrayList<AbstractSyntaxTreeNode>();
		if (mstn.children != null)
		{
			for (AbstractSyntaxTreeNode node : mstn.children)
			{
				childrenC.add(node.clone());
			}
		}
		mstn.children = childrenC;
		return mstn;
	}

	/**
	 * Default constructor
	 */
	public AbstractSyntaxTreeNode()
	{
		children = new ArrayList<AbstractSyntaxTreeNode>();
	}

	/**
	 * Constructor with NODE name
	 * 
	 * @param name
	 */
	public AbstractSyntaxTreeNode(String name)
	{
		this();
		setName(name);

	}

	/**
	 * Constructor with NODE name and NODE type
	 * 
	 * @param name
	 * @param type
	 */
	public AbstractSyntaxTreeNode(String name, String type)
	{
		this(name);
		this.type = type;
	}

	/**
	 * Constructor with optional option
	 * 
	 * @param name
	 * @param optional
	 */
	public AbstractSyntaxTreeNode(String name, boolean optional)
	{
		this(name);
		this.optional = optional;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
		this.alias = name;
	}

	/**
	 * @param type
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return
	 */
	public String getReference()
	{
		return reference;
	}

	/**
	 * @param typeReference
	 */
	public void setReference(String typeReference)
	{
		this.reference = typeReference;
	}

	/**
	 * @return
	 */
	public AbstractSyntaxTreeNode getParent()
	{
		return parent;
	}

	/**
	 * @param parent
	 */
	public void setParent(AbstractSyntaxTreeNode parent)
	{
		this.parent = parent;
	}

	/**
	 * @param child
	 * @return
	 */
	public boolean addChild(AbstractSyntaxTreeNode child)
	{
		if (child != null)
		{
			child.setParent(this);
			return children.add(child);
		}
		return false;
	}

	/**
	 * @param childrenList
	 * @return
	 */
	public boolean addChildren(List<AbstractSyntaxTreeNode> childrenList)
	{
		boolean trace = true;
		for (AbstractSyntaxTreeNode mstNode : childrenList)
		{
			mstNode.setParent(this);
			trace |= this.children.add(mstNode);
		}
		return trace;
	}

	/**
	 * @param child
	 * @return
	 */
	public boolean removeChild(AbstractSyntaxTreeNode child)
	{
		return children.remove(child);
	}

	/**
	 * @param childrenList
	 */
	public void setChildren(List<AbstractSyntaxTreeNode> childrenList)
	{
		for (AbstractSyntaxTreeNode mstNode : childrenList)
		{
			mstNode.setParent(this);
		}
		this.children = childrenList;
	}

	/**
	 * Calculate next DFS node iteratively
	 * 
	 * @return {@link AbstractSyntaxTreeNode}
	 */
	public AbstractSyntaxTreeNode dfsNext()
	{
		AbstractSyntaxTreeNode node = null;
		if (!isLeaf() && lastIterationIndex < count())
			return children.get(++lastIterationIndex);
		else if (parent != null)
			node = parent.dfsNext();
		return node;
	}

	/**
	 * Check whether this is leaf level node
	 * 
	 * @return boolean
	 */
	public boolean isLeaf()
	{
		return (children == null || children.size() == 0) ? true : false;
	}

	/**
	 * Check whether the given node has a parent or not, OR this the ROOT node
	 * 
	 * @return boolean
	 */
	public boolean hasParent()
	{
		return (parent == null) ? false : true;
	}

	/**
	 * @return list of nodes.
	 */
	public List<AbstractSyntaxTreeNode> getChildren()
	{
		return children;
	}

	/**
	 * @return int ; number of children of this node
	 */
	public int count()
	{
		return children.size();
	}

	/**
	 * @param index
	 * @return a {@link AbstractSyntaxTreeNode}
	 */
	public AbstractSyntaxTreeNode childByIndex(int index)
	{
		return (index < count() ? children.get(index) : null);
	}

	/**
	 * @return the alias
	 */
	public String getAlias()
	{
		return alias;
	}

	/**
	 * @param alias
	 *           the alias to set
	 */
	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	/**
	 * @return boolean
	 */
	public boolean isOptional()
	{
		return optional;
	}

	/**
	 * Set the node as optional
	 */
	public void setOptional()
	{
		optional = true;
	}

	/**
	 * @param isOptional
	 */
	public void setOptional(boolean isOptional)
	{
		this.optional = isOptional;
	}

	/**
	 * @param str
	 * @return String
	 */
	public abstract String toString(String str);

	/**
	 * @return boolean
	 */
	public abstract boolean isScalar();

	/**
	 * @return boolean
	 */
	public abstract boolean isDefined();

	/**
	 * @param visitor
	 */
	public abstract void visit(Visitor visitor);

	/**
	 * 
	 * @return String representation of generated java code for a particular type
	 */
//	public abstract String toJavaClassString();

	/**
	 * @return String
	 */
	public abstract String toXMLString();

}
