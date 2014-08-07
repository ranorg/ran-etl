package ink.jasn.ca.type.grammar;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//*********************************************
// Definition of AsnCharacterStringValue
//*********************************************
public class AsnCharacterStringValue
{

	public boolean isTuple;
	public boolean isQuadruple;
	public boolean isCharDefList;
	public String cStr;
	public List<CharDef> charDefsList;
	public List<BigInteger> tupleQuad;

	// Default Constructor
	public AsnCharacterStringValue()
	{
		charDefsList = new ArrayList<CharDef>();
		tupleQuad = new ArrayList<BigInteger>();
	}

	public String toString()
	{
		String ts = "";
		if (isTuple || isQuadruple)
		{
			Iterator<BigInteger> i = tupleQuad.iterator();
			while (i.hasNext())
			{
				ts += i.next() + "\n";
			}
		}
		else if (isCharDefList)
		{
			Iterator<CharDef> i = charDefsList.iterator();
			while (i.hasNext())
			{
				ts += i.next();
			}
		}
		return ts;
	}
}