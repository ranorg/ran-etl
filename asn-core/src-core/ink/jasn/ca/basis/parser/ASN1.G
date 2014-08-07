header 
{
	package ink.jasn.ca.basis.parser;
	import ink.jasn.ca.type.grammar.*;
	import ink.jasn.ca.type.impl.*;
	import ink.jasn.ca.type.base.*;
	import ink.jasn.ca.type.extern.*;
	import ink.jasn.ca.basis.central.AbstractSyntaxTreeNode;
	import java.lang.* ;
	import java.math.*;
	import java.util.*;
}

//	Creation of ASN.1 grammar for ANTLR	V2.7.1
// ===================================================
//		  TOKENS FOR ASN.1 LEXER DEFINITIONS
// ===================================================

class ASNLexer extends Lexer;

options	
{
	k =	11;
	exportVocab=ASN;
	charVocabulary = '\3'..'\377';
	caseSensitive=true;
	testLiterals = true;
	codeGenMakeSwitchThreshold = 2;  // Some optimizations
	codeGenBitsetTestThreshold = 3;
}

//	ASN1 Tokens 
tokens 
{
    ABSENT_KW				=	"ABSENT"			;
    ABSTRACT_SYNTAX_KW		=	"ABSTRACT-SYNTAX"	;
    ALL_KW					=	"ALL"				;
    ANY_KW					=	"ANY"				;
    ARGUMENT_KW				=	"ARGUMENT"			;
    APPLICATION_KW			=	"APPLICATION"		;
    AUTOMATIC_KW			=	"AUTOMATIC"			;
    BASED_NUM_KW			=	"BASEDNUM"			;
    BEGIN_KW				=	"BEGIN"				;
    BIT_KW					=	"BIT"				;
    BMP_STRING_KW			=	"BMPString"			;
    BOOLEAN_KW				=	"BOOLEAN"			;
    BY_KW					=	"BY"				;
    CHARACTER_KW			=	"CHARACTER"			;
    CHOICE_KW				=	"CHOICE"			;
    CLASS_KW				=	"CLASS"				;
    COMPONENTS_KW			=	"COMPONENTS"		;
    COMPONENT_KW			=	"COMPONENT"			;
    CONSTRAINED_KW			=	"CONSTRAINED"		;
    DEFAULT_KW				=	"DEFAULT"			;
    DEFINED_KW				=	"DEFINED"			;
    DEFINITIONS_KW			=	"DEFINITIONS"		;
    EMBEDDED_KW				=	"EMBEDDED"			;
    END_KW					=	"END"				;
    ENUMERATED_KW			=	"ENUMERATED"		;
    ERRORS_KW				=	"ERRORS"			;
    EXCEPT_KW				=	"EXCEPT"			;
    EXPLICIT_KW				=	"EXPLICIT"			;
    EXPORTS_KW				=	"EXPORTS"			;
    EXTENSIBILITY_KW		=	"EXTENSIBILITY"		;
    EXTERNAL_KW				=	"EXTERNAL"			;
    FALSE_KW				=	"FALSE"				;
    FROM_KW					=	"FROM"				;
    GENERALIZED_TIME_KW		=	"GeneralizedTime"	;
    GENERAL_STR_KW			=	"GeneralString"		;
    GRAPHIC_STR_KW			=	"GraphicString"		;
    IA5_STRING_KW			=	"IA5String"			;
    IDENTIFIER_KW			=	"IDENTIFIER"		;
    IMPLICIT_KW				=	"IMPLICIT"			;
    IMPLIED_KW				=	"IMPLIED"			;
    IMPORTS_KW				=	"IMPORTS"			;
    INCLUDES_KW				=	"INCLUDES"			;
    INSTANCE_KW				=	"INSTANCE"			;
    INTEGER_KW				=	"INTEGER"			;
    INTERSECTION_KW			=	"INTERSECTION"		;
    ISO646STRING_KW			=	"ISO646String"		;
    LINKED_KW				=	"LINKED"			;
    MAX_KW					=	"MAX"				;
    MINUS_INFINITY_KW		=	"MINUSINFINITY"		;
    MIN_KW					=	"MIN"				;
    NULL_KW					=	"NULL"				;
    NUMERIC_STR_KW			=	"NumericString"		;
    OBJECT_DESCRIPTOR_KW 	=	"ObjectDescriptor"	;
    OBJECT_KW				=	"OBJECT"			;
    OCTET_KW				=	"OCTET"				;
    OF_KW					=	"OF"				;
    OID_KW					=	"OID"				;
    OPTIONAL_KW				=	"OPTIONAL"			;
    PARAMETER_KW			=	"PARAMETER"			;
    PDV_KW					=	"PDV"				;
    PLUS_INFINITY_KW		=	"PLUSINFINITY"		;
    PRESENT_KW				=	"PRESENT"			;
    PRINTABLE_STR_KW		=	"PrintableString"	;
    PRIVATE_KW				=	"PRIVATE"			;
    REAL_KW					=	"REAL"				;
    RELATIVE_KW				=	"RELATIVE"			;
    RESULT_KW				=	"RESULT"			;
    SEQUENCE_KW				=	"SEQUENCE"			;
    SET_KW					=	"SET"				;
    SIZE_KW					=	"SIZE"				;
    STRING_KW				=	"STRING"			;
    TAGS_KW					=	"TAGS"				;
    TELETEX_STR_KW			=	"TeletexString"		;
    TRUE_KW					=	"TRUE"				;
    TYPE_IDENTIFIER_KW		=	"TYPE-IDENTIFIER"	;
    UNION_KW				=	"UNION"				;
    UNIQUE_KW				=	"UNIQUE"			;
    UNIVERSAL_KW			=	"UNIVERSAL"			;
    UNIVERSAL_STR_KW		=	"UniversalString"	;
    UTC_TIME_KW				=	"UTCTime"			;
    UTF8_STR_KW				=	"UTF8String"		;
    VIDEOTEX_STR_KW			=	"VideotexString"	;
    VISIBLE_STR_KW			=	"VisibleString"		;
    WITH_KW					=	"WITH"				;
}

