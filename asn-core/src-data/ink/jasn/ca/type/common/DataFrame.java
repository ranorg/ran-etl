/***********************************************************************************************************************
 * @Copyright licenses : Connectiva Systems (Ind) Pvt. Ltd. ,All rights reserved 
 * @Project : ParserGenX 
 **********************************************************************************************************************/

/***********************************************************************************************************************
 * Package Declaration :
 *@PKGS---------------------
 */

package ink.jasn.ca.type.common;

import java.io.Serializable;

/***********************************************************************************************************************
 * Packages and Classes Import :
 *@IMPRT-----------------------------
 */

/**
 * @Source : DataFrame.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Mar 20, 2009
 * @Time : 1:39:12 PM
 * @Version : $0.01
 */
public abstract class DataFrame implements Serializable
{
    private static final long serialVersionUID = -8231976548904587078L;
    protected int size;

    public int size()
    {
        return size;
    }

    public void size( int size )
    {
        this.size = size;
    }
}
