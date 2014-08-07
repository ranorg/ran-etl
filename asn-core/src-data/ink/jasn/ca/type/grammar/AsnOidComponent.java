package ink.jasn.ca.type.grammar;

// *********************************************
// Definition of OID_Component
// *********************************************
public class AsnOidComponent
{

	public boolean nameForm;
	public boolean numberForm;
	public boolean nameAndNumberForm;
	public boolean isDefinedValue;
	public AsnDefinedValue defval;
	public Integer num;
	public String name;

	// Default Constructor
	public AsnOidComponent()
	{
		name = "";
		num = null;
	}

	// toString Implementation
	public String toString()
	{
		String ts = "";
		if (numberForm)
		{
			ts += num + "\t";
		}
		else if (nameForm)
		{
			ts += name;
			if (nameAndNumberForm)
			{
				ts += "(" + num + ")\t";
			}
		}
		else if (isDefinedValue)
		{
			ts += defval;
		}
		return ts;
	}
}