// Operators

ASSIGN_OP			:	"::="	;
BAR					:	'|'		;
COLON				:	':'		;
COMMA				:	','		;
COMMENT				:	"--"	;
DOT					:	'.'		;
DOTDOT				:	".."	;
ELLIPSIS			:	"..."	;
EXCLAMATION			:	'!'		;
INTERSECTION		:	'^'		;
LESS				:	'<'		;
L_BRACE				:	'{'		;
L_BRACKET			:	'['		;
L_PAREN				:	'('		;
MINUS				:	'-'		;
PLUS				:	'+'		;
R_BRACE				:	'}'		;
R_BRACKET			:	']'		;
R_PAREN				:	')'		;
SEMI				:	';'		;
SINGLE_QUOTE		:	"'"		;
CHARB				:	"'B"	;
CHARH				:	"'H"	;

// Whitespace -- ignored
WS			
	:	(	' ' | '\t' | '\f'	|	(	options {generateAmbigWarnings=false;}
	:	"\r\n"		{ newline(); }// DOS
	|	'\r'   		{ newline(); }// Macintosh
	|	'\n'		{ newline(); }// Unix 
	))+
	{$setType(Token.SKIP); }
	;

// Single-line comments
SL_COMMENT
	: (options {warnWhenFollowAmbig=false;} 
	: COMMENT (  { LA(2)!='-' }? '-' |	~('\n'|'\r'))*	( (('\r')? '\n') { newline(); }| COMMENT) )
		{$setType(Token.SKIP);  }
	;

// Multi-line comments	
ML_COMMENT
	:	"/*"  ( options {greedy=false;}:.)* "*/" 			  {$setType(Token.SKIP);}
	|	'/' '\\' '\n' '*'  ( options {greedy=false;}:.)* "*/" {$setType(Token.SKIP);}
	;
	
NUMBER	:	('0'..'9')+ ;

UPPER	
options {testLiterals = false;}
	:   ('A'..'Z') 
		(options {warnWhenFollowAmbig = false;}
	:	( 'a'..'z' | 'A'..'Z' |'-' |'_'| '0'..'9' ))* 	;

LOWER
options {testLiterals = false;}
	:	('a'..'z') 
		(options {warnWhenFollowAmbig = false;}
	:	( 'a'..'z' | 'A'..'Z' |'-' |'_' |'0'..'9' ))* 	;


protected
BDIG		: ('0'|'1') ;

protected
HDIG		:	(options {warnWhenFollowAmbig = false;} :('0'..'9') )
			|	('A'..'F')
			|	('a'..'f')
			;

B_OR_H_STRING
	:	(options {warnWhenFollowAmbig = false;} 
		:(B_STRING)=>B_STRING {$setType(B_STRING);}
		| H_STRING {$setType(H_STRING);})
	;

protected
B_STRING 	: 	SINGLE_QUOTE (BDIG)+ SINGLE_QUOTE 'B'  ;

protected
H_STRING 	: 	SINGLE_QUOTE (HDIG)+ SINGLE_QUOTE 'H'  ;

			
C_STRING 	: 	'"'	(UPPER | LOWER)*  '"' ;


//=========================================================================
//-----------------		 PARSER DEFINITIONS		---------------------------
//=========================================================================
class ASNParser	extends	Parser;

options	
{
	exportVocab=ASN;
	k=3;
}

// Grammar Definitions
module_definition[ASNModule module] 	
{
	AsnModuleIdentifier mid;
	String s ;
	
}
	:	(mid = module_identifier
		{ module.moduleIdentifier = mid; 	})
		DEFINITIONS_KW 
		(( e:EXPLICIT_KW {module.tagDefault = e.getText();}
		  |i:IMPLICIT_KW {module.tagDefault = i.getText();}
		  |a:AUTOMATIC_KW {module.tagDefault = a.getText();}
		 ) TAGS_KW {module.tag = true;} |) 
		(EXTENSIBILITY_KW IMPLIED_KW {module.extensible=true;} | )
		ASSIGN_OP 
		BEGIN_KW 
		module_body[module] 
		END_KW
	; 

module_identifier  returns [ AsnModuleIdentifier mid ]
{mid = new AsnModuleIdentifier();
AsnOidComponentList cmplst; }
	:	(( md:UPPER { mid.name = md.getText();}) 
		 ((cmplst = obj_id_comp_lst { mid.componentList = cmplst; })|) 
		)
	;

obj_id_comp_lst	returns [AsnOidComponentList oidcmplst]
{
	oidcmplst = new AsnOidComponentList();
	AsnOidComponent oidcmp; AsnDefinedValue defval; 
}
	:	L_BRACE 
		(defval = defined_value {oidcmplst.isDefinitive=true;oidcmplst.defval=defval;})?
		(oidcmp = obj_id_component {oidcmplst.components.add(oidcmp);})+  
		R_BRACE 
	;


obj_id_component returns [AsnOidComponent oidcmp ]
{
	oidcmp = new AsnOidComponent(); 
	AsnDefinedValue defval;
	String s,n =""; 
}
	: 	((num:NUMBER {s=num.getText();oidcmp.num = new Integer(s); oidcmp.numberForm=true;})
	|	(LOWER (L_PAREN NUMBER R_PAREN)?)=>((lid:LOWER {oidcmp.name = lid.getText();oidcmp.nameForm=true;}) 
		( L_PAREN 
		 (num1:NUMBER {n=num1.getText(); oidcmp.num = new Integer(n);oidcmp.nameAndNumberForm=true;})
		R_PAREN ) ? )
	|	(defined_value)=>(defval = defined_value {oidcmp.isDefinedValue=true;oidcmp.defval=defval;}))
	;

		
