
package ink.jasn.ca.type.grammar;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNDefinedType;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.codec.ASNDataWriter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

// *********************************************
// Definition of MACRO OPERATION
// *********************************************
public class OperationMacro extends ASNType
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -39562601963453284L;
	public String name;
    public String argumentTypeIdentifier; // Argument NamedType identifier
    public Object argumentType; // Holds the argument Type
    public String argumentName; // Argument	Type Name 
    public String resultTypeIdentifier; // Result NamedType identifier 
    public Object resultType; // Holds the resultType
    public String resultName; // Result Type Name
    public boolean isArgumentName;
    public boolean isResult;
    public boolean isLinkedOperation;
    public boolean isResultName;
    public boolean isErrors;
    public ArrayList errorList;
    public ArrayList linkedOpList;

    // Default Constructors
    public OperationMacro()
    {
        errorList = new ArrayList();
        linkedOpList = new ArrayList();
    }

    // toString() definition

    public String toString()
    {
        String ts = "";
        ts += (name + "\t::=\t OPERATION");
        if( isArgumentName )
            ts += ("ARGUMENT\t\t" + argumentName);
        if( isResult )
        {
            ts += ("RESULT\t\t" + resultName);
        }
        if( isLinkedOperation )
        {
            ts += ("LINKED OPERATION\t");
            Iterator e = linkedOpList.iterator();
            if( e.hasNext() )
            {
                while( e.hasNext() )
                    ts += (e.next());
            }
            ts += ("}");
        }
        if( isErrors )
        {
            ts += ("ERRORS\t{");
            Iterator e = errorList.iterator();
            if( e.hasNext() )
            {
                while( e.hasNext() )
                    ts += e.next();
            }
            ts += ("}");
        }
        return ts;
    }

    // Get the first linked operationName
    public String get_firstLinkedOpName()
    {
        Object obj = linkedOpList.get( 0 );
        if( (AsnValue.class).isInstance( obj ) )
        {
            return "isValue";
        } else if( (ASNDefinedType.class).isInstance( obj ) )
        {
            return ((ASNDefinedType) obj).getType();
        } else
        {
            String nameoftype = null;
            try
            {
                Field nameField;
                Class c = obj.getClass();
                nameField = c.getField( "name" );
                nameoftype = (String) nameField.get( obj );
            } catch( Exception e )
            {
                e.printStackTrace( System.err );
            }
            return nameoftype;
        }
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
    public ASNType decide( Tag aTag) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }
}
