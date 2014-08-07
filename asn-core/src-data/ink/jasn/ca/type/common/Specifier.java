/***********************************************************************************************************************
 * |===============================================================================| 
 * | Copyright licenses : Connectiva Systems (Ind) Pvt. Ltd. ,All rights reserved | 
 * | Project : ParserGenX |
 * |===============================================================================|
 **********************************************************************************************************************/

/***********************************************************************************************************************
 * Package Declaration : 
 * ---------------------
 */
package ink.jasn.ca.type.common;

/***********************************************************************************************************************
 * Packages and Classes Import :
 *  -----------------------------
 */
import java.io.Serializable;
import java.util.logging.Logger;


/**
 * Source : Specifier.java 
 * Author : Nirmal Kumar Biswas
 * Date : Mar 20, 2009 
 * Time : 1:51:00 PM 
 * Version : $0.01
 */
public abstract class Specifier<N,T,V> implements Serializable
{
    private static final long serialVersionUID = -8623555876049362881L;

    protected transient Logger logger=Logger.getLogger(Specifier.class.getName());

    protected N name;
    protected T type;
    protected V value;
    
    /**
     * 
     */
    public Specifier()
    {
        // TODO Auto-generated constructor stub
    }
    
    public Specifier(N name,T type,V value)
    {
        this.name=name;
        this.value=value;
        this.type=type;
    }
    
    public Specifier(N name,V value)
    {
        this.name=name;
        this.value=value;
    }

    public void name( N name)
    {
        this.name=name;
    }

    /*
     * 
     */
    public N name()
    {
        return name;
    }

    /*
     *  Set value for the specifier
     */
    public void value( V value)
    {
        this.value=value;
    }
    
    /**
     * @return the type
     */
    public T type()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void type( T type)
    {
        this.type = type;
    }

    /*
     * Get value of the specier Object
     */
    public V value()
    {
        return value;
    }

    /*
     * Check whether the value for the specifier set or not
     */
    protected boolean isEmpty()
    {
        if(value==null)
        {
            return true;
        }
        return false;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.valueOf(value);//"["+String.valueOf(name)+":"+String.valueOf(type)+":"+String.valueOf( value)+"]";
    }
    
    public String toXMLString()
    {
        StringBuilder sb=new StringBuilder("<Specifier>");
        sb.append("\n\t<Name>"+String.valueOf(name)+"</Name>");
        sb.append("\n\t<Type>"+String.valueOf(type)+"</Type>");
        sb.append("\n\t<Value>"+String.valueOf(value)+"</Value>");
        sb.append("\n</Specifier>");
        return sb.toString();
    }
    
    public abstract V defaultValue();
    
}