tag_default returns [boolean s]
{ 
	s = false; 
}
	:	(tg:EXPLICIT_KW   {s=true;})	//{s = tg.getText();})
	|	(tg1:IMPLICIT_KW ) 				//{s = tg1.getText();})
	|	(tg2:AUTOMATIC_KW {s=true;})	//{s = tg2.getText();})
	;

		
module_body[ASNModule module] 
	:	(exports[module]|) (imports[module]|) ((assignment[module])+ |)
	;


exports[ASNModule module]		
{
	String s; 
	ArrayList syml = new ArrayList();
}
	:	EXPORTS_KW {module.exported=true;} 
		((syml = symbol_list {module.exportSymbolList = syml;} |)
		|ALL_KW) 
		SEMI
	;	
	
		
imports[ASNModule module]
	:	(IMPORTS_KW (((symbols_from_module[module])+)|)  SEMI)
		{module.imported=true;}
	;

symbols_from_module[ASNModule module]
{
	SymbolsFromModule sym = new SymbolsFromModule();
    String s = "" ; 
    AsnModuleIdentifier mid; 
    AsnDefinedValue defval;
    ArrayList arl; 
    AsnOidComponentList cmplist;
}

	:	((arl= symbol_list {sym.symbolList = arl;}) FROM_KW	
	    (up:UPPER {sym.modref = up.getText();}
	     (cmplist = obj_id_comp_lst {sym.isOidValue=true;sym.cmplist = cmplist;}
	     |(defined_value)=>(defval = defined_value {sym.isDefinedValue=true;sym.defval=defval;})|)))
	    {module.importSymbolFromModuleList.add(sym);}
	;
	
symbol_list returns[ArrayList symlist]
{
	symlist = new ArrayList(); 
	String s=""; 
}
	:	((s = symbol {symlist.add(s); })
		(COMMA (s = symbol {symlist.add(s); }))*) 
	; 

symbol returns [String s]
{
	s="";
}			
	:	up:UPPER  { s = up.getText();}
	|	lid:LOWER { s = lid.getText();}
	|	s=macroName   	//To solve the matching of Macro Name with Keyword
	;

macroName returns [String s]
{
	s="";
}
	:	"OPERATION_KW" 						{ s = "OPERATION";}
	|	"ERROR_KW"							{ s = "ERROR";}
 	|	"BIND"								{ s = "BIND";}
 	|	"UNBIND"							{ s = "UNBIND";}
 	|	"APPLICATION-SERVICE-ELEMENT"		{ s = "APPLICATION-SERVICE-ELEMENT";}
 	|	"APPLICATION-CONTEXT"				{ s = "APPLICATION-CONTEXT";}
 	|	"EXTENSION"							{ s = "EXTENSION";}
 	|	"EXTENSIONS"						{ s = "EXTENSIONS";}
 	|	"EXTENSION-ATTRIBUTE"				{ s = "EXTENSION-ATTRIBUTE";}
 	|	"TOKEN"								{ s = "TOKEN";}
 	|	"TOKEN-DATA"						{ s = "TOKEN-DATA";}
 	|	"SECURITY-CATEGORY"					{ s = "SECURITY-CATEGORY";}
 	|	"OBJECT"							{ s = "OBJECT";}
 	|	"PORT"								{ s = "PORT";}
 	|	"REFINE"							{ s = "REFINE";}
 	|	"ABSTRACT-BIND"						{ s = "ABSTRACT-BIND";}
 	|	"ABSTRACT-UNBIND"					{ s = "ABSTRACT-UNBIND";}
 	|	"ABSTRACT-OPERATION"				{ s = "ABSTRACT-OPERATION";}
 	|	"ABSTRACT-ERROR"					{ s = "ABSTRACT-ERROR";}
 	|	"ALGORITHM"							{ s = "ALGORITHM";}
 	|	"ENCRYPTED"							{ s = "ENCRYPTED";}
 	|	"SIGNED"							{ s = "SIGNED";}
 	|	"SIGNATURE"							{ s = "SIGNATURE";}
 	|	"PROTECTED"							{ s = "PROTECTED";}
 	|	"OBJECT-TYPE"						{ s = "OBJECT-TYPE";}
 	;

assignment[ASNModule module]	
{
	ASNType asnType ; 
	ASNType asnTypeV; 
	AsnValue val;	
}
:	//Type assignment definition 
	(up:UPPER ASSIGN_OP	(asnType=type) 
    	{ 
        	asnType.setName(up.getText());
        	module.asnTypes.add(asnType);
    	}
	)
	|// Value Assignment definition	
	(lo:LOWER (asnTypeV = type ) ASSIGN_OP (val = value) 
		{
			asnTypeV.setName(lo.getText());
			module.asnTypes.add(asnTypeV);
			//module.asnValues.add(asnTypeV);
		}
	)
	|// Definition of Macro type. Consume the definitions . No Actions
	(UPPER "MACRO" ASSIGN_OP BEGIN_KW (~(END_KW) )* END_KW)=>  UPPER "MACRO" ASSIGN_OP BEGIN_KW (~(END_KW))* END_KW;


/*=========================== TYPES DEFINITION ===========================*/
type returns [ASNType asnType]		
{
	asnType = null;
}
	:	(asnType = built_in_type)		// Builtin Type
	|	(asnType = defined_type)		// Referenced Type
	|	(asnType = selection_type ) 	// Referenced Type	// Grammar ok Frames to be created
	|	(asnType = macros_type)
	;

built_in_type returns [ASNType asnType]
{
	asnType = null;
}
	:	(asnType = any_type)
	|	(asnType = bit_string_type)
	|	(asnType = boolean_type )
	|	(asnType = character_str_type)
	|	(asnType = choice_type)
	|	(asnType = embedded_type) EMBEDDED_KW  PDV_KW		
	|	(asnType = enum_type) 
	|	(asnType = external_type) 
	|	(asnType = integer_type )
	|	(asnType = null_type )
	|	(asnType = object_identifier_type)
	|	(asnType = octetString_type)
	|	(asnType = real_type )
	|	(asnType = relativeOid_type)
	|	(asnType = sequence_type)
	|	(asnType = sequenceof_type)
	|	(asnType = set_type)
	|	(asnType = setof_type)
	|	(asnType = tagged_type)
	;
			
