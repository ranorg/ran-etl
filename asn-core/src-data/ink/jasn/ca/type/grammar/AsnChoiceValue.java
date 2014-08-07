package ink.jasn.ca.type.grammar;

//*********************************************
// Definition of ChoiceValue
//*********************************************
public class AsnChoiceValue
{
	public String name;
	public AsnValue value;

	// Default Constructor
	public AsnChoiceValue()
	{
	}

	// toString Method
	public String toString()
	{
		String ts = "";
		ts += name;
		ts += "\t" + ":" + value;
		return ts;
	}
}
