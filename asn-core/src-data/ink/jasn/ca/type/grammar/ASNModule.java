package ink.jasn.ca.type.grammar;

import ink.jasn.ca.type.base.ASNType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// *********************************************
// Definition of each Module
// *********************************************
public class ASNModule
{
	public AsnModuleIdentifier moduleIdentifier; // Name of Module
	public List<String> exportSymbolList;
	public List<String> importSymbolList;
	public List<SymbolsFromModule> importSymbolFromModuleList;
	public AsnTypes asnTypes;
	public List<ASNType> asnValues;
	public boolean tag;
	public boolean extensible;
	public String tagDefault;
	public boolean exported;
	public boolean imported;

	// Default Constructor
	public ASNModule()
	{
		exportSymbolList = new ArrayList<String>();
		importSymbolList = new ArrayList<String>();
		importSymbolFromModuleList = new ArrayList<SymbolsFromModule>();

		asnTypes = new AsnTypes();
		asnValues = new ArrayList<ASNType>();

		tagDefault = "";
	}

	// To String Method
	public String toString()
	{
		String ts = "";
		Iterator ii;
		ts += "MODULE NAME ::= \n";
		ts += moduleIdentifier + "\n";
		ts += "IMPORT SYMBOL LIST" + "\n";
		ii = importSymbolList.iterator();
		while (ii.hasNext())
		{
			ts += ii.next() + "\n";
		}
		ts += "IMPORT SYMBOLS FROM MODULE \n";
		ii = importSymbolFromModuleList.iterator();
		while (ii.hasNext())
		{
			ts += ii.next() + "\n";
		}
		ts += "EXPORT SYMBOL LIST \n";
		ii = exportSymbolList.iterator();
		while (ii.hasNext())
		{
			ts += ii.next() + "\n";
		}
		ts += "ASN TYPES LIST \n";
		ts += asnTypes + "\n";

		ts += "ASN VALUES LIST \n";
		ii = asnValues.iterator();
		while (ii.hasNext())
		{
			ts += ii.next() + "\n";
		}
		return ts;
	}
}