any_type returns [ASNType asnType]
{
	asnType = null;
	ASNAny any = new ASNAny();
}
	: 	(	ANY_KW  
			( DEFINED_KW BY_KW {any.isDefinedBy = true ;} 
				lid:LOWER { any.definedByType = lid.getText();}
			)? 
		)
		{asnType = any ;  any = null;}
	;
	
bit_string_type	returns [ASNType asnType]
{
	ASNBitString bstr = new ASNBitString(); 
	//AsnNamedNumberList nnlst ;
	ASNNamedNumber nnum; 
	AsnConstraint cnstrnt;
	asnType = null;
}
	:	(BIT_KW STRING_KW (nnum =namedNumber_map { bstr.asnNamedNumber = nnum;})? 
		(cnstrnt = constraint { bstr.constraint = cnstrnt;} )? )
		{asnType=bstr; nnum = null ; cnstrnt = null;}
	;

// Includes Useful types as well
character_str_type returns [ASNType asnType]
{
	ASNCharacterString cstr=null;
	asnType=null;
	AsnConstraint cnstrnt=null; 
}
	:	((CHARACTER_KW STRING_KW )//{cstr.isUCSType = true;})
	|	(cstr = character_specific_type) //{cstr.setType(s);} 
		(cnstrnt = constraint)? )
		{cstr.constraint = cnstrnt; asnType=cstr; cnstrnt = null; cstr = null;}
	;
		
character_specific_type returns [ASNCharacterString asnType]
{
	asnType = null;
}
	:	(s1:BMP_STR_KW 			{asnType=new ASNBMPString();})//s = s1.getText();})
	|	(s2:GENERALIZED_TIME_KW	{asnType=new ASNGeneralizedTime();})//s = s2.getText();})
	|	(s3:GENERAL_STR_KW		{asnType=new ASNGeneralString();})//s = s3.getText();})
	|	(s4:GRAPHIC_STR_KW		{asnType=new ASNGraphicString();})//s = s4.getText();})
	|	(s5:IA5_STRING_KW		{asnType=new ASNIA5String();})//s = s5.getText();})
	|	(s6:ISO646_STR_KW		{asnType=new ASNISO646String();})//s = s6.getText();})
	|	(s7:NUMERIC_STR_KW		{asnType=new ASNNumericString();})//s = s7.getText();})
	|	(s8:PRINTABLE_STR_KW	{asnType=new ASNPrintableString();})//s = s8.getText();})
	|	(s9:TELETEX_STR_KW		{asnType=new ASNTeletexString();})//s = s9.getText();})
	|	(s10:T61_STR_KW			{asnType=new ASNT61String();})//s = s10.getText();})
	|	(s11:UNIVERSAL_STR_KW	{asnType=new ASNUniversalString();})//s = s11.getText();})
	|	(s12:UTF8_STR_KW		{asnType=new ASNUTF8String();})//s = s12.getText();})
	|	(s13:UTC_TIME_KW		{asnType=new ASNUTCTime();})//s = s13.getText();})
	|	(s14:VIDEOTEX_STR_KW	{asnType=new ASNVideotexString();})//s = s14.getText();})
	|	(s15:VISIBLE_STR_KW		{asnType=new ASNVisibleString();})//s = s15.getText();})
	;		
	
	
boolean_type returns [ASNType asnType]
{
	asnType = null;
}
	: BOOLEAN_KW 
	  {asnType = new ASNBoolean();}
	;
				
choice_type	returns [ASNType asnType]
{
	ASNChoice ch = new ASNChoice(); 
	List<ModelSyntaxTreeNode> eltplst ; 
	asnType = null;
}
	: (	CHOICE_KW L_BRACE (eltplst = elementType_list) R_BRACE ) 
	{asnType = ch; ch.addChildren(eltplst); eltplst = null; ch = null;}
	;

embedded_type returns [ASNType asnType]
{
	asnType = null;
}
	:	(EMBEDDED_KW  PDV_KW)
		{asnType = new AsnEmbedded();}
	;
	
	
enum_type returns [ASNType asnType]
{
	ASNEnumerated enumtyp = new ASNEnumerated() ;
    //AsnNamedNumberList nnlst; 
    ASNNamedNumber nnum;
	asnType = null;
}
	: ( ENUMERATED_KW (nnum = namedNumber_map { enumtyp.asnNamedNumber = nnum;}) )
	  {asnType = enumtyp ; enumtyp=null;}	
	;
		
external_type returns [ASNType asnType]
{
	asnType = null; 
}
	: EXTERNAL_KW {asnType = new AsnExternal();}
	;

integer_type returns [ASNType asnType]	
{
	ASNInteger intgr = new ASNInteger();
	//AsnNamedNumberList numlst; 
	ASNNamedNumber nnum;
	AsnConstraint cnstrnt; 
	asnType=null;
}
	: (	INTEGER_KW (nnum = namedNumber_map {intgr.asnNamedNumber = nnum;}
		| cnstrnt = constraint {intgr.constraint = cnstrnt;})? )
		{asnType = intgr ; nnum = null ; cnstrnt = null; intgr = null; }
	;
		
null_type returns [ASNType asnType]
{
	ASNNull nll = new ASNNull(); 
	asnType = null;
}
	: NULL_KW
	  {asnType = nll; nll = null ; }
	;

object_identifier_type returns [ASNType asnType]
{ 
	asnType = null;
}
	: OBJECT_KW IDENTIFIER_KW {asnType = new ASNObjectIdentifier();}	
	; 
	
