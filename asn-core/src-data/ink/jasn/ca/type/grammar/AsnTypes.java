
package ink.jasn.ca.type.grammar;

import ink.jasn.ca.basis.central.AbstractSyntaxTreeNode;
import ink.jasn.ca.type.base.ASNType;

import java.util.ArrayList;
import java.util.List;

// *********************************************
// Definition of Basic Type
// *********************************************
public class AsnTypes
{
    public List<AbstractSyntaxTreeNode> typeList;
    
    /*
    public List<ASNType> anys;
    public List<ASNType> bitStrings;
    public List<ASNType> booleans;
    public List<ASNType> characterStrings;
    public List<ASNType> choices;
    public List<ASNType> enums;
    public List<ASNType> integers;
    public List<ASNType> nulls;
    public List<ASNType> objectIdentifiers;
    public List<ASNType> octetStrings;
    public List<ASNType> reals;
    public List<ASNType> sequenceSets;
    public List<ASNType> sequenceSetsOf;
    public List<ASNType> externals;
    public List<ASNType> relativeOids;
    public List<ASNType> selections;
    public List<ASNType> embeddeds;
    public List<ASNType> taggeds;
    public List<ASNType> defineds;
    public List<ASNType> macroOperations;
    public List<ASNType> macroErrors;
    public List<ASNType> macroObjectTypes;*/


    // Default Constructor
    public AsnTypes()
    {
       /* anys = new ArrayList<ASNType>();
        bitStrings = new ArrayList<ASNType>();
        booleans = new ArrayList<ASNType>();
        characterStrings = new ArrayList<ASNType>();
        choices = new ArrayList<ASNType>();
        enums = new ArrayList<ASNType>();
        integers = new ArrayList<ASNType>();
        nulls = new ArrayList<ASNType>();
        objectIdentifiers = new ArrayList<ASNType>();
        octetStrings = new ArrayList<ASNType>();
        reals = new ArrayList<ASNType>();
        sequenceSets = new ArrayList<ASNType>();
        sequenceSetsOf = new ArrayList<ASNType>();
        externals = new ArrayList<ASNType>();
        relativeOids = new ArrayList<ASNType>();
        selections = new ArrayList<ASNType>();
        taggeds = new ArrayList<ASNType>();
        defineds = new ArrayList<ASNType>();
        macroOperations = new ArrayList<ASNType>();
        macroErrors = new ArrayList<ASNType>();
        macroObjectTypes = new ArrayList<ASNType>();
        
        */
        typeList=new ArrayList<AbstractSyntaxTreeNode>();
    }
    
    public void add(ASNType asnType)
    {
        typeList.add( asnType );
    }

    // toString Method
    public String toString()
    {
        String ts = "";
        // Define the method
        return ts;
    }
}
