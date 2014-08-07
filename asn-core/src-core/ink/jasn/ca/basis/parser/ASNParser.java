// $ANTLR : "ASN1.G" -> "ASNParser.java"$

package ink.jasn.ca.basis.parser;

import ink.jasn.ca.basis.central.AbstractSyntaxTreeNode;
import ink.jasn.ca.type.base.ASNCharacterString;
import ink.jasn.ca.type.base.ASNDefinedType;
import ink.jasn.ca.type.base.ASNType;
import ink.jasn.ca.type.base.Tag;
import ink.jasn.ca.type.extern.ASNNamedNumber;
import ink.jasn.ca.type.grammar.ASNModule;
import ink.jasn.ca.type.grammar.AsnBitOrOctetStringValue;
import ink.jasn.ca.type.grammar.AsnCharacterStringValue;
import ink.jasn.ca.type.grammar.AsnChoiceValue;
import ink.jasn.ca.type.grammar.AsnConstraint;
import ink.jasn.ca.type.grammar.AsnDefinedValue;
import ink.jasn.ca.type.grammar.AsnElementType;
import ink.jasn.ca.type.grammar.AsnEmbedded;
import ink.jasn.ca.type.grammar.AsnExternal;
import ink.jasn.ca.type.grammar.AsnModuleIdentifier;
import ink.jasn.ca.type.grammar.AsnNamedValue;
import ink.jasn.ca.type.grammar.AsnOidComponent;
import ink.jasn.ca.type.grammar.AsnOidComponentList;
import ink.jasn.ca.type.grammar.AsnSelectionType;
import ink.jasn.ca.type.grammar.AsnSequenceOfValue;
import ink.jasn.ca.type.grammar.AsnSequenceValue;
import ink.jasn.ca.type.grammar.AsnValue;
import ink.jasn.ca.type.grammar.CharDef;
import ink.jasn.ca.type.grammar.ConstraintElements;
import ink.jasn.ca.type.grammar.ElementSetSpec;
import ink.jasn.ca.type.grammar.ErrorMacro;
import ink.jasn.ca.type.grammar.Intersection;
import ink.jasn.ca.type.grammar.NamedConstraint;
import ink.jasn.ca.type.grammar.ObjectType;
import ink.jasn.ca.type.grammar.OperationMacro;
import ink.jasn.ca.type.grammar.SymbolsFromModule;
import ink.jasn.ca.type.impl.ASNAny;
import ink.jasn.ca.type.impl.ASNBMPString;
import ink.jasn.ca.type.impl.ASNBitString;
import ink.jasn.ca.type.impl.ASNBoolean;
import ink.jasn.ca.type.impl.ASNChoice;
import ink.jasn.ca.type.impl.ASNEnumerated;
import ink.jasn.ca.type.impl.ASNGeneralString;
import ink.jasn.ca.type.impl.ASNGeneralizedTime;
import ink.jasn.ca.type.impl.ASNGraphicString;
import ink.jasn.ca.type.impl.ASNIA5String;
import ink.jasn.ca.type.impl.ASNISO646String;
import ink.jasn.ca.type.impl.ASNInteger;
import ink.jasn.ca.type.impl.ASNNull;
import ink.jasn.ca.type.impl.ASNNumericString;
import ink.jasn.ca.type.impl.ASNObjectIdentifier;
import ink.jasn.ca.type.impl.ASNOctetString;
import ink.jasn.ca.type.impl.ASNPrintableString;
import ink.jasn.ca.type.impl.ASNReal;
import ink.jasn.ca.type.impl.ASNRelativeObjectIdentifier;
import ink.jasn.ca.type.impl.ASNSequence;
import ink.jasn.ca.type.impl.ASNSequenceOf;
import ink.jasn.ca.type.impl.ASNSet;
import ink.jasn.ca.type.impl.ASNSetOf;
import ink.jasn.ca.type.impl.ASNT61String;
import ink.jasn.ca.type.impl.ASNTeletexString;
import ink.jasn.ca.type.impl.ASNUTCTime;
import ink.jasn.ca.type.impl.ASNUTF8String;
import ink.jasn.ca.type.impl.ASNUniversalString;
import ink.jasn.ca.type.impl.ASNVideotexString;
import ink.jasn.ca.type.impl.ASNVisibleString;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import antlr.NoViableAltException;
import antlr.ParserSharedInputState;
import antlr.RecognitionException;
import antlr.Token;
import antlr.TokenBuffer;
import antlr.TokenStream;
import antlr.TokenStreamException;
import antlr.collections.impl.BitSet;

public class ASNParser extends antlr.LLkParser implements ASNTokenTypes
{

	protected ASNParser(TokenBuffer tokenBuf, int k)
	{
		super(tokenBuf, k);
		tokenNames = _tokenNames;
	}

	public ASNParser(TokenBuffer tokenBuf)
	{
		this(tokenBuf, 3);
	}

	protected ASNParser(TokenStream lexer, int k)
	{
		super(lexer, k);
		tokenNames = _tokenNames;
	}

	public ASNParser(TokenStream lexer)
	{
		this(lexer, 3);
	}

	public ASNParser(ParserSharedInputState state)
	{
		super(state, 3);
		tokenNames = _tokenNames;
	}