octetString_type returns [ASNType asnType]
{
	ASNOctetString oct = new ASNOctetString();
	AsnConstraint cnstrnt ; asnType = null;
}
	: (	OCTET_KW STRING_KW (cnstrnt = constraint{oct.constraint = cnstrnt;})? )
		{asnType = oct ; cnstrnt = null;}
	;

real_type returns [ASNType asnType]
{
	ASNReal rl = new ASNReal();
	asnType = null;
}
	: REAL_KW  {asnType = rl ; rl = null;}		
	;

relativeOid_type returns [ASNType asnType]
{
	asnType = null; 
}
	: RELATIVE_KW MINUS OID_KW {asnType = new ASNRelativeObjectIdentifier();}
	;
		
sequence_type returns [ASNType asnType]
{
	ASNSequence seq = new ASNSequence();
    List<ModelSyntaxTreeNode> eltplist=null ; 
    asnType = null;
}
	:  ( SEQUENCE_KW {seq.isSequence = true;} 
	    	L_BRACE 
	   			(eltplist = elementType_list )? 
	    	R_BRACE 
	    )
		{asnType = seq ; asnType.addChildren(eltplist);eltplist = null; seq =null; }
	;
		
sequenceof_type returns [ASNType asnType]
{
	ASNSequenceOf seqof = new ASNSequenceOf();
    AsnConstraint cns; 
    asnType = null; 
    ASNType obj1 ; 
    String s ;
}
	:  ( SEQUENCE_KW //{seqof.isSequenceOf = true;}
	        (SIZE_KW /*{seqof.isSizeConstraint=true;}*/(cns = constraint {seqof.constraint = cns ;}))? OF_KW 
			( obj1 = type 
    			{	if((ASNDefinedType.class).isInstance(obj1))
    				{
    		  			//seqof.isDefinedType=true;
    					seqof.setReference(obj1.getReference()) ; 
    				}
    				else
    				{	
    					seqof.addChild(obj1) ; 
    				}
    			}
			) 
		)
		{asnType = seqof;  cns = null; seqof=null;}		
	;
	
	
set_type returns [ASNType asnType]
{
	ASNSet set = new ASNSet();
	List<ModelSyntaxTreeNode> eltplist =null;
	asnType = null;
}
	:  ( SET_KW L_BRACE (eltplist =  elementType_list)? R_BRACE )
		{asnType = set ;asnType.addChildren(eltplist); eltplist = null; set = null;}
	;
	
		
setof_type	returns [ASNType asnType]
{
	ASNSetOf setof = new ASNSetOf();
	AsnConstraint cns = null; 
	asnType = null;
	ASNType obj1 = null; 
	String s;
}
	:	(SET_KW	
		(SIZE_KW /*{seqof.isSizeConstraint=true;}*/(cns = constraint {setof.constraint = cns ;}))? OF_KW 
		(obj1 = type 
		{	if((ASNDefinedType.class).isInstance(obj1)){
		  		//seqof.isDefinedType=true;
				setof.setReference(obj1.getReference()); 
			}
			else{
				setof.addChild( obj1);
			} 
		}) )
		{asnType = setof; cns = null; obj1=null; setof=null;} 		
	;


tagged_type returns [ASNType asnType]
{//AsnTaggedType tgtyp = new AsnTaggedType();
Tag t; boolean s=false; asnType = null;}
	:	((t = tag ) 
		(s = tag_default )? //{ tgtyp.tagDefault = s ;})?
		(asnType = type { t.setExplicit(s) ; asnType.setTag(t) ;}
		
		/*{	if((AsnDefinedType.class).isInstance(obj1)){
		  		tgtyp.isDefinedType=true;
				tgtyp.typeName = ((AsnDefinedType)obj1).typeReference ; 
			}
			else{	
				tgtyp.typeReference = obj1; 
			} 
		}*/
		
		))
		//{asnType = tgtyp ;
			{ t = null; }
	;


tag	returns [Tag tag]	
{
	tag = new Tag(); 
	int c; 
} 
	:	(L_BRACKET (c = clazz {tag.setClass(c);})? (class_NUMBER[tag]) R_BRACKET )
	;
	
	
clazz returns [int val]
{
	val=0;
}			
	:	(c1:UNIVERSAL_KW 	{val=0x00;})//{s= c1.getText();})
	|	(c2:APPLICATION_KW	{val=0x40;})//{s= c2.getText();})
	|	(c3:PRIVATE_KW		{val=0xC0;})//{s= c3.getText();})
	;


class_NUMBER [Tag tag]		
{
	String s="";
}
	:	((num:NUMBER {s=num.getText(); tag.setValue(new Integer(s));})
	|	(lid:LOWER))  //{s=lid.getText(); cnum.name = s ;}) )
		
	;

// Useful types
defined_type returns [ASNType asnType]	
{
	ASNDefinedType deftype = new ASNDefinedType();
	AsnConstraint cnstrnt; 
	asnType = null;
}
	:	((up:UPPER {deftype.isModuleReference = true ;deftype.moduleReference = up.getText();} DOT )? 
		(up1:UPPER {deftype.setReference(up1.getText());})
		(cnstrnt = constraint{deftype.constraint = cnstrnt;})? )
		{asnType = deftype; deftype=null ; cnstrnt = null;}
	;
	
// Referenced Type
selection_type returns [ASNType asnType]
{
	AsnSelectionType seltype = new AsnSelectionType();
	asnType = null;
	Object obj1;
}
	:	((lid:LOWER { seltype.selectionID = lid.getText();})
	 	LESS
	 	(obj1=type {seltype.type = obj1;}))
	 	{asnType=seltype; seltype=null;}
	;

// Macros Types	
macros_type	returns [ASNType asnType]
{
	asnType = null;
}
		:	(asnType = operation_macro)
		|	(asnType = error_macro)
		|	(asnType = objecttype_macro)
		;

