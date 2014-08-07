
package ink.jasn.ca.type.grammar;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

// *********************************************
// Definition of Sequence & Set OF Elements
// *********************************************
public class AsnSequenceOf extends ASNType
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7268362531016151465L;
	public String name; // Refers	to assignment name
    public AsnConstraint constraint;
    public Object typeReference; // Refers	to typeReference after OF KW
    public boolean isSequenceOf; // Differntiates between SEQUENCE	OF and SET OF types
    public boolean isDefinedType;
    public boolean isSizeConstraint;
    public String typeName; // Name	of the defined type
    public final String BUILTINTYPE = "SEQUENCE OF";
    public final String BUILTINTYPE1 = "SET OF";

    // Default Constructor
    public AsnSequenceOf()
    {
        super("");
        name = "";
        constraint = null;
        typeReference = null;
        isSequenceOf = false;
        isDefinedType = false;
        isSizeConstraint = false;
        typeName = "";

    }

    // toString	definition
    public String toString()
    {
        String ts = "";
        ts += name + "\t::=\t";
        if( isSequenceOf )
        {
            ts += ("SEQUENCE\t");
            if( constraint != null )
                ts += (constraint);
            ts += ("\tOF\t");
        } else
        {
            ts += ("SET\t");
            if( constraint != null )
                ts += (constraint);
            ts += ("\tOF\t");
        }
        if( isDefinedType )
        {
            ts += (typeName);
        } else
        {
            ts += (typeReference.getClass().getName()); // Print builtinType Class Name 
        }
        return ts;
    }

    @Override
    public CadedDataTreeNode<Object> decode( ASNDataReader asnDataReader ) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CadedDataTreeNode<Object> encode( ASNDataWriter asnDataWriter ) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean checkCompleteness()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ASNType decide(Tag aTag ) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }
}
