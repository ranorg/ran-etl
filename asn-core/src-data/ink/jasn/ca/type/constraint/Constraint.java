
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
 package ink.jasn.ca.type.constraint;

/***
 * Packages and Classes Import :
 *-----------------------------*/

/**
 * Source  : Constraint.java
 * Author  : Nirmal Kumar Biswas
 * Date    : Jul 25, 2009
 * Time    : 11:47:29 AM
 * Version : $0.01
**/
public interface Constraint<T> 
{
	boolean isValidConstrint();
	boolean validate(T t);
}
