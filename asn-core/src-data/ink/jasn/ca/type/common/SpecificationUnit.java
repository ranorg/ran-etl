
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
 package ink.jasn.ca.type.common;

/***
 * Packages and Classes Import :
 *-----------------------------*/
 import java.io.Serializable;
 
/**
 * Source  : SpecificationUnit.java
 * Author  : Nirmal Kumar Biswas
 * Date    : Jul 14, 2009
 * Time    : 12:14:49 PM
 * Version : $0.01
 **/
public interface SpecificationUnit<T> extends Serializable
{
    abstract T type();
    
    abstract boolean validate(DataFrame dataFrame);
}
