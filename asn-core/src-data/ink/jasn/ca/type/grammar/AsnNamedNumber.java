package ink.jasn.ca.type.grammar;

// *********************************************
// Definition of NamedNumber
// *********************************************
public class AsnNamedNumber
{
	public String name;
	public boolean isSignedNumber;
	public AsnSignedNumber signedNumber;
	public AsnDefinedValue definedValue;

	// Default Constructor
	public AsnNamedNumber()
	{
		name = "";
	}

	// toString() Method Definition

	public String toString()
	{
		String ts = "";
		ts += (name + "\t(");

		if (isSignedNumber)
		{
			ts += (signedNumber);
		}
		else
		{
			ts += (definedValue);
		}
		ts += (")");
		return ts;
	}
}
