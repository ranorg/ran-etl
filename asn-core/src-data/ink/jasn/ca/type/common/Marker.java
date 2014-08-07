/***********************************************************************************************************************
 * |===============================================================================| 
 * | Copyright licenses : Connectiva Systems (Ind) Pvt. Ltd. ,All rights reserved  | 
 * | Project : ParserGenX                                                          |
 * |===============================================================================|
 **********************************************************************************************************************/

/***********************************************************************************************************************
 * Package Declaration : 
 * ---------------------
 */
package ink.jasn.ca.type.common;

/***********************************************************************************************************************
 * Packages and Classes Import : 
 * -----------------------------
 */
import java.io.Serializable;

/**
 * Source : Marker.java 
 * Author : Nirmal Kumar Biswas 
 * Date : Mar 20, 2009 
 * Time : 1:47:51 PM 
 * Version : $0.01
 */

public class Marker implements Serializable
{
    private static final long serialVersionUID = -1832832749164073872L;
    private int marker;
    
    public Marker()
    {
        
    }
    
    public Marker(int number)
    {
        marker=number;
    }
    
    public boolean mark()
    {
        return (marker==0)?true:false;
    }
    
    public int value()
    {
        return marker;
    }

    public void value( int number)
    {
        marker=number;
    }
    
    public void update( int number)
    {
        marker=(number==0)?number:(marker-number);
    }
    
    @Override
    public String toString()
    {
        return "Marker :" + marker;
    }
}
