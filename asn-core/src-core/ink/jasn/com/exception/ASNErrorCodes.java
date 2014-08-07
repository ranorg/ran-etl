/*
 * Copyright (c) 2009 CONNECTiVA Systems, Inc. J2, Block-GP, Sector-V, Kolkata, 700091, INDIA All
 * Rights Reserved. This software is the confidential and proprietary information of CONNECTiVA
 * Systems, Inc. ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you entered into with
 * CONNECTiVA.
 */

/**
 * Package declaration
 */

package ink.jasn.com.exception;

/**
 * Packages and classes import
 */

/**
 * @Source : ASNErrorCodes.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Oct 12, 2009
 * @Time : 6:20:48 PM
 * @Version : $1.00
 **/

public interface ASNErrorCodes
{
    public String CodePrefix = "ASN.1~";

    public String LOOKUP_BAD_KEY = "000";

    /*
     * @arg1 type
     * @arg2 name
     * @arg3 name of the core source
     */

    public int GRAMMAR_FILE_NOT_SET = 201;
    public int GRAMMAR_FILE_NOT_FOUND = 202;
    public int GRAMMAR_SYNTAX_ERROR = 204;
    public int GRAMMAR_STREAM_ERROR = 205;


    public int ORGANIZER_DUPLICATE_TYPE_DEFINITION = 211;

    /*
     * @arg IOException message as cause
     */
    public int DATA_FILE_READ_ERROR = 301;

    /*
     * @arg IOException message as cause
     */
    public int DATA_FILE_WRITE_ERROR = 302;


    public int ERROR_DECIDING_CHILD = 400;


    public int TNODE_MISSING_MANDATORY_FIELD = 400;
    public int TNODE_ERROR_DECIDING_CHILD = 401;
    public int TNODE_COMPLETENESS_CHECK_FAILED = 402;


    public int CODEC_PARSING_FAILED = 500;
    /*
     * @arg1 fetch content length
     * @srg2 actual content length
     */
    public int CODEC_CONTENT_LENGTH_MISMATCH = 501;
    public int CODEC_DATA_CORRUPTION = 502;
    public int CODEC_INVALID_LENGTH = 503;
    public int CODEC_UNSUPPORTED_TYPE = 504;
    public int CODEC_DECODING_ERROR = 505;
    public int CODEC_INVALID_TAG = 506;
    public int CODEC_UNSUPPOTED_ENCODING = 507;
    public int CODEC_INVALID_LENGTH_SIZE = 508;
    public int CODEC_BADLY_ENCODED_DATA = 509;

}