operation_macro	returns [ASNType asnType]
{
	OperationMacro op = new OperationMacro();
    String s ;
    asnType = null; 
    ASNType obj1; 
    ASNType obj2;
}
	: (	"OPERATION"
		(ARGUMENT_KW (ld1:LOWER {op.argumentTypeIdentifier = ld1.getText();})?
		((obj2 = type)
			{op.argumentType = obj2; op.isArgumentName=true;
				if((ASNDefinedType.class).isInstance(obj2))
					op.argumentName = obj2.getType();
				else
					op.argumentName = op.argumentTypeIdentifier;
			}
		))?
		(RESULT_KW  {op.isResult=true;} ((SEMI)=>SEMI|((LOWER)? type)=>(ld2:LOWER {op.resultTypeIdentifier = ld2.getText();})?
		(obj1=type 
			{op.resultType=obj1;op.isResultName=true;
				if((ASNDefinedType.class).isInstance(obj1))
					op.resultName = obj1.getType();
				else
					op.resultName = op.resultTypeIdentifier;
			}
		)|))?
		(ERRORS_KW L_BRACE (operation_errorlist[op]
				{op.isErrors=true;}|) R_BRACE )?
		(LINKED_KW L_BRACE (linkedOp_list[op]
			{op.isLinkedOperation = true ;})?	 R_BRACE )? )
		{asnType = op;}
	;

operation_errorlist[OperationMacro oper]
{
	Object obj;
}
	:	obj = typeorvalue {oper.errorList.add(obj);}
		(COMMA (obj = typeorvalue {oper.errorList.add(obj);}))*
	;
	
	
linkedOp_list[OperationMacro oper]
{Object obj;}
	:	obj = typeorvalue {oper.linkedOpList.add(obj);}
		(COMMA (obj = typeorvalue {oper.linkedOpList.add(obj);}))*
	;
	
	
error_macro	returns [ASNType asnType]
{
	ErrorMacro merr = new ErrorMacro();asnType = null;
	ASNType obj1;
}
	:  ( ERROR_KW  (PARAMETER_KW {merr.isParameter = true; }
		(( lw:LOWER { merr.parameterName = lw.getText(); })? 
		(obj1 = type 
		{	if((ASNDefinedType.class).isInstance(obj1)){
				merr.isDefinedType=true;
				merr.typeName = obj1.getType() ; 
			}
			else{
				merr.typeReference = obj1 ;
			}
		}) ) )? )
		{asnType = merr ; merr = null;}
	;
	
objecttype_macro returns [ASNType asnType]
{
	ObjectType objtype = new ObjectType();
	AsnValue val; 
	asnType = null; 
	String s; 
	Object typ;
} 
	: ("OBJECT-TYPE" "SYNTAX" (typ = type {objtype.type=typ;} )
		("ACCESS" lid:LOWER {objtype.accessPart = lid.getText();})
	  ("STATUS" lid1:LOWER {objtype.statusPart = lid1.getText();}) 
	  ("DESCRIPTION" CHARACTER_KW STRING_KW)?
	  ("REFERENCE" CHARACTER_KW STRING_KW)? 
	  ("INDEX" L_BRACE (typeorvaluelist[objtype]) R_BRACE)? 
	  ("DEFVAL" L_BRACE ( val = value {objtype.value = val;}) R_BRACE )? )
	  {asnType= objtype; objtype = null;}	
	;

typeorvaluelist[ObjectType objtype]
{
	Object obj; 
}
	: ((obj = typeorvalue {objtype.elements.add(obj);})
	   (COMMA (obj=typeorvalue {objtype.elements.add(obj);})* ))
	;

typeorvalue returns [Object obj]
{
	Object obj1; 
	obj=null;
}
	: ((type)=>(obj1 = type) | obj1 = value)
	  {obj = obj1; obj1=null;}
	;

elementType_list returns [List<ModelSyntaxTreeNode> eleTypelist]
{
	eleTypelist = new ArrayList<ModelSyntaxTreeNode>(); 
	ASNType eleType; 
}
	:	(eleType = elementType {eleTypelist.add(eleType); }
	    (COMMA (eleType = elementType {eleTypelist.add(eleType);}))*)
	;

elementType	returns [ASNType asnType]
{
	AsnValue val; 
	AsnElementType eletyp = new AsnElementType();
	ASNType obj; 
	Tag tg=null; 
	boolean s; 
	asnType=null;
}
	: (	(lid:LOWER //{eletyp.name = lid.getText();} 
		(tg = tag )?//{eletyp.isTag = true; eletyp.tag = tg ;})? 
		(s = tag_default)?// {eletyp.isTagDefault = true; eletyp.typeTagDefault = s ;})? 
		(obj = type {asnType = obj; asnType.setName(lid.getText()); asnType.setTag(tg);}) ( (OPTIONAL_KW {asnType.setOptional();}) 
		| (DEFAULT_KW //{ eletyp.isDefault = true;} 
		 val = value {eletyp.value = val;} ))? )
		|COMPONENTS_KW OF_KW {eletyp.isComponentsOf = true;}(obj = type ))
		{
			if((ASNDefinedType.class).isInstance(obj)){
				eletyp.isDefinedType=true;
				eletyp.typeName = obj.getType() ; 
			} else{		
				eletyp.typeReference = obj;
			}
		}
	;
		
namedNumber_map returns [ASNNamedNumber ann]
{
	ann = new ASNNamedNumber();
	ASNNamedNumber nnum ; 
}	
	: (	L_BRACE (nnum= namedNumber {ann.putAll(nnum); })
	   (COMMA (nnum = namedNumber  {ann.putAll(nnum); }) )*  R_BRACE )
	;

namedNumber	returns [ASNNamedNumber nnum]
{
	nnum = new ASNNamedNumber() ;
	BigInteger iKey; 
	String sValue=null;
	AsnDefinedValue s;	
}
	:	(lid:LOWER {sValue = lid.getText();} L_PAREN 
		(iKey = signed_number {nnum.put(iKey,sValue);}//{nnum.signedNumber = i;nnum.isSignedNumber=true;}
		| (s = defined_value /*{nnum.definedValue=s;}*/)) R_PAREN	)
	;
	
