/*
 * Copyright (c) 2009 CONNECTiVA Systems, Inc.
 * J2, Block-GP, Sector-V, Kolkata, 700091, INDIA
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * CONNECTiVA Systems, Inc. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with CONNECTiVA.
 *
 */

/**
* Package declaration
*/
package ink.jasn.ded.util.common;

/**
 * Packages and classes import
 */

/**
 * @Source  : ByteMask.java
 * @Author  : Nirmal Kumar Biswas
 * @Date    : Sep 10, 2009
 * @Time    : 10:16:45 PM
 * @Version : $1.00
 **/

public interface ByteMask
{
    // ALL BIT MASKING
    public int BIT_ALL_MASK = 0xFF;

    // SINGLE BIT MASKING
    public int BIT_8_MASK = 0x80;
    public int BIT_7_MASK = 0x40;
    public int BIT_6_MASK = 0x20;
    public int BIT_5_MASK = 0x10;
    public int BIT_4_MASK = 0x08;
    public int BIT_3_MASK = 0x04;
    public int BIT_2_MASK = 0x02;
    public int BIT_1_MASK = 0x01;

    // BIT COUPLE MASKING
    public int BIT_8_AND_7_MASK = 0xC0;
    public int BIT_6_AND_5_MASK = 0x30;
    public int BIT_4_AND_3_MASK = 0x0C;
    public int BIT_2_AND_1_MASK = 0x03;

    // BIT RANGE MASKING
    public int BIT_7_TO_1_MASK = 0x7F;
    public int BIT_6_TO_1_MASK = 0x3F;
    public int BIT_5_TO_1_MASK = 0x1F;
    public int BIT_4_TO_1_MASK = 0x0F;
    public int BIT_3_TO_1_MASK = 0x07;
}
