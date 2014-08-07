/*
 * 
 * Copyright 2013 ZCrypto, All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, without modification, 
 * are permitted subject to the following conditions:
 *
 * 1. Any use in source and binary forms must have a valid license from ZCrypto.
 *
 * 2. Redistributions of source code must retain the above copyright notice together with a valid license 
 *	   agreement and this list of conditions and the following disclaimer.
 *
 *	3. Redistributions in binary form must reproduce the above copyright notice together with a valid license 
 *	   agreement and this list of conditions and the following disclaimer in the documentation and/or other 
 * 	materials provided with the distribution.
 *
 * 4. Any short of use to endorse or promote products are not permitted without any prior written permission
 *		from ZCrypto.
 *
 * @Vertical 	 : Telecommunication   
 *	@Project  	 : Compiler for ASN with Data Encoder and Decoder (CA-DED)
 * @Source 	 	 : QuickFileUtil.java
 * @Description : SHORT DESCRIPTION ABOUT THE FILE
 * @Timeline 	 : Jul 22, 2013 3:11:51 PM
 * @Author 		 : iNK
 * @Version 	 : #1.00
 */

package ink.jasn.ca.gen.code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class QuickFileUtil
{
	public static void writeQuickFile(String filename, String data)
	{
		writeQuickFile(new File(filename), data);
	}

	public static void writeQuickFile(File file, String data)
	{
		try
		{
			FileWriter writer = new FileWriter(file);
			BufferedWriter buffWriter = new BufferedWriter(writer);
			buffWriter.write(data);
			buffWriter.flush();
			buffWriter.close();
			writer.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
