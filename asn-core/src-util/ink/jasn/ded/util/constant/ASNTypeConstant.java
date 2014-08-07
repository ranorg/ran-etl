
/**
 * Copyright Connectiva Systems, Inc @2009. All Rights Reserved. This software is the
 * proprietary information of Connectiva Systems, Inc. Use is subject to license
 * terms.
 *
 * Project: ParserGenX	
***/

/***
 * Package Declaration :
 *---------------------*/
 package ink.jasn.ded.util.constant;

/***
 * Packages and Classes Import :
 *-----------------------------*/

/**
 * Source  : ASNTypeConstant.java
 * Author  : Nirmal Kumar Biswas
 * Date    : Jul 27, 2009
 * Time    : 5:08:16 PM
 * Version : $0.01
 **/
public interface ASNTypeConstant 
{
	//Constrint type constant
	byte CTYPE_SINGLE_VALUE=1;
	byte CTYPE_CONTAINED_TYPE=2;
	byte CTYPE_VALUE_RANGE=3;
	byte CTYPE_SIZE_CONSTRINT=4;
	byte CTYPE_PERMITTED_ALPHABET=5;
	byte CTYPE_TYPE_CONSTRINT=6;
	byte CTYPE_INNER_SUBTYPING=7;
	byte CTYPE_PATTERN_CONSTRINT=8;
	
	//Data type costant 
	byte DTYPE_BIT_STRING=1;
	byte DTYPE_BOOLEAN=2;
	byte DTYPE_CHOICE=3;
	byte DTYPE_EMBEDDED_PDV=4;
	byte DTYPE_ENUMERATED=5;
	byte DTYPE_EXTERNAL=6;
	byte DTYPE_INSTANCE_OF=7;
	byte DTYPE_INTEGER=8;
	byte DTYPE_NULL=9;
	byte DTYPE_OBJECT_CLASS_FIELD_TYPE=10;
	byte DTYPE_OBJECT_DESCRIPTOR=11;
	byte DTYPE_OBJECT_IDENTIFIER=12;
	byte DTYPE_OCTET_STRING=13;
	byte DTYPE_OPEN_TYPE=14;
	byte DTYPE_REAL=15;
	byte DTYPE_RELATIVE_OBJECT_IDENTIFIER=16;
	byte DTYPE_RESTRICTED_CHARACTER_STRING_TYPE=17;
	byte DTYPE_SEQUENCE=18;
	byte DTYPE_SEQUENCE_OF=19;
	byte DTYPE_SET=20;
	byte DTYPE_SET_OF=21;
	byte DTYPE_TIME_TYPES=22;
	byte DTYPE_UNRESTRICTED_CHARACTER_STRING_TYPE=23;
}
