
package scanner;

import model.ErrorHandler;
import model.Token;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.List;
import java.util.LinkedList;

/**
 *	Scan and analyse source code lexically and return the valid tokens 
 *	and error messages.
 *
 *	@author Zaw Moe Htat (c3193528@uon.edu.au)
 */

public class Scanner {

	/**
	 *	Word read from source. Collection of characters.
	 */
	private String word;

	/**
	 *	Current token id detected while scanning.
	 */
	private int tid;

	/**
	 *	To determine if the state is changed.
	 */
	private boolean stateChanged;

	/**
	 *	To determine if the scanner is reading string
	 */
	private boolean readingString;

	/**
	 *	Final token of the word
	 */
	private Token token;

	/**
	 *	Source file
	 */
	private File file;

	/**
	 *	File reader
	 */
	private FileReader fileReader;

	/**
	 *	File buffered reader
	 */
	private BufferedReader bufferedReader;

	/**
	 *	Each line of source file
	 */
	private String line;

	/**
	 *	Pointer of scanner that point from beginning of the string to the end
	 */
	private int currentPointer;

	/**
	 *	Error handler of the scanner
	 */
	private ErrorHandler errorHandler;

	/**
	 *	Keep track of line number
	 */
	private int lineNumber;

	/**
	 *	List of tokens
	 */
	private LinkedList<Token> tokens;

	/**
	 *	Listing
	 */
	private LinkedList<String> programListing;

	/**
	 *	Line of code for program listing
	 */
	private String codeLine;

	/**
	 *	Constructor
	 *
     *  Instantiate scanner object.
     *
     *	@param source Path to source file.
     *
     *	@throws IOException Errors thrown when opening and reading source file
	 */
	public Scanner(String source) throws IOException {

		// Initialise all member variables
		this.word = "";
		this.tid = Token.TEOF;
		this.stateChanged = false;
		this.readingString = false;
		this.token = new Token();
		this.line = "";
		this.currentPointer = 0;
		this.errorHandler = new ErrorHandler();
		this.lineNumber = 0;
		this.tokens = new LinkedList<Token>();
		this.programListing = new LinkedList<String>();
		this.codeLine = "";

		// Open source file
		this.file = new File(source);
		this.fileReader = new FileReader(this.file);
		this.bufferedReader = new BufferedReader(this.fileReader);

	}

	/**
	 *	Read each line of source file
	 *
	 *	@return Line of source file. Null if the file is end.
	 *
	 *	@throws IOException Errors thrown when reading line of file
	 */
	public String readLine() throws IOException {
		this.line = this.bufferedReader.readLine();
		this.lineNumber++;

		// Reinitialise code line
		this.codeLine = "";

		return this.line;
	}

	/**
	 *	Get program listing
	 *
	 *	@return String Program listing
	 */
	public LinkedList<String> getProgramListing() {
		return this.programListing;
	}

	/**
	 *	Scan each characters in the line
	 *
	 *	@return True if there is character, false if not.
	 */
	public boolean scanNext() {

		// Trim the space
		//this.line = this.line.trim();

		// Make sure the error free if it is not TUNDF
		if (this.tid != Token.TUNDF) {
			errorHandler.clear();
		}

		if (this.currentPointer < this.line.length()) {

			// Ignore comment
			if (this.line.length() >= (this.currentPointer + 3) && 
				this.line.charAt(this.currentPointer) == '/' && 
				this.line.charAt(this.currentPointer + 1) == '-' && 
				this.line.charAt(this.currentPointer + 2) == '-' &&
				this.readingString == false) {

				// If comment is found somewhere in the middle
				if (this.currentPointer > 0) {

					// Check the status of the state machine
					if (this.isStartState() == true) {
						this.stateChanged = false;
					} else {
						this.tokenise(); // Tokenise the current state value
					}

				}

				// Set current point to the end
				this.currentPointer = this.line.length();

				if (this.isStartState() == true) {
					this.stateChanged = false;
				}

				// Break it and continue to read next
				return true;
			}

			// Delimiter when new space is detected
			if ((this.line.charAt(this.currentPointer) == ' ' || this.line.charAt(this.currentPointer) == '\t') && this.readingString == false){

				if (this.isStartState() == false) {
					// Tokenise the current state value
					this.tokenise();
				} else {
					this.stateChanged = false;
				}

				// Add code line
				this.codeLine += this.line.charAt(this.currentPointer);

				// Move to next character
				this.currentPointer++;

				// Break it and continue to read next
				return true;
			}

			// Start the state machine
			int t = this.checkState();

			if (this.isStateChanged() == true) {

				// Make sure the error free if it is not TUNDF
				if (this.tid != Token.TUNDF) {
					errorHandler.clear();
				}

				// Tokenise the current state value
				this.tokenise();

				// Reset reading string status
				this.readingString = false;

				// Break it and continue to read next
				return true;
			}

			// State value
			this.word += Character.toString(this.line.charAt(this.currentPointer));

			// Token Id
			this.tid = t;

			// When come to the end of line
			if (this.currentPointer == (this.line.length() - 1)) {

				// Tokenise the current state value
				this.tokenise();

			}

			// Move to next character
			this.currentPointer++;

			// Break it and continue to read next
			return true;
		}

		// If no more to scan
		this.currentPointer = 0; // Reset current pointer

		// Add to program listing
		if (this.codeLine != "") {
			this.programListing.add(this.codeLine);
		}

		// End of line
		return false;
	}

