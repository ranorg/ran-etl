package ink.jasn.ca.type.grammar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//*********************************************
// Definition of AsnBitStringOrOctetStringValue
//*********************************************
public class AsnBitOrOctetStringValue
{
	public boolean isHString;
	public boolean isBString;
	public String bhStr;
	public List<String> idlist;

	// Default Constructor
	public AsnBitOrOctetStringValue()
	{
		idlist = new ArrayList<String>();
	}

	public String toString()
	{
		String ts = "";
		if (isHString || isBString)
			ts += bhStr;
		else
		{
			if (idlist != null)
			{
				Iterator<String> e = idlist.iterator();
				while (e.hasNext())
				{
					ts += e.next();
				}
			}
		}
		return ts;
	}
}
