
package ink.jasn.ca.type.grammar;

import java.util.ArrayList;
import java.util.Iterator;

// Class CharDef
public class CharDef
{

    public boolean isCString;
    public boolean isTuple;
    public boolean isQuadruple;
    public boolean isDefinedValue;
    public AsnDefinedValue defval;
    public String cStr;
    public ArrayList tupleQuad;

    // Default Constructor
    public CharDef()
    {
        tupleQuad = new ArrayList();
    }

    public String toString()
    {
        String ts = "";
        if( isCString )
        {
            ts += ("\t" + cStr);
        } else if( isTuple || isQuadruple )
        {
            Iterator i = tupleQuad.iterator();
            while( i.hasNext() )
            {
                ts += i.next() + "\n";
            }
        } else if( isDefinedValue )
        {
            ts += ("\t" + defval);
        }
        return ts;
    }
}