// Updated Grammar for ASN constraints
constraint returns [AsnConstraint cnstrnt]
{
	cnstrnt=new AsnConstraint();
}
	: L_PAREN 
		(element_set_specs[cnstrnt]{cnstrnt.isElementSetSpecs=true;})? 
		(exception_spec[cnstrnt])? 
	  R_PAREN
	;

exception_spec[AsnConstraint cnstrnt]
{
	//AsnSignedNumber signum;
	BigInteger signum; 
	AsnDefinedValue defval;
	Object typ;AsnValue val;
}
	: (EXCLAMATION 
	  ( (signed_number)=>(signum=signed_number {cnstrnt.isSignedNumber=true;cnstrnt.signedNumber=signum;})
	   |(defined_value)=>(defval=defined_value {cnstrnt.isDefinedValue=true;cnstrnt.definedValue=defval;})
	   |typ=type COLON val=value {cnstrnt.isColonValue=true;cnstrnt.type=typ;cnstrnt.value=val;}))
	   {cnstrnt.isExceptionSpec=true;}
	;

element_set_specs[AsnConstraint cnstrnt]
{
	ElementSetSpec elemspec;
}
	:	(elemspec=element_set_spec {cnstrnt.elemSetSpec=elemspec;}
		(COMMA DOTDOTDOT {cnstrnt.isCommaDotDot=true;})? 
		(COMMA elemspec=element_set_spec {cnstrnt.addElemSetSpec=elemspec;cnstrnt.isAdditionalElementSpec=true;})?)
	;

element_set_spec returns [ElementSetSpec elemspec]	
{
	elemspec = new ElementSetSpec(); 
	Intersection intersect;
	ConstraintElements cnselem;
}
	:	intersect=intersections {elemspec.intersectionList.add(intersect);}
		((BAR | UNION_KW ) intersect=intersections {elemspec.intersectionList.add(intersect);})*
	| 	ALL EXCEPT cnselem=constraint_elements {elemspec.allExceptCnselem=cnselem;elemspec.isAllExcept=true;}
	;

// Coding is not proper for EXCEPT constraint elements. 
// One EXCEPT constraint elements should be tied to one Constraint elements
//(an object of constraint and except list)
// and not in one single list
intersections returns [Intersection intersect]
{
	intersect = new Intersection();
	ConstraintElements cnselem;
}
	:	cnselem=constraint_elements {intersect.cnsElemList.add(cnselem);}
	   (EXCEPT {intersect.isExcept=true;} cnselem=constraint_elements {intersect.exceptCnsElem.add(cnselem);})? 
	   ((INTERSECTION | INTERSECTION_KW) {intersect.isInterSection=true;}
	   cnselem=constraint_elements {intersect.cnsElemList.add(cnselem);}
	   (EXCEPT cnselem=constraint_elements {intersect.exceptCnsElem.add(cnselem);})?)*
	;
				
constraint_elements	returns [ConstraintElements cnsElem]
{ 
	cnsElem = new ConstraintElements(); 
	AsnValue val;
	AsnConstraint cns; 
	ElementSetSpec elespec;
	Object typ; 
}
	:	(value)=>(val = value {cnsElem.isValue=true;cnsElem.value=val;})
	|	(value_range[cnsElem])=>(value_range[cnsElem]	{cnsElem.isValueRange=true;})
	|	(SIZE_KW cns=constraint {cnsElem.isSizeConstraint=true;cnsElem.constraint=cns;})
	|	(FROM_KW cns=constraint {cnsElem.isAlphabetConstraint=true;cnsElem.constraint=cns;})
	|	(L_PAREN elespec=element_set_spec {cnsElem.isElementSetSpec=true;cnsElem.elespec=elespec;} R_PAREN)
	|	((INCLUDES {cnsElem.isIncludeType=true;})? typ=type {cnsElem.isTypeConstraint=true;cnsElem.type=typ;})
	|	(PATTERN_KW val=value {cnsElem.isPatternValue=true;cnsElem.value=val;})
	|	(WITH_KW 
		((COMPONENT_KW cns=constraint {cnsElem.isWithComponent=true;cnsElem.constraint=cns;})
		|	
		(COMPONENTS_KW {cnsElem.isWithComponents=true;}
		L_BRACE (DOTDOTDOT COMMA)? type_constraint_list[cnsElem] R_BRACE )))
	;

value_range[ConstraintElements cnsElem]
{
	AsnValue val;
}
	: (val=value {cnsElem.lEndValue=val;} | MIN_KW {cnsElem.isMinKw=true;}) (LESS {cnsElem.isLEndLess=true;})?  // lower end
	   DOTDOT
	  (LESS {cnsElem.isUEndLess=true;})? (val=value{cnsElem.uEndValue=val;} | MAX_KW {cnsElem.isMaxKw=true;}) // upper end
	;
	
type_constraint_list[ConstraintElements cnsElem]
{
	NamedConstraint namecns;
}
	: namecns=named_constraint {cnsElem.typeConstraintList.add(namecns);}
	 (COMMA namecns=named_constraint {cnsElem.typeConstraintList.add(namecns);})*
	;

named_constraint returns [NamedConstraint namecns]
{
	namecns = new NamedConstraint(); 
	AsnConstraint cns;
}
	:	lid:LOWER {namecns.name=lid.getText();}
	    (cns=constraint {namecns.isConstraint=true;namecns.constraint=cns;})? 
	    (PRESENT_KW {namecns.isPresentKw=true;}
	     |ABSENT_KW {namecns.isAbsentKw=true;}
	     | OPTIONAL_KW {namecns.isOptionalKw=true;})?
	;
				