	/**
	 *	Get error handler
	 *
	 *	@return Error handler
	 */
	public ErrorHandler getErrorHandler() {
		return this.errorHandler;
	}

	/**
	 *	Get token
	 *
	 *	@return Token
	 */
	public Token getToken() {
		return this.token;
	}

	/**
	 *	Get line number of current reading
	 *
	 *	@return Line number
	 */
	public int getLineNumber() {
		return this.lineNumber;
	}

	/**
	 *	Get list of tokens
	 *
	 *	@return List of tokens
	 */
	public LinkedList<Token> getAllTokens() {
		return this.tokens;
	}

	/**
	 *	Determine if the state is changed
	 *
	 *	@return State status
	 */
	public boolean isStateChanged() {
		return this.stateChanged;
	}

	/**
	 *	Check the state in the state machine
	 *
	 *	@return Token ID 
	 */
	private int checkState() {

		// Current character
		char c = this.line.charAt(this.currentPointer);

		// Check character if it is not scanning through string
		if (this.readingString == false) {

			// Scanning alphabet
			if (this.isAlpha(c) == true) {

				// The state machine is currently scanning alphabet or in the start state
				if (this.tid == Token.TIDEN || this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TIDEN;
			}

			// Scanning number
			if (this.isNum(c) == true) {

				// The state machine is currently scanning number or in the start state
				if (this.tid == Token.TILIT || this.isStartState() == true) {
					this.stateChanged = false;
					return Token.TILIT;
				}

				// The state machine is currently scanning alphabet or number for identifier: [a-zA-Z]([a-zA-Z]|[0-9])*
				if (this.tid == Token.TIDEN) {
					this.stateChanged = false;
					return Token.TIDEN;
				}

				// The state machine is currently scanning float value: ([0-9]+)(.)([0-9]+)
				if (this.tid == Token.TFLIT) {
					this.stateChanged = false;
					return Token.TFLIT;
				}

				// The state is changed
				this.stateChanged = true;
				return Token.TILIT;
			}

			// Detect character: '.'
			if (c == '.') {

				// The state machine is currently scanning integer value
				// Check if the next value is integer to determine float value
				if (this.tid == Token.TILIT && this.currentPointer < (this.line.length() - 1) && this.isNum(this.line.charAt(this.currentPointer + 1)) == true) {
					this.stateChanged = false;
					return Token.TFLIT;
				}

				// Just for dot
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TDOT;
			}

			// Detect quote: look for end quote to determine if it is string
			if (c == '"') {

				// Flag to determine if end quote is found
				boolean endQuoteFound = false;

				// Let the scanner to treat the rest as string
				this.readingString = true;

				// Search closing quote
				for (int j = (this.currentPointer + 1); j < this.line.length(); j++) {
					if (this.line.charAt(j) == '"') {
						endQuoteFound = true;
						break;
					}
				}

				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				// It is string indeed
				if (endQuoteFound == true) {
					return Token.TSTRG;
				}

				// It is not string. So, here is an error for it.
				this.errorHandler.setMessage("Incomplete String");
				this.errorHandler.setLineNumber(this.lineNumber);
				return Token.TUNDF;
			}

			// Semicolon
			if (c == ';') {

				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TSEMI;
			}

			// Equal
			if (c == '=') {

				// Set state status
				this.stateChanged = false;

				// Check if it is an operator
				if (word.equals("<")) {		   // <=
					return Token.TLEQL;
				} else if (word.equals(">")) { // >=
					return Token.TGEQL;
				} else if (word.equals("!")) { // !=
					return Token.TNEQL;
				} else if (word.equals("=")) { // ==
					return Token.TEQEQ;
				} else if (word.equals("+")) { // +=
					return Token.TPLEQ;
				} else if (word.equals("-")) { // -=
					return Token.TMNEQ;
				} else if (word.equals("*")) { // *=
					return Token.TSTEQ;
				} else if (word.equals("/")) { // /=
					return Token.TDVEQ;
				}

				// The state is not at the start
				if (this.isStartState() == false) {
					this.stateChanged = true;
				}

				return Token.TEQUL;
			}

			// Left bracket
			if (c == '[') {

				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TLBRK;
			}

			// Right bracket
			if (c == ']') {
				
				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TRBRK;
			}

			// Comma
			if (c == ',') {
				
				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TCOMA;
			}

			// Left param
			if (c == '(') {
				
				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TLPAR;
			}

			// Right param
			if (c == ')') {
				
				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TRPAR;
			}

			// Plus
			if (c == '+') {
				
				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TPLUS;
			}

			// Minus
			if (c == '-') {
				
				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TMINS;
			}

			// Star
			if (c == '*') {
				
				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TSTAR;
			}	

			// Divide
			if (c == '/') {
				
				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TDIVD;
			}

			// Percent
			if (c == '%') {
				
				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}
				
				return Token.TPERC;
			}

			// Caret
			if (c == '^') {
				
				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TCART;
			}

			// Less than
			if (c == '<') {
				
				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TLESS;
			}

			// Greater than
			if (c == '>') {
				
				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TGRTR;
			}

			// Colon
			if (c == ':') {
				
				// Check if the state is at the start
				if (this.isStartState() == true) {
					this.stateChanged = false;
				} else {
					this.stateChanged = true;
				}

				return Token.TCOLN;
			}

			// All other characters are invalid
			if (this.tid == Token.TUNDF || this.isStartState() == true) {
				this.stateChanged = false;
			} else {
				this.stateChanged = true;
			}

			// Error message
			if (word.length() > 1) {
				this.errorHandler.setMessage("Invalid Characters");
				this.errorHandler.setLineNumber(this.lineNumber);
			} else {
				this.errorHandler.setMessage("Invalid Character");
				this.errorHandler.setLineNumber(this.lineNumber);
			}

			return Token.TUNDF;

		} else {

			// String has come to the end
			if (c == '"') {
				this.readingString = false;
			}

			this.stateChanged = false;
			return this.tid;
		}
	}
	
