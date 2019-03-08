
package scanner;

import model.ErrorHandler;
import model.Token;

/**
 *	Handle output of scanner
 *
 *	@author Zaw Moe Htat (c3193528@uon.edu.au)
 */

public class ScannerOutput {

	/**
	 *	Remaining number of character in a row
	 */
	private int count;

	/**
	 *	Constructor
	 *
	 *	Intialise member variables
	 */
	public ScannerOutput() {
		this.count = 60;
	}

	/**
	 *	Print the token
	 *
	 *	@param token Token to be printed
	 */
	public void print(Token token) {

		// Print the token out
		System.out.print(token);

		// Count down the counter
		this.count = this.count - token.toString().length();

		// Prepare to print next in new row if it exceed the maximum
		if (this.count < 0) {
			this.count = 60;
			System.out.println(); // New line
		}

	}

	/**
	 *	Print error message
	 *
	 *	@param token        Token to be printed
	 *	@param errorHandler Error handler
	 */
	public void printError(Token token, ErrorHandler errorHandler) {

		// Start in new line if it is not already in
		if (this.count != 60) {
			System.out.println();
		}

		System.out.println(token);
		System.out.println("Lexical Error : " + errorHandler.getMessage() + " " + token.getLexeme() + " at line " + errorHandler.getLineNumber());

		this.count = 60;

	}

	/**
	 *	Print end of file
	 */
	public void end() {
		Token token = new Token(Token.TEOF, "", 0);
		this.print(token);
		System.out.println();
	}

}