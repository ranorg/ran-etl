
/**
 * Copyright Connectiva Systems, Inc @2009. All Rights Reserved. This software is the
 * proprietary information of Connectiva Systems, Inc. Use is subject to license
 * terms.
 *
 * Project: GenericParser	
***/

/***
 * Package Declaration :
 *---------------------*/
 package ink.jasn.ded.util.codec;

/***
 * Packages and Classes Import :
 *-----------------------------*/


/**
 * Source  : ObjectPool.java
 * Author  : Nirmal Kumar Biswas
 * Date    : Aug 18, 2009
 * Time    : 12:46:19 PM
 * Version : $0.01
 **/
 
public class ObjectPool
{
    private Object[][] pool;

    public ObjectPool()
    {
        pool=null;
    }

    public Object get( Object key )
    {

        if( pool == null )
        {
            return null;
        }

        for( int i = 0; i < pool[0].length; i++ )
        {
            if( pool[0][i] == key )
            {
                return pool[1][i];
            }
        }
        return null;
    }

    public void put( Object key, Object entry )
    {

        if( pool == null )
        {
            pool = new Object[2][10];
        }

        int i = 0;
        for( ; i < pool[0].length && pool[0][i] != null; i++ )
        {
            if( pool[0][i] == key )
            {
                pool[1][i] = entry;
                return;
            }
        }

        if( i == pool[0].length )
        {
            Object[][] newPool = new Object[pool[0].length * 2][2];
            System.arraycopy( pool[0], 0, newPool[0], 0, pool[0].length );
            System.arraycopy( pool[1], 0, newPool[1], 0, pool[0].length );
            pool = newPool;
        } else
        {
            pool[0][i] = key;
            pool[1][i] = entry;
        }
    }
}
