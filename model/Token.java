
package model;

/**
 * Tokens
 *
 * @author Zaw Moe Htat (c3193528@uon.edu.au)
 */

public class Token {
    /**
     *  Token Value
     */
    public static final int TEOF  = 0;      // End of file
    public static final int TCD18 = 1;      // CD18
    public static final int TCONS = 2;      // Constants
    public static final int TTYPS = 3;      // Types
    public static final int TIS   = 4;      // Is
    public static final int TARRS = 5;      // Arrays
    public static final int TMAIN = 6;      // Main
    public static final int TBEGN = 7;      // Begin
    public static final int TEND  = 8;      // End
    public static final int TARAY = 9;      // Array
    public static final int TOF   = 10;     // Of
    public static final int TFUNC = 11;     // Func
    public static final int TVOID = 12;     // Void
    public static final int TCNST = 13;     // Const
    public static final int TINTG = 14;     // Integer
    public static final int TREAL = 15;     // Real
    public static final int TBOOL = 16;     // Boolean
    public static final int TFOR  = 17;     // For
    public static final int TREPT = 18;     // Repeat
    public static final int TUNTL = 19;     // Until
    public static final int TIFTH = 20;     // If
    public static final int TELSE = 21;     // Else
    public static final int TINPT = 22;     // Input
    public static final int TPRIN = 23;     // Print
    public static final int TPRLN = 24;     // Printline
    public static final int TRETN = 25;     // Return
    public static final int TNOT  = 26;     // Not
    public static final int TAND  = 27;     // And
    public static final int TOR   = 28;     // Or
    public static final int TXOR  = 29;     // Xor
    public static final int TTRUE = 30;     // True
    public static final int TFALS = 31;     // False
    public static final int TCOMA = 32;     // ;
    public static final int TLBRK = 33;     // [
    public static final int TRBRK = 34;     // ]
    public static final int TLPAR = 35;     // (
    public static final int TRPAR = 36;     // )
    public static final int TEQUL = 37;     // =
    public static final int TPLUS = 38;     // +
    public static final int TMINS = 39;     // -
    public static final int TSTAR = 40;     // *
    public static final int TDIVD = 41;     // /
    public static final int TPERC = 42;     // %
    public static final int TCART = 43;     // ^
    public static final int TLESS = 44;     // <
    public static final int TGRTR = 45;     // >
    public static final int TCOLN = 46;     // :
    public static final int TLEQL = 47;     // <=
    public static final int TGEQL = 48;     // >=
    public static final int TNEQL = 49;     // !=
    public static final int TEQEQ = 50;     // ==
    public static final int TPLEQ = 51;     // +=
    public static final int TMNEQ = 52;     // -=
    public static final int TSTEQ = 53;     // *=
    public static final int TDVEQ = 54;     // /=
    public static final int TPCEQ = 55;     // %=
    public static final int TSEMI = 56;     // ;
    public static final int TDOT  = 57;     // .
    public static final int TIDEN = 58;     // [a-zA-Z]([a-zA-Z]|[0-9])*
    public static final int TILIT = 59;     // [0-9]
    public static final int TFLIT = 60;     // [0-9]+(.)([0-9])+
    public static final int TSTRG = 61;     // "(.*)"
    public static final int TUNDF = 62;     // Undefined
    
    /**
     *  Token String
     */
    private static final String TPRINT[] = {
        "TEOF ",
        "TCD18",
        "TCONS",
        "TTYPS",
        "TIS  ",
        "TARRS",
        "TMAIN",
        "TBEGN",
        "TEND ",
        "TARAY",
        "TOF  ",
        "TFUNC",
        "TVOID",
        "TCNST",
        "TINTG",
        "TREAL",
        "TBOOL",
        "TFOR ",
        "TREPT",
        "TUNTL",
        "TIFTH",
        "TELSE",
        "TINPT",
        "TPRIN",
        "TPRLN",
        "TRETN",
        "TNOT ",
        "TAND ",
        "TOR  ",
        "TXOR ",
        "TTRUE",
        "TFALS",
        "TCOMA",
        "TLBRK",
        "TRBRK",
        "TLPAR",
        "TRPAR",
        "TEQUL",
        "TPLUS",
        "TMINS",
        "TSTAR",
        "TDIVD",
        "TPERC",
        "TCART",
        "TLESS",
        "TGRTR",
        "TCOLN",
        "TLEQL",
        "TGEQL",
        "TNEQL",
        "TEQEQ",
        "TPLEQ",
        "TMNEQ",
        "TSTEQ",
        "TDVEQ",
        "TPCEQ",
        "TSEMI",
        "TDOT ",
        "TIDEN",
        "TILIT",
        "TFLIT",
        "TSTRG",
        "TUNDF",
    };
    
    /**
     *  Token Id
     */
    private int tid;

    /**
     *  Lexeme value
     */
    private String lexeme;

    /**
     *  Line number where this token is at
     */
    private int lineNumber;

