package ink.jasn.ca.type.grammar;

import ink.jasn.ca.type.base.Tag;

//*********************************************
// Definition of Element Type
//*********************************************
public class AsnElementType {

    public boolean	isOptional;			
    public boolean	isDefault;
    public boolean	isComponentsOf;		
    public boolean	isTagDefault;
    public boolean	isTag;				
    public boolean	isDefinedType;			// Element type	is defined Type
    public Tag tag;					
    public AsnValue value;
    public String typeTagDefault;	
	public String name;			// type
    public Object typeReference;		// type	Reference
    public String typeName;			// If defined type then	typeName
	
	// Default Constructor
    public AsnElementType() {
		isOptional = false;
	}
	
	// toString() Method Definition
	
	public String toString() {
		String ts =	"";
		if(isComponentsOf){
				ts += ("\tCOMPONENTS	OF\t");
		} else {
			ts += (name);
			if(isTag){		
				ts += ("\t");
				ts += (tag);  
			}
			if(isTagDefault){		
				ts += ("\t");
				ts += (typeTagDefault);		  
			}
		}
		if(isDefinedType){				
			ts += ("\t");
			ts += (typeName);
		} else {
			ts += (typeReference.getClass().getName());
		}
		if(isOptional){
				ts += ("\tOPTIONAL");
		}
		if(isDefault){
				ts += ("\tDEFAULT\t");
				ts += (value);
		}
	return ts;
	}		
}
