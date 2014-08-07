
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

import java.io.IOException;

/***
 * Packages and Classes Import :
 *-----------------------------*/


/**
 * Source  : Channel.java
 * Author  : Nirmal Kumar Biswas
 * Date    : Jul 24, 2009
 * Time    : 12:43:28 PM
 * Version : $0.01
 **/
public abstract class Channel
{
    protected boolean channelOpen;

    public boolean isChannelOpen()
    {
        return channelOpen;
    }
    
    public abstract void closeChannel() throws IOException;
}
