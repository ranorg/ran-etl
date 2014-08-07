package ink.jasn.ca.basis.helper;

import ink.jasn.ca.basis.central.AbstractSyntaxTreeNode;
import ink.jasn.ca.basis.central.CadedDataTreeNode;
import ink.jasn.ca.basis.parser.ASNGrammarReader;
import ink.jasn.ca.type.base.ASNMaster;
import ink.jasn.ded.util.codec.ASNDataReader;
import ink.jasn.ded.util.io.DataReaderFactory;

import java.util.Properties;

public class ASN1Parser
{
	private ASNMaster asnMaster;

	private ASNDataReader asnDataReader;

	private String grammarFileName;

	private String decodeType = null;

	private String fileName = null;

	public ASN1Parser()
	{

	}

	// @Override
	public void configure() throws Exception
	{

	}

	// @Override
	public void initialize(Properties properties) throws Exception
	{
		ASNGrammarReader asnGrammarReader = new ASNGrammarReader();
		asnMaster = asnGrammarReader.parseSyntax(grammarFileName);
		asnDataReader = DataReaderFactory.getASNDataReader(getType());
	}

	// @Override
	public void execute() throws Exception
	{
		asnDataReader.fixNewFile(getResource());
	}

	// @Override
	public boolean hasMoreRecord()
	{
		return asnMaster.hasMoreVTN();
	}

	// @Override
	public CadedDataTreeNode<Object> readNextRecord() throws Exception
	{
		CadedDataTreeNode<Object> mvtNode = asnMaster.process(asnDataReader);
		return mvtNode;
	}

	// @Override
	public void setGrammarResource(String graRes)
	{
		grammarFileName = graRes;
	}

	// @Override
	public void setGrammarSyntax(AbstractSyntaxTreeNode graSyntaxNode)
	{
		asnMaster = (ASNMaster) graSyntaxNode;
	}

	// @Override
	public void setResource(String resource)
	{
		fileName = resource;
	}

	// @Override
	public void setType(String type)
	{
		decodeType = type;
	}

	// @Override
	public String getResource()
	{
		return fileName;
	}

	// @Override
	public String getType()
	{
		return decodeType;
	}

	// @Override
	public void abort() throws Exception
	{
		asnDataReader.close();
	}

	// @Override
	public void close() throws Exception
	{
		asnDataReader.close();
	}
}
