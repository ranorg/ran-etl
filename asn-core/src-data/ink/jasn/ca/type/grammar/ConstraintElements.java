
package ink.jasn.ca.type.grammar;

import java.util.ArrayList;
import java.util.Iterator;

// *********************************************
// Definition of Constraint_Elements
// *********************************************
public class ConstraintElements
{

    public boolean isValue;
    public boolean isValueRange;
    public boolean isSizeConstraint;
    public boolean isElementSetSpec;
    public boolean isIncludeType;
    public boolean isPatternValue;
    public boolean isWithComponent;
    public boolean isWithComponents;
    public boolean isMinKw;
    public boolean isMaxKw;
    public boolean isLEndLess;
    public boolean isUEndLess;
    public boolean isTypeConstraint;
    public boolean isAlphabetConstraint;

    public AsnConstraint constraint;
    public AsnValue value, lEndValue, uEndValue;
    public ElementSetSpec elespec;
    public Object type;
    public ArrayList typeConstraintList;

    public ConstraintElements()
    {
        typeConstraintList = new ArrayList();
    }

    //toString definition
    public String toString()
    {
        String ts = "";
        if( isValue )
        {
            ts += value;
        } else if( isValueRange )
        {
            ts += lEndValue + ".." + uEndValue;
        } else if( isSizeConstraint )
        {
            ts += constraint;
        } else if( isElementSetSpec )
        {
            ts += elespec;
        } else if( isPatternValue )
        {
            ts += "PATTERN" + value;
        } else if( isWithComponent )
        {
            ts += "WITH COMPONENT " + constraint;
        } else if( isWithComponents )
        {
            Iterator e = typeConstraintList.iterator();
            ts += "WITH COMPONENTS" + "\t";
            while( e.hasNext() )
            {
                ts += e.next();
            }
        } else if( isAlphabetConstraint )
        {
            ts += "FROM" + constraint;
        }
        return ts;
    }
}