	public final void module_definition(ASNModule module) throws RecognitionException,
				TokenStreamException
	{

		Token e = null;
		Token i = null;
		Token a = null;

		AsnModuleIdentifier mid;
		String s;

		try
		{ // for error handling
			{
				mid = module_identifier();
				if (inputState.guessing == 0)
				{
					module.moduleIdentifier = mid;
				}
			}
			match(DEFINITIONS_KW);
			{
				switch (LA(1))
				{
					case AUTOMATIC_KW:
					case EXPLICIT_KW:
					case IMPLICIT_KW:
						{
							{
								switch (LA(1))
								{
									case EXPLICIT_KW:
										{
											e = LT(1);
											match(EXPLICIT_KW);
											if (inputState.guessing == 0)
											{
												module.tagDefault = e.getText();
											}
											break;
										}
									case IMPLICIT_KW:
										{
											i = LT(1);
											match(IMPLICIT_KW);
											if (inputState.guessing == 0)
											{
												module.tagDefault = i.getText();
											}
											break;
										}
									case AUTOMATIC_KW:
										{
											a = LT(1);
											match(AUTOMATIC_KW);
											if (inputState.guessing == 0)
											{
												module.tagDefault = a.getText();
											}
											break;
										}
									default:
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
								}
							}
							match(TAGS_KW);
							if (inputState.guessing == 0)
							{
								module.tag = true;
							}
							break;
						}
					case EXTENSIBILITY_KW:
					case ASSIGN_OP:
						{
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			{
				switch (LA(1))
				{
					case EXTENSIBILITY_KW:
						{
							match(EXTENSIBILITY_KW);
							match(IMPLIED_KW);
							if (inputState.guessing == 0)
							{
								module.extensible = true;
							}
							break;
						}
					case ASSIGN_OP:
						{
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			match(ASSIGN_OP);
			match(BEGIN_KW);
			module_body(module);
			match(END_KW);
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_0);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final AsnModuleIdentifier module_identifier() throws RecognitionException,
				TokenStreamException
	{
		AsnModuleIdentifier mid;

		Token md = null;
		mid = new AsnModuleIdentifier();
		AsnOidComponentList cmplst;

		try
		{ // for error handling
			{
				{
					md = LT(1);
					match(UPPER);
					if (inputState.guessing == 0)
					{
						mid.name = md.getText();
					}
				}
				{
					switch (LA(1))
					{
						case L_BRACE:
							{
								{
									cmplst = obj_id_comp_lst();
									if (inputState.guessing == 0)
									{
										mid.componentList = cmplst;
									}
								}
								break;
							}
						case DEFINITIONS_KW:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_1);
			}
			else
			{
				throw ex;
			}
		}
		return mid;
	}

	public final void module_body(ASNModule module) throws RecognitionException,
				TokenStreamException
	{

		try
		{ // for error handling
			{
				switch (LA(1))
				{
					case EXPORTS_KW:
						{
							exports(module);
							break;
						}
					case END_KW:
					case IMPORTS_KW:
					case UPPER:
					case LOWER:
						{
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			{
				switch (LA(1))
				{
					case IMPORTS_KW:
						{
							imports(module);
							break;
						}
					case END_KW:
					case UPPER:
					case LOWER:
						{
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			{
				switch (LA(1))
				{
					case UPPER:
					case LOWER:
						{
							{
								int _cnt621 = 0;
								_loop621: do
								{
									if ((LA(1) == UPPER || LA(1) == LOWER))
									{
										assignment(module);
									}
									else
									{
										if (_cnt621 >= 1)
										{
											break _loop621;
										}
										else
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
									}

									_cnt621++;
								}
								while (true);
							}
							break;
						}
					case END_KW:
						{
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_2);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final AsnOidComponentList obj_id_comp_lst() throws RecognitionException,
				TokenStreamException
	{
		AsnOidComponentList oidcmplst;

		oidcmplst = new AsnOidComponentList();
		AsnOidComponent oidcmp;
		AsnDefinedValue defval;

		try
		{ // for error handling
			match(L_BRACE);
			{
				if ((LA(1) == UPPER || LA(1) == LOWER) && (_tokenSet_3.member(LA(2)))
							&& (_tokenSet_4.member(LA(3))))
				{
					defval = defined_value();
					if (inputState.guessing == 0)
					{
						oidcmplst.isDefinitive = true;
						oidcmplst.defval = defval;
					}
				}
				else if (((LA(1) >= NUMBER && LA(1) <= LOWER)) && (_tokenSet_4.member(LA(2)))
							&& (_tokenSet_5.member(LA(3))))
				{
				}
				else
				{
					throw new NoViableAltException(LT(1), getFilename());
				}

			}
			{
				int _cnt598 = 0;
				_loop598: do
				{
					if (((LA(1) >= NUMBER && LA(1) <= LOWER)))
					{
						oidcmp = obj_id_component();
						if (inputState.guessing == 0)
						{
							oidcmplst.components.add(oidcmp);
						}
					}
					else
					{
						if (_cnt598 >= 1)
						{
							break _loop598;
						}
						else
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
					}

					_cnt598++;
				}
				while (true);
			}
			match(R_BRACE);
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_6);
			}
			else
			{
				throw ex;
			}
		}
		return oidcmplst;
	}

	protected final AsnDefinedValue defined_value() throws RecognitionException,
				TokenStreamException
	{
		AsnDefinedValue defval;

		Token up = null;
		Token lid = null;
		defval = new AsnDefinedValue();

		try
		{ // for error handling
			{
				{
					switch (LA(1))
					{
						case UPPER:
							{
								up = LT(1);
								match(UPPER);
								if (inputState.guessing == 0)
								{
									defval.moduleIdentifier = up.getText();
								}
								match(DOT);
								if (inputState.guessing == 0)
								{
									defval.isDotPresent = true;
								}
								break;
							}
						case LOWER:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				lid = LT(1);
				match(LOWER);
				if (inputState.guessing == 0)
				{
					defval.name = lid.getText();
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_7);
			}
			else
			{
				throw ex;
			}
		}
		return defval;
	}

	public final AsnOidComponent obj_id_component() throws RecognitionException,
				TokenStreamException
	{
		AsnOidComponent oidcmp;

		Token num = null;
		Token lid = null;
		Token num1 = null;

		oidcmp = new AsnOidComponent();
		AsnDefinedValue defval;
		String s, n = "";

		try
		{ // for error handling
			{
				if ((LA(1) == NUMBER))
				{
					{
						num = LT(1);
						match(NUMBER);
						if (inputState.guessing == 0)
						{
							s = num.getText();
							oidcmp.num = new Integer(s);
							oidcmp.numberForm = true;
						}
					}
				}
				else
				{
					boolean synPredMatched604 = false;
					if (((LA(1) == LOWER) && (_tokenSet_8.member(LA(2))) && (_tokenSet_5
								.member(LA(3)))))
					{
						int _m604 = mark();
						synPredMatched604 = true;
						inputState.guessing++;
						try
						{
							{
								match(LOWER);
								{
									if ((LA(1) == L_PAREN))
									{
										match(L_PAREN);
										match(NUMBER);
										match(R_PAREN);
									}
									else
									{
									}

								}
							}
						}
						catch (RecognitionException pe)
						{
							synPredMatched604 = false;
						}
						rewind(_m604);
						inputState.guessing--;
					}
					if (synPredMatched604)
					{
						{
							{
								lid = LT(1);
								match(LOWER);
								if (inputState.guessing == 0)
								{
									oidcmp.name = lid.getText();
									oidcmp.nameForm = true;
								}
							}
							{
								switch (LA(1))
								{
									case L_PAREN:
										{
											match(L_PAREN);
											{
												num1 = LT(1);
												match(NUMBER);
												if (inputState.guessing == 0)
												{
													n = num1.getText();
													oidcmp.num = new Integer(n);
													oidcmp.nameAndNumberForm = true;
												}
											}
											match(R_PAREN);
											break;
										}
									case R_BRACE:
									case NUMBER:
									case UPPER:
									case LOWER:
										{
											break;
										}
									default:
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
								}
							}
						}
					}
					else
					{
						boolean synPredMatched610 = false;
						if (((LA(1) == UPPER || LA(1) == LOWER) && (_tokenSet_9.member(LA(2))) && (_tokenSet_5
									.member(LA(3)))))
						{
							int _m610 = mark();
							synPredMatched610 = true;
							inputState.guessing++;
							try
							{
								{
									defined_value();
								}
							}
							catch (RecognitionException pe)
							{
								synPredMatched610 = false;
							}
							rewind(_m610);
							inputState.guessing--;
						}
						if (synPredMatched610)
						{
							{
								defval = defined_value();
								if (inputState.guessing == 0)
								{
									oidcmp.isDefinedValue = true;
									oidcmp.defval = defval;
								}
							}
						}
						else
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
					}
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_10);
			}
			else
			{
				throw ex;
			}
		}
		return oidcmp;
	}

	public final boolean tag_default() throws RecognitionException, TokenStreamException
	{
		boolean s;

		Token tg = null;
		Token tg1 = null;
		Token tg2 = null;

		s = false;

		try
		{ // for error handling
			switch (LA(1))
			{
				case EXPLICIT_KW:
					{
						{
							tg = LT(1);
							match(EXPLICIT_KW);
							if (inputState.guessing == 0)
							{
								s = true;
							}
						}
						break;
					}
				case IMPLICIT_KW:
					{
						{
							tg1 = LT(1);
							match(IMPLICIT_KW);
						}
						break;
					}
				case AUTOMATIC_KW:
					{
						{
							tg2 = LT(1);
							match(AUTOMATIC_KW);
							if (inputState.guessing == 0)
							{
								s = true;
							}
						}
						break;
					}
				default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_11);
			}
			else
			{
				throw ex;
			}
		}
		return s;
	}

	public final void exports(ASNModule module) throws RecognitionException,
				TokenStreamException
	{

		String s;
		ArrayList syml = new ArrayList();

		try
		{ // for error handling
			match(EXPORTS_KW);
			if (inputState.guessing == 0)
			{
				module.exported = true;
			}
			{
				switch (LA(1))
				{
					case OBJECT_KW:
					case SEMI:
					case UPPER:
					case LOWER:
					case LITERAL_OPERATION_KW:
					case LITERAL_ERROR_KW:
					case LITERAL_BIND:
					case LITERAL_UNBIND:
					case 127:
					case 128:
					case LITERAL_EXTENSION:
					case LITERAL_EXTENSIONS:
					case 131:
					case LITERAL_TOKEN:
					case 133:
					case 134:
					case LITERAL_PORT:
					case LITERAL_REFINE:
					case 137:
					case 138:
					case 139:
					case 140:
					case LITERAL_ALGORITHM:
					case LITERAL_ENCRYPTED:
					case LITERAL_SIGNED:
					case LITERAL_SIGNATURE:
					case LITERAL_PROTECTED:
					case 146:
						{
							{
								switch (LA(1))
								{
									case OBJECT_KW:
									case UPPER:
									case LOWER:
									case LITERAL_OPERATION_KW:
									case LITERAL_ERROR_KW:
									case LITERAL_BIND:
									case LITERAL_UNBIND:
									case 127:
									case 128:
									case LITERAL_EXTENSION:
									case LITERAL_EXTENSIONS:
									case 131:
									case LITERAL_TOKEN:
									case 133:
									case 134:
									case LITERAL_PORT:
									case LITERAL_REFINE:
									case 137:
									case 138:
									case 139:
									case 140:
									case LITERAL_ALGORITHM:
									case LITERAL_ENCRYPTED:
									case LITERAL_SIGNED:
									case LITERAL_SIGNATURE:
									case LITERAL_PROTECTED:
									case 146:
										{
											syml = symbol_list();
											if (inputState.guessing == 0)
											{
												module.exportSymbolList = syml;
											}
											break;
										}
									case SEMI:
										{
											break;
										}
									default:
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
								}
							}
							break;
						}
					case ALL_KW:
						{
							match(ALL_KW);
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			match(SEMI);
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_12);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final void imports(ASNModule module) throws RecognitionException,
				TokenStreamException
	{

		try
		{ // for error handling
			{
				match(IMPORTS_KW);
				{
					switch (LA(1))
					{
						case OBJECT_KW:
						case UPPER:
						case LOWER:
						case LITERAL_OPERATION_KW:
						case LITERAL_ERROR_KW:
						case LITERAL_BIND:
						case LITERAL_UNBIND:
						case 127:
						case 128:
						case LITERAL_EXTENSION:
						case LITERAL_EXTENSIONS:
						case 131:
						case LITERAL_TOKEN:
						case 133:
						case 134:
						case LITERAL_PORT:
						case LITERAL_REFINE:
						case 137:
						case 138:
						case 139:
						case 140:
						case LITERAL_ALGORITHM:
						case LITERAL_ENCRYPTED:
						case LITERAL_SIGNED:
						case LITERAL_SIGNATURE:
						case LITERAL_PROTECTED:
						case 146:
							{
								{
									{
										int _cnt630 = 0;
										_loop630: do
										{
											if ((_tokenSet_13.member(LA(1))))
											{
												symbols_from_module(module);
											}
											else
											{
												if (_cnt630 >= 1)
												{
													break _loop630;
												}
												else
												{
													throw new NoViableAltException(LT(1),
																getFilename());
												}
											}

											_cnt630++;
										}
										while (true);
									}
								}
								break;
							}
						case SEMI:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				match(SEMI);
			}
			if (inputState.guessing == 0)
			{
				module.imported = true;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_14);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final void assignment(ASNModule module) throws RecognitionException,
				TokenStreamException
	{

		Token up = null;
		Token lo = null;

		ASNType asnType;
		ASNType asnTypeV;
		AsnValue val;

		try
		{ // for error handling
			if ((LA(1) == UPPER) && (LA(2) == ASSIGN_OP))
			{
				{
					up = LT(1);
					match(UPPER);
					match(ASSIGN_OP);
					{
						asnType = type();
					}
					if (inputState.guessing == 0)
					{

						asnType.setName(up.getText());
						module.asnTypes.add(asnType);

					}
				}
			}
			else if ((LA(1) == LOWER))
			{
				{
					lo = LT(1);
					match(LOWER);
					{
						asnTypeV = type();
					}
					match(ASSIGN_OP);
					{
						val = value();
					}
					if (inputState.guessing == 0)
					{

						asnTypeV.setName(lo.getText());
						module.asnTypes.add(asnTypeV);
						// module.asnValues.add(asnTypeV);

					}
				}
			}
			else
			{
				boolean synPredMatched657 = false;
				if (((LA(1) == UPPER) && (LA(2) == LITERAL_MACRO)))
				{
					int _m657 = mark();
					synPredMatched657 = true;
					inputState.guessing++;
					try
					{
						{
							match(UPPER);
							match(LITERAL_MACRO);
							match(ASSIGN_OP);
							match(BEGIN_KW);
							{
								_loop656: do
								{
									if ((_tokenSet_15.member(LA(1))))
									{
										{
											match(_tokenSet_15);
										}
									}
									else
									{
										break _loop656;
									}

								}
								while (true);
							}
							match(END_KW);
						}
					}
					catch (RecognitionException pe)
					{
						synPredMatched657 = false;
					}
					rewind(_m657);
					inputState.guessing--;
				}
				if (synPredMatched657)
				{
					match(UPPER);
					match(LITERAL_MACRO);
					match(ASSIGN_OP);
					match(BEGIN_KW);
					{
						_loop660: do
						{
							if ((_tokenSet_15.member(LA(1))))
							{
								{
									match(_tokenSet_15);
								}
							}
							else
							{
								break _loop660;
							}

						}
						while (true);
					}
					match(END_KW);
				}
				else
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_14);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final ArrayList symbol_list() throws RecognitionException, TokenStreamException
	{
		ArrayList symlist;

		symlist = new ArrayList();
		String s = "";

		try
		{ // for error handling
			{
				{
					s = symbol();
					if (inputState.guessing == 0)
					{
						symlist.add(s);
					}
				}
				{
					_loop644: do
					{
						if ((LA(1) == COMMA))
						{
							match(COMMA);
							{
								s = symbol();
								if (inputState.guessing == 0)
								{
									symlist.add(s);
								}
							}
						}
						else
						{
							break _loop644;
						}

					}
					while (true);
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_16);
			}
			else
			{
				throw ex;
			}
		}
		return symlist;
	}

	public final void symbols_from_module(ASNModule module) throws RecognitionException,
				TokenStreamException
	{

		Token up = null;

		SymbolsFromModule sym = new SymbolsFromModule();
		String s = "";
		AsnModuleIdentifier mid;
		AsnDefinedValue defval;
		ArrayList arl;
		AsnOidComponentList cmplist;

		try
		{ // for error handling
			{
				{
					arl = symbol_list();
					if (inputState.guessing == 0)
					{
						sym.symbolList = arl;
					}
				}
				match(FROM_KW);
				{
					up = LT(1);
					match(UPPER);
					if (inputState.guessing == 0)
					{
						sym.modref = up.getText();
					}
					{
						if ((LA(1) == L_BRACE))
						{
							cmplist = obj_id_comp_lst();
							if (inputState.guessing == 0)
							{
								sym.isOidValue = true;
								sym.cmplist = cmplist;
							}
						}
						else
						{
							boolean synPredMatched637 = false;
							if (((LA(1) == UPPER || LA(1) == LOWER)
										&& (_tokenSet_17.member(LA(2))) && (_tokenSet_18
											.member(LA(3)))))
							{
								int _m637 = mark();
								synPredMatched637 = true;
								inputState.guessing++;
								try
								{
									{
										defined_value();
									}
								}
								catch (RecognitionException pe)
								{
									synPredMatched637 = false;
								}
								rewind(_m637);
								inputState.guessing--;
							}
							if (synPredMatched637)
							{
								{
									defval = defined_value();
									if (inputState.guessing == 0)
									{
										sym.isDefinedValue = true;
										sym.defval = defval;
									}
								}
							}
							else if ((_tokenSet_19.member(LA(1)))
										&& (_tokenSet_18.member(LA(2)))
										&& (_tokenSet_20.member(LA(3))))
							{
							}
							else
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
						}
					}
				}
			}
			if (inputState.guessing == 0)
			{
				module.importSymbolFromModuleList.add(sym);
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_19);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final String symbol() throws RecognitionException, TokenStreamException
	{
		String s;

		Token up = null;
		Token lid = null;

		s = "";

		try
		{ // for error handling
			switch (LA(1))
			{
				case UPPER:
					{
						up = LT(1);
						match(UPPER);
						if (inputState.guessing == 0)
						{
							s = up.getText();
						}
						break;
					}
				case LOWER:
					{
						lid = LT(1);
						match(LOWER);
						if (inputState.guessing == 0)
						{
							s = lid.getText();
						}
						break;
					}
				case OBJECT_KW:
				case LITERAL_OPERATION_KW:
				case LITERAL_ERROR_KW:
				case LITERAL_BIND:
				case LITERAL_UNBIND:
				case 127:
				case 128:
				case LITERAL_EXTENSION:
				case LITERAL_EXTENSIONS:
				case 131:
				case LITERAL_TOKEN:
				case 133:
				case 134:
				case LITERAL_PORT:
				case LITERAL_REFINE:
				case 137:
				case 138:
				case 139:
				case 140:
				case LITERAL_ALGORITHM:
				case LITERAL_ENCRYPTED:
				case LITERAL_SIGNED:
				case LITERAL_SIGNATURE:
				case LITERAL_PROTECTED:
				case 146:
					{
						s = macroName();
						break;
					}
				default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_21);
			}
			else
			{
				throw ex;
			}
		}
		return s;
	}

	public final String macroName() throws RecognitionException, TokenStreamException
	{
		String s;

		s = "";

		try
		{ // for error handling
			switch (LA(1))
			{
				case LITERAL_OPERATION_KW:
					{
						match(LITERAL_OPERATION_KW);
						if (inputState.guessing == 0)
						{
							s = "OPERATION";
						}
						break;
					}
				case LITERAL_ERROR_KW:
					{
						match(LITERAL_ERROR_KW);
						if (inputState.guessing == 0)
						{
							s = "ERROR";
						}
						break;
					}
				case LITERAL_BIND:
					{
						match(LITERAL_BIND);
						if (inputState.guessing == 0)
						{
							s = "BIND";
						}
						break;
					}
				case LITERAL_UNBIND:
					{
						match(LITERAL_UNBIND);
						if (inputState.guessing == 0)
						{
							s = "UNBIND";
						}
						break;
					}
				case 127:
					{
						match(127);
						if (inputState.guessing == 0)
						{
							s = "APPLICATION-SERVICE-ELEMENT";
						}
						break;
					}
				case 128:
					{
						match(128);
						if (inputState.guessing == 0)
						{
							s = "APPLICATION-CONTEXT";
						}
						break;
					}
				case LITERAL_EXTENSION:
					{
						match(LITERAL_EXTENSION);
						if (inputState.guessing == 0)
						{
							s = "EXTENSION";
						}
						break;
					}
				case LITERAL_EXTENSIONS:
					{
						match(LITERAL_EXTENSIONS);
						if (inputState.guessing == 0)
						{
							s = "EXTENSIONS";
						}
						break;
					}
				case 131:
					{
						match(131);
						if (inputState.guessing == 0)
						{
							s = "EXTENSION-ATTRIBUTE";
						}
						break;
					}
				case LITERAL_TOKEN:
					{
						match(LITERAL_TOKEN);
						if (inputState.guessing == 0)
						{
							s = "TOKEN";
						}
						break;
					}
				case 133:
					{
						match(133);
						if (inputState.guessing == 0)
						{
							s = "TOKEN-DATA";
						}
						break;
					}
				case 134:
					{
						match(134);
						if (inputState.guessing == 0)
						{
							s = "SECURITY-CATEGORY";
						}
						break;
					}
				case OBJECT_KW:
					{
						match(OBJECT_KW);
						if (inputState.guessing == 0)
						{
							s = "OBJECT";
						}
						break;
					}
				case LITERAL_PORT:
					{
						match(LITERAL_PORT);
						if (inputState.guessing == 0)
						{
							s = "PORT";
						}
						break;
					}
				case LITERAL_REFINE:
					{
						match(LITERAL_REFINE);
						if (inputState.guessing == 0)
						{
							s = "REFINE";
						}
						break;
					}
				case 137:
					{
						match(137);
						if (inputState.guessing == 0)
						{
							s = "ABSTRACT-BIND";
						}
						break;
					}
				case 138:
					{
						match(138);
						if (inputState.guessing == 0)
						{
							s = "ABSTRACT-UNBIND";
						}
						break;
					}
				case 139:
					{
						match(139);
						if (inputState.guessing == 0)
						{
							s = "ABSTRACT-OPERATION";
						}
						break;
					}
				case 140:
					{
						match(140);
						if (inputState.guessing == 0)
						{
							s = "ABSTRACT-ERROR";
						}
						break;
					}
				case LITERAL_ALGORITHM:
					{
						match(LITERAL_ALGORITHM);
						if (inputState.guessing == 0)
						{
							s = "ALGORITHM";
						}
						break;
					}
				case LITERAL_ENCRYPTED:
					{
						match(LITERAL_ENCRYPTED);
						if (inputState.guessing == 0)
						{
							s = "ENCRYPTED";
						}
						break;
					}
				case LITERAL_SIGNED:
					{
						match(LITERAL_SIGNED);
						if (inputState.guessing == 0)
						{
							s = "SIGNED";
						}
						break;
					}
				case LITERAL_SIGNATURE:
					{
						match(LITERAL_SIGNATURE);
						if (inputState.guessing == 0)
						{
							s = "SIGNATURE";
						}
						break;
					}
				case LITERAL_PROTECTED:
					{
						match(LITERAL_PROTECTED);
						if (inputState.guessing == 0)
						{
							s = "PROTECTED";
						}
						break;
					}
				case 146:
					{
						match(146);
						if (inputState.guessing == 0)
						{
							s = "OBJECT-TYPE";
						}
						break;
					}
				default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_21);
			}
			else
			{
				throw ex;
			}
		}
		return s;
	}

	public final ASNType type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		asnType = null;

		try
		{ // for error handling
			switch (LA(1))
			{
				case ANY_KW:
				case BIT_KW:
				case BOOLEAN_KW:
				case CHARACTER_KW:
				case CHOICE_KW:
				case EMBEDDED_KW:
				case ENUMERATED_KW:
				case EXTERNAL_KW:
				case GENERALIZED_TIME_KW:
				case GENERAL_STR_KW:
				case GRAPHIC_STR_KW:
				case IA5_STRING_KW:
				case INTEGER_KW:
				case NULL_KW:
				case NUMERIC_STR_KW:
				case OBJECT_KW:
				case OCTET_KW:
				case PRINTABLE_STR_KW:
				case REAL_KW:
				case RELATIVE_KW:
				case SEQUENCE_KW:
				case SET_KW:
				case TELETEX_STR_KW:
				case UNIVERSAL_STR_KW:
				case UTC_TIME_KW:
				case UTF8_STR_KW:
				case VIDEOTEX_STR_KW:
				case VISIBLE_STR_KW:
				case L_BRACKET:
				case BMP_STR_KW:
				case ISO646_STR_KW:
				case T61_STR_KW:
					{
						{
							asnType = built_in_type();
						}
						break;
					}
				case UPPER:
					{
						{
							asnType = defined_type();
						}
						break;
					}
				case LOWER:
					{
						{
							asnType = selection_type();
						}
						break;
					}
				case 146:
				case LITERAL_OPERATION:
				case ERROR_KW:
					{
						{
							asnType = macros_type();
						}
						break;
					}
				default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final AsnValue value() throws RecognitionException, TokenStreamException
	{
		AsnValue value;

		Token c = null;

		value = new AsnValue();
		AsnSequenceValue seqval;
		AsnDefinedValue defval;
		String aStr;
		// AsnSignedNumber
		BigInteger num;
		AsnOidComponentList cmplst;

		try
		{ // for error handling
			switch (LA(1))
			{
				case TRUE_KW:
					{
						{
							match(TRUE_KW);
							if (inputState.guessing == 0)
							{
								value.isTrueKW = true;
							}
						}
						break;
					}
				case FALSE_KW:
					{
						{
							match(FALSE_KW);
							if (inputState.guessing == 0)
							{
								value.isFalseKW = true;
							}
						}
						break;
					}
				case NULL_KW:
					{
						{
							match(NULL_KW);
							if (inputState.guessing == 0)
							{
								value.isNullKW = true;
							}
						}
						break;
					}
				case C_STRING:
					{
						{
							c = LT(1);
							match(C_STRING);
							if (inputState.guessing == 0)
							{
								value.isCString = true;
								value.cStr = c.getText();
							}
						}
						break;
					}
				case MINUS:
				case NUMBER:
					{
						{
							num = signed_number();
							if (inputState.guessing == 0)
							{
								value.isSignedNumber = true;
								value.signedNumber = num;
							}
						}
						break;
					}
				case PLUS_INFINITY_KW:
					{
						{
							match(PLUS_INFINITY_KW);
							if (inputState.guessing == 0)
							{
								value.isPlusInfinity = true;
							}
						}
						break;
					}
				case MINUS_INFINITY_KW:
					{
						{
							match(MINUS_INFINITY_KW);
							if (inputState.guessing == 0)
							{
								value.isMinusInfinity = true;
							}
						}
						break;
					}
				default:
					boolean synPredMatched929 = false;
					if (((LA(1) == UPPER || LA(1) == LOWER) && (_tokenSet_23.member(LA(2))) && (_tokenSet_24
								.member(LA(3)))))
					{
						int _m929 = mark();
						synPredMatched929 = true;
						inputState.guessing++;
						try
						{
							{
								defined_value();
							}
						}
						catch (RecognitionException pe)
						{
							synPredMatched929 = false;
						}
						rewind(_m929);
						inputState.guessing--;
					}
					if (synPredMatched929)
					{
						{
							defval = defined_value();
							if (inputState.guessing == 0)
							{
								value.isDefinedValue = true;
								value.definedValue = defval;
							}
						}
					}
					else
					{
						boolean synPredMatched935 = false;
						if (((LA(1) == LOWER) && (_tokenSet_25.member(LA(2))) && (_tokenSet_26
									.member(LA(3)))))
						{
							int _m935 = mark();
							synPredMatched935 = true;
							inputState.guessing++;
							try
							{
								{
									choice_value(value);
								}
							}
							catch (RecognitionException pe)
							{
								synPredMatched935 = false;
							}
							rewind(_m935);
							inputState.guessing--;
						}
						if (synPredMatched935)
						{
							{
								choice_value(value);
								if (inputState.guessing == 0)
								{
									value.isChoiceValue = true;
								}
							}
						}
						else
						{
							boolean synPredMatched938 = false;
							if (((LA(1) == L_BRACE)
										&& (LA(2) == COMMA || LA(2) == R_BRACE || LA(2) == LOWER) && (_tokenSet_27
											.member(LA(3)))))
							{
								int _m938 = mark();
								synPredMatched938 = true;
								inputState.guessing++;
								try
								{
									{
										sequence_value();
									}
								}
								catch (RecognitionException pe)
								{
									synPredMatched938 = false;
								}
								rewind(_m938);
								inputState.guessing--;
							}
							if (synPredMatched938)
							{
								{
									seqval = sequence_value();
									if (inputState.guessing == 0)
									{
										value.isSequenceValue = true;
										value.seqval = seqval;
									}
								}
							}
							else
							{
								boolean synPredMatched941 = false;
								if (((LA(1) == L_BRACE) && (_tokenSet_28.member(LA(2))) && (_tokenSet_26
											.member(LA(3)))))
								{
									int _m941 = mark();
									synPredMatched941 = true;
									inputState.guessing++;
									try
									{
										{
											sequenceof_value(value);
										}
									}
									catch (RecognitionException pe)
									{
										synPredMatched941 = false;
									}
									rewind(_m941);
									inputState.guessing--;
								}
								if (synPredMatched941)
								{
									{
										sequenceof_value(value);
										if (inputState.guessing == 0)
										{
											value.isSequenceOfValue = true;
										}
									}
								}
								else
								{
									boolean synPredMatched944 = false;
									if (((LA(1) == L_BRACE || LA(1) == B_STRING || LA(1) == H_STRING)
												&& (_tokenSet_27.member(LA(2))) && (_tokenSet_24
													.member(LA(3)))))
									{
										int _m944 = mark();
										synPredMatched944 = true;
										inputState.guessing++;
										try
										{
											{
												cstr_value(value);
											}
										}
										catch (RecognitionException pe)
										{
											synPredMatched944 = false;
										}
										rewind(_m944);
										inputState.guessing--;
									}
									if (synPredMatched944)
									{
										{
											cstr_value(value);
											if (inputState.guessing == 0)
											{
												value.isCStrValue = true;
											}
										}
									}
									else
									{
										boolean synPredMatched947 = false;
										if (((LA(1) == L_BRACE)
													&& ((LA(2) >= NUMBER && LA(2) <= LOWER)) && (_tokenSet_4
														.member(LA(3)))))
										{
											int _m947 = mark();
											synPredMatched947 = true;
											inputState.guessing++;
											try
											{
												{
													obj_id_comp_lst();
												}
											}
											catch (RecognitionException pe)
											{
												synPredMatched947 = false;
											}
											rewind(_m947);
											inputState.guessing--;
										}
										if (synPredMatched947)
										{
											{
												cmplst = obj_id_comp_lst();
												if (inputState.guessing == 0)
												{
													value.isAsnOIDValue = true;
													value.oidval = cmplst;
												}
											}
										}
										else
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
									}
								}
							}
						}
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_27);
			}
			else
			{
				throw ex;
			}
		}
		return value;
	}

	public final ASNType built_in_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		asnType = null;

		try
		{ // for error handling
			switch (LA(1))
			{
				case ANY_KW:
					{
						{
							asnType = any_type();
						}
						break;
					}
				case BIT_KW:
					{
						{
							asnType = bit_string_type();
						}
						break;
					}
				case BOOLEAN_KW:
					{
						{
							asnType = boolean_type();
						}
						break;
					}
				case CHARACTER_KW:
				case GENERALIZED_TIME_KW:
				case GENERAL_STR_KW:
				case GRAPHIC_STR_KW:
				case IA5_STRING_KW:
				case NUMERIC_STR_KW:
				case PRINTABLE_STR_KW:
				case TELETEX_STR_KW:
				case UNIVERSAL_STR_KW:
				case UTC_TIME_KW:
				case UTF8_STR_KW:
				case VIDEOTEX_STR_KW:
				case VISIBLE_STR_KW:
				case BMP_STR_KW:
				case ISO646_STR_KW:
				case T61_STR_KW:
					{
						{
							asnType = character_str_type();
						}
						break;
					}
				case CHOICE_KW:
					{
						{
							asnType = choice_type();
						}
						break;
					}
				case EMBEDDED_KW:
					{
						{
							asnType = embedded_type();
						}
						match(EMBEDDED_KW);
						match(PDV_KW);
						break;
					}
				case ENUMERATED_KW:
					{
						{
							asnType = enum_type();
						}
						break;
					}
				case EXTERNAL_KW:
					{
						{
							asnType = external_type();
						}
						break;
					}
				case INTEGER_KW:
					{
						{
							asnType = integer_type();
						}
						break;
					}
				case NULL_KW:
					{
						{
							asnType = null_type();
						}
						break;
					}
				case OBJECT_KW:
					{
						{
							asnType = object_identifier_type();
						}
						break;
					}
				case OCTET_KW:
					{
						{
							asnType = octetString_type();
						}
						break;
					}
				case REAL_KW:
					{
						{
							asnType = real_type();
						}
						break;
					}
				case RELATIVE_KW:
					{
						{
							asnType = relativeOid_type();
						}
						break;
					}
				case L_BRACKET:
					{
						{
							asnType = tagged_type();
						}
						break;
					}
				default:
					if ((LA(1) == SEQUENCE_KW) && (LA(2) == L_BRACE))
					{
						{
							asnType = sequence_type();
						}
					}
					else if ((LA(1) == SEQUENCE_KW) && (LA(2) == OF_KW || LA(2) == SIZE_KW))
					{
						{
							asnType = sequenceof_type();
						}
					}
					else if ((LA(1) == SET_KW) && (LA(2) == L_BRACE))
					{
						{
							asnType = set_type();
						}
					}
					else if ((LA(1) == SET_KW) && (LA(2) == OF_KW || LA(2) == SIZE_KW))
					{
						{
							asnType = setof_type();
						}
					}
					else
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType defined_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		Token up = null;
		Token up1 = null;

		ASNDefinedType deftype = new ASNDefinedType();
		AsnConstraint cnstrnt;
		asnType = null;

		try
		{ // for error handling
			{
				{
					if ((LA(1) == UPPER) && (LA(2) == DOT))
					{
						up = LT(1);
						match(UPPER);
						if (inputState.guessing == 0)
						{
							deftype.isModuleReference = true;
							deftype.moduleReference = up.getText();
						}
						match(DOT);
					}
					else if ((LA(1) == UPPER) && (_tokenSet_29.member(LA(2))))
					{
					}
					else
					{
						throw new NoViableAltException(LT(1), getFilename());
					}

				}
				{
					up1 = LT(1);
					match(UPPER);
					if (inputState.guessing == 0)
					{
						deftype.setReference(up1.getText());
					}
				}
				{
					switch (LA(1))
					{
						case L_PAREN:
							{
								cnstrnt = constraint();
								if (inputState.guessing == 0)
								{
									deftype.constraint = cnstrnt;
								}
								break;
							}
						case ANY_KW:
						case BIT_KW:
						case BOOLEAN_KW:
						case CHARACTER_KW:
						case CHOICE_KW:
						case DEFAULT_KW:
						case EMBEDDED_KW:
						case END_KW:
						case ENUMERATED_KW:
						case ERRORS_KW:
						case EXTERNAL_KW:
						case FALSE_KW:
						case GENERALIZED_TIME_KW:
						case GENERAL_STR_KW:
						case GRAPHIC_STR_KW:
						case IA5_STRING_KW:
						case INTEGER_KW:
						case INTERSECTION_KW:
						case LINKED_KW:
						case MINUS_INFINITY_KW:
						case NULL_KW:
						case NUMERIC_STR_KW:
						case OBJECT_KW:
						case OCTET_KW:
						case OPTIONAL_KW:
						case PLUS_INFINITY_KW:
						case PRINTABLE_STR_KW:
						case REAL_KW:
						case RELATIVE_KW:
						case RESULT_KW:
						case SEQUENCE_KW:
						case SET_KW:
						case TELETEX_STR_KW:
						case TRUE_KW:
						case UNION_KW:
						case UNIVERSAL_STR_KW:
						case UTC_TIME_KW:
						case UTF8_STR_KW:
						case VIDEOTEX_STR_KW:
						case VISIBLE_STR_KW:
						case ASSIGN_OP:
						case BAR:
						case COLON:
						case COMMA:
						case EXCLAMATION:
						case INTERSECTION:
						case L_BRACE:
						case L_BRACKET:
						case MINUS:
						case R_BRACE:
						case R_PAREN:
						case NUMBER:
						case UPPER:
						case LOWER:
						case B_STRING:
						case H_STRING:
						case C_STRING:
						case 146:
						case BMP_STR_KW:
						case ISO646_STR_KW:
						case T61_STR_KW:
						case LITERAL_OPERATION:
						case ERROR_KW:
						case LITERAL_ACCESS:
						case EXCEPT:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
			}
			if (inputState.guessing == 0)
			{
				asnType = deftype;
				deftype = null;
				cnstrnt = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType selection_type() throws RecognitionException,
				TokenStreamException
	{
		ASNType asnType;

		Token lid = null;

		AsnSelectionType seltype = new AsnSelectionType();
		asnType = null;
		Object obj1;

		try
		{ // for error handling
			{
				{
					lid = LT(1);
					match(LOWER);
					if (inputState.guessing == 0)
					{
						seltype.selectionID = lid.getText();
					}
				}
				match(LESS);
				{
					obj1 = type();
					if (inputState.guessing == 0)
					{
						seltype.type = obj1;
					}
				}
			}
			if (inputState.guessing == 0)
			{
				asnType = seltype;
				seltype = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType macros_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		asnType = null;

		try
		{ // for error handling
			switch (LA(1))
			{
				case LITERAL_OPERATION:
					{
						{
							asnType = operation_macro();
						}
						break;
					}
				case ERROR_KW:
					{
						{
							asnType = error_macro();
						}
						break;
					}
				case 146:
					{
						{
							asnType = objecttype_macro();
						}
						break;
					}
				default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType any_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		Token lid = null;

		asnType = null;
		ASNAny any = new ASNAny();

		try
		{ // for error handling
			{
				match(ANY_KW);
				{
					switch (LA(1))
					{
						case DEFINED_KW:
							{
								match(DEFINED_KW);
								match(BY_KW);
								if (inputState.guessing == 0)
								{
									any.isDefinedBy = true;
								}
								lid = LT(1);
								match(LOWER);
								if (inputState.guessing == 0)
								{
									any.definedByType = lid.getText();
								}
								break;
							}
						case ANY_KW:
						case BIT_KW:
						case BOOLEAN_KW:
						case CHARACTER_KW:
						case CHOICE_KW:
						case DEFAULT_KW:
						case EMBEDDED_KW:
						case END_KW:
						case ENUMERATED_KW:
						case ERRORS_KW:
						case EXTERNAL_KW:
						case FALSE_KW:
						case GENERALIZED_TIME_KW:
						case GENERAL_STR_KW:
						case GRAPHIC_STR_KW:
						case IA5_STRING_KW:
						case INTEGER_KW:
						case INTERSECTION_KW:
						case LINKED_KW:
						case MINUS_INFINITY_KW:
						case NULL_KW:
						case NUMERIC_STR_KW:
						case OBJECT_KW:
						case OCTET_KW:
						case OPTIONAL_KW:
						case PLUS_INFINITY_KW:
						case PRINTABLE_STR_KW:
						case REAL_KW:
						case RELATIVE_KW:
						case RESULT_KW:
						case SEQUENCE_KW:
						case SET_KW:
						case TELETEX_STR_KW:
						case TRUE_KW:
						case UNION_KW:
						case UNIVERSAL_STR_KW:
						case UTC_TIME_KW:
						case UTF8_STR_KW:
						case VIDEOTEX_STR_KW:
						case VISIBLE_STR_KW:
						case ASSIGN_OP:
						case BAR:
						case COLON:
						case COMMA:
						case EXCLAMATION:
						case INTERSECTION:
						case L_BRACE:
						case L_BRACKET:
						case MINUS:
						case R_BRACE:
						case R_PAREN:
						case NUMBER:
						case UPPER:
						case LOWER:
						case B_STRING:
						case H_STRING:
						case C_STRING:
						case 146:
						case BMP_STR_KW:
						case ISO646_STR_KW:
						case T61_STR_KW:
						case LITERAL_OPERATION:
						case ERROR_KW:
						case LITERAL_ACCESS:
						case EXCEPT:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
			}
			if (inputState.guessing == 0)
			{
				asnType = any;
				any = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType bit_string_type() throws RecognitionException,
				TokenStreamException
	{
		ASNType asnType;

		ASNBitString bstr = new ASNBitString();
		// AsnNamedNumberList nnlst ;
		ASNNamedNumber nnum;
		AsnConstraint cnstrnt;
		asnType = null;

		try
		{ // for error handling
			{
				match(BIT_KW);
				match(STRING_KW);
				{
					if ((LA(1) == L_BRACE) && (LA(2) == LOWER) && (LA(3) == L_PAREN))
					{
						nnum = namedNumber_map();
						if (inputState.guessing == 0)
						{
							bstr.asnNamedNumber = nnum;
						}
					}
					else if ((_tokenSet_29.member(LA(1))) && (_tokenSet_30.member(LA(2)))
								&& (_tokenSet_31.member(LA(3))))
					{
					}
					else
					{
						throw new NoViableAltException(LT(1), getFilename());
					}

				}
				{
					switch (LA(1))
					{
						case L_PAREN:
							{
								cnstrnt = constraint();
								if (inputState.guessing == 0)
								{
									bstr.constraint = cnstrnt;
								}
								break;
							}
						case ANY_KW:
						case BIT_KW:
						case BOOLEAN_KW:
						case CHARACTER_KW:
						case CHOICE_KW:
						case DEFAULT_KW:
						case EMBEDDED_KW:
						case END_KW:
						case ENUMERATED_KW:
						case ERRORS_KW:
						case EXTERNAL_KW:
						case FALSE_KW:
						case GENERALIZED_TIME_KW:
						case GENERAL_STR_KW:
						case GRAPHIC_STR_KW:
						case IA5_STRING_KW:
						case INTEGER_KW:
						case INTERSECTION_KW:
						case LINKED_KW:
						case MINUS_INFINITY_KW:
						case NULL_KW:
						case NUMERIC_STR_KW:
						case OBJECT_KW:
						case OCTET_KW:
						case OPTIONAL_KW:
						case PLUS_INFINITY_KW:
						case PRINTABLE_STR_KW:
						case REAL_KW:
						case RELATIVE_KW:
						case RESULT_KW:
						case SEQUENCE_KW:
						case SET_KW:
						case TELETEX_STR_KW:
						case TRUE_KW:
						case UNION_KW:
						case UNIVERSAL_STR_KW:
						case UTC_TIME_KW:
						case UTF8_STR_KW:
						case VIDEOTEX_STR_KW:
						case VISIBLE_STR_KW:
						case ASSIGN_OP:
						case BAR:
						case COLON:
						case COMMA:
						case EXCLAMATION:
						case INTERSECTION:
						case L_BRACE:
						case L_BRACKET:
						case MINUS:
						case R_BRACE:
						case R_PAREN:
						case NUMBER:
						case UPPER:
						case LOWER:
						case B_STRING:
						case H_STRING:
						case C_STRING:
						case 146:
						case BMP_STR_KW:
						case ISO646_STR_KW:
						case T61_STR_KW:
						case LITERAL_OPERATION:
						case ERROR_KW:
						case LITERAL_ACCESS:
						case EXCEPT:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
			}
			if (inputState.guessing == 0)
			{
				asnType = bstr;
				nnum = null;
				cnstrnt = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType boolean_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		asnType = null;

		try
		{ // for error handling
			match(BOOLEAN_KW);
			if (inputState.guessing == 0)
			{
				asnType = new ASNBoolean();
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType character_str_type() throws RecognitionException,
				TokenStreamException
	{
		ASNType asnType;

		ASNCharacterString cstr = null;
		asnType = null;
		AsnConstraint cnstrnt = null;

		try
		{ // for error handling
			{
				switch (LA(1))
				{
					case CHARACTER_KW:
						{
							{
								match(CHARACTER_KW);
								match(STRING_KW);
							}
							break;
						}
					case GENERALIZED_TIME_KW:
					case GENERAL_STR_KW:
					case GRAPHIC_STR_KW:
					case IA5_STRING_KW:
					case NUMERIC_STR_KW:
					case PRINTABLE_STR_KW:
					case TELETEX_STR_KW:
					case UNIVERSAL_STR_KW:
					case UTC_TIME_KW:
					case UTF8_STR_KW:
					case VIDEOTEX_STR_KW:
					case VISIBLE_STR_KW:
					case BMP_STR_KW:
					case ISO646_STR_KW:
					case T61_STR_KW:
						{
							{
								cstr = character_specific_type();
							}
							{
								switch (LA(1))
								{
									case L_PAREN:
										{
											cnstrnt = constraint();
											break;
										}
									case ANY_KW:
									case BIT_KW:
									case BOOLEAN_KW:
									case CHARACTER_KW:
									case CHOICE_KW:
									case DEFAULT_KW:
									case EMBEDDED_KW:
									case END_KW:
									case ENUMERATED_KW:
									case ERRORS_KW:
									case EXTERNAL_KW:
									case FALSE_KW:
									case GENERALIZED_TIME_KW:
									case GENERAL_STR_KW:
									case GRAPHIC_STR_KW:
									case IA5_STRING_KW:
									case INTEGER_KW:
									case INTERSECTION_KW:
									case LINKED_KW:
									case MINUS_INFINITY_KW:
									case NULL_KW:
									case NUMERIC_STR_KW:
									case OBJECT_KW:
									case OCTET_KW:
									case OPTIONAL_KW:
									case PLUS_INFINITY_KW:
									case PRINTABLE_STR_KW:
									case REAL_KW:
									case RELATIVE_KW:
									case RESULT_KW:
									case SEQUENCE_KW:
									case SET_KW:
									case TELETEX_STR_KW:
									case TRUE_KW:
									case UNION_KW:
									case UNIVERSAL_STR_KW:
									case UTC_TIME_KW:
									case UTF8_STR_KW:
									case VIDEOTEX_STR_KW:
									case VISIBLE_STR_KW:
									case ASSIGN_OP:
									case BAR:
									case COLON:
									case COMMA:
									case EXCLAMATION:
									case INTERSECTION:
									case L_BRACE:
									case L_BRACKET:
									case MINUS:
									case R_BRACE:
									case R_PAREN:
									case NUMBER:
									case UPPER:
									case LOWER:
									case B_STRING:
									case H_STRING:
									case C_STRING:
									case 146:
									case BMP_STR_KW:
									case ISO646_STR_KW:
									case T61_STR_KW:
									case LITERAL_OPERATION:
									case ERROR_KW:
									case LITERAL_ACCESS:
									case EXCEPT:
										{
											break;
										}
									default:
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
								}
							}
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			if (inputState.guessing == 0)
			{
				cstr.constraint = cnstrnt;
				asnType = cstr;
				cnstrnt = null;
				cstr = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType choice_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		ASNChoice ch = new ASNChoice();
		List<AbstractSyntaxTreeNode> eltplst;
		asnType = null;

		try
		{ // for error handling
			{
				match(CHOICE_KW);
				match(L_BRACE);
				{
					eltplst = elementType_list();
				}
				match(R_BRACE);
			}
			if (inputState.guessing == 0)
			{
				asnType = ch;
				ch.addChildren(eltplst);
				eltplst = null;
				ch = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType embedded_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		asnType = null;

		try
		{ // for error handling
			{
				match(EMBEDDED_KW);
				match(PDV_KW);
			}
			if (inputState.guessing == 0)
			{
				asnType = new AsnEmbedded();
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_32);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType enum_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		ASNEnumerated enumtyp = new ASNEnumerated();
		// AsnNamedNumberList nnlst;
		ASNNamedNumber nnum;
		asnType = null;

		try
		{ // for error handling
			{
				match(ENUMERATED_KW);
				{
					nnum = namedNumber_map();
					if (inputState.guessing == 0)
					{
						enumtyp.asnNamedNumber = nnum;
					}
				}
			}
			if (inputState.guessing == 0)
			{
				asnType = enumtyp;
				enumtyp = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType external_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		asnType = null;

		try
		{ // for error handling
			match(EXTERNAL_KW);
			if (inputState.guessing == 0)
			{
				asnType = new AsnExternal();
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType integer_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		ASNInteger intgr = new ASNInteger();
		// AsnNamedNumberList numlst;
		ASNNamedNumber nnum;
		AsnConstraint cnstrnt;
		asnType = null;

		try
		{ // for error handling
			{
				match(INTEGER_KW);
				{
					if ((LA(1) == L_BRACE) && (LA(2) == LOWER) && (LA(3) == L_PAREN))
					{
						nnum = namedNumber_map();
						if (inputState.guessing == 0)
						{
							intgr.asnNamedNumber = nnum;
						}
					}
					else if ((LA(1) == L_PAREN))
					{
						cnstrnt = constraint();
						if (inputState.guessing == 0)
						{
							intgr.constraint = cnstrnt;
						}
					}
					else if ((_tokenSet_22.member(LA(1))) && (_tokenSet_30.member(LA(2)))
								&& (_tokenSet_31.member(LA(3))))
					{
					}
					else
					{
						throw new NoViableAltException(LT(1), getFilename());
					}

				}
			}
			if (inputState.guessing == 0)
			{
				asnType = intgr;
				nnum = null;
				cnstrnt = null;
				intgr = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType null_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		ASNNull nll = new ASNNull();
		asnType = null;

		try
		{ // for error handling
			match(NULL_KW);
			if (inputState.guessing == 0)
			{
				asnType = nll;
				nll = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType object_identifier_type() throws RecognitionException,
				TokenStreamException
	{
		ASNType asnType;

		asnType = null;

		try
		{ // for error handling
			match(OBJECT_KW);
			match(IDENTIFIER_KW);
			if (inputState.guessing == 0)
			{
				asnType = new ASNObjectIdentifier();
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType octetString_type() throws RecognitionException,
				TokenStreamException
	{
		ASNType asnType;

		ASNOctetString oct = new ASNOctetString();
		AsnConstraint cnstrnt;
		asnType = null;

		try
		{ // for error handling
			{
				match(OCTET_KW);
				match(STRING_KW);
				{
					switch (LA(1))
					{
						case L_PAREN:
							{
								cnstrnt = constraint();
								if (inputState.guessing == 0)
								{
									oct.constraint = cnstrnt;
								}
								break;
							}
						case ANY_KW:
						case BIT_KW:
						case BOOLEAN_KW:
						case CHARACTER_KW:
						case CHOICE_KW:
						case DEFAULT_KW:
						case EMBEDDED_KW:
						case END_KW:
						case ENUMERATED_KW:
						case ERRORS_KW:
						case EXTERNAL_KW:
						case FALSE_KW:
						case GENERALIZED_TIME_KW:
						case GENERAL_STR_KW:
						case GRAPHIC_STR_KW:
						case IA5_STRING_KW:
						case INTEGER_KW:
						case INTERSECTION_KW:
						case LINKED_KW:
						case MINUS_INFINITY_KW:
						case NULL_KW:
						case NUMERIC_STR_KW:
						case OBJECT_KW:
						case OCTET_KW:
						case OPTIONAL_KW:
						case PLUS_INFINITY_KW:
						case PRINTABLE_STR_KW:
						case REAL_KW:
						case RELATIVE_KW:
						case RESULT_KW:
						case SEQUENCE_KW:
						case SET_KW:
						case TELETEX_STR_KW:
						case TRUE_KW:
						case UNION_KW:
						case UNIVERSAL_STR_KW:
						case UTC_TIME_KW:
						case UTF8_STR_KW:
						case VIDEOTEX_STR_KW:
						case VISIBLE_STR_KW:
						case ASSIGN_OP:
						case BAR:
						case COLON:
						case COMMA:
						case EXCLAMATION:
						case INTERSECTION:
						case L_BRACE:
						case L_BRACKET:
						case MINUS:
						case R_BRACE:
						case R_PAREN:
						case NUMBER:
						case UPPER:
						case LOWER:
						case B_STRING:
						case H_STRING:
						case C_STRING:
						case 146:
						case BMP_STR_KW:
						case ISO646_STR_KW:
						case T61_STR_KW:
						case LITERAL_OPERATION:
						case ERROR_KW:
						case LITERAL_ACCESS:
						case EXCEPT:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
			}
			if (inputState.guessing == 0)
			{
				asnType = oct;
				cnstrnt = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType real_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		ASNReal rl = new ASNReal();
		asnType = null;

		try
		{ // for error handling
			match(REAL_KW);
			if (inputState.guessing == 0)
			{
				asnType = rl;
				rl = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType relativeOid_type() throws RecognitionException,
				TokenStreamException
	{
		ASNType asnType;

		asnType = null;

		try
		{ // for error handling
			match(RELATIVE_KW);
			match(MINUS);
			match(OID_KW);
			if (inputState.guessing == 0)
			{
				asnType = new ASNRelativeObjectIdentifier();
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType sequence_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		ASNSequence seq = new ASNSequence();
		List<AbstractSyntaxTreeNode> eltplist = null;
		asnType = null;

		try
		{ // for error handling
			{
				match(SEQUENCE_KW);
				if (inputState.guessing == 0)
				{
					seq.isSequence = true;
				}
				match(L_BRACE);
				{
					switch (LA(1))
					{
						case COMPONENTS_KW:
						case LOWER:
							{
								eltplist = elementType_list();
								break;
							}
						case R_BRACE:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				match(R_BRACE);
			}
			if (inputState.guessing == 0)
			{
				asnType = seq;
				asnType.addChildren(eltplist);
				eltplist = null;
				seq = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType sequenceof_type() throws RecognitionException,
				TokenStreamException
	{
		ASNType asnType;

		ASNSequenceOf seqof = new ASNSequenceOf();
		AsnConstraint cns;
		asnType = null;
		ASNType obj1;
		String s;

		try
		{ // for error handling
			{
				match(SEQUENCE_KW);
				{
					switch (LA(1))
					{
						case SIZE_KW:
							{
								match(SIZE_KW);
								{
									cns = constraint();
									if (inputState.guessing == 0)
									{
										seqof.constraint = cns;
									}
								}
								break;
							}
						case OF_KW:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				match(OF_KW);
				{
					obj1 = type();
					if (inputState.guessing == 0)
					{
						if ((ASNDefinedType.class).isInstance(obj1))
						{
							// seqof.isDefinedType=true;
							seqof.setReference(obj1.getReference());
						}
						else
						{
							seqof.addChild(obj1);
						}

					}
				}
			}
			if (inputState.guessing == 0)
			{
				asnType = seqof;
				cns = null;
				seqof = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType set_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		ASNSet set = new ASNSet();
		List<AbstractSyntaxTreeNode> eltplist = null;
		asnType = null;

		try
		{ // for error handling
			{
				match(SET_KW);
				match(L_BRACE);
				{
					switch (LA(1))
					{
						case COMPONENTS_KW:
						case LOWER:
							{
								eltplist = elementType_list();
								break;
							}
						case R_BRACE:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				match(R_BRACE);
			}
			if (inputState.guessing == 0)
			{
				asnType = set;
				asnType.addChildren(eltplist);
				eltplist = null;
				set = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType setof_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		ASNSetOf setof = new ASNSetOf();
		AsnConstraint cns = null;
		asnType = null;
		ASNType obj1 = null;
		String s;

		try
		{ // for error handling
			{
				match(SET_KW);
				{
					switch (LA(1))
					{
						case SIZE_KW:
							{
								match(SIZE_KW);
								{
									cns = constraint();
									if (inputState.guessing == 0)
									{
										setof.constraint = cns;
									}
								}
								break;
							}
						case OF_KW:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				match(OF_KW);
				{
					obj1 = type();
					if (inputState.guessing == 0)
					{
						if ((ASNDefinedType.class).isInstance(obj1))
						{
							// seqof.isDefinedType=true;
							setof.setReference(obj1.getReference());
						}
						else
						{
							setof.addChild(obj1);
						}

					}
				}
			}
			if (inputState.guessing == 0)
			{
				asnType = setof;
				cns = null;
				obj1 = null;
				setof = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType tagged_type() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		// AsnTaggedType tgtyp = new AsnTaggedType();
		Tag t;
		boolean s = false;
		asnType = null;

		try
		{ // for error handling
			{
				{
					t = tag();
				}
				{
					switch (LA(1))
					{
						case AUTOMATIC_KW:
						case EXPLICIT_KW:
						case IMPLICIT_KW:
							{
								s = tag_default();
								break;
							}
						case ANY_KW:
						case BIT_KW:
						case BOOLEAN_KW:
						case CHARACTER_KW:
						case CHOICE_KW:
						case EMBEDDED_KW:
						case ENUMERATED_KW:
						case EXTERNAL_KW:
						case GENERALIZED_TIME_KW:
						case GENERAL_STR_KW:
						case GRAPHIC_STR_KW:
						case IA5_STRING_KW:
						case INTEGER_KW:
						case NULL_KW:
						case NUMERIC_STR_KW:
						case OBJECT_KW:
						case OCTET_KW:
						case PRINTABLE_STR_KW:
						case REAL_KW:
						case RELATIVE_KW:
						case SEQUENCE_KW:
						case SET_KW:
						case TELETEX_STR_KW:
						case UNIVERSAL_STR_KW:
						case UTC_TIME_KW:
						case UTF8_STR_KW:
						case VIDEOTEX_STR_KW:
						case VISIBLE_STR_KW:
						case L_BRACKET:
						case UPPER:
						case LOWER:
						case 146:
						case BMP_STR_KW:
						case ISO646_STR_KW:
						case T61_STR_KW:
						case LITERAL_OPERATION:
						case ERROR_KW:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				{
					asnType = type();
					if (inputState.guessing == 0)
					{
						t.setExplicit(s);
						asnType.setTag(t);
					}
				}
			}
			if (inputState.guessing == 0)
			{
				t = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNNamedNumber namedNumber_map() throws RecognitionException,
				TokenStreamException
	{
		ASNNamedNumber ann;

		ann = new ASNNamedNumber();
		ASNNamedNumber nnum;

		try
		{ // for error handling
			{
				match(L_BRACE);
				{
					nnum = namedNumber();
					if (inputState.guessing == 0)
					{
						ann.putAll(nnum);
					}
				}
				{
					_loop855: do
					{
						if ((LA(1) == COMMA))
						{
							match(COMMA);
							{
								nnum = namedNumber();
								if (inputState.guessing == 0)
								{
									ann.putAll(nnum);
								}
							}
						}
						else
						{
							break _loop855;
						}

					}
					while (true);
				}
				match(R_BRACE);
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_29);
			}
			else
			{
				throw ex;
			}
		}
		return ann;
	}

	public final AsnConstraint constraint() throws RecognitionException,
				TokenStreamException
	{
		AsnConstraint cnstrnt;

		cnstrnt = new AsnConstraint();

		try
		{ // for error handling
			match(L_PAREN);
			{
				switch (LA(1))
				{
					case ANY_KW:
					case BIT_KW:
					case BOOLEAN_KW:
					case CHARACTER_KW:
					case CHOICE_KW:
					case EMBEDDED_KW:
					case ENUMERATED_KW:
					case EXTERNAL_KW:
					case FALSE_KW:
					case FROM_KW:
					case GENERALIZED_TIME_KW:
					case GENERAL_STR_KW:
					case GRAPHIC_STR_KW:
					case IA5_STRING_KW:
					case INTEGER_KW:
					case MINUS_INFINITY_KW:
					case MIN_KW:
					case NULL_KW:
					case NUMERIC_STR_KW:
					case OBJECT_KW:
					case OCTET_KW:
					case PLUS_INFINITY_KW:
					case PRINTABLE_STR_KW:
					case REAL_KW:
					case RELATIVE_KW:
					case SEQUENCE_KW:
					case SET_KW:
					case SIZE_KW:
					case TELETEX_STR_KW:
					case TRUE_KW:
					case UNIVERSAL_STR_KW:
					case UTC_TIME_KW:
					case UTF8_STR_KW:
					case VIDEOTEX_STR_KW:
					case VISIBLE_STR_KW:
					case WITH_KW:
					case L_BRACE:
					case L_BRACKET:
					case L_PAREN:
					case MINUS:
					case NUMBER:
					case UPPER:
					case LOWER:
					case B_STRING:
					case H_STRING:
					case C_STRING:
					case 146:
					case BMP_STR_KW:
					case ISO646_STR_KW:
					case T61_STR_KW:
					case LITERAL_OPERATION:
					case ERROR_KW:
					case ALL:
					case INCLUDES:
					case PATTERN_KW:
						{
							element_set_specs(cnstrnt);
							if (inputState.guessing == 0)
							{
								cnstrnt.isElementSetSpecs = true;
							}
							break;
						}
					case EXCLAMATION:
					case R_PAREN:
						{
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			{
				switch (LA(1))
				{
					case EXCLAMATION:
						{
							exception_spec(cnstrnt);
							break;
						}
					case R_PAREN:
						{
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			match(R_PAREN);
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_33);
			}
			else
			{
				throw ex;
			}
		}
		return cnstrnt;
	}

	public final ASNCharacterString character_specific_type() throws RecognitionException,
				TokenStreamException
	{
		ASNCharacterString asnType;

		Token s1 = null;
		Token s2 = null;
		Token s3 = null;
		Token s4 = null;
		Token s5 = null;
		Token s6 = null;
		Token s7 = null;
		Token s8 = null;
		Token s9 = null;
		Token s10 = null;
		Token s11 = null;
		Token s12 = null;
		Token s13 = null;
		Token s14 = null;
		Token s15 = null;

		asnType = null;

		try
		{ // for error handling
			switch (LA(1))
			{
				case BMP_STR_KW:
					{
						{
							s1 = LT(1);
							match(BMP_STR_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNBMPString();
							}
						}
						break;
					}
				case GENERALIZED_TIME_KW:
					{
						{
							s2 = LT(1);
							match(GENERALIZED_TIME_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNGeneralizedTime();
							}
						}
						break;
					}
				case GENERAL_STR_KW:
					{
						{
							s3 = LT(1);
							match(GENERAL_STR_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNGeneralString();
							}
						}
						break;
					}
				case GRAPHIC_STR_KW:
					{
						{
							s4 = LT(1);
							match(GRAPHIC_STR_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNGraphicString();
							}
						}
						break;
					}
				case IA5_STRING_KW:
					{
						{
							s5 = LT(1);
							match(IA5_STRING_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNIA5String();
							}
						}
						break;
					}
				case ISO646_STR_KW:
					{
						{
							s6 = LT(1);
							match(ISO646_STR_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNISO646String();
							}
						}
						break;
					}
				case NUMERIC_STR_KW:
					{
						{
							s7 = LT(1);
							match(NUMERIC_STR_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNNumericString();
							}
						}
						break;
					}
				case PRINTABLE_STR_KW:
					{
						{
							s8 = LT(1);
							match(PRINTABLE_STR_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNPrintableString();
							}
						}
						break;
					}
				case TELETEX_STR_KW:
					{
						{
							s9 = LT(1);
							match(TELETEX_STR_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNTeletexString();
							}
						}
						break;
					}
				case T61_STR_KW:
					{
						{
							s10 = LT(1);
							match(T61_STR_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNT61String();
							}
						}
						break;
					}
				case UNIVERSAL_STR_KW:
					{
						{
							s11 = LT(1);
							match(UNIVERSAL_STR_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNUniversalString();
							}
						}
						break;
					}
				case UTF8_STR_KW:
					{
						{
							s12 = LT(1);
							match(UTF8_STR_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNUTF8String();
							}
						}
						break;
					}
				case UTC_TIME_KW:
					{
						{
							s13 = LT(1);
							match(UTC_TIME_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNUTCTime();
							}
						}
						break;
					}
				case VIDEOTEX_STR_KW:
					{
						{
							s14 = LT(1);
							match(VIDEOTEX_STR_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNVideotexString();
							}
						}
						break;
					}
				case VISIBLE_STR_KW:
					{
						{
							s15 = LT(1);
							match(VISIBLE_STR_KW);
							if (inputState.guessing == 0)
							{
								asnType = new ASNVisibleString();
							}
						}
						break;
					}
				default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_29);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final List<AbstractSyntaxTreeNode> elementType_list()
				throws RecognitionException, TokenStreamException
	{
		List<AbstractSyntaxTreeNode> eleTypelist;

		eleTypelist = new ArrayList<AbstractSyntaxTreeNode>();
		ASNType eleType;

		try
		{ // for error handling
			{
				eleType = elementType();
				if (inputState.guessing == 0)
				{
					eleTypelist.add(eleType);
				}
				{
					_loop839: do
					{
						if ((LA(1) == COMMA))
						{
							match(COMMA);
							{
								eleType = elementType();
								if (inputState.guessing == 0)
								{
									eleTypelist.add(eleType);
								}
							}
						}
						else
						{
							break _loop839;
						}

					}
					while (true);
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_34);
			}
			else
			{
				throw ex;
			}
		}
		return eleTypelist;
	}

	public final Tag tag() throws RecognitionException, TokenStreamException
	{
		Tag tag;

		tag = new Tag();
		int c;

		try
		{ // for error handling
			{
				match(L_BRACKET);
				{
					switch (LA(1))
					{
						case APPLICATION_KW:
						case PRIVATE_KW:
						case UNIVERSAL_KW:
							{
								c = clazz();
								if (inputState.guessing == 0)
								{
									tag.setClass(c);
								}
								break;
							}
						case NUMBER:
						case LOWER:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				{
					class_NUMBER(tag);
				}
				match(R_BRACKET);
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_35);
			}
			else
			{
				throw ex;
			}
		}
		return tag;
	}

	public final int clazz() throws RecognitionException, TokenStreamException
	{
		int val;

		Token c1 = null;
		Token c2 = null;
		Token c3 = null;

		val = 0;

		try
		{ // for error handling
			switch (LA(1))
			{
				case UNIVERSAL_KW:
					{
						{
							c1 = LT(1);
							match(UNIVERSAL_KW);
							if (inputState.guessing == 0)
							{
								val = 0x00;
							}
						}
						break;
					}
				case APPLICATION_KW:
					{
						{
							c2 = LT(1);
							match(APPLICATION_KW);
							if (inputState.guessing == 0)
							{
								val = 0x40;
							}
						}
						break;
					}
				case PRIVATE_KW:
					{
						{
							c3 = LT(1);
							match(PRIVATE_KW);
							if (inputState.guessing == 0)
							{
								val = 0xC0;
							}
						}
						break;
					}
				default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_36);
			}
			else
			{
				throw ex;
			}
		}
		return val;
	}

	public final void class_NUMBER(Tag tag) throws RecognitionException,
				TokenStreamException
	{

		Token num = null;
		Token lid = null;

		String s = "";

		try
		{ // for error handling
			{
				switch (LA(1))
				{
					case NUMBER:
						{
							{
								num = LT(1);
								match(NUMBER);
								if (inputState.guessing == 0)
								{
									s = num.getText();
									tag.setValue(new Integer(s));
								}
							}
							break;
						}
					case LOWER:
						{
							{
								lid = LT(1);
								match(LOWER);
							}
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_37);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final ASNType operation_macro() throws RecognitionException,
				TokenStreamException
	{
		ASNType asnType;

		Token ld1 = null;
		Token ld2 = null;

		OperationMacro op = new OperationMacro();
		String s;
		asnType = null;
		ASNType obj1;
		ASNType obj2;

		try
		{ // for error handling
			{
				match(LITERAL_OPERATION);
				{
					switch (LA(1))
					{
						case ARGUMENT_KW:
							{
								match(ARGUMENT_KW);
								{
									if ((LA(1) == LOWER) && (_tokenSet_11.member(LA(2)))
												&& (_tokenSet_38.member(LA(3))))
									{
										ld1 = LT(1);
										match(LOWER);
										if (inputState.guessing == 0)
										{
											op.argumentTypeIdentifier = ld1.getText();
										}
									}
									else if ((_tokenSet_11.member(LA(1)))
												&& (_tokenSet_38.member(LA(2)))
												&& (_tokenSet_39.member(LA(3))))
									{
									}
									else
									{
										throw new NoViableAltException(LT(1), getFilename());
									}

								}
								{
									{
										obj2 = type();
									}
									if (inputState.guessing == 0)
									{
										op.argumentType = obj2;
										op.isArgumentName = true;
										if ((ASNDefinedType.class).isInstance(obj2))
											op.argumentName = obj2.getType();
										else
											op.argumentName = op.argumentTypeIdentifier;

									}
								}
								break;
							}
						case ANY_KW:
						case BIT_KW:
						case BOOLEAN_KW:
						case CHARACTER_KW:
						case CHOICE_KW:
						case DEFAULT_KW:
						case EMBEDDED_KW:
						case END_KW:
						case ENUMERATED_KW:
						case ERRORS_KW:
						case EXTERNAL_KW:
						case FALSE_KW:
						case GENERALIZED_TIME_KW:
						case GENERAL_STR_KW:
						case GRAPHIC_STR_KW:
						case IA5_STRING_KW:
						case INTEGER_KW:
						case INTERSECTION_KW:
						case LINKED_KW:
						case MINUS_INFINITY_KW:
						case NULL_KW:
						case NUMERIC_STR_KW:
						case OBJECT_KW:
						case OCTET_KW:
						case OPTIONAL_KW:
						case PLUS_INFINITY_KW:
						case PRINTABLE_STR_KW:
						case REAL_KW:
						case RELATIVE_KW:
						case RESULT_KW:
						case SEQUENCE_KW:
						case SET_KW:
						case TELETEX_STR_KW:
						case TRUE_KW:
						case UNION_KW:
						case UNIVERSAL_STR_KW:
						case UTC_TIME_KW:
						case UTF8_STR_KW:
						case VIDEOTEX_STR_KW:
						case VISIBLE_STR_KW:
						case ASSIGN_OP:
						case BAR:
						case COLON:
						case COMMA:
						case EXCLAMATION:
						case INTERSECTION:
						case L_BRACE:
						case L_BRACKET:
						case MINUS:
						case R_BRACE:
						case R_PAREN:
						case NUMBER:
						case UPPER:
						case LOWER:
						case B_STRING:
						case H_STRING:
						case C_STRING:
						case 146:
						case BMP_STR_KW:
						case ISO646_STR_KW:
						case T61_STR_KW:
						case LITERAL_OPERATION:
						case ERROR_KW:
						case LITERAL_ACCESS:
						case EXCEPT:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				{
					if ((LA(1) == RESULT_KW) && (_tokenSet_40.member(LA(2)))
								&& (_tokenSet_30.member(LA(3))))
					{
						match(RESULT_KW);
						if (inputState.guessing == 0)
						{
							op.isResult = true;
						}
						{
							boolean synPredMatched789 = false;
							if (((LA(1) == SEMI)))
							{
								int _m789 = mark();
								synPredMatched789 = true;
								inputState.guessing++;
								try
								{
									{
										match(SEMI);
									}
								}
								catch (RecognitionException pe)
								{
									synPredMatched789 = false;
								}
								rewind(_m789);
								inputState.guessing--;
							}
							if (synPredMatched789)
							{
								match(SEMI);
							}
							else
							{
								boolean synPredMatched792 = false;
								if (((_tokenSet_11.member(LA(1))) && (_tokenSet_38.member(LA(2))) && (_tokenSet_39
											.member(LA(3)))))
								{
									int _m792 = mark();
									synPredMatched792 = true;
									inputState.guessing++;
									try
									{
										{
											{
												if ((LA(1) == LOWER) && (_tokenSet_11.member(LA(2)))
															&& (true))
												{
													match(LOWER);
												}
												else if ((_tokenSet_11.member(LA(1))) && (true) && (true))
												{
												}
												else
												{
													throw new NoViableAltException(LT(1),
																getFilename());
												}

											}
											type();
										}
									}
									catch (RecognitionException pe)
									{
										synPredMatched792 = false;
									}
									rewind(_m792);
									inputState.guessing--;
								}
								if (synPredMatched792)
								{
									{
										if ((LA(1) == LOWER) && (_tokenSet_11.member(LA(2)))
													&& (_tokenSet_38.member(LA(3))))
										{
											ld2 = LT(1);
											match(LOWER);
											if (inputState.guessing == 0)
											{
												op.resultTypeIdentifier = ld2.getText();
											}
										}
										else if ((_tokenSet_11.member(LA(1)))
													&& (_tokenSet_38.member(LA(2)))
													&& (_tokenSet_39.member(LA(3))))
										{
										}
										else
										{
											throw new NoViableAltException(LT(1), getFilename());
										}

									}
									{
										obj1 = type();
										if (inputState.guessing == 0)
										{
											op.resultType = obj1;
											op.isResultName = true;
											if ((ASNDefinedType.class).isInstance(obj1))
												op.resultName = obj1.getType();
											else
												op.resultName = op.resultTypeIdentifier;

										}
									}
								}
								else if ((_tokenSet_22.member(LA(1)))
											&& (_tokenSet_30.member(LA(2)))
											&& (_tokenSet_31.member(LA(3))))
								{
								}
								else
								{
									throw new NoViableAltException(LT(1), getFilename());
								}
							}
						}
					}
					else if ((_tokenSet_22.member(LA(1))) && (_tokenSet_30.member(LA(2)))
								&& (_tokenSet_31.member(LA(3))))
					{
					}
					else
					{
						throw new NoViableAltException(LT(1), getFilename());
					}

				}
				{
					if ((LA(1) == ERRORS_KW) && (LA(2) == L_BRACE)
								&& (_tokenSet_41.member(LA(3))))
					{
						match(ERRORS_KW);
						match(L_BRACE);
						{
							switch (LA(1))
							{
								case ANY_KW:
								case BIT_KW:
								case BOOLEAN_KW:
								case CHARACTER_KW:
								case CHOICE_KW:
								case EMBEDDED_KW:
								case ENUMERATED_KW:
								case EXTERNAL_KW:
								case FALSE_KW:
								case GENERALIZED_TIME_KW:
								case GENERAL_STR_KW:
								case GRAPHIC_STR_KW:
								case IA5_STRING_KW:
								case INTEGER_KW:
								case MINUS_INFINITY_KW:
								case NULL_KW:
								case NUMERIC_STR_KW:
								case OBJECT_KW:
								case OCTET_KW:
								case PLUS_INFINITY_KW:
								case PRINTABLE_STR_KW:
								case REAL_KW:
								case RELATIVE_KW:
								case SEQUENCE_KW:
								case SET_KW:
								case TELETEX_STR_KW:
								case TRUE_KW:
								case UNIVERSAL_STR_KW:
								case UTC_TIME_KW:
								case UTF8_STR_KW:
								case VIDEOTEX_STR_KW:
								case VISIBLE_STR_KW:
								case L_BRACE:
								case L_BRACKET:
								case MINUS:
								case NUMBER:
								case UPPER:
								case LOWER:
								case B_STRING:
								case H_STRING:
								case C_STRING:
								case 146:
								case BMP_STR_KW:
								case ISO646_STR_KW:
								case T61_STR_KW:
								case LITERAL_OPERATION:
								case ERROR_KW:
									{
										operation_errorlist(op);
										if (inputState.guessing == 0)
										{
											op.isErrors = true;
										}
										break;
									}
								case R_BRACE:
									{
										break;
									}
								default:
									{
										throw new NoViableAltException(LT(1), getFilename());
									}
							}
						}
						match(R_BRACE);
					}
					else if ((_tokenSet_22.member(LA(1))) && (_tokenSet_30.member(LA(2)))
								&& (_tokenSet_31.member(LA(3))))
					{
					}
					else
					{
						throw new NoViableAltException(LT(1), getFilename());
					}

				}
				{
					if ((LA(1) == LINKED_KW) && (LA(2) == L_BRACE)
								&& (_tokenSet_41.member(LA(3))))
					{
						match(LINKED_KW);
						match(L_BRACE);
						{
							switch (LA(1))
							{
								case ANY_KW:
								case BIT_KW:
								case BOOLEAN_KW:
								case CHARACTER_KW:
								case CHOICE_KW:
								case EMBEDDED_KW:
								case ENUMERATED_KW:
								case EXTERNAL_KW:
								case FALSE_KW:
								case GENERALIZED_TIME_KW:
								case GENERAL_STR_KW:
								case GRAPHIC_STR_KW:
								case IA5_STRING_KW:
								case INTEGER_KW:
								case MINUS_INFINITY_KW:
								case NULL_KW:
								case NUMERIC_STR_KW:
								case OBJECT_KW:
								case OCTET_KW:
								case PLUS_INFINITY_KW:
								case PRINTABLE_STR_KW:
								case REAL_KW:
								case RELATIVE_KW:
								case SEQUENCE_KW:
								case SET_KW:
								case TELETEX_STR_KW:
								case TRUE_KW:
								case UNIVERSAL_STR_KW:
								case UTC_TIME_KW:
								case UTF8_STR_KW:
								case VIDEOTEX_STR_KW:
								case VISIBLE_STR_KW:
								case L_BRACE:
								case L_BRACKET:
								case MINUS:
								case NUMBER:
								case UPPER:
								case LOWER:
								case B_STRING:
								case H_STRING:
								case C_STRING:
								case 146:
								case BMP_STR_KW:
								case ISO646_STR_KW:
								case T61_STR_KW:
								case LITERAL_OPERATION:
								case ERROR_KW:
									{
										linkedOp_list(op);
										if (inputState.guessing == 0)
										{
											op.isLinkedOperation = true;
										}
										break;
									}
								case R_BRACE:
									{
										break;
									}
								default:
									{
										throw new NoViableAltException(LT(1), getFilename());
									}
							}
						}
						match(R_BRACE);
					}
					else if ((_tokenSet_22.member(LA(1))) && (_tokenSet_30.member(LA(2)))
								&& (_tokenSet_31.member(LA(3))))
					{
					}
					else
					{
						throw new NoViableAltException(LT(1), getFilename());
					}

				}
			}
			if (inputState.guessing == 0)
			{
				asnType = op;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType error_macro() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		Token lw = null;

		ErrorMacro merr = new ErrorMacro();
		asnType = null;
		ASNType obj1;

		try
		{ // for error handling
			{
				match(ERROR_KW);
				{
					switch (LA(1))
					{
						case PARAMETER_KW:
							{
								match(PARAMETER_KW);
								if (inputState.guessing == 0)
								{
									merr.isParameter = true;
								}
								{
									{
										if ((LA(1) == LOWER) && (_tokenSet_11.member(LA(2)))
													&& (_tokenSet_38.member(LA(3))))
										{
											lw = LT(1);
											match(LOWER);
											if (inputState.guessing == 0)
											{
												merr.parameterName = lw.getText();
											}
										}
										else if ((_tokenSet_11.member(LA(1)))
													&& (_tokenSet_38.member(LA(2)))
													&& (_tokenSet_39.member(LA(3))))
										{
										}
										else
										{
											throw new NoViableAltException(LT(1), getFilename());
										}

									}
									{
										obj1 = type();
										if (inputState.guessing == 0)
										{
											if ((ASNDefinedType.class).isInstance(obj1))
											{
												merr.isDefinedType = true;
												merr.typeName = obj1.getType();
											}
											else
											{
												merr.typeReference = obj1;
											}

										}
									}
								}
								break;
							}
						case ANY_KW:
						case BIT_KW:
						case BOOLEAN_KW:
						case CHARACTER_KW:
						case CHOICE_KW:
						case DEFAULT_KW:
						case EMBEDDED_KW:
						case END_KW:
						case ENUMERATED_KW:
						case ERRORS_KW:
						case EXTERNAL_KW:
						case FALSE_KW:
						case GENERALIZED_TIME_KW:
						case GENERAL_STR_KW:
						case GRAPHIC_STR_KW:
						case IA5_STRING_KW:
						case INTEGER_KW:
						case INTERSECTION_KW:
						case LINKED_KW:
						case MINUS_INFINITY_KW:
						case NULL_KW:
						case NUMERIC_STR_KW:
						case OBJECT_KW:
						case OCTET_KW:
						case OPTIONAL_KW:
						case PLUS_INFINITY_KW:
						case PRINTABLE_STR_KW:
						case REAL_KW:
						case RELATIVE_KW:
						case RESULT_KW:
						case SEQUENCE_KW:
						case SET_KW:
						case TELETEX_STR_KW:
						case TRUE_KW:
						case UNION_KW:
						case UNIVERSAL_STR_KW:
						case UTC_TIME_KW:
						case UTF8_STR_KW:
						case VIDEOTEX_STR_KW:
						case VISIBLE_STR_KW:
						case ASSIGN_OP:
						case BAR:
						case COLON:
						case COMMA:
						case EXCLAMATION:
						case INTERSECTION:
						case L_BRACE:
						case L_BRACKET:
						case MINUS:
						case R_BRACE:
						case R_PAREN:
						case NUMBER:
						case UPPER:
						case LOWER:
						case B_STRING:
						case H_STRING:
						case C_STRING:
						case 146:
						case BMP_STR_KW:
						case ISO646_STR_KW:
						case T61_STR_KW:
						case LITERAL_OPERATION:
						case ERROR_KW:
						case LITERAL_ACCESS:
						case EXCEPT:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
			}
			if (inputState.guessing == 0)
			{
				asnType = merr;
				merr = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNType objecttype_macro() throws RecognitionException,
				TokenStreamException
	{
		ASNType asnType;

		Token lid = null;
		Token lid1 = null;

		ObjectType objtype = new ObjectType();
		AsnValue val;
		asnType = null;
		String s;
		Object typ;

		try
		{ // for error handling
			{
				match(146);
				match(LITERAL_SYNTAX);
				{
					typ = type();
					if (inputState.guessing == 0)
					{
						objtype.type = typ;
					}
				}
				{
					match(LITERAL_ACCESS);
					lid = LT(1);
					match(LOWER);
					if (inputState.guessing == 0)
					{
						objtype.accessPart = lid.getText();
					}
				}
				{
					match(LITERAL_STATUS);
					lid1 = LT(1);
					match(LOWER);
					if (inputState.guessing == 0)
					{
						objtype.statusPart = lid1.getText();
					}
				}
				{
					switch (LA(1))
					{
						case LITERAL_DESCRIPTION:
							{
								match(LITERAL_DESCRIPTION);
								match(CHARACTER_KW);
								match(STRING_KW);
								break;
							}
						case ANY_KW:
						case BIT_KW:
						case BOOLEAN_KW:
						case CHARACTER_KW:
						case CHOICE_KW:
						case DEFAULT_KW:
						case EMBEDDED_KW:
						case END_KW:
						case ENUMERATED_KW:
						case ERRORS_KW:
						case EXTERNAL_KW:
						case FALSE_KW:
						case GENERALIZED_TIME_KW:
						case GENERAL_STR_KW:
						case GRAPHIC_STR_KW:
						case IA5_STRING_KW:
						case INTEGER_KW:
						case INTERSECTION_KW:
						case LINKED_KW:
						case MINUS_INFINITY_KW:
						case NULL_KW:
						case NUMERIC_STR_KW:
						case OBJECT_KW:
						case OCTET_KW:
						case OPTIONAL_KW:
						case PLUS_INFINITY_KW:
						case PRINTABLE_STR_KW:
						case REAL_KW:
						case RELATIVE_KW:
						case RESULT_KW:
						case SEQUENCE_KW:
						case SET_KW:
						case TELETEX_STR_KW:
						case TRUE_KW:
						case UNION_KW:
						case UNIVERSAL_STR_KW:
						case UTC_TIME_KW:
						case UTF8_STR_KW:
						case VIDEOTEX_STR_KW:
						case VISIBLE_STR_KW:
						case ASSIGN_OP:
						case BAR:
						case COLON:
						case COMMA:
						case EXCLAMATION:
						case INTERSECTION:
						case L_BRACE:
						case L_BRACKET:
						case MINUS:
						case R_BRACE:
						case R_PAREN:
						case NUMBER:
						case UPPER:
						case LOWER:
						case B_STRING:
						case H_STRING:
						case C_STRING:
						case 146:
						case BMP_STR_KW:
						case ISO646_STR_KW:
						case T61_STR_KW:
						case LITERAL_OPERATION:
						case ERROR_KW:
						case LITERAL_ACCESS:
						case LITERAL_REFERENCE:
						case LITERAL_INDEX:
						case LITERAL_DEFVAL:
						case EXCEPT:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				{
					switch (LA(1))
					{
						case LITERAL_REFERENCE:
							{
								match(LITERAL_REFERENCE);
								match(CHARACTER_KW);
								match(STRING_KW);
								break;
							}
						case ANY_KW:
						case BIT_KW:
						case BOOLEAN_KW:
						case CHARACTER_KW:
						case CHOICE_KW:
						case DEFAULT_KW:
						case EMBEDDED_KW:
						case END_KW:
						case ENUMERATED_KW:
						case ERRORS_KW:
						case EXTERNAL_KW:
						case FALSE_KW:
						case GENERALIZED_TIME_KW:
						case GENERAL_STR_KW:
						case GRAPHIC_STR_KW:
						case IA5_STRING_KW:
						case INTEGER_KW:
						case INTERSECTION_KW:
						case LINKED_KW:
						case MINUS_INFINITY_KW:
						case NULL_KW:
						case NUMERIC_STR_KW:
						case OBJECT_KW:
						case OCTET_KW:
						case OPTIONAL_KW:
						case PLUS_INFINITY_KW:
						case PRINTABLE_STR_KW:
						case REAL_KW:
						case RELATIVE_KW:
						case RESULT_KW:
						case SEQUENCE_KW:
						case SET_KW:
						case TELETEX_STR_KW:
						case TRUE_KW:
						case UNION_KW:
						case UNIVERSAL_STR_KW:
						case UTC_TIME_KW:
						case UTF8_STR_KW:
						case VIDEOTEX_STR_KW:
						case VISIBLE_STR_KW:
						case ASSIGN_OP:
						case BAR:
						case COLON:
						case COMMA:
						case EXCLAMATION:
						case INTERSECTION:
						case L_BRACE:
						case L_BRACKET:
						case MINUS:
						case R_BRACE:
						case R_PAREN:
						case NUMBER:
						case UPPER:
						case LOWER:
						case B_STRING:
						case H_STRING:
						case C_STRING:
						case 146:
						case BMP_STR_KW:
						case ISO646_STR_KW:
						case T61_STR_KW:
						case LITERAL_OPERATION:
						case ERROR_KW:
						case LITERAL_ACCESS:
						case LITERAL_INDEX:
						case LITERAL_DEFVAL:
						case EXCEPT:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				{
					switch (LA(1))
					{
						case LITERAL_INDEX:
							{
								match(LITERAL_INDEX);
								match(L_BRACE);
								{
									typeorvaluelist(objtype);
								}
								match(R_BRACE);
								break;
							}
						case ANY_KW:
						case BIT_KW:
						case BOOLEAN_KW:
						case CHARACTER_KW:
						case CHOICE_KW:
						case DEFAULT_KW:
						case EMBEDDED_KW:
						case END_KW:
						case ENUMERATED_KW:
						case ERRORS_KW:
						case EXTERNAL_KW:
						case FALSE_KW:
						case GENERALIZED_TIME_KW:
						case GENERAL_STR_KW:
						case GRAPHIC_STR_KW:
						case IA5_STRING_KW:
						case INTEGER_KW:
						case INTERSECTION_KW:
						case LINKED_KW:
						case MINUS_INFINITY_KW:
						case NULL_KW:
						case NUMERIC_STR_KW:
						case OBJECT_KW:
						case OCTET_KW:
						case OPTIONAL_KW:
						case PLUS_INFINITY_KW:
						case PRINTABLE_STR_KW:
						case REAL_KW:
						case RELATIVE_KW:
						case RESULT_KW:
						case SEQUENCE_KW:
						case SET_KW:
						case TELETEX_STR_KW:
						case TRUE_KW:
						case UNION_KW:
						case UNIVERSAL_STR_KW:
						case UTC_TIME_KW:
						case UTF8_STR_KW:
						case VIDEOTEX_STR_KW:
						case VISIBLE_STR_KW:
						case ASSIGN_OP:
						case BAR:
						case COLON:
						case COMMA:
						case EXCLAMATION:
						case INTERSECTION:
						case L_BRACE:
						case L_BRACKET:
						case MINUS:
						case R_BRACE:
						case R_PAREN:
						case NUMBER:
						case UPPER:
						case LOWER:
						case B_STRING:
						case H_STRING:
						case C_STRING:
						case 146:
						case BMP_STR_KW:
						case ISO646_STR_KW:
						case T61_STR_KW:
						case LITERAL_OPERATION:
						case ERROR_KW:
						case LITERAL_ACCESS:
						case LITERAL_DEFVAL:
						case EXCEPT:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				{
					switch (LA(1))
					{
						case LITERAL_DEFVAL:
							{
								match(LITERAL_DEFVAL);
								match(L_BRACE);
								{
									val = value();
									if (inputState.guessing == 0)
									{
										objtype.value = val;
									}
								}
								match(R_BRACE);
								break;
							}
						case ANY_KW:
						case BIT_KW:
						case BOOLEAN_KW:
						case CHARACTER_KW:
						case CHOICE_KW:
						case DEFAULT_KW:
						case EMBEDDED_KW:
						case END_KW:
						case ENUMERATED_KW:
						case ERRORS_KW:
						case EXTERNAL_KW:
						case FALSE_KW:
						case GENERALIZED_TIME_KW:
						case GENERAL_STR_KW:
						case GRAPHIC_STR_KW:
						case IA5_STRING_KW:
						case INTEGER_KW:
						case INTERSECTION_KW:
						case LINKED_KW:
						case MINUS_INFINITY_KW:
						case NULL_KW:
						case NUMERIC_STR_KW:
						case OBJECT_KW:
						case OCTET_KW:
						case OPTIONAL_KW:
						case PLUS_INFINITY_KW:
						case PRINTABLE_STR_KW:
						case REAL_KW:
						case RELATIVE_KW:
						case RESULT_KW:
						case SEQUENCE_KW:
						case SET_KW:
						case TELETEX_STR_KW:
						case TRUE_KW:
						case UNION_KW:
						case UNIVERSAL_STR_KW:
						case UTC_TIME_KW:
						case UTF8_STR_KW:
						case VIDEOTEX_STR_KW:
						case VISIBLE_STR_KW:
						case ASSIGN_OP:
						case BAR:
						case COLON:
						case COMMA:
						case EXCLAMATION:
						case INTERSECTION:
						case L_BRACE:
						case L_BRACKET:
						case MINUS:
						case R_BRACE:
						case R_PAREN:
						case NUMBER:
						case UPPER:
						case LOWER:
						case B_STRING:
						case H_STRING:
						case C_STRING:
						case 146:
						case BMP_STR_KW:
						case ISO646_STR_KW:
						case T61_STR_KW:
						case LITERAL_OPERATION:
						case ERROR_KW:
						case LITERAL_ACCESS:
						case EXCEPT:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
			}
			if (inputState.guessing == 0)
			{
				asnType = objtype;
				objtype = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_22);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final void operation_errorlist(OperationMacro oper)
				throws RecognitionException, TokenStreamException
	{

		Object obj;

		try
		{ // for error handling
			obj = typeorvalue();
			if (inputState.guessing == 0)
			{
				oper.errorList.add(obj);
			}
			{
				_loop802: do
				{
					if ((LA(1) == COMMA))
					{
						match(COMMA);
						{
							obj = typeorvalue();
							if (inputState.guessing == 0)
							{
								oper.errorList.add(obj);
							}
						}
					}
					else
					{
						break _loop802;
					}

				}
				while (true);
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_34);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final void linkedOp_list(OperationMacro oper) throws RecognitionException,
				TokenStreamException
	{

		Object obj;

		try
		{ // for error handling
			obj = typeorvalue();
			if (inputState.guessing == 0)
			{
				oper.linkedOpList.add(obj);
			}
			{
				_loop806: do
				{
					if ((LA(1) == COMMA))
					{
						match(COMMA);
						{
							obj = typeorvalue();
							if (inputState.guessing == 0)
							{
								oper.linkedOpList.add(obj);
							}
						}
					}
					else
					{
						break _loop806;
					}

				}
				while (true);
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_34);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final Object typeorvalue() throws RecognitionException, TokenStreamException
	{
		Object obj;

		Object obj1;
		obj = null;

		try
		{ // for error handling
			{
				boolean synPredMatched833 = false;
				if (((_tokenSet_11.member(LA(1))) && (_tokenSet_42.member(LA(2))) && (_tokenSet_43
							.member(LA(3)))))
				{
					int _m833 = mark();
					synPredMatched833 = true;
					inputState.guessing++;
					try
					{
						{
							type();
						}
					}
					catch (RecognitionException pe)
					{
						synPredMatched833 = false;
					}
					rewind(_m833);
					inputState.guessing--;
				}
				if (synPredMatched833)
				{
					{
						obj1 = type();
					}
				}
				else if ((_tokenSet_44.member(LA(1))) && (_tokenSet_45.member(LA(2)))
							&& (_tokenSet_46.member(LA(3))))
				{
					obj1 = value();
				}
				else
				{
					throw new NoViableAltException(LT(1), getFilename());
				}

			}
			if (inputState.guessing == 0)
			{
				obj = obj1;
				obj1 = null;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_47);
			}
			else
			{
				throw ex;
			}
		}
		return obj;
	}

	public final void typeorvaluelist(ObjectType objtype) throws RecognitionException,
				TokenStreamException
	{

		Object obj;

		try
		{ // for error handling
			{
				{
					obj = typeorvalue();
					if (inputState.guessing == 0)
					{
						objtype.elements.add(obj);
					}
				}
				{
					match(COMMA);
					{
						_loop829: do
						{
							if ((_tokenSet_48.member(LA(1))))
							{
								obj = typeorvalue();
								if (inputState.guessing == 0)
								{
									objtype.elements.add(obj);
								}
							}
							else
							{
								break _loop829;
							}

						}
						while (true);
					}
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_34);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final ASNType elementType() throws RecognitionException, TokenStreamException
	{
		ASNType asnType;

		Token lid = null;

		AsnValue val;
		AsnElementType eletyp = new AsnElementType();
		ASNType obj;
		Tag tg = null;
		boolean s;
		asnType = null;

		try
		{ // for error handling
			{
				switch (LA(1))
				{
					case LOWER:
						{
							{
								lid = LT(1);
								match(LOWER);
								{
									if ((LA(1) == L_BRACKET)
												&& (_tokenSet_49.member(LA(2)))
												&& (LA(3) == R_BRACKET || LA(3) == NUMBER || LA(3) == LOWER))
									{
										tg = tag();
									}
									else if ((_tokenSet_35.member(LA(1)))
												&& (_tokenSet_50.member(LA(2)))
												&& (_tokenSet_51.member(LA(3))))
									{
									}
									else
									{
										throw new NoViableAltException(LT(1), getFilename());
									}

								}
								{
									switch (LA(1))
									{
										case AUTOMATIC_KW:
										case EXPLICIT_KW:
										case IMPLICIT_KW:
											{
												s = tag_default();
												break;
											}
										case ANY_KW:
										case BIT_KW:
										case BOOLEAN_KW:
										case CHARACTER_KW:
										case CHOICE_KW:
										case EMBEDDED_KW:
										case ENUMERATED_KW:
										case EXTERNAL_KW:
										case GENERALIZED_TIME_KW:
										case GENERAL_STR_KW:
										case GRAPHIC_STR_KW:
										case IA5_STRING_KW:
										case INTEGER_KW:
										case NULL_KW:
										case NUMERIC_STR_KW:
										case OBJECT_KW:
										case OCTET_KW:
										case PRINTABLE_STR_KW:
										case REAL_KW:
										case RELATIVE_KW:
										case SEQUENCE_KW:
										case SET_KW:
										case TELETEX_STR_KW:
										case UNIVERSAL_STR_KW:
										case UTC_TIME_KW:
										case UTF8_STR_KW:
										case VIDEOTEX_STR_KW:
										case VISIBLE_STR_KW:
										case L_BRACKET:
										case UPPER:
										case LOWER:
										case 146:
										case BMP_STR_KW:
										case ISO646_STR_KW:
										case T61_STR_KW:
										case LITERAL_OPERATION:
										case ERROR_KW:
											{
												break;
											}
										default:
											{
												throw new NoViableAltException(LT(1), getFilename());
											}
									}
								}
								{
									obj = type();
									if (inputState.guessing == 0)
									{
										asnType = obj;
										asnType.setName(lid.getText());
										asnType.setTag(tg);
									}
								}
								{
									switch (LA(1))
									{
										case OPTIONAL_KW:
											{
												{
													match(OPTIONAL_KW);
													if (inputState.guessing == 0)
													{
														asnType.setOptional();
													}
												}
												break;
											}
										case DEFAULT_KW:
											{
												{
													match(DEFAULT_KW);
													val = value();
													if (inputState.guessing == 0)
													{
														eletyp.value = val;
													}
												}
												break;
											}
										case COMMA:
										case R_BRACE:
											{
												break;
											}
										default:
											{
												throw new NoViableAltException(LT(1), getFilename());
											}
									}
								}
							}
							break;
						}
					case COMPONENTS_KW:
						{
							match(COMPONENTS_KW);
							match(OF_KW);
							if (inputState.guessing == 0)
							{
								eletyp.isComponentsOf = true;
							}
							{
								obj = type();
							}
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			if (inputState.guessing == 0)
			{

				if ((ASNDefinedType.class).isInstance(obj))
				{
					eletyp.isDefinedType = true;
					eletyp.typeName = obj.getType();
				}
				else
				{
					eletyp.typeReference = obj;
				}

			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_52);
			}
			else
			{
				throw ex;
			}
		}
		return asnType;
	}

	public final ASNNamedNumber namedNumber() throws RecognitionException,
				TokenStreamException
	{
		ASNNamedNumber nnum;

		Token lid = null;

		nnum = new ASNNamedNumber();
		BigInteger iKey;
		String sValue = null;
		AsnDefinedValue s;

		try
		{ // for error handling
			{
				lid = LT(1);
				match(LOWER);
				if (inputState.guessing == 0)
				{
					sValue = lid.getText();
				}
				match(L_PAREN);
				{
					switch (LA(1))
					{
						case MINUS:
						case NUMBER:
							{
								iKey = signed_number();
								if (inputState.guessing == 0)
								{
									nnum.put(iKey, sValue);
								}
								break;
							}
						case UPPER:
						case LOWER:
							{
								{
									s = defined_value();
								}
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				match(R_PAREN);
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_52);
			}
			else
			{
				throw ex;
			}
		}
		return nnum;
	}

	public final BigInteger signed_number() throws RecognitionException,
				TokenStreamException
	{
		BigInteger i;

		Token m = null;
		Token n = null;
		i = null;
		String s = "";

		try
		{ // for error handling
			{
				{
					switch (LA(1))
					{
						case MINUS:
							{
								m = LT(1);
								match(MINUS);
								if (inputState.guessing == 0)
								{
									s = m.getText();
								}
								break;
							}
						case NUMBER:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				{
					n = LT(1);
					match(NUMBER);
					if (inputState.guessing == 0)
					{
						s += n.getText();
						i = new BigInteger(s);
					}
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_27);
			}
			else
			{
				throw ex;
			}
		}
		return i;
	}

	public final void element_set_specs(AsnConstraint cnstrnt)
				throws RecognitionException, TokenStreamException
	{

		ElementSetSpec elemspec;

		try
		{ // for error handling
			{
				elemspec = element_set_spec();
				if (inputState.guessing == 0)
				{
					cnstrnt.elemSetSpec = elemspec;
				}
				{
					if ((LA(1) == COMMA) && (LA(2) == DOTDOTDOT))
					{
						match(COMMA);
						match(DOTDOTDOT);
						if (inputState.guessing == 0)
						{
							cnstrnt.isCommaDotDot = true;
						}
					}
					else if ((LA(1) == COMMA || LA(1) == EXCLAMATION || LA(1) == R_PAREN)
								&& (_tokenSet_53.member(LA(2))))
					{
					}
					else
					{
						throw new NoViableAltException(LT(1), getFilename());
					}

				}
				{
					switch (LA(1))
					{
						case COMMA:
							{
								match(COMMA);
								elemspec = element_set_spec();
								if (inputState.guessing == 0)
								{
									cnstrnt.addElemSetSpec = elemspec;
									cnstrnt.isAdditionalElementSpec = true;
								}
								break;
							}
						case EXCLAMATION:
						case R_PAREN:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_54);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final void exception_spec(AsnConstraint cnstrnt) throws RecognitionException,
				TokenStreamException
	{

		// AsnSignedNumber signum;
		BigInteger signum;
		AsnDefinedValue defval;
		Object typ;
		AsnValue val;

		try
		{ // for error handling
			{
				match(EXCLAMATION);
				{
					boolean synPredMatched867 = false;
					if (((LA(1) == MINUS || LA(1) == NUMBER)))
					{
						int _m867 = mark();
						synPredMatched867 = true;
						inputState.guessing++;
						try
						{
							{
								signed_number();
							}
						}
						catch (RecognitionException pe)
						{
							synPredMatched867 = false;
						}
						rewind(_m867);
						inputState.guessing--;
					}
					if (synPredMatched867)
					{
						{
							signum = signed_number();
							if (inputState.guessing == 0)
							{
								cnstrnt.isSignedNumber = true;
								cnstrnt.signedNumber = signum;
							}
						}
					}
					else
					{
						boolean synPredMatched870 = false;
						if (((LA(1) == UPPER || LA(1) == LOWER)
									&& (LA(2) == DOT || LA(2) == R_PAREN) && (_tokenSet_33
										.member(LA(3)))))
						{
							int _m870 = mark();
							synPredMatched870 = true;
							inputState.guessing++;
							try
							{
								{
									defined_value();
								}
							}
							catch (RecognitionException pe)
							{
								synPredMatched870 = false;
							}
							rewind(_m870);
							inputState.guessing--;
						}
						if (synPredMatched870)
						{
							{
								defval = defined_value();
								if (inputState.guessing == 0)
								{
									cnstrnt.isDefinedValue = true;
									cnstrnt.definedValue = defval;
								}
							}
						}
						else if ((_tokenSet_11.member(LA(1))) && (_tokenSet_55.member(LA(2)))
									&& (_tokenSet_56.member(LA(3))))
						{
							typ = type();
							match(COLON);
							val = value();
							if (inputState.guessing == 0)
							{
								cnstrnt.isColonValue = true;
								cnstrnt.type = typ;
								cnstrnt.value = val;
							}
						}
						else
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
					}
				}
			}
			if (inputState.guessing == 0)
			{
				cnstrnt.isExceptionSpec = true;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_57);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final ElementSetSpec element_set_spec() throws RecognitionException,
				TokenStreamException
	{
		ElementSetSpec elemspec;

		elemspec = new ElementSetSpec();
		Intersection intersect;
		ConstraintElements cnselem;

		try
		{ // for error handling
			switch (LA(1))
			{
				case ANY_KW:
				case BIT_KW:
				case BOOLEAN_KW:
				case CHARACTER_KW:
				case CHOICE_KW:
				case EMBEDDED_KW:
				case ENUMERATED_KW:
				case EXTERNAL_KW:
				case FALSE_KW:
				case FROM_KW:
				case GENERALIZED_TIME_KW:
				case GENERAL_STR_KW:
				case GRAPHIC_STR_KW:
				case IA5_STRING_KW:
				case INTEGER_KW:
				case MINUS_INFINITY_KW:
				case MIN_KW:
				case NULL_KW:
				case NUMERIC_STR_KW:
				case OBJECT_KW:
				case OCTET_KW:
				case PLUS_INFINITY_KW:
				case PRINTABLE_STR_KW:
				case REAL_KW:
				case RELATIVE_KW:
				case SEQUENCE_KW:
				case SET_KW:
				case SIZE_KW:
				case TELETEX_STR_KW:
				case TRUE_KW:
				case UNIVERSAL_STR_KW:
				case UTC_TIME_KW:
				case UTF8_STR_KW:
				case VIDEOTEX_STR_KW:
				case VISIBLE_STR_KW:
				case WITH_KW:
				case L_BRACE:
				case L_BRACKET:
				case L_PAREN:
				case MINUS:
				case NUMBER:
				case UPPER:
				case LOWER:
				case B_STRING:
				case H_STRING:
				case C_STRING:
				case 146:
				case BMP_STR_KW:
				case ISO646_STR_KW:
				case T61_STR_KW:
				case LITERAL_OPERATION:
				case ERROR_KW:
				case INCLUDES:
				case PATTERN_KW:
					{
						intersect = intersections();
						if (inputState.guessing == 0)
						{
							elemspec.intersectionList.add(intersect);
						}
						{
							_loop879: do
							{
								if ((LA(1) == UNION_KW || LA(1) == BAR))
								{
									{
										switch (LA(1))
										{
											case BAR:
												{
													match(BAR);
													break;
												}
											case UNION_KW:
												{
													match(UNION_KW);
													break;
												}
											default:
												{
													throw new NoViableAltException(LT(1),
																getFilename());
												}
										}
									}
									intersect = intersections();
									if (inputState.guessing == 0)
									{
										elemspec.intersectionList.add(intersect);
									}
								}
								else
								{
									break _loop879;
								}

							}
							while (true);
						}
						break;
					}
				case ALL:
					{
						match(ALL);
						match(EXCEPT);
						cnselem = constraint_elements();
						if (inputState.guessing == 0)
						{
							elemspec.allExceptCnselem = cnselem;
							elemspec.isAllExcept = true;
						}
						break;
					}
				default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_58);
			}
			else
			{
				throw ex;
			}
		}
		return elemspec;
	}

	public final Intersection intersections() throws RecognitionException,
				TokenStreamException
	{
		Intersection intersect;

		intersect = new Intersection();
		ConstraintElements cnselem;

		try
		{ // for error handling
			cnselem = constraint_elements();
			if (inputState.guessing == 0)
			{
				intersect.cnsElemList.add(cnselem);
			}
			{
				switch (LA(1))
				{
					case EXCEPT:
						{
							match(EXCEPT);
							if (inputState.guessing == 0)
							{
								intersect.isExcept = true;
							}
							cnselem = constraint_elements();
							if (inputState.guessing == 0)
							{
								intersect.exceptCnsElem.add(cnselem);
							}
							break;
						}
					case INTERSECTION_KW:
					case UNION_KW:
					case BAR:
					case COMMA:
					case EXCLAMATION:
					case INTERSECTION:
					case R_PAREN:
						{
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			{
				_loop885: do
				{
					if ((LA(1) == INTERSECTION_KW || LA(1) == INTERSECTION))
					{
						{
							switch (LA(1))
							{
								case INTERSECTION:
									{
										match(INTERSECTION);
										break;
									}
								case INTERSECTION_KW:
									{
										match(INTERSECTION_KW);
										break;
									}
								default:
									{
										throw new NoViableAltException(LT(1), getFilename());
									}
							}
						}
						if (inputState.guessing == 0)
						{
							intersect.isInterSection = true;
						}
						cnselem = constraint_elements();
						if (inputState.guessing == 0)
						{
							intersect.cnsElemList.add(cnselem);
						}
						{
							switch (LA(1))
							{
								case EXCEPT:
									{
										match(EXCEPT);
										cnselem = constraint_elements();
										if (inputState.guessing == 0)
										{
											intersect.exceptCnsElem.add(cnselem);
										}
										break;
									}
								case INTERSECTION_KW:
								case UNION_KW:
								case BAR:
								case COMMA:
								case EXCLAMATION:
								case INTERSECTION:
								case R_PAREN:
									{
										break;
									}
								default:
									{
										throw new NoViableAltException(LT(1), getFilename());
									}
							}
						}
					}
					else
					{
						break _loop885;
					}

				}
				while (true);
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_59);
			}
			else
			{
				throw ex;
			}
		}
		return intersect;
	}

	public final ConstraintElements constraint_elements() throws RecognitionException,
				TokenStreamException
	{
		ConstraintElements cnsElem;

		cnsElem = new ConstraintElements();
		AsnValue val;
		AsnConstraint cns;
		ElementSetSpec elespec;
		Object typ;

		try
		{ // for error handling
			switch (LA(1))
			{
				case SIZE_KW:
					{
						{
							match(SIZE_KW);
							cns = constraint();
							if (inputState.guessing == 0)
							{
								cnsElem.isSizeConstraint = true;
								cnsElem.constraint = cns;
							}
						}
						break;
					}
				case FROM_KW:
					{
						{
							match(FROM_KW);
							cns = constraint();
							if (inputState.guessing == 0)
							{
								cnsElem.isAlphabetConstraint = true;
								cnsElem.constraint = cns;
							}
						}
						break;
					}
				case L_PAREN:
					{
						{
							match(L_PAREN);
							elespec = element_set_spec();
							if (inputState.guessing == 0)
							{
								cnsElem.isElementSetSpec = true;
								cnsElem.elespec = elespec;
							}
							match(R_PAREN);
						}
						break;
					}
				case PATTERN_KW:
					{
						{
							match(PATTERN_KW);
							val = value();
							if (inputState.guessing == 0)
							{
								cnsElem.isPatternValue = true;
								cnsElem.value = val;
							}
						}
						break;
					}
				case WITH_KW:
					{
						{
							match(WITH_KW);
							{
								switch (LA(1))
								{
									case COMPONENT_KW:
										{
											{
												match(COMPONENT_KW);
												cns = constraint();
												if (inputState.guessing == 0)
												{
													cnsElem.isWithComponent = true;
													cnsElem.constraint = cns;
												}
											}
											break;
										}
									case COMPONENTS_KW:
										{
											{
												match(COMPONENTS_KW);
												if (inputState.guessing == 0)
												{
													cnsElem.isWithComponents = true;
												}
												match(L_BRACE);
												{
													switch (LA(1))
													{
														case DOTDOTDOT:
															{
																match(DOTDOTDOT);
																match(COMMA);
																break;
															}
														case LOWER:
															{
																break;
															}
														default:
															{
																throw new NoViableAltException(LT(1),
																			getFilename());
															}
													}
												}
												type_constraint_list(cnsElem);
												match(R_BRACE);
											}
											break;
										}
									default:
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
								}
							}
						}
						break;
					}
				default:
					boolean synPredMatched888 = false;
					if (((_tokenSet_44.member(LA(1))) && (_tokenSet_60.member(LA(2))) && (_tokenSet_61
								.member(LA(3)))))
					{
						int _m888 = mark();
						synPredMatched888 = true;
						inputState.guessing++;
						try
						{
							{
								value();
							}
						}
						catch (RecognitionException pe)
						{
							synPredMatched888 = false;
						}
						rewind(_m888);
						inputState.guessing--;
					}
					if (synPredMatched888)
					{
						{
							val = value();
							if (inputState.guessing == 0)
							{
								cnsElem.isValue = true;
								cnsElem.value = val;
							}
						}
					}
					else
					{
						boolean synPredMatched891 = false;
						if (((_tokenSet_62.member(LA(1))) && (_tokenSet_63.member(LA(2))) && (_tokenSet_64
									.member(LA(3)))))
						{
							int _m891 = mark();
							synPredMatched891 = true;
							inputState.guessing++;
							try
							{
								{
									value_range(cnsElem);
								}
							}
							catch (RecognitionException pe)
							{
								synPredMatched891 = false;
							}
							rewind(_m891);
							inputState.guessing--;
						}
						if (synPredMatched891)
						{
							{
								value_range(cnsElem);
								if (inputState.guessing == 0)
								{
									cnsElem.isValueRange = true;
								}
							}
						}
						else if ((_tokenSet_65.member(LA(1))) && (_tokenSet_66.member(LA(2)))
									&& (_tokenSet_67.member(LA(3))))
						{
							{
								{
									switch (LA(1))
									{
										case INCLUDES:
											{
												match(INCLUDES);
												if (inputState.guessing == 0)
												{
													cnsElem.isIncludeType = true;
												}
												break;
											}
										case ANY_KW:
										case BIT_KW:
										case BOOLEAN_KW:
										case CHARACTER_KW:
										case CHOICE_KW:
										case EMBEDDED_KW:
										case ENUMERATED_KW:
										case EXTERNAL_KW:
										case GENERALIZED_TIME_KW:
										case GENERAL_STR_KW:
										case GRAPHIC_STR_KW:
										case IA5_STRING_KW:
										case INTEGER_KW:
										case NULL_KW:
										case NUMERIC_STR_KW:
										case OBJECT_KW:
										case OCTET_KW:
										case PRINTABLE_STR_KW:
										case REAL_KW:
										case RELATIVE_KW:
										case SEQUENCE_KW:
										case SET_KW:
										case TELETEX_STR_KW:
										case UNIVERSAL_STR_KW:
										case UTC_TIME_KW:
										case UTF8_STR_KW:
										case VIDEOTEX_STR_KW:
										case VISIBLE_STR_KW:
										case L_BRACKET:
										case UPPER:
										case LOWER:
										case 146:
										case BMP_STR_KW:
										case ISO646_STR_KW:
										case T61_STR_KW:
										case LITERAL_OPERATION:
										case ERROR_KW:
											{
												break;
											}
										default:
											{
												throw new NoViableAltException(LT(1), getFilename());
											}
									}
								}
								typ = type();
								if (inputState.guessing == 0)
								{
									cnsElem.isTypeConstraint = true;
									cnsElem.type = typ;
								}
							}
						}
						else
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_68);
			}
			else
			{
				throw ex;
			}
		}
		return cnsElem;
	}

	public final void value_range(ConstraintElements cnsElem) throws RecognitionException,
				TokenStreamException
	{

		AsnValue val;

		try
		{ // for error handling
			{
				switch (LA(1))
				{
					case FALSE_KW:
					case MINUS_INFINITY_KW:
					case NULL_KW:
					case PLUS_INFINITY_KW:
					case TRUE_KW:
					case L_BRACE:
					case MINUS:
					case NUMBER:
					case UPPER:
					case LOWER:
					case B_STRING:
					case H_STRING:
					case C_STRING:
						{
							val = value();
							if (inputState.guessing == 0)
							{
								cnsElem.lEndValue = val;
							}
							break;
						}
					case MIN_KW:
						{
							match(MIN_KW);
							if (inputState.guessing == 0)
							{
								cnsElem.isMinKw = true;
							}
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			{
				switch (LA(1))
				{
					case LESS:
						{
							match(LESS);
							if (inputState.guessing == 0)
							{
								cnsElem.isLEndLess = true;
							}
							break;
						}
					case DOTDOT:
						{
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			match(DOTDOT);
			{
				switch (LA(1))
				{
					case LESS:
						{
							match(LESS);
							if (inputState.guessing == 0)
							{
								cnsElem.isUEndLess = true;
							}
							break;
						}
					case FALSE_KW:
					case MAX_KW:
					case MINUS_INFINITY_KW:
					case NULL_KW:
					case PLUS_INFINITY_KW:
					case TRUE_KW:
					case L_BRACE:
					case MINUS:
					case NUMBER:
					case UPPER:
					case LOWER:
					case B_STRING:
					case H_STRING:
					case C_STRING:
						{
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			{
				switch (LA(1))
				{
					case FALSE_KW:
					case MINUS_INFINITY_KW:
					case NULL_KW:
					case PLUS_INFINITY_KW:
					case TRUE_KW:
					case L_BRACE:
					case MINUS:
					case NUMBER:
					case UPPER:
					case LOWER:
					case B_STRING:
					case H_STRING:
					case C_STRING:
						{
							val = value();
							if (inputState.guessing == 0)
							{
								cnsElem.uEndValue = val;
							}
							break;
						}
					case MAX_KW:
						{
							match(MAX_KW);
							if (inputState.guessing == 0)
							{
								cnsElem.isMaxKw = true;
							}
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_68);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final void type_constraint_list(ConstraintElements cnsElem)
				throws RecognitionException, TokenStreamException
	{

		NamedConstraint namecns;

		try
		{ // for error handling
			namecns = named_constraint();
			if (inputState.guessing == 0)
			{
				cnsElem.typeConstraintList.add(namecns);
			}
			{
				_loop911: do
				{
					if ((LA(1) == COMMA))
					{
						match(COMMA);
						namecns = named_constraint();
						if (inputState.guessing == 0)
						{
							cnsElem.typeConstraintList.add(namecns);
						}
					}
					else
					{
						break _loop911;
					}

				}
				while (true);
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_34);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final NamedConstraint named_constraint() throws RecognitionException,
				TokenStreamException
	{
		NamedConstraint namecns;

		Token lid = null;

		namecns = new NamedConstraint();
		AsnConstraint cns;

		try
		{ // for error handling
			lid = LT(1);
			match(LOWER);
			if (inputState.guessing == 0)
			{
				namecns.name = lid.getText();
			}
			{
				switch (LA(1))
				{
					case L_PAREN:
						{
							cns = constraint();
							if (inputState.guessing == 0)
							{
								namecns.isConstraint = true;
								namecns.constraint = cns;
							}
							break;
						}
					case ABSENT_KW:
					case OPTIONAL_KW:
					case PRESENT_KW:
					case COMMA:
					case R_BRACE:
						{
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			{
				switch (LA(1))
				{
					case PRESENT_KW:
						{
							match(PRESENT_KW);
							if (inputState.guessing == 0)
							{
								namecns.isPresentKw = true;
							}
							break;
						}
					case ABSENT_KW:
						{
							match(ABSENT_KW);
							if (inputState.guessing == 0)
							{
								namecns.isAbsentKw = true;
							}
							break;
						}
					case OPTIONAL_KW:
						{
							match(OPTIONAL_KW);
							if (inputState.guessing == 0)
							{
								namecns.isOptionalKw = true;
							}
							break;
						}
					case COMMA:
					case R_BRACE:
						{
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_52);
			}
			else
			{
				throw ex;
			}
		}
		return namecns;
	}

	public final void choice_value(AsnValue value) throws RecognitionException,
				TokenStreamException
	{

		Token lid = null;
		AsnChoiceValue chval = new AsnChoiceValue();
		AsnValue val;

		try
		{ // for error handling
			{
				{
					lid = LT(1);
					match(LOWER);
					if (inputState.guessing == 0)
					{
						chval.name = lid.getText();
					}
				}
				{
					switch (LA(1))
					{
						case COLON:
							{
								match(COLON);
								break;
							}
						case FALSE_KW:
						case MINUS_INFINITY_KW:
						case NULL_KW:
						case PLUS_INFINITY_KW:
						case TRUE_KW:
						case L_BRACE:
						case MINUS:
						case NUMBER:
						case UPPER:
						case LOWER:
						case B_STRING:
						case H_STRING:
						case C_STRING:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				{
					val = value();
					if (inputState.guessing == 0)
					{
						chval.value = val;
					}
				}
			}
			if (inputState.guessing == 0)
			{
				value.chval = chval;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_27);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final AsnSequenceValue sequence_value() throws RecognitionException,
				TokenStreamException
	{
		AsnSequenceValue seqval;

		AsnNamedValue nameval = new AsnNamedValue();
		seqval = new AsnSequenceValue();

		try
		{ // for error handling
			match(L_BRACE);
			{
				{
					switch (LA(1))
					{
						case LOWER:
							{
								nameval = named_value();
								if (inputState.guessing == 0)
								{
									seqval.isValPresent = true;
									seqval.namedValueList.add(nameval);
								}
								break;
							}
						case COMMA:
						case R_BRACE:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				{
					_loop1007: do
					{
						if ((LA(1) == COMMA))
						{
							match(COMMA);
							nameval = named_value();
							if (inputState.guessing == 0)
							{
								seqval.namedValueList.add(nameval);
							}
						}
						else
						{
							break _loop1007;
						}

					}
					while (true);
				}
			}
			match(R_BRACE);
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_27);
			}
			else
			{
				throw ex;
			}
		}
		return seqval;
	}

	public final void sequenceof_value(AsnValue value) throws RecognitionException,
				TokenStreamException
	{

		AsnValue val;
		value.seqOfVal = new AsnSequenceOfValue();

		try
		{ // for error handling
			match(L_BRACE);
			{
				{
					switch (LA(1))
					{
						case FALSE_KW:
						case MINUS_INFINITY_KW:
						case NULL_KW:
						case PLUS_INFINITY_KW:
						case TRUE_KW:
						case L_BRACE:
						case MINUS:
						case NUMBER:
						case UPPER:
						case LOWER:
						case B_STRING:
						case H_STRING:
						case C_STRING:
							{
								val = value();
								if (inputState.guessing == 0)
								{
									value.seqOfVal.valueList.add(val);
								}
								break;
							}
						case COMMA:
						case R_BRACE:
							{
								break;
							}
						default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
					}
				}
				{
					_loop1012: do
					{
						if ((LA(1) == COMMA))
						{
							match(COMMA);
							val = value();
							if (inputState.guessing == 0)
							{
								value.seqOfVal.valueList.add(val);
							}
						}
						else
						{
							break _loop1012;
						}

					}
					while (true);
				}
			}
			match(R_BRACE);
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_27);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final void cstr_value(AsnValue value) throws RecognitionException,
				TokenStreamException
	{

		Token h = null;
		Token b = null;
		AsnBitOrOctetStringValue bstrval = new AsnBitOrOctetStringValue();
		AsnCharacterStringValue cstrval = new AsnCharacterStringValue();
		AsnSequenceValue seqval;

		try
		{ // for error handling
			{
				switch (LA(1))
				{
					case H_STRING:
						{
							{
								h = LT(1);
								match(H_STRING);
								if (inputState.guessing == 0)
								{
									bstrval.isHString = true;
									bstrval.bhStr = h.getText();
								}
							}
							break;
						}
					case B_STRING:
						{
							{
								b = LT(1);
								match(B_STRING);
								if (inputState.guessing == 0)
								{
									bstrval.isBString = true;
									bstrval.bhStr = b.getText();
								}
							}
							break;
						}
					case L_BRACE:
						{
							{
								match(L_BRACE);
								{
									boolean synPredMatched966 = false;
									if (((LA(1) == LOWER) && (LA(2) == COMMA || LA(2) == R_BRACE) && (_tokenSet_27
												.member(LA(3)))))
									{
										int _m966 = mark();
										synPredMatched966 = true;
										inputState.guessing++;
										try
										{
											{
												id_list(bstrval);
											}
										}
										catch (RecognitionException pe)
										{
											synPredMatched966 = false;
										}
										rewind(_m966);
										inputState.guessing--;
									}
									if (synPredMatched966)
									{
										{
											id_list(bstrval);
										}
									}
									else
									{
										boolean synPredMatched969 = false;
										if (((_tokenSet_69.member(LA(1)))
													&& (_tokenSet_70.member(LA(2))) && (_tokenSet_27
														.member(LA(3)))))
										{
											int _m969 = mark();
											synPredMatched969 = true;
											inputState.guessing++;
											try
											{
												{
													char_defs_list(cstrval);
												}
											}
											catch (RecognitionException pe)
											{
												synPredMatched969 = false;
											}
											rewind(_m969);
											inputState.guessing--;
										}
										if (synPredMatched969)
										{
											{
												char_defs_list(cstrval);
											}
										}
										else if ((LA(1) == MINUS || LA(1) == NUMBER))
										{
											tuple_or_quad(cstrval);
										}
										else
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
									}
								}
								match(R_BRACE);
							}
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
			if (inputState.guessing == 0)
			{
				value.cStrValue = cstrval;
				value.bStrValue = bstrval;
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_27);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final void id_list(AsnBitOrOctetStringValue bstrval)
				throws RecognitionException, TokenStreamException
	{

		Token ld = null;
		Token ld1 = null;
		String s = "";

		try
		{ // for error handling
			{
				ld = LT(1);
				match(LOWER);
				if (inputState.guessing == 0)
				{
					s = ld.getText();
					bstrval.idlist.add(s);
				}
			}
			{
				_loop974: do
				{
					if ((LA(1) == COMMA))
					{
						match(COMMA);
						ld1 = LT(1);
						match(LOWER);
						if (inputState.guessing == 0)
						{
							s = ld1.getText();
							bstrval.idlist.add(s);
						}
					}
					else
					{
						break _loop974;
					}

				}
				while (true);
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_34);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final void char_defs_list(AsnCharacterStringValue cstrval)
				throws RecognitionException, TokenStreamException
	{

		CharDef a;

		try
		{ // for error handling
			a = char_defs();
			if (inputState.guessing == 0)
			{
				cstrval.isCharDefList = true;
				cstrval.charDefsList.add(a);
			}
			{
				_loop978: do
				{
					if ((LA(1) == COMMA))
					{
						match(COMMA);
						{
							a = char_defs();
							if (inputState.guessing == 0)
							{
								cstrval.charDefsList.add(a);
							}
						}
					}
					else
					{
						break _loop978;
					}

				}
				while (true);
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_34);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final void tuple_or_quad(AsnCharacterStringValue cstrval)
				throws RecognitionException, TokenStreamException
	{

		BigInteger n;

		try
		{ // for error handling
			{
				n = signed_number();
				if (inputState.guessing == 0)
				{
					cstrval.tupleQuad.add(n);
				}
			}
			match(COMMA);
			{
				n = signed_number();
				if (inputState.guessing == 0)
				{
					cstrval.tupleQuad.add(n);
				}
			}
			{
				switch (LA(1))
				{
					case R_BRACE:
						{
							{
								match(R_BRACE);
								if (inputState.guessing == 0)
								{
									cstrval.isTuple = true;
								}
							}
							break;
						}
					case COMMA:
						{
							{
								match(COMMA);
								{
									n = signed_number();
									if (inputState.guessing == 0)
									{
										cstrval.tupleQuad.add(n);
									}
								}
								match(COMMA);
								{
									n = signed_number();
									if (inputState.guessing == 0)
									{
										cstrval.tupleQuad.add(n);
									}
								}
							}
							break;
						}
					default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_34);
			}
			else
			{
				throw ex;
			}
		}
	}

	public final CharDef char_defs() throws RecognitionException, TokenStreamException
	{
		CharDef chardef;

		Token c = null;
		chardef = new CharDef();
		BigInteger n;
		AsnDefinedValue defval;

		try
		{ // for error handling
			switch (LA(1))
			{
				case C_STRING:
					{
						{
							c = LT(1);
							match(C_STRING);
							if (inputState.guessing == 0)
							{
								chardef.isCString = true;
								chardef.cStr = c.getText();
							}
						}
						break;
					}
				case L_BRACE:
					{
						{
							match(L_BRACE);
							{
								n = signed_number();
								if (inputState.guessing == 0)
								{
									chardef.tupleQuad.add(n);
								}
							}
							match(COMMA);
							{
								n = signed_number();
								if (inputState.guessing == 0)
								{
									chardef.tupleQuad.add(n);
								}
							}
							{
								switch (LA(1))
								{
									case R_BRACE:
										{
											{
												match(R_BRACE);
												if (inputState.guessing == 0)
												{
													chardef.isTuple = true;
												}
											}
											break;
										}
									case COMMA:
										{
											{
												match(COMMA);
												{
													n = signed_number();
													if (inputState.guessing == 0)
													{
														chardef.tupleQuad.add(n);
													}
												}
												match(COMMA);
												{
													n = signed_number();
													if (inputState.guessing == 0)
													{
														chardef.tupleQuad.add(n);
													}
												}
												match(R_BRACE);
												if (inputState.guessing == 0)
												{
													chardef.isQuadruple = true;
												}
											}
											break;
										}
									default:
										{
											throw new NoViableAltException(LT(1), getFilename());
										}
								}
							}
						}
						break;
					}
				case UPPER:
				case LOWER:
					{
						{
							defval = defined_value();
							if (inputState.guessing == 0)
							{
								chardef.defval = defval;
							}
						}
						break;
					}
				default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_52);
			}
			else
			{
				throw ex;
			}
		}
		return chardef;
	}

	public final AsnNamedValue named_value() throws RecognitionException,
				TokenStreamException
	{
		AsnNamedValue nameval;

		Token lid = null;
		nameval = new AsnNamedValue();
		AsnValue val;

		try
		{ // for error handling
			{
				lid = LT(1);
				match(LOWER);
				if (inputState.guessing == 0)
				{
					nameval.name = lid.getText();
				}
				val = value();
				if (inputState.guessing == 0)
				{
					nameval.value = val;
				}
			}
		}
		catch (RecognitionException ex)
		{
			if (inputState.guessing == 0)
			{
				reportError(ex);
				recover(ex, _tokenSet_52);
			}
			else
			{
				throw ex;
			}
		}
		return nameval;
	}

	public static final String[] _tokenNames = { "<0>", "EOF", "<2>",
			"NULL_TREE_LOOKAHEAD", "\"ABSENT\"", "\"ABSTRACT-SYNTAX\"", "\"ALL\"",
			"\"ANY\"", "\"ARGUMENT\"", "\"APPLICATION\"", "\"AUTOMATIC\"", "\"BASEDNUM\"",
			"\"BEGIN\"", "\"BIT\"", "\"BMPString\"", "\"BOOLEAN\"", "\"BY\"",
			"\"CHARACTER\"", "\"CHOICE\"", "\"CLASS\"", "\"COMPONENTS\"", "\"COMPONENT\"",
			"\"CONSTRAINED\"", "\"DEFAULT\"", "\"DEFINED\"", "\"DEFINITIONS\"",
			"\"EMBEDDED\"", "\"END\"", "\"ENUMERATED\"", "\"ERRORS\"", "\"EXCEPT\"",
			"\"EXPLICIT\"", "\"EXPORTS\"", "\"EXTENSIBILITY\"", "\"EXTERNAL\"", "\"FALSE\"",
			"\"FROM\"", "\"GeneralizedTime\"", "\"GeneralString\"", "\"GraphicString\"",
			"\"IA5String\"", "\"IDENTIFIER\"", "\"IMPLICIT\"", "\"IMPLIED\"", "\"IMPORTS\"",
			"\"INCLUDES\"", "\"INSTANCE\"", "\"INTEGER\"", "\"INTERSECTION\"",
			"\"ISO646String\"", "\"LINKED\"", "\"MAX\"", "\"MINUSINFINITY\"", "\"MIN\"",
			"\"NULL\"", "\"NumericString\"", "\"ObjectDescriptor\"", "\"OBJECT\"",
			"\"OCTET\"", "\"OF\"", "\"OID\"", "\"OPTIONAL\"", "\"PARAMETER\"", "\"PDV\"",
			"\"PLUSINFINITY\"", "\"PRESENT\"", "\"PrintableString\"", "\"PRIVATE\"",
			"\"REAL\"", "\"RELATIVE\"", "\"RESULT\"", "\"SEQUENCE\"", "\"SET\"", "\"SIZE\"",
			"\"STRING\"", "\"TAGS\"", "\"TeletexString\"", "\"TRUE\"",
			"\"TYPE-IDENTIFIER\"", "\"UNION\"", "\"UNIQUE\"", "\"UNIVERSAL\"",
			"\"UniversalString\"", "\"UTCTime\"", "\"UTF8String\"", "\"VideotexString\"",
			"\"VisibleString\"", "\"WITH\"", "ASSIGN_OP", "BAR", "COLON", "COMMA",
			"COMMENT", "DOT", "DOTDOT", "ELLIPSIS", "EXCLAMATION", "INTERSECTION", "LESS",
			"L_BRACE", "L_BRACKET", "L_PAREN", "MINUS", "PLUS", "R_BRACE", "R_BRACKET",
			"R_PAREN", "SEMI", "SINGLE_QUOTE", "CHARB", "CHARH", "WS", "SL_COMMENT",
			"ML_COMMENT", "NUMBER", "UPPER", "LOWER", "BDIG", "HDIG", "B_OR_H_STRING",
			"B_STRING", "H_STRING", "C_STRING", "\"OPERATION_KW\"", "\"ERROR_KW\"",
			"\"BIND\"", "\"UNBIND\"", "\"APPLICATION-SERVICE-ELEMENT\"",
			"\"APPLICATION-CONTEXT\"", "\"EXTENSION\"", "\"EXTENSIONS\"",
			"\"EXTENSION-ATTRIBUTE\"", "\"TOKEN\"", "\"TOKEN-DATA\"",
			"\"SECURITY-CATEGORY\"", "\"PORT\"", "\"REFINE\"", "\"ABSTRACT-BIND\"",
			"\"ABSTRACT-UNBIND\"", "\"ABSTRACT-OPERATION\"", "\"ABSTRACT-ERROR\"",
			"\"ALGORITHM\"", "\"ENCRYPTED\"", "\"SIGNED\"", "\"SIGNATURE\"",
			"\"PROTECTED\"", "\"OBJECT-TYPE\"", "\"MACRO\"", "BMP_STR_KW", "ISO646_STR_KW",
			"T61_STR_KW", "\"OPERATION\"", "ERROR_KW", "\"SYNTAX\"", "\"ACCESS\"",
			"\"STATUS\"", "\"DESCRIPTION\"", "\"REFERENCE\"", "\"INDEX\"", "\"DEFVAL\"",
			"DOTDOTDOT", "ALL", "EXCEPT", "INCLUDES", "PATTERN_KW" };

	private static final long[] mk_tokenSet_0()
	{
		long[] data = { 2L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());

	private static final long[] mk_tokenSet_1()
	{
		long[] data = { 33554432L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());

	private static final long[] mk_tokenSet_2()
	{
		long[] data = { 134217728L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());

	private static final long[] mk_tokenSet_3()
	{
		long[] data = { 0L, 7881299884769280L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());

	private static final long[] mk_tokenSet_4()
	{
		long[] data = { 0L, 7882536835350528L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());

	private static final long[] mk_tokenSet_5()
	{
		long[] data = { 491316685476110464L, -64161453791465035L, 17212899327L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());

	private static final long[] mk_tokenSet_6()
	{
		long[] data = { 491316685476110464L, -64161591767289419L, 17212899327L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());

	private static final long[] mk_tokenSet_7()
	{
		long[] data = { 491316685442556032L, -64161591767289419L, 17212899327L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());

	private static final long[] mk_tokenSet_8()
	{
		long[] data = { 0L, 7882536298479616L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());

	private static final long[] mk_tokenSet_9()
	{
		long[] data = { 0L, 7882399396397056L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());

	private static final long[] mk_tokenSet_10()
	{
		long[] data = { 0L, 7882398859526144L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());

	private static final long[] mk_tokenSet_11()
	{
		long[] data = { 486531576344518784L, 6755468168663476L, 32768000L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());

	private static final long[] mk_tokenSet_12()
	{
		long[] data = { 17592320262144L, 6755399441055744L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());

	private static final long[] mk_tokenSet_13()
	{
		long[] data = { 144115188075855872L, -569705352862367744L, 524287L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());

	private static final long[] mk_tokenSet_14()
	{
		long[] data = { 134217728L, 6755399441055744L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());

	private static final long[] mk_tokenSet_15()
	{
		long[] data = { -134217744L, -1L, 137438953471L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());

	private static final long[] mk_tokenSet_16()
	{
		long[] data = { 68719476736L, 8796093022208L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());

	private static final long[] mk_tokenSet_17()
	{
		long[] data = { 144115188075855872L, -569696556232474624L, 524287L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());

	private static final long[] mk_tokenSet_18()
	{
		long[] data = { 68853694464L, 6755399575273472L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());

	private static final long[] mk_tokenSet_19()
	{
		long[] data = { 144115188075855872L, -569696556769345536L, 524287L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());

	private static final long[] mk_tokenSet_20()
	{
		long[] data = { 486531576344518786L, -569705284117982796L, 33554431L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_20 = new BitSet(mk_tokenSet_20());

	private static final long[] mk_tokenSet_21()
	{
		long[] data = { 68719476736L, 8796227239936L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_21 = new BitSet(mk_tokenSet_21());

	private static final long[] mk_tokenSet_22()
	{
		long[] data = { 2798285595108352128L, 512290346273386997L, 17279746048L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_22 = new BitSet(mk_tokenSet_22());

	private static final long[] mk_tokenSet_23()
	{
		long[] data = { 491316685442556032L, 512290364979982773L, 17212637184L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_23 = new BitSet(mk_tokenSet_23());

	private static final long[] mk_tokenSet_24()
	{
		long[] data = { -1225678404186627182L, 512290502511343615L, 135425425408L, 0L, 0L,
				0L };
		return data;
	}

	public static final BitSet _tokenSet_24 = new BitSet(mk_tokenSet_24());

	private static final long[] mk_tokenSet_25()
	{
		long[] data = { 22518032496590848L, 512284766918156289L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_25 = new BitSet(mk_tokenSet_25());

	private static final long[] mk_tokenSet_26()
	{
		long[] data = { 491316685442556032L, 512290365047091637L, 17212637184L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_26 = new BitSet(mk_tokenSet_26());

	private static final long[] mk_tokenSet_27()
	{
		long[] data = { 491316685442556032L, 512290364443111861L, 17212637184L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_27 = new BitSet(mk_tokenSet_27());

	private static final long[] mk_tokenSet_28()
	{
		long[] data = { 22518032496590848L, 512285866496892929L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_28 = new BitSet(mk_tokenSet_28());

	private static final long[] mk_tokenSet_29()
	{
		long[] data = { 2798285595108352128L, 512290483712340469L, 17279746048L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_29 = new BitSet(mk_tokenSet_29());

	private static final long[] mk_tokenSet_30()
	{
		long[] data = { -1227930204000312430L, 512299297530623999L, 135425425408L, 0L, 0L,
				0L };
		return data;
	}

	public static final BitSet _tokenSet_30 = new BitSet(mk_tokenSet_30());

	private static final long[] mk_tokenSet_31()
	{
		long[] data = { -75004299197306990L, 512301497627621375L, 135559643136L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_31 = new BitSet(mk_tokenSet_31());

	private static final long[] mk_tokenSet_32()
	{
		long[] data = { 67108864L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_32 = new BitSet(mk_tokenSet_32());

	private static final long[] mk_tokenSet_33()
	{
		long[] data = { 3374746347411775632L, 512290346273386999L, 17279746048L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_33 = new BitSet(mk_tokenSet_33());

	private static final long[] mk_tokenSet_34()
	{
		long[] data = { 0L, 1099511627776L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_34 = new BitSet(mk_tokenSet_34());

	private static final long[] mk_tokenSet_35()
	{
		long[] data = { 486535976538514560L, 6755468168663476L, 32768000L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_35 = new BitSet(mk_tokenSet_35());

	private static final long[] mk_tokenSet_36()
	{
		long[] data = { 0L, 5629499534213120L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_36 = new BitSet(mk_tokenSet_36());

	private static final long[] mk_tokenSet_37()
	{
		long[] data = { 0L, 2199023255552L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_37 = new BitSet(mk_tokenSet_37());

	private static final long[] mk_tokenSet_38()
	{
		long[] data = { -1236937471975578752L, 512290501429213181L, 17313300480L, 0L, 0L,
				0L };
		return data;
	}

	public static final BitSet _tokenSet_38 = new BitSet(mk_tokenSet_38());

	private static final long[] mk_tokenSet_39()
	{
		long[] data = { -75008699393399918L, 512301496553879551L, 135425425408L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_39 = new BitSet(mk_tokenSet_39());

	private static final long[] mk_tokenSet_40()
	{
		long[] data = { 2798285595108352128L, 512299142366409205L, 17279746048L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_40 = new BitSet(mk_tokenSet_40());

	private static final long[] mk_tokenSet_41()
	{
		long[] data = { 491035210331627648L, 512285935090282933L, 32768000L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_41 = new BitSet(mk_tokenSet_41());

	private static final long[] mk_tokenSet_42()
	{
		long[] data = { -3543061956308589696L, 512286090380326909L, 66322432L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_42 = new BitSet(mk_tokenSet_42());

	private static final long[] mk_tokenSet_43()
	{
		long[] data = { -75008699393399936L, 512301496553879549L, 131129933824L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_43 = new BitSet(mk_tokenSet_43());

	private static final long[] mk_tokenSet_44()
	{
		long[] data = { 22518032496590848L, 512284766851047425L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_44 = new BitSet(mk_tokenSet_44());

	private static final long[] mk_tokenSet_45()
	{
		long[] data = { 491035210331627648L, 512285935828480437L, 32768000L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_45 = new BitSet(mk_tokenSet_45());

	private static final long[] mk_tokenSet_46()
	{
		long[] data = { -1236937471975578752L, 512290501429213181L, 19460784128L, 0L, 0L,
				0L };
		return data;
	}

	public static final BitSet _tokenSet_46 = new BitSet(mk_tokenSet_46());

	private static final long[] mk_tokenSet_47()
	{
		long[] data = { 491035210331627648L, 512285935224500661L, 32768000L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_47 = new BitSet(mk_tokenSet_47());

	private static final long[] mk_tokenSet_48()
	{
		long[] data = { 491035210331627648L, 512284835578655157L, 32768000L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_48 = new BitSet(mk_tokenSet_48());

	private static final long[] mk_tokenSet_49()
	{
		long[] data = { 512L, 5629499534344200L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_49 = new BitSet(mk_tokenSet_49());

	private static final long[] mk_tokenSet_50()
	{
		long[] data = { -1241722581073616000L, 7882932114823164L, 66322432L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_50 = new BitSet(mk_tokenSet_50());

	private static final long[] mk_tokenSet_51()
	{
		long[] data = { -75008699393399936L, 512301496553879549L, 128982450176L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_51 = new BitSet(mk_tokenSet_51());

	private static final long[] mk_tokenSet_52()
	{
		long[] data = { 0L, 1099645845504L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_52 = new BitSet(mk_tokenSet_52());

	private static final long[] mk_tokenSet_53()
	{
		long[] data = { 3383753615385993360L, 512290483720729591L, 128948895744L, 0L, 0L,
				0L };
		return data;
	}

	public static final BitSet _tokenSet_53 = new BitSet(mk_tokenSet_53());

	private static final long[] mk_tokenSet_54()
	{
		long[] data = { 0L, 4402341478400L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_54 = new BitSet(mk_tokenSet_54());

	private static final long[] mk_tokenSet_55()
	{
		long[] data = { -4034097166640217344L, 5629963994793544L, 33554432L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_55 = new BitSet(mk_tokenSet_55());

	private static final long[] mk_tokenSet_56()
	{
		long[] data = { 1654089883357520000L, 512301470062490549L, 111701917696L, 0L, 0L,
				0L };
		return data;
	}

	public static final BitSet _tokenSet_56 = new BitSet(mk_tokenSet_56());

	private static final long[] mk_tokenSet_57()
	{
		long[] data = { 0L, 4398046511104L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_57 = new BitSet(mk_tokenSet_57());

	private static final long[] mk_tokenSet_58()
	{
		long[] data = { 0L, 4402475696128L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_58 = new BitSet(mk_tokenSet_58());

	private static final long[] mk_tokenSet_59()
	{
		long[] data = { 0L, 4402509283328L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_59 = new BitSet(mk_tokenSet_59());

	private static final long[] mk_tokenSet_60()
	{
		long[] data = { 22799507473301504L, 512290278065872897L, 17179869184L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_60 = new BitSet(mk_tokenSet_60());

	private static final long[] mk_tokenSet_61()
	{
		long[] data = { 3383753615385993360L, 512290484257600503L, 133243863040L, 0L, 0L,
				0L };
		return data;
	}

	public static final BitSet _tokenSet_61 = new BitSet(mk_tokenSet_61());

	private static final long[] mk_tokenSet_62()
	{
		long[] data = { 31525231751331840L, 512284766851047425L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_62 = new BitSet(mk_tokenSet_62());

	private static final long[] mk_tokenSet_63()
	{
		long[] data = { 22518032496590848L, 512285885354483713L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_63 = new BitSet(mk_tokenSet_63());

	private static final long[] mk_tokenSet_64()
	{
		long[] data = { 24769832310276096L, 512286022793437185L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_64 = new BitSet(mk_tokenSet_64());

	private static final long[] mk_tokenSet_65()
	{
		long[] data = { 486531576344518784L, 6755468168663476L, 34392506368L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_65 = new BitSet(mk_tokenSet_65());

	private static final long[] mk_tokenSet_66()
	{
		long[] data = { -3547284115318987904L, 7886243568195580L, 17246191616L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_66 = new BitSet(mk_tokenSet_66());

	private static final long[] mk_tokenSet_67()
	{
		long[] data = { -75008699393399920L, 512301496553879551L, 133277417472L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_67 = new BitSet(mk_tokenSet_67());

	private static final long[] mk_tokenSet_68()
	{
		long[] data = { 281474976710656L, 4411099217920L, 17179869184L, 0L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_68 = new BitSet(mk_tokenSet_68());

	private static final long[] mk_tokenSet_69()
	{
		long[] data = { 0L, 294985809952505856L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_69 = new BitSet(mk_tokenSet_69());

	private static final long[] mk_tokenSet_70()
	{
		long[] data = { 0L, 1127274967465984L, 0L, 0L };
		return data;
	}

	public static final BitSet _tokenSet_70 = new BitSet(mk_tokenSet_70());

}
