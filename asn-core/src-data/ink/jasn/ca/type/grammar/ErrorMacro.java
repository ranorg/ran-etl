package ink.jasn.ca.type.grammar;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

// *********************************************
// Definition of Macro ERROR
// *********************************************
public class ErrorMacro extends ASNType
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2951682380598541587L;
	public String name;
	public String parameterName;
	public boolean isParameter;
	public Object typeReference;
	public boolean isDefinedType; // Type is Defined or builtin type
	public String typeName; // Name of the Defined Type

	// Default Constructor
	public ErrorMacro()
	{
	}

	// toString Method Definition

	public String toString()
	{
		String ts = "";
		ts += (name + "\t::=\tERROR");
		if (isParameter)
		{
			if (parameterName != "")
			{
				ts += ("PARAMETER\t" + parameterName);
			}
			else
			{
				ts += ("PARAMETER\t");
				if (isDefinedType)
					ts += (typeName);
				else
					ts += (typeReference.getClass().getName());
			}
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
