
package model;

/**
 *	Tree Node
 *
 *	@author Zaw Moe Htat (c3193528@uon.edu.au)
 */

public class TreeNode {
	/**
	 *	Node values
	 */
	public static final int NUNDEF = 0;
	public static final int NPROG  = 1;
	public static final int NGLOB  = 2;
	public static final int NILIST = 3;
	public static final int NINIT  = 4;
	public static final int NFUNCS = 5;
	public static final int NMAIN  = 6;
	public static final int NSDLST = 7;
	public static final int NTYPEL = 8;
	public static final int NRTYPE = 9;
	public static final int NATYPE = 10;
	public static final int NFLIST = 11;
	public static final int NSDECL = 12;
	public static final int NALIST = 13;
	public static final int NARRD  = 14;
	public static final int NFUND  = 15;
	public static final int NPLIST = 16;
	public static final int NSIMP  = 17;
	public static final int NARRP  = 18;
	public static final int NARRC  = 19;
	public static final int NDLIST = 20;
	public static final int NSTATS = 21;
	public static final int NFOR   = 22;
	public static final int NREPT  = 23;
	public static final int NASGNS = 24;
	public static final int NIFTH  = 25;
	public static final int NIFTE  = 26;
	public static final int NASGN  = 27;
	public static final int NPLEQ  = 28;
	public static final int NMNEQ  = 29;
	public static final int NSTEQ  = 30;
	public static final int NDVEQ  = 31;
	public static final int NINPUT = 32;
	public static final int NPRINT = 33;
	public static final int NPRLN  = 34;
	public static final int NCALL  = 35;
	public static final int NRETN  = 36;
	public static final int NVLIST = 37;
	public static final int NSIMV  = 38;
	public static final int NARRV  = 39;
	public static final int NEXPL  = 40;
	public static final int NBOOL  = 41;
	public static final int NNOT   = 42;
	public static final int NAND   = 43;
	public static final int NOR    = 44;
	public static final int NXOR   = 4;
	public static final int NEQL   = 46;
	public static final int NNEQ   = 47;
	public static final int NGRT   = 48;
	public static final int NLSS   = 49;
	public static final int NLEQ   = 50;
	public static final int NADD   = 51;
	public static final int NSUB   = 52;
	public static final int NMUL   = 53;
	public static final int NDIV   = 54;
	public static final int NMOD   = 55;
	public static final int NPOW   = 56;
	public static final int NILIT  = 57;
	public static final int NFLIT  = 58;
	public static final int NTRUE  = 59;
	public static final int NFALS  = 6;
	public static final int NFCALL = 61;
	public static final int NPRLST = 62;
	public static final int NSTRG  = 63;
	public static final int NGEQ   = 64;

	private static final String PRINTNODE[] = { 
		"NUNDEF",		
		"NPROG",	
		"NGLOB",	
		"NILIST",	
		"NINIT",	
		"NFUNCS",		
		"NMAIN",	
		"NSDLST",	
		"NTYPEL",	
		"NRTYPE",	
		"NATYPE",
		"NFLIST",	
		"NSDECL",	
		"NALIST",	
		"NARRD",	
		"NFUND",
		"NPLIST",	
		"NSIMP",	
		"NARRP",	
		"NARRC",	
		"NDLIST",
		"NSTATS",	
		"NFOR",		
		"NREPT",	
		"NASGNS",	
		"NIFTH",
		"NIFTE",	
		"NASGN",	
		"NPLEQ",	
		"NMNEQ",	
		"NSTEQ",
		"NDVEQ",	
		"NINPUT",	
		"NPRINT",	
		"NPRLN",	
		"NCALL",
		"NRETN",	
		"NVLIST",	
		"NSIMV",	
		"NARRV",	
		"NEXPL",
		"NBOOL",	
		"NNOT",		
		"NAND",		
		"NOR",		
		"NXOR",
		"NEQL",		
		"NNEQ",		
		"NGRT",		
		"NLSS",		
		"NLEQ",
		"NADD",		
		"NSUB",		
		"NMUL",		
		"NDIV",		
		"NMOD",
		"NPOW",		
		"NILIT",	
		"NFLIT",	
		"NTRUE",	
		"NFALS",
		"NFCALL",	
		"NPRLST",	
		"NSTRG",	
		"NGEQ"
	};

	private int nodeValue;

	private TreeNode leftNode;

	private TreeNode middleNode;

	private TreeNode rightNode;

	private SymbolData symbol;

	public TreeNode(int nodeValue) {
		this.nodeValue = nodeValue;
		this.leftNode = null;
		this.middleNode = null;
		this.rightNode = null;
		this.symbol = null;
	}

	public TreeNode(int nodeValue, SymbolData symbol) {
		this.nodeValue = nodeValue;
		this.leftNode = null;
		this.middleNode = null;
		this.rightNode = null;
		this.symbol = symbol;
	}

	public TreeNode(int nodeValue, TreeNode leftNode, TreeNode middleNode, TreeNode rightNode) {
		this.nodeValue = nodeValue;
		this.leftNode = leftNode;
		this.middleNode = middleNode;
		this.rightNode = rightNode;
		this.symbol = null;
	}

	public TreeNode(int nodeValue, SymbolData symbol, TreeNode leftNode, TreeNode middleNode, TreeNode rightNode) {
		this.nodeValue = nodeValue;
		this.leftNode = leftNode;
		this.middleNode = middleNode;
		this.rightNode = rightNode;
		this.symbol = symbol;
	}

	public int getNodeValue() {
		return this.nodeValue;
	}

	public String getNodeValueStr() {
		return this.PRINTNODE[this.nodeValue];
	}

	public TreeNode getLeftNode() {
		return this.leftNode;
	}

	public TreeNode getRightNode() {
		return this.rightNode;
	}

	public TreeNode getMiddleNode() {
		return this.middleNode;
	}

	public SymbolData getSymbol() {
		return this.symbol;
	}

	public void setNodeValue(int nodeValue) {
		this.nodeValue = nodeValue;
	}

	public void setLeftNode(TreeNode leftNode) {
		this.leftNode = leftNode;
	}

	public void setRightNode(TreeNode rightNode) {
		this.rightNode = rightNode;
	}

	public void setMiddleNode(TreeNode middleNode) {
		this.middleNode = middleNode;
	}

	public void setSymbol(SymbolData symbol) {
		this.symbol = symbol;
	}
}