    /**
     *  Default constructor
     *
     *  Initialise member variables
     */
    public Token()
    {
        this.tid = 0;
        this.lexeme = "";
        this.lineNumber = 0;

    }
    
    /**
     *  Constructor
     *
     *  Initialise member variables with parameter values
     *
     *  @param tid        Token ID
     *  @param lexeme     Lexeme value
     *  @param lineNumber Line number
     */
    public Token(int tid, String lexeme, int lineNumber) {
        this.tid = tid;
        this.lexeme = lexeme;
        this.lineNumber = lineNumber;
    }
    
    /**
     *  Get token id
     *
     *  @return Token id
     */
    public int getTokenId() {
        return this.tid;
    }
    
    /** 
     *  Set token id
     *
     *  @param tid Token id
     */
    public void setTokenId(int tid) {
        this.tid = tid;
    }
    
    /**
     *  Get token in string
     *
     *  @return Token in string
     */
    public String getTokenString() {
        return Token.TPRINT[this.tid];
    }
    
    /**
     *  Get lexeme value
     *
     *  @return Lexeme value
     */
    public String getLexeme() {
        return this.lexeme;
    }
    
    /**
     *  Set lexeme value
     *
     *  @param lexeme Lexeme value
     */
    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    /**
     *  Get line number
     *
     *  @return Line number
     */
    public int getLineNumber() {
        return this.lineNumber;
    }

    /**
     *  Set line number
     *
     *  @param lineNumber
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
    
    /**
     *  Get keyword of token
     *
     *  @return Token id if lexeme is keywords, -1 otherwise.
     */
    public int getKeywordToken(String s) {

        // Keywords are case insensitive
        s = s.toLowerCase();
        
        if (s.equals("cd18")) {	
            return Token.TCD18;
        }
        
    	if (s.equals("constants")) {
    	    return Token.TCONS;
    	}
    	   
    	if (s.equals("types")) {
    	    return Token.TTYPS;
    	}
    	
    	if (s.equals("is")) {
    	    return Token.TIS;
    	}
    	
    	if (s.equals("arrays")) {
    	    return Token.TARRS;
    	}
    	
    	if (s.equals("main")) {
    	    return Token.TMAIN;
    	}
    	
    	if (s.equals("begin")) {
    	    return Token.TBEGN;
    	}
    	
    	if (s.equals("end")) {
    	    return Token.TEND;   
    	}
    	
    	if (s.equals("array")) {
    	    return Token.TARAY;
    	}
    	
    	if (s.equals("of")) {
    	    return Token.TOF;
    	}
    	
    	if (s.equals("func")) {
    	    return Token.TFUNC;
    	}
    	
    	if (s.equals("void")) {	
    	    return Token.TVOID;
    	}
    	
    	if (s.equals("const")) {
    	    return Token.TCNST;
    	}
    	
    	if (s.equals("integer")) {
    	    return Token.TINTG;
    	}
    	
    	if (s.equals("real")) {	
    	    return Token.TREAL;
    	}
    	
    	if (s.equals("boolean")) {
    	    return Token.TBOOL;
    	}
    	
    	if (s.equals("for")) {
    	    return Token.TFOR;
    	}
    	
    	if (s.equals("repeat")) {
    	    return Token.TREPT;
    	}
    	
    	if (s.equals("until")) {
    	    return Token.TUNTL;
    	}
    	
    	if (s.equals("if")) {
    	    return Token.TIFTH;
    	}
    	
    	if (s.equals("else")) {
    	    return Token.TELSE;
    	}
    	if (s.equals("input")) {
    	    return Token.TINPT;
    	}
    	
    	if (s.equals("print")) {
    	    return Token.TPRIN;
    	}
    	
    	if (s.equals("printline")) {
    	    return Token.TPRLN;
    	}
    	
    	if (s.equals("return")) {
    	    return Token.TRETN;
    	}
    	
    	if (s.equals("and")) {
    	    return Token.TAND;
    	}
    	
    	if (s.equals("or")) {
    	    return Token.TOR;
    	}
    	
    	if (s.equals("xor")) {
    	    return Token.TXOR;
    	}
    	
    	if (s.equals("not")) {
    	    return Token.TNOT;
    	}
    	
    	if (s.equals("true")) {
    	    return Token.TTRUE;
    	}
    	
    	if (s.equals("false")) {
    	    return Token.TFALS;
    	}
    	
    	return -1;
    }
    
    /**
     *  Stringify the object
     */
    public String toString() {

        if (getKeywordToken(this.lexeme) != -1) {
            this.tid = getKeywordToken(this.lexeme);
        }

        if (this.tid == Token.TIDEN || this.tid == Token.TILIT || this.tid == Token.TFLIT || this.tid == Token.TSTRG || this.tid == Token.TUNDF) {
            String output = Token.TPRINT[this.tid] + " " + this.lexeme;

            // Pad to fill 6 characters
            for (int i = 0; i < (6 - (this.lexeme.length() % 6)); i++) { 
                output += " ";
            }

            return output;
        } else { // No lexeme need to be printed
            return TPRINT[this.tid] + " ";
        }
    }
}