/*-----------VALUES ---------------------------------------*/
value returns [AsnValue value]
{
	value = new AsnValue(); 
	AsnSequenceValue seqval;
    AsnDefinedValue defval;
    String aStr;
    //AsnSignedNumber 
    BigInteger num; 
    AsnOidComponentList cmplst;
}		
	: 	(TRUE_KW)=>(TRUE_KW 				{value.isTrueKW = true; })
	|	(FALSE_KW)=>(FALSE_KW				{value.isFalseKW = true;})
	|	(NULL_KW)=>(NULL_KW				{value.isNullKW = true;})
	|	(C_STRING)=>(c:C_STRING				{value.isCString=true; value.cStr = c.getText();})
	|	(defined_value)=>(defval = defined_value {value.isDefinedValue = true; value.definedValue = defval;})
	|	(signed_number)=>(num = signed_number	{value.isSignedNumber=true ; value.signedNumber = num;}) 
	|	(choice_value[value])=>(choice_value[value]	{value.isChoiceValue = true;})
	|	(sequence_value)=>(seqval=sequence_value	{value.isSequenceValue=true;value.seqval=seqval;})
	|	(sequenceof_value[value])=>(sequenceof_value[value] {value.isSequenceOfValue=true;})
	|	(cstr_value[value])=>(cstr_value[value]		{value.isCStrValue = true;})
	|	(obj_id_comp_lst)=>(cmplst=obj_id_comp_lst	{value.isAsnOIDValue=true;value.oidval=cmplst;})
	|	(PLUS_INFINITY_KW)=>(PLUS_INFINITY_KW		{value.isPlusInfinity = true;})
	|	(MINUS_INFINITY_KW)=>(MINUS_INFINITY_KW		{value.isMinusInfinity = true;})
	;

cstr_value[AsnValue value]
{AsnBitOrOctetStringValue bstrval = new AsnBitOrOctetStringValue();
AsnCharacterStringValue cstrval = new AsnCharacterStringValue();
AsnSequenceValue seqval;}
	:  ((H_STRING)=>(h:H_STRING 	{bstrval.isHString=true; bstrval.bhStr = h.getText();})
	|	(B_STRING)=>(b:B_STRING		{bstrval.isBString=true; bstrval.bhStr = b.getText();})
	|	(L_BRACE	((id_list[bstrval])=>(id_list[bstrval])
					|(char_defs_list[cstrval])=>(char_defs_list[cstrval])
					| tuple_or_quad[cstrval])    R_BRACE))
		{value.cStrValue=cstrval;value.bStrValue=bstrval;}
	;

id_list[AsnBitOrOctetStringValue bstrval]
{String s="";}
	: (ld:LOWER {s = ld.getText(); bstrval.idlist.add(s);}) 
	  (COMMA ld1:LOWER {s = ld1.getText();bstrval.idlist.add(s);})*
	;
	
char_defs_list[AsnCharacterStringValue cstrval]
{CharDef a ;}
	:a = char_defs {cstrval.isCharDefList = true;cstrval.charDefsList.add(a);} 
	(COMMA (a = char_defs {cstrval.charDefsList.add(a);}))* 
	;

tuple_or_quad[AsnCharacterStringValue cstrval]
{BigInteger n;}//AsnSignedNumber n;}
	: (n = signed_number {cstrval.tupleQuad.add(n);}) 
	  COMMA 
	  (n = signed_number {cstrval.tupleQuad.add(n);}) 
	  ((R_BRACE {cstrval.isTuple=true;})  |  (COMMA 
	  (n = signed_number {cstrval.tupleQuad.add(n);}) 
	  COMMA (n = signed_number {cstrval.tupleQuad.add(n);})))
	;

char_defs  returns [CharDef chardef]
{chardef = new CharDef(); 
BigInteger n ; AsnDefinedValue defval;}
	:	(c:C_STRING {chardef.isCString = true;chardef.cStr=c.getText();})
	|	(L_BRACE (n = signed_number {chardef.tupleQuad.add(n);}) COMMA (n = signed_number {chardef.tupleQuad.add(n);}) 
		((R_BRACE {chardef.isTuple=true;})
		|(COMMA (n = signed_number {chardef.tupleQuad.add(n);}) 
		COMMA (n = signed_number {chardef.tupleQuad.add(n);}) R_BRACE{chardef.isQuadruple=true;})))
	|	(defval = defined_value {chardef.defval=defval;})
	;

choice_value[AsnValue value]
{AsnChoiceValue chval = new AsnChoiceValue(); AsnValue val;}
	: ((lid:LOWER {chval.name = lid.getText();})
	   (COLON)?  (val=value {chval.value = val;}))
	  {value.chval = chval;}
	;

sequence_value returns [AsnSequenceValue seqval]
{AsnNamedValue nameval = new AsnNamedValue();
seqval = new AsnSequenceValue();}
	:	L_BRACE  ((nameval=named_value {seqval.isValPresent=true; seqval.namedValueList.add(nameval);})?
		(COMMA nameval=named_value {seqval.namedValueList.add(nameval);})*)   R_BRACE
	;

sequenceof_value[AsnValue value]
{AsnValue val; value.seqOfVal = new AsnSequenceOfValue();}
	: L_BRACE ((val=value {value.seqOfVal.valueList.add(val);})?
       (COMMA val=value {value.seqOfVal.valueList.add(val);})*) 
	  R_BRACE
	;

protected
defined_value returns [AsnDefinedValue defval]
{defval = new AsnDefinedValue(); }
	:	((up:UPPER {defval.moduleIdentifier = up.getText(); } 
			DOT {defval.isDotPresent=true;})? 
		lid:LOWER { defval.name = lid.getText();})
	;
		
signed_number returns [BigInteger i]
{i = null; String s="" ; }
	:	((m:MINUS {s=m.getText();})? 
		(n:NUMBER  {s+= n.getText(); i= new BigInteger(s);}) )
	;
	
named_value returns [AsnNamedValue nameval]
{nameval = new AsnNamedValue(); AsnValue val;}	
	:	(lid:LOWER	{nameval.name = lid.getText(); } 
		val=value	{nameval.value = val;})
	;	

