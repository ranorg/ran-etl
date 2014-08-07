package ink.jasn.ca.type.impl;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNPrimitive;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

public class ASNBoolean extends ASNPrimitive
{
	private static final long serialVersionUID = 7081123390792408263L;

	public final String BUILTINTYPE = "BOOLEAN";

	private final String TYPE_STRING = "BOOLEAN";

	{
		type = "BOOLEAN";
		tagUniv = new Tag(Tag.TAG_VALUE_BOOLEAN);
	}

	/**
     * 
     */
	public ASNBoolean()
	{
		super("");
		type = TYPE_STRING;
	}

	/**
	 * @param name
	 */
	public ASNBoolean(String name)
	{
		super(name);
	}

	/**
	 * @param name
	 * @param tag
	 */
	public ASNBoolean(String name, Tag tag)
	{
		super(name, tag);
	}

	/**
	 * @param name
	 * @param tag
	 * @param optional
	 */
	public ASNBoolean(String name, Tag tag, boolean optional)
	{
		super(name, tag, optional);
	}

	@Override
	public CadedDataTreeNode<Object> decode(ASNDataReader asnDataReader) throws Exception
	{
		CadedDataTreeNode<Object> mvtNode = new CadedDataTreeNode<Object>(name);
		mvtNode.setData(asnDataReader.decodeBoolean());
		return mvtNode;
	}

	@Override
	public CadedDataTreeNode<Object> encode(ASNDataWriter asnDataWriter) throws Exception
	{
		return null;
	}

}
