
package ink.jasn.ca.type.grammar;

public class NamedConstraint
{
    public String name;
    public boolean isConstraint;
    public boolean isPresentKw;
    public boolean isAbsentKw;
    public boolean isOptionalKw;
    public AsnConstraint constraint;

    public NamedConstraint()
    {
    }

    public String toString()
    {
        String ts = "";
        ts += name;
        if( isConstraint )
        {
            ts += constraint;
        }
        if( isPresentKw )
            ts += "\t" + "PRESENT";
        if( isAbsentKw )
            ts += "\t" + "ABSENT";
        if( isOptionalKw )
            ts += "\t" + "OPTIONAL";
        return ts;
    }
}
