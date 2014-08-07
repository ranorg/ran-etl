package ink.jasn.ca.type.grammar;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

// *********************************************
// Definition of EXTERNAL
// *********************************************
public class AsnExternal extends ASNType
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -463635121754992325L;
	public String name;
	public final String BUILTINTYPE = "EXTERNAL";

	// Default Constructor
	public AsnExternal()
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
