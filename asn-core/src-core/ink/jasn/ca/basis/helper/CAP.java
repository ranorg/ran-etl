/**
 * 
 */

package ink.jasn.ca.basis.helper;

import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.basis.parser.ASNGrammarReader;
import ink.jasn.ca.type.base.ASNMaster;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author nkbiswas
 */
public class CAP
{
    private static FileWriter tempFileW;

    private static HashMap<String, Integer> options;

    static
    {
        options = new HashMap<String, Integer>( 3 );
        options.put( "-s", 1 );
        options.put( "-sd", 2 );
        options.put( "-e", 3 );
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main( String[] args ) throws Exception
    {
        String gFile = null, dFile = null, oFile = null, decoder = null;
        if( args.length < 2 )
        {
            System.out.println( "Insufficient arguments in syntax !!!" );
            showAction();
        }
        else
        {
            switch (options.get( args[0] ))
            {
                case 1:
                    File gfile = new File( args[1] );
                    if( !gfile.canRead() )
                    {
                        System.out.println( "Invalid syntax file path given or the file is not accessible !!!" );
                        break;
                    }
                    gFile = args[1];
                    if( args.length > 2 && args[2] != null )
                    {
                        if( args[2].equals( "-o" ) && args[3] != null )
                        {
                            oFile = args[3];
                        }
                    }
                    generatSyntaxTree( gFile, oFile, false );
                    break;
                case 2:
                    File gfile1 = new File( args[1] );
                    if( !gfile1.canRead() )
                    {
                        System.out.println( "Invalid syntax file path given or the file is not accessible !!!" );
                        break;
                    }
                    gFile = args[1];
                    if( args.length > 3 && args[2] != null && args[2].equals( "-o" ) && args[3] != null )
                    {
                        oFile = args[3];
                    }
                    generatSyntaxTree( gFile, oFile, true );
                    break;
                case 3:
                    if( args.length < 3 )
                        System.out.println( "Not enough argument in grammar data evaluation !!!" );
                    else
                    {
                        File cFile = new File( args[1] );
                        if( !cFile.canRead() )
                        {
                            System.out.println( "Invalid syntax file path given or the file is not accessible !!!" );
                            break;
                        }
                        gFile = args[1];
                        cFile = new File( args[2] );
                        if( !cFile.canRead() )
                        {
                            System.out.println( "Invalid data file path given or the file is not accessible !!!" );
                            break;
                        }
                        dFile = args[2];
                        if( args.length > 4 && args[3] != null )
                        {
                            if( args[3].equals( "-o" ) )
                                oFile = args[4];
                            else if( args[3].equals( "-d" ) )
                                decoder = args[4];
                        }

                        if( args.length > 6 && args[5] != null )
                        {
                            if( args[5].equals( "-o" ) )
                                oFile = args[6];
                            else if( args[5].equals( "-d" ) )
                                decoder = args[6];
                        }
                    }
                    evaluateGrammarData( gFile, decoder, dFile, oFile );
                    break;
                default:
                    System.out.println( "Invalid systax !!!" );
                    showAction();
                    break;
            }
        }
    }


    private static void generatSyntaxTree( String G, String out, boolean option )
    {
        try
        {
            ASNMaster m = parserTheGrammar( G );
            if( out != null )
                MSTWrite( out, m );
            else
                System.out.println( m );
            System.out.println( "Command executed successfully." );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    private static void evaluateGrammarData( String G, String decoder, String data, String out ) throws Exception
    {
        try
        {
            ASNMaster m = parserTheGrammar( G );
            if( out != null )
                MVTOpenWrite( out );


            GenericASNParser gp = new GenericASNParser( m );
            if( decoder == null )
                decoder = "BER";
            gp.configure( decoder, data );
            while( gp.hasMoreVTN() )
            {
                if( out != null )
                    MVTWrite( gp.slide() );
                else
                    System.out.println( gp.slide() );
            }
            if( out != null )
                MVTCloseWrite();

            System.out.println( "Command executed successfully." );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }


    private static ASNMaster parserTheGrammar( String file )
    {
        ASNGrammarReader agr = new ASNGrammarReader( file );
        try
        {
            return agr.parseGrammar();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        return null;
    }

    private static void MSTWrite( String file, ASNMaster master )
    {
        try
        {
            FileWriter fwr = new FileWriter( file );
            fwr.write( master.toStringFull() );
            fwr.flush();
            fwr.close();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }

    private static void MVTOpenWrite( String file )
    {
        try
        {
            tempFileW = new FileWriter( file );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }

    private static void MVTWrite( CadedDataTreeNode<Object> vtn )
    {
        try
        {
            tempFileW.write( vtn.toString( "#_____________" ) );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }

    private static void MVTWrite( String str )
    {
        try
        {
            tempFileW.write( str );
        }
        catch( IOException e )
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
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }

    private static void showAction()
    {
        System.out.println( "" );
        System.out.println( "Please follow the command syntax for useful result-" );
        System.out.println( "" );
        System.out.println( "OUTLINE -" );
        System.out
                  .println( "Options and arguments are given in parenthesis. Options only have a additionl \"-\" sign before it." );
        System.out.println( "" );
        System.out.println( "COMMAND -" );
        System.out
                  .println( "CAP [(-e) (grammar-file) (data-file)] / [ (-s)/(-sd) (grammar-file)] [(-d) (decoder)] [(-o) (output-file)]" );
        System.out.println( "" );
        System.out.println( "" );
        System.out
                  .println( "- Use this sytax for file [drive:][path][filename] - Specifies drive, directory and file to use" );
        System.out.println( "(grammar-file) -> This must be ASN.1 grammar syntax file." );
        System.out.println( "(data-file)    -> Data file that is going to be parsed by prepended grammar file." );
        System.out.println( "(output-file)  -> Output file will be used to write output result of the command." );
        System.out
                  .println( "(decoder)      -> Valid ASN.1 decoder name. Only two supported types are BER and DER (Mind that decoder will be in CAPS)." );
        System.out.println( "" );
        System.out.println( "Example commands :" );
        System.out.println( "CAP -sd D:/data/grammar.asn " );
        System.out.println( "CAP -s D:/data/grammar.asn -o D:/data/output.txt" );
        System.out.println( "CAP -e D:/data/grammar.asn D:/data/data.dat" );
        System.out.println( "CAP -e D:/data/grammar.asn D:/data/data.dat -o D:/data/output.txt" );
        System.out.println( "CAP -e D:/data/grammar.asn D:/data/data.dat -d BER -o D:/data/output.txt" );
        
    }
}
