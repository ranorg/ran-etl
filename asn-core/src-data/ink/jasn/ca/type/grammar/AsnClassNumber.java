package ink.jasn.ca.type.grammar;

//*********************************************
// Definition of Class_Number
//*********************************************
public class AsnClassNumber
{
	public Integer num;
	public String name;

	// Default Constructor
	public AsnClassNumber()
	{
	}

	// toString definition
	public String toString()
	{
		String ts = "";
		if (num != null)
		{
			ts += (num);
		}
		else
		{
			ts += (name);
		}
		return ts;
	}
}
