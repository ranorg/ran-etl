/***
 * Package Declaration :
 *---------------------*/

package ink.jasn.ca.basis.helper;

/***
 * Packages and Classes Import :
 *-----------------------------*/
import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.basis.parser.ASNGrammarReader;
import ink.jasn.ca.type.base.ASNMaster;
import ink.jasn.ded.util.constant.StaticParameters;

import java.io.FileNotFoundException;
import java.io.FileWriter;

import antlr.RecognitionException;
import antlr.TokenStreamException;

/**
 * @Source : TestAParser.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 12, 2009
 * @Time : 5:49:39 PM
 * @Version : $0.01
 **/
public class TestAParser
{
	public static void main(String[] args)
	{
		// ASNGrammarReader agr = new
		// ASNGrammarReader("D:\\GAMMATZ\\Protocol\\T_Mobile_rating_enable.asn");
		// ASNGrammarReader agr = new
		// ASNGrammarReader("D:/CurrentDevelRelated/ASNTestComplete/BAH_SIEMENS12_SWT/SR.Bahrain.asn");
		// ASNGrammarReader agr = new
		// ASNGrammarReader("D:/CurrentDevelRelated/ASNTestComplete/Ericsson_EMM_NGNAdapter/EMM_NGN.asn");
		// ASNGrammarReader agr = new
		// ASNGrammarReader("D:/CurrentDevelRelated/ASNTestComplete/GGSN/ggsn_r6_new.asn");
		// ** ASNGrammarReader agr = new
		// ASNGrammarReader("D:/CurrentDevelRelated/ASNTestComplete/Ericsson_MSC/Ericsson_MSC_R12Grammar_new.asn");
		// ASNGrammarReader agr = new
		// ASNGrammarReader("D:/CurrentDevelRelated/ASNTestComplete/Motorola_Sgsn_R6Adapter/R6Grammar.asn");

		// ASNGrammarReader agr = new
		// ASNGrammarReader("D:/CurrentDevelRelated/ASNTestComplete/HuaweiMsc_v1r5sr5/MSC_R5C5.asn");
		ASNGrammarReader agr = new ASNGrammarReader(
					"/home/enirbis/Extra/ProjectOASN/CDRF_R6_Org.asn");

		ASNMaster masterNode;

		try
		{
			// FileWriter fwr = new
			// FileWriter("D:\\GAMMATZ\\Protocol\\ModelSytaxTreeF.TXT");
			FileWriter fwr = new FileWriter(
						"/home/enirbis/Extra/ProjectOASN/ModelSytaxTreeF.TXT");
			// FileWriter fwr = new
			// FileWriter("D:/CurrentDevelRelated/ASNTestComplete/Ericsson_EMM_NGNAdapter/ModelSytaxTreeF.TXT");
			// FileWriter fwr = new
			// FileWriter("D:/CurrentDevelRelated/ASNTestComplete/GGSN/ModelSytaxTreeF.TXT");
			// ** FileWriter fwr = new
			// FileWriter("D:/CurrentDevelRelated/ASNTestComplete/Ericsson_MSC/ModelSytaxTreeF.TXT");
			// FileWriter fwr = new
			// FileWriter("D:/CurrentDevelRelated/ASNTestComplete/Motorola_Sgsn_R6Adapter/ModelSytaxTreeF.TXT");

			// FileWriter fwr = new
			// FileWriter("D:/CurrentDevelRelated/ASNTestComplete/HuaweiMsc_v1r5sr5/ModelSytaxTreeF.TXT");

			masterNode = agr.parseGrammar();
			fwr.write(masterNode.toStringFull());
			fwr.flush();
			fwr.close();

			GenericASNParser genPer = new GenericASNParser(masterNode);

			// genPer.configure(StaticParameters.ASN_BER,"D:/CurrentDevelRelated/ASNTestComplete/TMobile_Germany/data/GPRS.RE.RBR.IBMD.00057000_18march_ch_23.asn");
			genPer.configure(StaticParameters.ASN_BER,
						"/home/enirbis/Extra/ProjectOASN/S-CDR.dat");
			// genPer.configure(StaticParameters.ASN_BER,"D:/CurrentDevelRelated/ASNTestComplete/Ericsson_EMM_NGNAdapter/XTFILE00-5949-11.001");
			// genPer.configure(StaticParameters.ASN_BER,"D:/CurrentDevelRelated/ASNTestComplete/GGSN/AKGGSN1_20090314235901_29595");
			// **
			// genPer.configure(StaticParameters.ASN_BER,"D:/CurrentDevelRelated/ASNTestComplete/Ericsson_MSC/MSCN5_0812180002501815");
			// genPer.configure(StaticParameters.ASN_BER,"D:/CurrentDevelRelated/ASNTestComplete/Motorola_Sgsn_R6Adapter/data/ac0_20090322080011_003200");

			// genPer.configure(StaticParameters.ASN_BER,"D:/CurrentDevelRelated/ASNTestComplete/HuaweiMsc_v1r5sr5/data/dar1b00429676.dat");

			// fwr = new FileWriter("D:\\GAMMATZ\\Protocol\\ModelValueTreeF.TXT");
			fwr = new FileWriter("/home/enirbis/Extra/ProjectOASN/ModelValueTreeF.TXT");
			// fwr = new
			// FileWriter("D:/CurrentDevelRelated/ASNTestComplete/Ericsson_EMM_NGNAdapter/ModelValueTreeF.TXT");
			// fwr = new
			// FileWriter("D:/CurrentDevelRelated/ASNTestComplete/GGSN/ModelValueTreeF.TXT");
			// ** fwr = new
			// FileWriter("D:/CurrentDevelRelated/ASNTestComplete/Ericsson_MSC/ModelValueTreeF.TXT");
			// fwr = new
			// FileWriter("D:/CurrentDevelRelated/ASNTestComplete/Motorola_Sgsn_R6Adapter/ModelValueTreeF.TXT");

			// fwr = new
			// FileWriter("D:/CurrentDevelRelated/ASNTestComplete/HuaweiMsc_v1r5sr5/ModelValueTreeF.TXT");

			int i = 0;
			while (genPer.hasMoreVTN())
			{
				CadedDataTreeNode<Object> mvtN = genPer.slide();
				System.out.println("TestAParser.main()===>\n"+mvtN);
				fwr.write(mvtN.toString());
				i++;
			}
			fwr.flush();
			fwr.close();

			System.out.println("TestAParser.main()---> FINISH !!!---> " + i);
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
	}
}