	/**
	 *	Determine if the character is alphabet
	 *
	 *	@param c Character to be checked
	 *
	 *	@return True if it is, false otherwise
	 */
	private boolean isAlpha(char c) {
        return (
            c == 'a' || c == 'A' ||
            c == 'b' || c == 'B' ||
            c == 'c' || c == 'C' ||
            c == 'd' || c == 'D' ||
            c == 'e' || c == 'E' ||
            c == 'f' || c == 'F' ||
            c == 'g' || c == 'G' ||
            c == 'h' || c == 'H' ||
            c == 'i' || c == 'I' ||
            c == 'j' || c == 'J' ||
            c == 'k' || c == 'K' ||
            c == 'l' || c == 'L' ||
            c == 'm' || c == 'M' ||
            c == 'n' || c == 'N' ||
            c == 'o' || c == 'O' ||
            c == 'p' || c == 'P' ||
            c == 'q' || c == 'Q' ||
            c == 'r' || c == 'R' ||
            c == 's' || c == 'S' ||
            c == 't' || c == 'T' ||
            c == 'u' || c == 'U' ||
            c == 'v' || c == 'V' ||
            c == 'w' || c == 'W' ||
            c == 'x' || c == 'X' ||
            c == 'y' || c == 'Y' ||
            c == 'z' || c == 'Z'
        );
    }

    /**
     *	Determine if the character is number
     *
     *	@param c Character to be checked
     *
     *	@return True if it is, false otherwise
     */
    private boolean isNum(char c) {
        return (
            c == '1' ||
            c == '2' ||
            c == '3' ||
            c == '4' ||
            c == '5' ||
            c == '6' ||
            c == '7' ||
            c == '8' ||
            c == '9' ||
            c == '0'
        );
    }

    /**
     *	Determine if it is start state
     *
     *	@return True if it is, false otherwise
     */
    private boolean isStartState() {
    	return (this.word == "" && this.tid == Token.TEOF);
    }

    /**
     *	Reset the state to start state
     */
    private void resetState() {
    	this.word = "";
    	this.tid = Token.TEOF;
    }

    /**
     *	Tokenise the word
     */
    private void tokenise() {
    	
    	// Get token
    	this.token = new Token(this.tid, this.word, this.lineNumber);

    	// Add to code line
    	this.codeLine += this.word;

    	// Reset
    	this.word = "";
    	this.tid = Token.TEOF;
    	this.readingString = false;

    	// State change flag
    	this.stateChanged = true;

    	// Add to list
    	this.tokens.add(this.token);
    }

}