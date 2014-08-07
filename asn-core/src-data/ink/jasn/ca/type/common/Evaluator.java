
/**
 * Copyright Connectiva Systems, Inc. All Rights Reserved. This software is the
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
 * Source  : Evaluator.java
 * Author  : Nirmal Kumar Biswas
 * Date    : Apr 29, 2009
 * Time    : 11:32:17 AM
 * Version : $0.01
 **/
public interface Evaluator<D> extends Serializable
{
    void evaluateNull( Data<D> data);

    Boolean evaluateBoolean( Data<D> data);

    String evaluateString( Data<D> data);

    Byte evaluateByte( Data<D> data);

    Short evaluateShort( Data<D> data);

    Integer evaluateInteger( Data<D> data);

    Long evaluateLong( Data<D> data);

    Float evaluateFloat( Data<D> data);

    Double evaluateDouble( Data<D> data);

}
