package ink.jasn.ca.basis.central;

import java.io.Serializable;

public abstract class TreeNode<T, V> implements Cloneable, Serializable
{
	private static final long serialVersionUID = 7959571896627854178L;
	protected String name;
	protected V value;
	protected int count;
	protected int itrTndex;
	protected transient T parent;
	protected transient T[] children;

	@SuppressWarnings("unchecked")
	public TreeNode()
	{
		this.children = (T[]) new Object[3];
	}

	/**
	 * @param name
	 */
	public TreeNode(String aName)
	{
		this();
		this.name = aName;
	}

	/**
	 * @param name
	 * @param value
	 */
	public TreeNode(String aName, V aValue)
	{
		this(aName);
		this.value = aValue;
	}

	public abstract boolean addChild(T aChild);

	public abstract void addChildAt(int index, T aChild);

	public abstract T dfsNext();

	public boolean removeChild(T aChild)
	{
		if (aChild == null)
		{
			for (int index = 0; index < count; index++)
			{
				T aChild2 = aChild;
				if (aChild2.equals(children[index]))
				{
					update(index);
					return true;
				}
			}
		}
		else
		{
			for (int index = 0; index < count; index++)
				if (children[index] == null)
				{
					update(index);
					return true;
				}
		}
		return false;
	}

	public T removeChildAt(int index)
	{
		if (index >= count)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + count);
		T oldValue = children[index];
		update(index);
		return oldValue;
	}

	@SuppressWarnings("unchecked")
	protected void assureContent(int requiredCapacity)
	{
		int availableCapacity = children.length;
		if (requiredCapacity > availableCapacity)
		{
			Object oldData[] = children;
			int newCapacity = (availableCapacity * 3) / 2 + 1;
			if (newCapacity < requiredCapacity)
				newCapacity = requiredCapacity;
			children = (T[]) new Object[newCapacity];
			System.arraycopy(oldData, 0, children, 0, count);
		}
	}

	private void update(int index)
	{
		int rmIndex = count - index - 1;
		if (rmIndex > 0)
			System.arraycopy(children, index + 1, children, index, rmIndex);
		children[--count] = null;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String aName)
	{
		this.name = aName;
	}

	public V getValue()
	{
		return value;
	}

	public void setValue(V aValue)
	{
		this.value = aValue;
	}

	public T getParent()
	{
		return parent;
	}

	public void setParent(T aParent)
	{
		this.parent = aParent;
	}

	public T[] getChildren()
	{
		return children;
	}

	public boolean isLeaf()
	{
		return (children.length == 0) ? true : false;
	}

	public int childrenCount()
	{
		return count;
	}

	public void setChildren(T[] aChildren)
	{
		this.children = aChildren;
	}

	@Override
	public String toString()
	{
		return toString("");
	}

	public abstract String toString(String aIndention);
}
