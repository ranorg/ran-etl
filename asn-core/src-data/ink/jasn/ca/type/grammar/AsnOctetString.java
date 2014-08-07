package ink.jasn.ca.type.grammar;

import ink.jasn.ca.type.impl.ASNOctetString;

// *********************************************
// Definition of Octet String
// *********************************************
public class AsnOctetString extends ASNOctetString
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6416290550660932746L;
	public AsnConstraint constraint;
	public final String BUILTINTYPE = "OCTET STRING";

	// Default Constructor
	public AsnOctetString()
	{
		super("");
	}

	// toString Definition
	public String toString()
	{
		String ts = "";
		ts += name + "\t::=" + BUILTINTYPE + "\t";
		if (constraint != null)
		{
			ts += constraint;
		}
		return ts;
	}
}
