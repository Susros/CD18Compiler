
package parser;

import model.*;
import java.util.*;
import java.io.*;

/**
 *	Output controller for parser
 *
 *	@author Zaw Moe Htat (c3193528@uon.edu.au)
 */

public class ParserOutput {

	/**
	 *	Remaining number of character in a row
	 */
	private int count;

	/**
	 *	Constructor
	 *
	 *	Intialise member variables
	 */
	public ParserOutput() {
		this.count = 70;
	}

	/**
	 *	Print the tree node
	 *
	 *	@param treeNode Tree node to be printed
	 */
	public void print(TreeNode treeNode) {

		preOrderTraversal(treeNode);

	}

	/**
	 *	Pre order traversal of syntax tree
	 */
	public void preOrderTraversal(TreeNode treeNode) {

		String nodeValue = this.padding(treeNode.getNodeValueStr());

		String lexeme = (treeNode.getSymbol() == null) ? "" : this.padding(treeNode.getSymbol().getName());

		String output = nodeValue + lexeme;

		System.out.print(output);

		this.count = this.count - output.length();

		if (this.count <= 0) {
			this.count = 70;
			System.out.println();
		}

		if (treeNode.getLeftNode() != null) {
			preOrderTraversal(treeNode.getLeftNode());
		}

		if (treeNode.getMiddleNode() != null) {
			preOrderTraversal(treeNode.getMiddleNode());
		}

		if (treeNode.getRightNode() != null) {
			preOrderTraversal(treeNode.getRightNode());
		}

	}

	/**
	 *	Print error message
	 *
	 */
	public void printError(LinkedList<ErrorHandler> errorHandler) {

		ListIterator l = errorHandler.listIterator();
		while(l.hasNext()) {
			ErrorHandler e = (ErrorHandler) l.next();
			System.out.println("Syntax Error - (line " + e.getLineNumber() + ") : " + e.getMessage());
		}

	}

	/**
	 *	Print semantic error
	 */
	public void printSemanticError(LinkedList<ErrorHandler> errorHandler) {

		ListIterator l = errorHandler.listIterator();
		while(l.hasNext()) {
			ErrorHandler e = (ErrorHandler) l.next();
			System.out.println("Semantic Error - (line " + e.getLineNumber() + ") : " + e.getMessage());
		}
	}

	/**
	 *	Padding 7 characters
	 */
	private String padding(String str) {
		// Pad to fill 6 characters
		String output = str;
        for (int i = 0; i < (7 - (str.length() % 7)); i++) { 
			output += " ";
		}

		return output;
	}

}