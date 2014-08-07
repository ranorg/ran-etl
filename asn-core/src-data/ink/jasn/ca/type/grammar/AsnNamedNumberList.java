package ink.jasn.ca.type.grammar;

import ink.jasn.ca.type.extern.ASNNamedNumber;

import java.util.ArrayList;
import java.util.List;

// *********************************************
// Definition of Named Number List
// *********************************************
public class AsnNamedNumberList
{
	public List<ASNNamedNumber> namedNumbers;

	// Default Constructor
	public AsnNamedNumberList()
	{
		namedNumbers = new ArrayList<ASNNamedNumber>();
	}

	// Return the total number of elements in the list
	public int count()
	{
		return namedNumbers.size();
	}
}
