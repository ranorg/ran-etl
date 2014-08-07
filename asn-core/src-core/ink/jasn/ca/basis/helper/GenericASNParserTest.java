package ink.jasn.ca.basis.helper;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.basis.parser.ASNGrammarReader;
import ink.jasn.ca.type.base.ASNMaster;
import ink.jasn.ded.util.constant.StaticParameters;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import antlr.RecognitionException;
import antlr.TokenStreamException;

public class GenericASNParserTest
{
	private static FileWriter tempFileW;

	public static void main(String[] args)
	{
		int counter = 0;
		try
		{
			ASNMaster masterNode = parserTheGrammar("E:/NAWRAS_PRODUCTION/Eric_GGSN_R6/Eric_GGSN_R6.asn");
			MSTWrite("E:/NAWRAS_PRODUCTION/Eric_GGSN_R6/ModelSytaxTree_Phase1.TXT",
						masterNode);

			System.out.println("TestAParser.main() T1 :" + System.nanoTime());
			GenericASNParser genPer = new GenericASNParser(masterNode);

			genPer.configure(StaticParameters.ASN_BER,
						"E:/NAWRAS_PRODUCTION/Eric_GGSN_R6/AC1GGS1_20100708210919_50546");

			MVTOpenWrite("E:/NAWRAS_PRODUCTION/Eric_GGSN_R6/ModelValueTree1.TXT");
			while (genPer.hasMoreVTN())
			{
				CadedDataTreeNode<Object> mvtN = genPer.slide();

				MVTWrite(mvtN);
				MVTWrite("\n");

				++counter;
			}
			MVTCloseWrite();

			// MSTWrite(
			// "E:/NAWRAS_PRODUCTION/Eric_GGSN_R4/ModelSytaxTree_Phase2.TXT",
			// masterNode );
			//
			//
			// genPer.prepare(
			// "E:/NAWRAS_PRODUCTION/Eric_GGSN_R4/AC1GGS1_20090731235417_47730" );
			// MVTOpenWrite(
			// "E:/NAWRAS_PRODUCTION/Eric_GGSN_R4/ModelValueTree2.TXT" );
			//
			// while( genPer.hasMoreVTN() )
			// {
			// // System.out.println( "GenericASNParserTest.main()99999i" );
			// CadedDataTreeNode<Object> mvtN = genPer.slide();
			// // System.out.println( "GenericASNParserTest.main()||| ->"+mvtN );
			//
			// MVTWrite( mvtN );
			// MVTWrite( "\n" );
			//
			// ++counter;
			// }
			// MVTCloseWrite();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (RecognitionException e)
		{
			e.printStackTrace();
		}
		catch (TokenStreamException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("GenericASNParserTest.main()===>" + counter);
		}
	}

	private static ASNMaster parserTheGrammar(String file)
	{
		ASNGrammarReader agr = new ASNGrammarReader(file);
		try
		{
			return agr.parseGrammar();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private static void MSTWrite(String file, ASNMaster master)
	{
		try
		{
			FileWriter fwr = new FileWriter(file);
			fwr.write(master.toStringFull());
			fwr.flush();
			fwr.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void MVTOpenWrite(String file)
	{
		try
		{
			tempFileW = new FileWriter(file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void MVTWrite(CadedDataTreeNode<Object> vtn)
	{
		try
		{
			tempFileW.write(vtn.toString("#_____________"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void MVTWrite(String str)
	{
		try
		{
			tempFileW.write(str);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void MVTCloseWrite()
	{
		try
		{
			tempFileW.flush();
			tempFileW.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
