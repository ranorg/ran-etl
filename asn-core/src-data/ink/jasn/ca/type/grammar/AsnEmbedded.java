package ink.jasn.ca.type.grammar;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

// *********************************************
// Definition of EMBEDDED PDV TYPE
// *********************************************
public class AsnEmbedded extends ASNType
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9076084751798336427L;
	public String name;
	public final String BUILTINTYPE = "EMBEDDED PDV";

	// Default Constructor
	public AsnEmbedded()
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
