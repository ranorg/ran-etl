package ink.jasn.ca.type.grammar;

//*********************************************
// Definition of Defined Value
//*********************************************
public class AsnDefinedValue
{

	public boolean isDotPresent;
	public String moduleIdentifier;
	public String name;

	// Default Constructor
	public AsnDefinedValue()
	{
	}

	// toString() Method Definition
	public String toString()
	{
		String ts = "";
		if (isDotPresent)
		{
			ts += (moduleIdentifier + "." + name);
		}
		else
		{
			ts += name;
		}
		return ts;
	}
}
