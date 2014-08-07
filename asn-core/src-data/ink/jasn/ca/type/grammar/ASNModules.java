package ink.jasn.ca.type.grammar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// To hold all the parsed Modules
public class ASNModules
{
	public List<ASNModule> module_list;

	// Default Constructor
	public ASNModules()
	{
		module_list = new ArrayList<ASNModule>();
	}

	// toString Method
	public String toString()
	{
		String ts = "";
		Iterator<ASNModule> i = module_list.iterator();
		while (i.hasNext())
		{
			ts += i.next();
		}
		return ts;
	}

	public void add(ASNModule module)
	{
		module_list.add(module);
	}
}
