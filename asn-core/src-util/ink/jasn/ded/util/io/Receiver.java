
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
 package ink.jasn.ded.util.io;

/***
 * Packages and Classes Import :
 *-----------------------------*/


/**
 * Source  : Taker.java
 * Author  : Nirmal Kumar Biswas
 * Date    : Jul 24, 2009
 * Time    : 12:22:47 PM
 * Version : $0.01
 **/
public interface Receiver<D>
{
    void receive(D d);
}
