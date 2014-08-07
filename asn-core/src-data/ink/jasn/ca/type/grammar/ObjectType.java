package ink.jasn.ca.type.grammar;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

import java.util.ArrayList;

// *********************************************
// Definition of OBJECT-TYPE Macro
// *********************************************
public class ObjectType extends ASNType
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1733438230464371242L;
	public String accessPart;
	public String statusPart;
	public ArrayList elements;
	public AsnValue value;
	public Object type;
	public final String BUILTINTYPE = "OBJECT-TYPE";

	// Default Constructor
	public ObjectType()
	{
		elements = new ArrayList();
	}

	public String toString()
	{
		String ts = "";
		ts += ("AccessPart is " + accessPart);
		return ts;
	}

	@Override
	public CadedDataTreeNode<Object> decode(ASNDataReader asnDataReader) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CadedDataTreeNode<Object> encode(ASNDataWriter asnDataWriter) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkCompleteness()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ASNType decide(Tag aTag) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}
}
