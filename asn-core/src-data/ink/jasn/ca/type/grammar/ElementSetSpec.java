
package ink.jasn.ca.type.grammar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// -------------------------------------------------------
public class ElementSetSpec
{
    public boolean isAllExcept;
    public ConstraintElements allExceptCnselem;
    public List<Intersection> intersectionList;

    // Default Constructor
    public ElementSetSpec()
    {
        intersectionList = new ArrayList<Intersection>();
    }

    // toString Method
    public String toString()
    {
        String ts = "";
        Iterator<Intersection> e = intersectionList.iterator();
        if( e != null )
        {
            while( e.hasNext() )
            {
                ts += e.next();
                ts += "|";
            }
        }
        if( isAllExcept )
        {
            ts += "ALL EXCEPT  " + allExceptCnselem;
        }
        return ts;
    }
}
