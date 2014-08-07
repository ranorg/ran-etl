package ink.jasn.ca.type.grammar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// *********************************************
// Definition of Symbols From Module List
// *********************************************
public class SymbolsFromModule
{
	public List<String> symbolList;
	public String modref;
	public boolean isOidValue;
	public boolean isDefinedValue;
	public AsnOidComponentList cmplist;
	public AsnDefinedValue defval;

	// Default Constructor
	public SymbolsFromModule()
	{
		symbolList = new ArrayList<String>();
	}

	// toString Method
	public String toString()
	{
		String ts = "Following SYMBOLS ::\n";
		Iterator<String> s = symbolList.iterator();
		if (s != null)
		{
			while (s.hasNext())
			{
				ts += s.next() + "\n";
			}
		}
		ts += "ARE IMPORTED FROM \n";
		ts += modref;
		if (isOidValue)
			ts += cmplist;
		if (isDefinedValue)
			ts += defval;

		return ts;
	}
}
