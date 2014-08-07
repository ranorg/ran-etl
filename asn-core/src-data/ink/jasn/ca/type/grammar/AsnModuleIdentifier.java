package ink.jasn.ca.type.grammar;

//*********************************************
// Definition of ASN ModuleIdentifier
//*********************************************
public class AsnModuleIdentifier
{
	public String name;
	public AsnOidComponentList componentList;

	// Default Constructor
	public AsnModuleIdentifier()
	{
		componentList = new AsnOidComponentList();
	}

	// toString Implementation
	public String toString()
	{
		String ts = "";
		ts = name + "  ";
		if (componentList != null)
			ts += componentList;
		return ts;
	}
}
