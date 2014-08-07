
package ink.jasn.ca.type.grammar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// *********************************************
// Definition of AsnSequenceValue
// *********************************************
public class AsnSequenceValue
{
    public boolean isValPresent;
    public List<AsnNamedValue> namedValueList;

    // Default Constructor
    public AsnSequenceValue()
    {
        namedValueList = new ArrayList<AsnNamedValue>();
    }
    
    public void add(AsnNamedValue asnNamedValue)
    {
        namedValueList.add( asnNamedValue );
    }

    // toString Method
    public String toString()
    {
        String ts = "";
        Iterator<AsnNamedValue> i = namedValueList.iterator();
        while( i.hasNext() )
        {
            ts += i.next();
        }
        return ts;
    }
}
