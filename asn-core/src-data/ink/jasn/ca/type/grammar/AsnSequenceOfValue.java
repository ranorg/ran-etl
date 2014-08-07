
package ink.jasn.ca.type.grammar;

import java.util.ArrayList;
import java.util.Iterator;

// *********************************************
// Definition of AsnSequenceOfValue
// *********************************************
public class AsnSequenceOfValue
{
    public boolean isValPresent;
    public ArrayList valueList;

    // Default Constructor
    public AsnSequenceOfValue()
    {
        valueList = new ArrayList();
    }

    // toString Method
    public String toString()
    {
        String ts = "";
        Iterator i = valueList.iterator();
        while( i.hasNext() )
        {
            ts += i.next();
        }
        return ts;
    }
}
