
package model;

/**
 *	Handle error
 *
 *	@author Zaw Moe Htat (c3193528@uon.edu.au)
 */

public class ErrorHandler {

	/**
	 *	Error flag. True if there is an error, false otherwise
	 */
	private boolean flag;

	/**
	 *	Error message
	 */
	private String message;

	/**
	 *	Line number
	 */
	private int lineNumber;

	/**
	 *	Default constructor
	 *
	 *	Initiate all member variables
	 */
	public ErrorHandler() {
		this.flag = false;
		this.message = "";
		this.lineNumber = 0;
	}

	/**
	 *	Constructor to set message
	 *
	 *	By setting message will flag an error
	 *
	 *	@param message    Error message 
	 *	@param lineNumber Line number
	 */
	public ErrorHandler(String message, int lineNumber) {
		this.flag = true;
		this.message = message;
		this.lineNumber = lineNumber;
	}

	/**
	 *	Set error message
	 *
	 *	By setting message will flag an error
	 *
	 *	@param message Error message
	 */
	public void setMessage(String message) {
		this.flag = true;
		this.message = message;
	}

	/**
	 *	Set line number
	 *
	 *	@param lineNumber Line number
	 */
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 *	Determine if an error is empty
	 *
	 *	@return True if no error, false otherwise.
	 */
	public boolean isEmpty() {
		return (!this.flag);
	}

	/**
	 *	Get error message
	 *
	 *	@return Error message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 *	Get line number
	 *
	 *	@return Line number
	 */
	public int getLineNumber() {
		return this.lineNumber;
	}

	/**
	 *	Clear error
	 */
	public void clear() {
		this.flag = false;
		this.message = "";
		this.lineNumber = 0;
	}
}