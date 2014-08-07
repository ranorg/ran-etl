package ink.jasn.ca.type.grammar;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

// *********************************************
// Definition of RELATIVE OID TYPE
// *********************************************
public class AsnRelativeOid extends ASNType
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6964646478832528276L;
	public String name;
	final String BUILTINTYPE = "RELATIVE-OID";

	// Default Constructor
	public AsnRelativeOid()
	{
	}

	public String toString()
	{
		String ts = "";
		ts += BUILTINTYPE;
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
