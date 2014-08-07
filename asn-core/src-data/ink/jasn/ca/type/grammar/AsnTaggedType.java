package ink.jasn.ca.type.grammar;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

// *********************************************
// Definition of Tagged Type
// *********************************************
public class AsnTaggedType extends ASNType
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7720456379946252477L;
	public String name;
	public String tagDefault;
	public Object typeReference; // Type Reference
	public boolean isDefinedType; // Distinguish between builtin and defined
											// types
	public String typeName; // Name of defined type

	// Default Constructor
	public AsnTaggedType()
	{
		super("");
		name = "";
		tagDefault = "";
		typeReference = null;
		isDefinedType = false;
		typeName = "";
	}

	// toString() definition

	public String toString()
	{
		String ts = "";
		ts += name;
		ts += ("\t" + tag + "\t" + tagDefault + "\t");
		if (isDefinedType)
		{
			ts += (typeName);
		}
		else
		{
			ts += (typeReference.getClass().getName());
		}
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
