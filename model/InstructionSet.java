package model;

public class InstructionSet {

	public static final int HALT  = 0;
	public static final int NOOP  = 1;
	public static final int TRAP  = 2;
	public static final int ZERO  = 3;
	public static final int FALSE = 4;
	public static final int TRUE  = 5;
	public static final int TYPE  = 7;
	public static final int ITYPE = 8;
	public static final int FTYPE = 9;

	public static final int ADD = 11;
	public static final int SUB = 12;
	public static final int MUL = 13;
	public static final int DIV = 14;
	public static final int REM = 15;
	public static final int POW = 16;
	public static final int CHS = 17;
	public static final int ABS = 18;

	public static final int GT = 21;
	public static final int GE = 22;
	public static final int LT = 23;
	public static final int LE = 24;
	public static final int EQ = 25;
	public static final int NE = 26;

	public static final int AND = 31;
	public static final int OR  = 32;
	public static final int XOR = 33;
	public static final int NOT = 34;
	public static final int BT  = 35;
	public static final int BF  = 36;
	public static final int BR  = 37;

	public static final int L  = 40;
	public static final int LB = 41;
	public static final int LH = 42;
	public static final int ST = 43;

	public static final int STEP  = 51;
	public static final int ALLOC = 52;
	public static final int ARRAY = 53;
	public static final int INDEX = 54;
	public static final int SIZE  = 55;
	public static final int DUP   = 56;

	public static final int READF = 60;
	public static final int READI = 61;
	public static final int VALPR = 62;
	public static final int STRPR = 63;
	public static final int CHRPR = 64;
	public static final int NEWLN = 65;
	public static final int SPACE = 66;

	public static final int RVAL = 70;
	public static final int RETN = 71;
	public static final int JS2  = 72;

	public static final int LV0 = 80;
	public static final int LV1 = 81;
	public static final int LV2 = 82;

	public static final int LA0 = 90;
	public static final int LA1 = 91;
	public static final int LA2 = 92;

}