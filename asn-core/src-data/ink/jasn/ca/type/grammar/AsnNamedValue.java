
package ink.jasn.ca.type.grammar;

// *********************************************
// Definition of NamedValue
// *********************************************
public class AsnNamedValue
{
    public String name;
    public AsnValue value;

    // Default Constructor
    public AsnNamedValue()
    {
    }

    public String toString()
    {
        String ts = "";
        ts += name;
        ts += ("\t" + value);
        return ts;
    }

    public String toString( boolean name )
    {
        String ts = "";
        if( name )
        {
            ts += name;
        } else
        {
            ts += value;
        }
        return ts;
    }
}
