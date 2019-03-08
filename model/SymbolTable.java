package model;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;

/**
 *	Stores symbols
 *
 *	@author Zaw Moe Htat (c3193528@uon.edu.au)
 */

public class SymbolTable {

	/**
	 *	Based memory counter
	 *
	 *	[b0, b1, b2]
	 */
	public static int bmcounter[] = {0, 0, 0};

	/**
	 *	Hashtable for symbols
	 */
	private Map<Integer, SymbolData> map;

	/**
	 *	Default constructor
	 *
	 *	Initalise hash table
	 */
	public SymbolTable() {
		this.map = new HashMap<Integer, SymbolData>();
	}

	/**
	 *	Insert symbol data into hash table
	 *
	 *	@param symbolData Symbol to add
	 */
	public void add(SymbolData symbolData) {
		this.map.put(symbolData.getId(), symbolData);
	}

	/**
	 *	Get symbol in hash table
	 *
	 *	@param symbolDataId Id of symbol data
	 *
	 *	@return Symbol data if found.
	 */
	public SymbolData get(int symbolDataId) {
		return this.map.get(symbolDataId);
	}

	/**
	 *	Check if hash table has symbol
	 *
	 *	@param symbolDataId Id of symbol data
	 *
	 *	@return True if hash table contains symbol, false otherwise.
	 */
	public boolean has(int symbolDataId) {
		return this.map.containsKey(symbolDataId);
	}

	/**
	 *	Remove symbol data from hash table
	 *
	 *	@param symbolDataId Id of symbol data
	 */
	public void remove(int symbolDataId) {
		this.map.remove(symbolDataId);
	}

	/**
	 *	Check if symbol table is empty
	 *
	 *	@return True if it is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	public List<SymbolData> getAll() {
		List<SymbolData> list = new LinkedList<SymbolData>();
		list.addAll(this.map.values());
		return list;
	}

}