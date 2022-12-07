// Generated from java-escape by ANTLR 4.11.1
package grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ArduinomlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, PORT_NUMBER=11, IDENTIFIER=12, SIGNAL=13, AND=14, OR=15, OPERATOR=16, 
		NEWLINE=17, WS=18, COMMENT=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "PORT_NUMBER", "IDENTIFIER", "SIGNAL", "AND", "OR", "OPERATOR", 
			"LOWERCASE", "UPPERCASE", "NEWLINE", "WS", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'application'", "'sensor'", "'actuator'", "':'", "'{'", "'}'", 
			"'<='", "'is'", "'=>'", "'->'", null, null, null, "'and'", "'or'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "PORT_NUMBER", 
			"IDENTIFIER", "SIGNAL", "AND", "OR", "OPERATOR", "NEWLINE", "WS", "COMMENT"
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


	public ArduinomlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Arduinoml.g4"; }

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
		"\u0004\u0000\u0013\u009d\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n_\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0004\u000bd\b\u000b\u000b"+
		"\u000b\f\u000be\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\fo\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f}\b\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0003\u0012\u0084\b\u0012\u0001\u0012\u0001\u0012\u0004\u0012"+
		"\u0088\b\u0012\u000b\u0012\f\u0012\u0089\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0004\u0013\u008f\b\u0013\u000b\u0013\f\u0013\u0090\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0005\u0014\u0097\b\u0014\n\u0014"+
		"\f\u0014\u009a\t\u0014\u0001\u0014\u0001\u0014\u0000\u0000\u0015\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d"+
		"\u000f\u001f\u0010!\u0000#\u0000%\u0011\'\u0012)\u0013\u0001\u0000\u0005"+
		"\u0001\u000019\u0001\u0000az\u0001\u0000AZ\u0002\u0000\t\t  \u0002\u0000"+
		"\n\n\r\r\u00a5\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000"+
		"\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000"+
		")\u0001\u0000\u0000\u0000\u0001+\u0001\u0000\u0000\u0000\u00037\u0001"+
		"\u0000\u0000\u0000\u0005>\u0001\u0000\u0000\u0000\u0007G\u0001\u0000\u0000"+
		"\u0000\tI\u0001\u0000\u0000\u0000\u000bK\u0001\u0000\u0000\u0000\rM\u0001"+
		"\u0000\u0000\u0000\u000fP\u0001\u0000\u0000\u0000\u0011S\u0001\u0000\u0000"+
		"\u0000\u0013V\u0001\u0000\u0000\u0000\u0015^\u0001\u0000\u0000\u0000\u0017"+
		"`\u0001\u0000\u0000\u0000\u0019n\u0001\u0000\u0000\u0000\u001bp\u0001"+
		"\u0000\u0000\u0000\u001dt\u0001\u0000\u0000\u0000\u001f|\u0001\u0000\u0000"+
		"\u0000!~\u0001\u0000\u0000\u0000#\u0080\u0001\u0000\u0000\u0000%\u0087"+
		"\u0001\u0000\u0000\u0000\'\u008e\u0001\u0000\u0000\u0000)\u0094\u0001"+
		"\u0000\u0000\u0000+,\u0005a\u0000\u0000,-\u0005p\u0000\u0000-.\u0005p"+
		"\u0000\u0000./\u0005l\u0000\u0000/0\u0005i\u0000\u000001\u0005c\u0000"+
		"\u000012\u0005a\u0000\u000023\u0005t\u0000\u000034\u0005i\u0000\u0000"+
		"45\u0005o\u0000\u000056\u0005n\u0000\u00006\u0002\u0001\u0000\u0000\u0000"+
		"78\u0005s\u0000\u000089\u0005e\u0000\u00009:\u0005n\u0000\u0000:;\u0005"+
		"s\u0000\u0000;<\u0005o\u0000\u0000<=\u0005r\u0000\u0000=\u0004\u0001\u0000"+
		"\u0000\u0000>?\u0005a\u0000\u0000?@\u0005c\u0000\u0000@A\u0005t\u0000"+
		"\u0000AB\u0005u\u0000\u0000BC\u0005a\u0000\u0000CD\u0005t\u0000\u0000"+
		"DE\u0005o\u0000\u0000EF\u0005r\u0000\u0000F\u0006\u0001\u0000\u0000\u0000"+
		"GH\u0005:\u0000\u0000H\b\u0001\u0000\u0000\u0000IJ\u0005{\u0000\u0000"+
		"J\n\u0001\u0000\u0000\u0000KL\u0005}\u0000\u0000L\f\u0001\u0000\u0000"+
		"\u0000MN\u0005<\u0000\u0000NO\u0005=\u0000\u0000O\u000e\u0001\u0000\u0000"+
		"\u0000PQ\u0005i\u0000\u0000QR\u0005s\u0000\u0000R\u0010\u0001\u0000\u0000"+
		"\u0000ST\u0005=\u0000\u0000TU\u0005>\u0000\u0000U\u0012\u0001\u0000\u0000"+
		"\u0000VW\u0005-\u0000\u0000WX\u0005>\u0000\u0000X\u0014\u0001\u0000\u0000"+
		"\u0000Y_\u0007\u0000\u0000\u0000Z[\u00051\u0000\u0000[_\u00051\u0000\u0000"+
		"\\]\u00051\u0000\u0000]_\u00052\u0000\u0000^Y\u0001\u0000\u0000\u0000"+
		"^Z\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000_\u0016\u0001\u0000"+
		"\u0000\u0000`c\u0003!\u0010\u0000ad\u0003!\u0010\u0000bd\u0003#\u0011"+
		"\u0000ca\u0001\u0000\u0000\u0000cb\u0001\u0000\u0000\u0000de\u0001\u0000"+
		"\u0000\u0000ec\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000f\u0018"+
		"\u0001\u0000\u0000\u0000gh\u0005H\u0000\u0000hi\u0005I\u0000\u0000ij\u0005"+
		"G\u0000\u0000jo\u0005H\u0000\u0000kl\u0005L\u0000\u0000lm\u0005O\u0000"+
		"\u0000mo\u0005W\u0000\u0000ng\u0001\u0000\u0000\u0000nk\u0001\u0000\u0000"+
		"\u0000o\u001a\u0001\u0000\u0000\u0000pq\u0005a\u0000\u0000qr\u0005n\u0000"+
		"\u0000rs\u0005d\u0000\u0000s\u001c\u0001\u0000\u0000\u0000tu\u0005o\u0000"+
		"\u0000uv\u0005r\u0000\u0000v\u001e\u0001\u0000\u0000\u0000wx\u0005A\u0000"+
		"\u0000xy\u0005N\u0000\u0000y}\u0005D\u0000\u0000z{\u0005O\u0000\u0000"+
		"{}\u0005R\u0000\u0000|w\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000"+
		"} \u0001\u0000\u0000\u0000~\u007f\u0007\u0001\u0000\u0000\u007f\"\u0001"+
		"\u0000\u0000\u0000\u0080\u0081\u0007\u0002\u0000\u0000\u0081$\u0001\u0000"+
		"\u0000\u0000\u0082\u0084\u0005\r\u0000\u0000\u0083\u0082\u0001\u0000\u0000"+
		"\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000"+
		"\u0000\u0085\u0088\u0005\n\u0000\u0000\u0086\u0088\u0005\r\u0000\u0000"+
		"\u0087\u0083\u0001\u0000\u0000\u0000\u0087\u0086\u0001\u0000\u0000\u0000"+
		"\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u0087\u0001\u0000\u0000\u0000"+
		"\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000"+
		"\u008b\u008c\u0006\u0012\u0000\u0000\u008c&\u0001\u0000\u0000\u0000\u008d"+
		"\u008f\u0007\u0003\u0000\u0000\u008e\u008d\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0090"+
		"\u0091\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092"+
		"\u0093\u0006\u0013\u0000\u0000\u0093(\u0001\u0000\u0000\u0000\u0094\u0098"+
		"\u0005#\u0000\u0000\u0095\u0097\b\u0004\u0000\u0000\u0096\u0095\u0001"+
		"\u0000\u0000\u0000\u0097\u009a\u0001\u0000\u0000\u0000\u0098\u0096\u0001"+
		"\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099\u009b\u0001"+
		"\u0000\u0000\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009b\u009c\u0006"+
		"\u0014\u0000\u0000\u009c*\u0001\u0000\u0000\u0000\u000b\u0000^cen|\u0083"+
		"\u0087\u0089\u0090\u0098\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}