/***************************************************************************************************
 * Copyright Connectiva Systems, Inc
 * @2009. All Rights Reserved. This software is the proprietary information of Connectiva Systems,
 *        Inc. Use is subject to license terms.
 * @Project: GenericParser
 **************************************************************************************************/

/***************************************************************************************************
 * Package Declaration : 
 * ---------------------
 */

package ink.jasn.ded.util.constant;

/***************************************************************************************************
 * Packages and Classes Import : 
 * -----------------------------
 */


/**
 * @Source : ASNTagParamaters.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 13, 2009
 * @Time : 10:14:53 PM
 * @Version : $0.01
 */
public interface ASNTagParamaters
{
    /*********************************************************************
     * Constant values for Tag classes.
     *********************************************************************/
    public int TAG_CLASS_UNIVERSAL = 0x00;                      // | 000 |
    public int TAG_CLASS_APPLICATION = 0x40;                    // | 064 |
    public int TAG_CLASS_CONTEXT = 0x80;                        // | 128 |
    public int TAG_CLASS_PRIVATE = 0xC0;                        // | 192 |


    /*********************************************************************
     * Constant values for Tag values.
     *********************************************************************/
    public int TAG_VALUE_BASIC_ENCODING_RULE_RESERVED = 0x00;   // | 00  |
    /*********************************************************************/
    public int TAG_VALUE_BOOLEAN = 0x01;                        // | 01  |
    public int TAG_VALUE_INTEGER = 0x02;                        // | 02  |
    public int TAG_VALUE_BIT_STRING = 0x03;                     // | 03  | 
    public int TAG_VALUE_OCTET_STRING = 0x04;                   // | 04  |
    public int TAG_VALUE_NULL = 0x05;                           // | 05  |
    public int TAG_VALUE_OBJECT_IDENTIFIER = 0x06;              // | 06  |
    public int TAG_VALUE_OBJECT_DESCRIPTOR = 0x07;              // | 07  |
    public int TAG_VALUE_EXTERNAL = 0x08;                       // | 08  |
    public int TAG_VALUE_INSTANCE_OF = 0x08;                    // | 09  |
    public int TAG_VALUE_REAL = 0x09;                           // | 10  |
    public int TAG_VALUE_ENUMERATED = 0x0A;                     // | 11  |
    public int TAG_VALUE_EMBEDED_PDV = 0x0B;                    // | 12  |
    public int TAG_VALUE_UTF8_STRING = 0x0C;                    // | 13  |
    public int TAG_VALUE_RELATIVE_OID = 0x0D;                   // | 14  |

    /*********************************************************************/
    //public int TAG_VALUE_FUTURE_USED_RESERVED=0x0E - 0x0F;    // | 15  |
    /*********************************************************************/

    public int TAG_VALUE_SEQUENCE = 0x10;                       // | 16  |
    public int TAG_VALUE_SEQUENCE_OF = 0x10;                    // | 16  |
    public int TAG_VALUE_SET = 0x11;                            // | 17  |
    public int TAG_VALUE_SET_OF = 0x11;                         // | 17  |
    
    public int TAG_VALUE_NUMERIC_STRING = 0x12;                 // | 18  |
    public int TAG_VALUE_PRINTABLE_STRING = 0x13;               // | 18  |
    public int TAG_VALUE_T61_STRING = 0x14;                     // | 20  |
    public int TAG_VALUE_TELETEX_STRING = 0x14;                 // | 20  |
    public int TAG_VALUE_VIDEOTEX_STRING = 0x15;                // | 21  |
    public int TAG_VALUE_IA5_STRING = 0x16;                     // | 21  |
    public int TAG_VALUE_UTC_TIME = 0x17;                       // | 23  |
    public int TAG_VALUE_GENERALIZED_TIME = 0x18;               // | 24  |
    public int TAG_VALUE_GRAPHIC_STRING = 0x19;                 // | 25  |
    public int TAG_VALUE_ISO646_STRING = 0x1A;                  // | 26  |
    public int TAG_VALUE_VISIBLE_STRING = 0x1A;                 // | 26  |
    public int TAG_VALUE_GENERAL_STRING = 0x1B;                 // | 27  |
    public int TAG_VALUE_UNIVERSAL_STRING = 0x1C;               // | 28  |
    public int TAG_VALUE_BMP_STRING = 0x1E;                     // | 30  |

    /*********************************************************************/
    //public int TAG_VALUE_ADDENDA_RESERVED=0x1F                // | 31  |
    /*********************************************************************/
}
