
package ink.jasn.ca.type.grammar;

import java.math.BigInteger;

// *********************************************
// Definition of SignedNumber
// *********************************************
public class AsnSignedNumber
{
    public boolean positive;
    public BigInteger num;

    // Default Constructor
    public AsnSignedNumber()
    {
        positive = true;
    }

    // toString() Method Definition
    public String toString()
    {
        String ts = "";
        if( num != null )
        {
            ts += (num);
        } else
        {
            ts += ("Signed Number is Null");
        }
        return ts;
    }
}
