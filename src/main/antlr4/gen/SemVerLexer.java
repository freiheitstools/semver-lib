// Generated from /Users/plx/code/fhg/limsemver/src/main/antlr4/SemVerLexer.g4 by ANTLR 4.13.1

//    package io.github.freiheitstools.semver.parser.internal;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class SemVerLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		HYPHEN=1, DOT=2, PLUS=3, LETTER=4, ZERO_DIGIT=5, POSITIVE_DIGIT=6, DIGIT=7, 
		VERSION_NUMBER_FRAGMENT=8;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"HYPHEN", "DOT", "PLUS", "LETTER", "ZERO_DIGIT", "POSITIVE_DIGIT", "DIGIT", 
			"VERSION_NUMBER_FRAGMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'.'", "'+'", null, "'0'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "HYPHEN", "DOT", "PLUS", "LETTER", "ZERO_DIGIT", "POSITIVE_DIGIT", 
			"DIGIT", "VERSION_NUMBER_FRAGMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public SemVerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SemVerLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\b)\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0005\u0007#\b\u0007\n\u0007\f\u0007&\t\u0007\u0003\u0007(\b\u0007\u0000"+
		"\u0000\b\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b"+
		"\u0006\r\u0007\u000f\b\u0001\u0000\u0003\u0002\u0000AZaz\u0001\u00001"+
		"9\u0001\u000009*\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001"+
		"\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001"+
		"\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000"+
		"\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000"+
		"\u0000\u0001\u0011\u0001\u0000\u0000\u0000\u0003\u0013\u0001\u0000\u0000"+
		"\u0000\u0005\u0015\u0001\u0000\u0000\u0000\u0007\u0017\u0001\u0000\u0000"+
		"\u0000\t\u0019\u0001\u0000\u0000\u0000\u000b\u001b\u0001\u0000\u0000\u0000"+
		"\r\u001d\u0001\u0000\u0000\u0000\u000f\'\u0001\u0000\u0000\u0000\u0011"+
		"\u0012\u0005-\u0000\u0000\u0012\u0002\u0001\u0000\u0000\u0000\u0013\u0014"+
		"\u0005.\u0000\u0000\u0014\u0004\u0001\u0000\u0000\u0000\u0015\u0016\u0005"+
		"+\u0000\u0000\u0016\u0006\u0001\u0000\u0000\u0000\u0017\u0018\u0007\u0000"+
		"\u0000\u0000\u0018\b\u0001\u0000\u0000\u0000\u0019\u001a\u00050\u0000"+
		"\u0000\u001a\n\u0001\u0000\u0000\u0000\u001b\u001c\u0007\u0001\u0000\u0000"+
		"\u001c\f\u0001\u0000\u0000\u0000\u001d\u001e\u0007\u0002\u0000\u0000\u001e"+
		"\u000e\u0001\u0000\u0000\u0000\u001f(\u0003\t\u0004\u0000 $\u0003\u000b"+
		"\u0005\u0000!#\u0003\r\u0006\u0000\"!\u0001\u0000\u0000\u0000#&\u0001"+
		"\u0000\u0000\u0000$\"\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000"+
		"%(\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000\u0000\'\u001f\u0001\u0000"+
		"\u0000\u0000\' \u0001\u0000\u0000\u0000(\u0010\u0001\u0000\u0000\u0000"+
		"\u0003\u0000$\'\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}