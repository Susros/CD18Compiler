
package model;

import java.util.*;

/**
 *	Stores data of symbol
 *
 *	@author Zaw Moe Htat (c3193528@uon.edu.au)
 */

public class SymbolData {

	/**
	 *	Id of symbol. This is generated with hash function
	 */
	private int id;

	/**
	 *	Type of token
	 */
	private int type;

	/**
	 *	Data type of symbol
	 */
	private int dataType;

	/**
	 *	Value of symbol
	 */
	private String value;

	/**
	 *	Name of symbol
	 */
	private String name;

	/**
	 *	Line number of symbol
	 */
	private int line;

	/**
	 *	Scope of symbol
	 */
	private String scope;

	/**
	 *	Flag to check if this symbol has been declared
	 */
	private boolean declared;

	/**
	 *	Flag to check if it is function
	 */
	private boolean function;

	/**
	 *	Address
	 */
	private int addr;

	/**
	 *	Parameter arguments types
	 */
	private LinkedList<Integer> functionArgs;

	private int arraySize;
	private int otherSymbolId;

	/**
	 *	Default constructor
	 *
	 *	Initialise all member variables with default value
	 */
	public SymbolData() {
		this.type = Token.TUNDF;
		this.dataType = Token.TUNDF;
		this.value = "";
		this.name = "";
		this.scope = "";
		this.line = 0;
		this.declared = false;
		this.id = this.dataType + this.type + (this.scope + this.name).hashCode();

		this.function = false;
		this.functionArgs = new LinkedList<Integer>();
		this.arraySize = 0;
		this.otherSymbolId = 0;
	}

	/**
	 *	Constructor with arguments
	 *
	 *	Initialise all member variables with values of parameter arguments
	 *
	 *	@param type  Token type
	 *	@param name  Name of symbol
	 *	@param scope Scope of symbol
	 *	@param line  Line number
	 */
	public SymbolData(int type, String name, String scope, int line) {
		this.type = type;
		this.dataType = Token.TUNDF;
		this.value = "";
		this.name = name;
		this.scope = scope;
		this.line = line;
		this.declared = true;
		this.id = this.type + (this.scope + this.name).hashCode();

		this.function = false;
		this.functionArgs = new LinkedList<Integer>();
	}

	/**
	 *	Set token type
	 *
	 *	@param type Type of token
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 *	Set value of symbol
	 *
	 *	@param value Lexeme value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 *	Set name of symbol
	 *
	 *	@param name Name of symbol
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *	Set scope
	 *
	 *	@param scope Scope of symbol
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 *	Set line number
	 *
	 *	@param line Line number
	 */
	public void setLine(int line) {
		this.line = line;
	}

	public void setArraySize(int size) {
		this.arraySize = size;
	}

	/** 
	 *	Set datatype
	 *
	 *	@param dataType Datatype
	 */
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	/**
	 *	Set function
	 */
	public void setFunction(boolean function) {
		this.function = function;
	}

	public void setFunctionArgs(LinkedList<Integer> args) {
		this.functionArgs = args;
	}

	public void pushFunctionArgs(int arg) {
		this.functionArgs.add(arg);
	}

	public void linkToSymbol(int symbolId) {
		this.otherSymbolId = symbolId;
	}

	public void setAddress(int addr) {
		this.addr = addr;
	}

	/**
	 *	Get symbol id
	 *
	 *	@return Id of symbol
	 */
	public int getId() {
		return this.type + (this.scope + this.name).hashCode();
	}

	/**
	 *	Get token type
	 *
	 *	@return Type of token
	 */
	public int getType() {
		return this.type;
	}

	/**
	 *	Get value of symbol
	 *
	 *	@return Value of symbol
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 *	Get name of symbol
	 *
	 *	@return Name of symbol
	 */
	public String getName() {
		return this.name;
	}

	/**
	 *	Get scope
	 *
	 *	@return Scope of symbol
	 */
	public String getScope() {
		return this.scope;
	}

	/**
	 *	Get line number
	 *
	 *	@return Line number
	 */
	public int getLine() {
		return this.line;
	}

	/** 
	 *	Get datatype
	 *
	 *	@return Datatype
	 */
	public int getDataType() {
		return this.dataType;
	}

	public boolean isFunction() {
		return this.function;
	}

	public LinkedList<Integer> getFunctionArgs() {
		return this.functionArgs;
	}

	public int getArraySize() {
		return this.arraySize;
	}

	public int getLinkedSymbolId() {
		return this.otherSymbolId;
	}

	public int getAddress() {
		return this.addr;
	}

	@Override
	public boolean equals(Object o) {
		
		if (this == o) {
			return true;
		}

		if (!(o instanceof SymbolData)) {
			return false;
		}

		SymbolData sd = (SymbolData) o;

		if (this.id != sd.getId()) {
			return false;
		}

		if (this.dataType != sd.getDataType()) {
			return false;
		}

		if (this.type != sd.getType()) {
			return false;
		}

		if (this.scope != null) {
			if (!this.scope.equals(sd.getScope())) {
				return false;
			}
		} else {
			if (sd.getScope() != null) {
				return false;
			}
		}

		if (this.value != null) {
			if (!this.value.equals(sd.getValue())) {
				return false;
			}
		} else {
			if (sd.getValue() != null) {
				return false;
			}
		}

		if (this.name != null) {
			if (!this.name.equals(sd.getName())) {
				return false;
			}
		} else {
			if (sd.getName() != null) {
				return false;
			}
		}

		return true;

	}

	@Override
	public int hashCode() {
		int hash = this.id + this.dataType;
		hash = 31 * hash + (this.scope != null ? this.scope.hashCode() : 0);
		hash = 31 * hash + (this.name != null ? this.name.hashCode() : 0);

		return hash;
	}

}