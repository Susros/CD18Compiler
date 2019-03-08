/**
 *	CD18 Compiler
 *
 *	Assignmen 3: Parser
 *
 *	@author Zaw Moe Htat (c3193528@uon.edu.au)
 */

import scanner.Scanner;
import scanner.ScannerOutput;
import codegen.*;

import model.*;
import parser.*;
import java.io.*;
import java.util.*;

public class CD {
	public static void main(String[] args) {

		// Flag for lexical error
		boolean lexicalSuccess = true;

		// Check the arguments
		if (args.length > 0) {

			// Scanner output handler
			ScannerOutput scannerOutput = new ScannerOutput();

			// Scanner to scan the program
			Scanner scanner = null;

			// Start scanning the program
			try {

				// Load the CD18 source file
				scanner = new Scanner(args[0]);

				System.out.println();
				System.out.println("Tokens - Lexical Analyser");
				System.out.println("--------------------------------");
				
				// Scan line by line
				while(scanner.readLine() != null) {

					// Scan word by word in the line
					while(scanner.scanNext()) {

						// Check if the stat changed
						if (scanner.isStateChanged() == true) {

							// Check if there is an error
							if (scanner.getErrorHandler().isEmpty() == false) {

								// Print lexical error
								scannerOutput.printError(scanner.getToken(), scanner.getErrorHandler());
								lexicalSuccess = false;

							} else {

								// Print tokens out
								scannerOutput.print(scanner.getToken());
							}
						}
					}
				}

				scannerOutput.end();

			} catch(IOException e) {
				e.printStackTrace();
			}

			System.out.println("================================================================================");

			// Program Listing
			LinkedList<String> programListing = scanner.getProgramListing();

			/*System.out.println();
			System.out.println("Program Listing");
			System.out.println("----------------------");*/

			try {
				PrintWriter programListingWriter = new PrintWriter("programlisting.txt", "UTF-8");

				ListIterator programListingIterator = programListing.listIterator();
				while(programListingIterator.hasNext()) {
					programListingWriter.println(programListingIterator.next());
				}

				programListingWriter.close();
			} catch(IOException e) {
				e.printStackTrace();
			}

			//System.out.println("================================================================================");

			// List of tokens from lexical analyser
			LinkedList<Token> tokens = scanner.getAllTokens();

			// If lexical errors are free
			if (lexicalSuccess == true) {

				// Prepare to parse the list of token
				Parser parser = new Parser(tokens);

				// Parse the tokens list
				TreeNode treeNode = parser.parse();

				// Parser header
				System.out.println();
				System.out.println("Syntax Tree - Syntactic Analyser");
				System.out.println("--------------------------------------");

				// Parser output handler
				ParserOutput parserOutput = new ParserOutput();

				// Print syntax tree
				parserOutput.print(treeNode);

				// Syntax error handler
				LinkedList<ErrorHandler> errorHandler = parser.getErrorHandler();

				if (errorHandler.size() > 0) {
					System.out.println("\n");
					parserOutput.printError(errorHandler);
				}

				// Semantic error
				LinkedList<ErrorHandler> semanticErrorHandler = parser.getSemanticErrorHandler();

				if (semanticErrorHandler.size() > 0) {
					System.out.println("\n");
					parserOutput.printSemanticError(semanticErrorHandler);
				}

				// Number of errors
				int numErr = errorHandler.size() + semanticErrorHandler.size();

				if (numErr > 0) {
					System.out.println();
					System.out.println();
					System.out.println("Found " + numErr + " errors");
				} else {
					System.out.println();
					System.out.println();
					System.out.println("No errors found");

					// Code Generator
					CodeGenerator codegen = new CodeGenerator();
					codegen.generate(treeNode);

					String fDirChunk[] = args[0].split("/");
					String fnameChunk[] = fDirChunk[fDirChunk.length - 1].split("\\.");

					String fname = fnameChunk[0] + ".mod";

					try {
						PrintWriter writer = new PrintWriter(fname, "UTF-8");

						ListIterator l = codegen.getInstructions().listIterator();

						writer.println(codegen.getInstructions().size());

						int numLine = 0;

						while(l.hasNext()) {
							if (numLine < (codegen.getInstructions().size() - 1)) {
								writer.println(l.next());
							} else {
								writer.print(l.next());
							}

							numLine++;
						}

						writer.close();
					} catch(IOException e) {
						e.printStackTrace();
					}

					System.out.println();
					System.out.println(fname + " compiled successfuly");
				}

			} else {
				System.out.println();
				System.out.println("Lexical errors.");
			}

		} else {
			System.out.println();
			System.out.println("Source file is missing.");
			System.out.println();
		}

		// Just a new line.
		System.out.println("\n");

	}
}