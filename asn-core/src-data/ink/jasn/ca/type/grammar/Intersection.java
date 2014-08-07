package ink.jasn.ca.type.grammar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// -------------------------------------------------------
public class Intersection
{
	public boolean isInterSection;
	public boolean isExcept;
	public List cnsElemList;
	public List exceptCnsElem;

	// Default Constructor
	public Intersection()
	{
		cnsElemList = new ArrayList();
		exceptCnsElem = new ArrayList();
	}

	// toString Method
	public String toString()
	{
		String ts = "";
		Iterator e = cnsElemList.iterator();
		Iterator i = exceptCnsElem.iterator();
		while (e.hasNext())
		{
			ts += "\t" + e.next();
		}
		if (isExcept)
		{
			ts += "EXCEPT";
			while (i.hasNext())
			{
				ts += "\t" + i.next();
			}
		}
		return ts;
	}
}
