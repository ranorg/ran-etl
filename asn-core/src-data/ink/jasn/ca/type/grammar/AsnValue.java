
package ink.jasn.ca.type.grammar;

import java.math.BigInteger;

// *********************************************
// Definition of Value
// *********************************************
public class AsnValue
{

    public String name;
    public String typeName;
    public boolean isTrueKW;
    public boolean isFalseKW;
    public boolean isNullKW;
    public boolean isPlusInfinity;
    public boolean isMinusInfinity;
    public boolean isCString;
    public boolean isSignedNumber;
    public boolean isDefinedValue;
    public boolean isBStrOrOstrValue;
    public boolean isCStrValue;
    public boolean isSequenceValue;
    public boolean isSequenceOfValue;
    public boolean isEnumIntValue;
    public boolean isAsnOIDValue;
    public boolean isChoiceValue;
    public String bStr, cStr, hStr, enumIntVal;
    public AsnBitOrOctetStringValue bStrValue;
    public AsnCharacterStringValue cStrValue;
    public AsnSequenceValue seqval;
    public AsnSequenceOfValue seqOfVal;
    public AsnChoiceValue chval;
    public AsnDefinedValue definedValue;
    //public AsnSignedNumber signedNumber;
    public BigInteger signedNumber;
    public AsnOidComponentList oidval;

    //Default Constructor	
    public AsnValue()
    {
    }

    // toString() Method Definition

    public String toString()
    {
        String ts = "";
        if( name != null && typeName != null )
        {
            ts += (name + "\t" + typeName);
        }
        if( isTrueKW )
        {
            ts += ("\tTRUE");
        } else if( isFalseKW )
        {
            ts += ("\tFALSE");
        } else if( isNullKW )
        {
            ts += ("\tNULL");
        } else if( isPlusInfinity )
        {
            ts += ("\tplusInfinity");
        } else if( isMinusInfinity )
        {
            ts += ("\tminusInfinity");
        } else if( isCString )
        {
            ts += ("\t" + cStr);
        } else if( isBStrOrOstrValue )
        {
            ts += ("\t" + bStrValue);
        } else if( isCStrValue )
        {
            ts += ("\t" + cStrValue);
        } else if( isSequenceValue )
        {
            ts += ("\t" + seqval);
        } else if( isChoiceValue )
        {
            ts += ("\t" + chval);
        } else if( isEnumIntValue )
        {
            ts += ("\t" + enumIntVal);
        } else if( isSignedNumber )
        {
            ts += signedNumber;
        } else if( isAsnOIDValue )
        {
            ts += oidval;
        } else if( isSequenceOfValue )
        {
            ts += seqOfVal;
        } else if( isDefinedValue )
        {
            ts += (definedValue);
        } else
        {
            ts += ("Unknown	Value");
        }
        return ts;
    }
}
