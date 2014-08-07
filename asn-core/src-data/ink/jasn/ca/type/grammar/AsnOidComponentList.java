package ink.jasn.ca.type.grammar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// *********************************************
// Definition of OID_Component_LIST
// *********************************************
public class AsnOidComponentList
{
	public boolean isDefinitive;
	public AsnDefinedValue defval;
	public List<AsnOidComponent> components;

	// Default Constructor
	public AsnOidComponentList()
	{
		components = new ArrayList<AsnOidComponent>();
	}

	// toString Method
	public String toString()
	{
		String ts = "";
		if (isDefinitive)
			ts += defval;
		if (components != null)
		{
			Iterator<AsnOidComponent> i = components.iterator();
			while (i.hasNext())
			{
				ts += (i.next());
			}
		}
		return ts;
	}
}
