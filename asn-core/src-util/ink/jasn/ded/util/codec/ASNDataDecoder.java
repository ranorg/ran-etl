
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
 package ink.jasn.ded.util.codec;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.type.base.ASNCollection;
import ink.jasn.ca.type.base.ASNContainer;
import ink.jasn.ca.type.base.ASNDefinedType;
import ink.jasn.ca.type.base.ASNSelection;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.impl.ASNAny;
import ink.jasn.ca.type.impl.ASNEnumerated;
import ink.jasn.ded.util.io.Slider;

import java.math.BigInteger;
import java.util.Date;


/***
 * Packages and Classes Import :
 *-----------------------------*/


/**
 * Source  : ASNDataDecoder.java
 * Author  : Nirmal Kumar Biswas
 * Date    : Jul 24, 2009
 * Time    : 1:39:24 PM
 * Version : $0.01
 **/

public interface ASNDataDecoder extends Slider<Boolean>
{
    
    // =========================================================================================
    /**
     * Decode user defined data type for ASN.1
     * @return {@link CadedDataTreeNode}
     * @throws Exception
     */
    CadedDataTreeNode<Object> decodeDefined( ASNDefinedType definedType ) throws Exception;


    // =========================================================================================
    /**
     * Decode collection container [ "SEQUENCE OF" or "SET OF" ] type for ASN.1
     * @return {@link CadedDataTreeNode}
     * @throws Exception
     */
    CadedDataTreeNode<Object> decodeContainer( ASNContainer containerType ) throws Exception;

    /**
     * Decode collection [ "SEQUENCE" or "SET" ] type ASN.1
     * @param collectionType
     * @return {@link CadedDataTreeNode}
     * @throws Exception
     */
    CadedDataTreeNode<Object> decodeCollection( ASNCollection collectionType ) throws Exception;

    /**
     * Decode selection [ "CHOICE" ] type for ASN.1
     * @param selectionType
     * @return {@link CadedDataTreeNode}
     * @throws Exception
     */
    CadedDataTreeNode<Object> decodeSelection( ASNSelection selectionType ) throws Exception;


    // =========================================================================================
    /**
     * Decode ANY type for ASN.1
     * @return {@link ASNType}
     * @throws Exception
     */
    CadedDataTreeNode<Object> decodeAny( ASNAny anyType ) throws Exception;

    /**
     * Decode NULL type for ASN.1
     * @return {@link ASNType}
     * @throws Exception
     */
    ASNType decodeNull() throws Exception;


    // =========================================================================================
    /**
     * Decode INTEGER type for ASN.1
     * @return {@link BigInteger}
     * @throws Exception
     */
    BigInteger decodeInteger() throws Exception;

    /**
     * Decode REAL type for ASN.1
     * @return {@link Double}
     * @throws Exception
     */
    Double decodeReal() throws Exception;


    // =========================================================================================
    /**
     * Decode "BIT STRING" type for ASN.1
     * @return {@link String}
     * @throws Exception
     */
    String decodeBitString() throws Exception;

    /**
     * Decode "OCTET STRING" type for ASN.1
     * @return {@link String}
     * @throws Exception
     */
    String decodeOctetString() throws Exception;


    // =========================================================================================
    /**
     * Decode any of the character string type for ASN.1
     * @return {@link String}
     * @throws Exception
     */
    String decodeString() throws Exception;

    // String decodeBMPString()throws Exception;

    // String decodeGeneralString()throws Exception;

    // String decodeGraphicString()throws Exception;

    // String decodeIA5String()throws Exception;

    // String decodeISO646String()throws Exception;

    // String decodeISO8601String()throws Exception;

    // String decodeNumericString()throws Exception;

    // String decodePrintableString()throws Exception;

    // String decodeT61String()throws Exception;

    // String decodeTeletextString()throws Exception;

    // String decodeUniversalString()throws Exception;

    // String decodeVisibleString()throws Exception;

    // String decodeUTF8String()throws Exception;

    // String decodeVideotextString()throws Exception;

    // =========================================================================================
    /**
     * Decode BOOLEAN type for ASN.1
     * @return {@link Boolean}
     * @throws Exception
     */
    Boolean decodeBoolean() throws Exception;

    /**
     * Decode ENUMERATED type for ASN.1
     * @param asnType
     * @return {@link String}
     * @throws Exception
     */
    String decodeEnumerated( ASNEnumerated asnEnumerated ) throws Exception;


    // =========================================================================================
    /**
     * Decode "OBJECT IDENTIFIER" type for ASN.1
     * @return {@link String}
     * @throws Exception
     */
    String decodeObjectIdentifier() throws Exception;

    /**
     * Decode "RELATIVE OBJECT IDENTIFIER" type for ASN.1
     * @return {@link String}
     * @throws Exception
     */
    String decodeRelativeObjectIdentifier() throws Exception;


    // =========================================================================================
    /**
     * Decode "UTCTIME" type for ASN.1
     * @return {@link String}
     * @throws Exception
     */
    Date decodeUTCTime() throws Exception;

    /**
     * Decode "GENERALIZEDTIME" type for ASN.1
     * @return {@link Date}
     * @throws Exception
     */
    Date decodeGeneralizedTime() throws Exception;

}
