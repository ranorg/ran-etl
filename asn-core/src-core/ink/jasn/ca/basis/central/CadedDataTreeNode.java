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
 * @Source 	 	 : CadedDataTreeNode.java
 * @Description : A general purpose tree node implementation can be used to represent any tree data.
 * @Timeline 	 : Jan 13, 2013 11:30:14 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */

package ink.jasn.ca.basis.central;

import java.util.ArrayList;
import java.util.List;

public class CadedDataTreeNode<T>
{
	private String name; // Name of the value tree NODE<T>

	private String alias;

	private T data; // Data, if present for the tree NODE<T>

	private CadedDataTreeNode<T> parent; // Link to the parent NODE<T> if it is
														// available

	private List<CadedDataTreeNode<T>> children; // Child list for the NODE<T>

	/**
	 * Default constructor
	 */
	public CadedDataTreeNode()
	{
		children = new ArrayList<CadedDataTreeNode<T>>();
	}

	/**
	 * Constructor with NODE name
	 * 
	 * @param name
	 */
	public CadedDataTreeNode(String name)
	{
		this();
		setName(name);
	}

	/**
	 * Constructor with name and value(data) for the tree node
	 * 
	 * @param name
	 * @param value
	 */
	public CadedDataTreeNode(String name, T value)
	{
		this(name);
		data = value;
	}

	/**
	 * Check whether the NODE has a parent
	 * 
	 * @return
	 */
	public boolean hasParent()
	{
		return (parent == null) ? false : true;
	}

	/**
	 * @return the parent
	 */
	public CadedDataTreeNode<T> getParent()
	{
		return parent;
	}

	/**
	 * @param parent
	 *           the parent to set
	 */
	public void setParent(CadedDataTreeNode<T> parent)
	{
		this.parent = parent;
	}

	/**
	 * Return the children of Node<T>. The Tree<T> is represented by a single
	 * root Node<T> whose children are represented by a List<Node<T>>. Each of
	 * these Node<T> elements in the List can have children. The getChildren()
	 * method will return the children of a Node<T>.
	 * 
	 * @return the children of Node<T>
	 */
	public List<CadedDataTreeNode<T>> getChildren()
	{
		return this.children;
	}

	/**
	 * Return the children of Node<T>. The Tree<T> is represented by a single
	 * root Node<T> whose children are represented by a List<Node<T>>. Each of
	 * these Node<T> elements in the List can have children. The getChildren()
	 * method will return the children of a Node<T>.
	 * 
	 * @return the children of Node<T>
	 */
	public List<CadedDataTreeNode<T>> getElements()
	{
		return this.children;
	}

	/**
	 * Sets the children of a Node<T> object. See docs for getChildren() for more
	 * information.
	 * 
	 * @param children
	 *           the List<Node<T>> to set.
	 */
	public void setChildren(List<CadedDataTreeNode<T>> children)
	{
		for (CadedDataTreeNode<T> mvtNode : children)
		{
			mvtNode.setParent(this);
		}
		this.children = children;
	}

	/**
	 * Returns the number of immediate children of this Node<T>.
	 * 
	 * @return the number of immediate children.
	 */
	public int getNumberOfChildren()
	{
		return (children == null) ? 0 : children.size();
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *           the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
		this.alias = name;
	}

	/**
	 * Adds a child to the list of children for this Node<T>. The addition of the
	 * first child will create a new List<Node<T>>.
	 * 
	 * @param child
	 *           a Node<T> object to set.
	 */
	public void addChild(CadedDataTreeNode<T> child)
	{
		if (child != null)
		{
			child.setParent(this);
			children.add(child);
		}
	}

	/**
	 * Checks the NODE is leaf
	 * 
	 * @return
	 */
	public boolean isLeaf()
	{
		return (children.size() == 0) ? true : false;
	}

	/**
	 * Inserts a Node<T> at the specified position in the child list. Will *
	 * throw an ArrayIndexOutOfBoundsException if the index does not exist.
	 * 
	 * @param index
	 *           the position to insert at.
	 * @param child
	 *           the Node<T> object to insert.
	 * @throws IndexOutOfBoundsException
	 *            if thrown.
	 */
	public void insertChildAt(int index, CadedDataTreeNode<T> child)
				throws IndexOutOfBoundsException
	{
		if (index == getNumberOfChildren())
		{
			addChild(child);
			return;
		}
		else
		{
			children.get(index); // just to throw the exception, and stop here
			children.add(index, child);
		}
	}

	/**
	 * Remove the Node<T> element at index index of the List<Node<T>>.
	 * 
	 * @param index
	 *           the index of the element to delete.
	 * @throws IndexOutOfBoundsException
	 *            if thrown.
	 */
	public void removeChildAt(int index) throws IndexOutOfBoundsException
	{
		children.remove(index);
	}

	/**
	 * Get data object of the tree node
	 * 
	 * @return
	 */
	public T getData()
	{
		return this.data;
	}

	/**
	 * Get data object of the tree node
	 * 
	 * @return
	 */
	public String getValue()
	{
		return (data == null) ? null : String.valueOf(data);
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
	 * Set data object to the tree node
	 * 
	 * @param data
	 */
	public void setData(T data)
	{
		this.data = data;
	}

	/**
	 * Override toString method of Object
	 */
	@Override
	public String toString()
	{
		return toString("");
	}

	public static void main(String[] args)
	{
		System.out.println("CadedDataTreeNode.main(): " + String.valueOf(null));
	}

	/**
	 * @param str
	 * @return String representation of the giver value tree
	 */
	public String toString(String str)
	{
		StringBuilder returnString;
		if (data != null)
			returnString = new StringBuilder("\n" + str + "[ Name:" + name + "\tData:"
						+ data + " ]");
		else
			returnString = new StringBuilder("\n" + str + "[ Name:" + name + " ]");

		if (children != null)
		{
			str += "\t|---";
			for (CadedDataTreeNode<T> asnType : children)
			{
				returnString.append(asnType.toString(str));
			}
		}
		return returnString.toString();
	}
}
