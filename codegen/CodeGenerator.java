
package codegen;

import java.util.*;
import model.*;

public class CodeGenerator {

	private LinkedList<String> instructions;
	private String opcodeStr;

	public CodeGenerator() {
		this.instructions = new LinkedList<String>();
		this.opcodeStr = "";
	}

	public void generate(TreeNode node) {

		// Main Node
		TreeNode mainNode = node.getRightNode();

		int lb = this.numDecl(mainNode.getLeftNode(), 0);

		this.appendOpcodeString(InstructionSet.LB);
		this.appendOpcodeString(lb);
		this.appendOpcodeString(InstructionSet.ALLOC);

		// Stats node
		this.statsOpcode(mainNode.getMiddleNode());

		this.appendOpcodeString(InstructionSet.RETN);
	}

	public LinkedList<String> getInstructions() {
		if (this.opcodeStr != "") {

			while(this.opcodeStr.length() < 23) {
				this.opcodeStr += " 00";
			}

			this.instructions.add(this.opcodeStr);
			this.opcodeStr = "";
		}

		return this.instructions;
	}

	private void appendOpcodeString(int opcode) {
		if (this.opcodeStr.length() == 23) {
			this.instructions.add(this.opcodeStr);
			this.opcodeStr = "";
		}

		if (this.opcodeStr.length() > 0) {
			this.opcodeStr += " ";
		}

		this.opcodeStr += String.format("%02d", opcode);
	}

	private void appendOpcodeFourBytesDigit(int digit) {
		String digitStr = String.valueOf(digit);
		String str = "";

		if (digitStr.length() < 8) {
			for(int i = 0; i < (8 - digitStr.length()); i++) {
				str += "0";
			}
		}

		str += digitStr;

		for(int i = 0; i < 8; i += 2) {
			this.appendOpcodeString(Integer.parseInt("" + str.charAt(i) + str.charAt(i + 1)));
		}
	}

	private int numDecl(TreeNode node, int n) {
		
		int total = 0;

		if (node.getNodeValue() == TreeNode.NSDECL) {
			return 1 + n;
		}

		if (node.getNodeValue() == TreeNode.NSDLST) {
			if (node.getLeftNode() != null) {
				total += this.numDecl(node.getLeftNode(), n);
			}

			if (node.getMiddleNode() != null) {
				total += this.numDecl(node.getMiddleNode(), n);
			}

			if (node.getRightNode() != null) {
				total += this.numDecl(node.getRightNode(), n);
			}
		}

		return total;

	}

	private void statsOpcode(TreeNode node) {

		if (node.getNodeValue() == TreeNode.NSTATS || node.getNodeValue() == TreeNode.NVLIST || 
			node.getNodeValue() == TreeNode.NASGN) {
			if (node.getLeftNode() != null) {
				this.statsOpcode(node.getLeftNode());
			}

			if (node.getMiddleNode() != null) {
				this.statsOpcode(node.getMiddleNode());
			}

			if (node.getRightNode() != null) {
				this.statsOpcode(node.getRightNode());
			}
		}

		if (node.getNodeValue() == TreeNode.NINPUT) {
			this.statsOpcode(node.getLeftNode());

			this.appendOpcodeString(InstructionSet.READI);
			this.appendOpcodeString(InstructionSet.ST);
		}

		if (node.getNodeValue() == TreeNode.NSIMV) {
			this.appendOpcodeString(InstructionSet.LA1);
			this.appendOpcodeFourBytesDigit(node.getSymbol().getAddress());
		}

		if (node.getNodeValue() == TreeNode.NILIT) {
			this.appendOpcodeString(InstructionSet.LV0);
			this.appendOpcodeFourBytesDigit(Integer.parseInt(node.getSymbol().getName()));
		}

		if (node.getNodeValue() == TreeNode.NADD) {

			if (node.getLeftNode().getNodeValue() == TreeNode.NILIT) {
				this.appendOpcodeString(InstructionSet.LV0);
				this.appendOpcodeFourBytesDigit(Integer.parseInt(node.getLeftNode().getSymbol().getName()));
			} else if (node.getLeftNode().getNodeValue() == TreeNode.NSIMV) {
				this.appendOpcodeString(InstructionSet.LV1);
				this.appendOpcodeFourBytesDigit(node.getLeftNode().getSymbol().getAddress());
			}

			if (node.getRightNode().getNodeValue() == TreeNode.NILIT) {
				this.appendOpcodeString(InstructionSet.LV0);
				this.appendOpcodeFourBytesDigit(Integer.parseInt(node.getRightNode().getSymbol().getName()));
			} else if (node.getNodeValue() == TreeNode.NSIMV) {
				this.appendOpcodeString(InstructionSet.LV1);
				this.appendOpcodeFourBytesDigit(node.getRightNode().getSymbol().getAddress());
			}

			this.appendOpcodeString(InstructionSet.ADD);
			this.appendOpcodeString(InstructionSet.ST);
		}

		if (node.getNodeValue() == TreeNode.NSUB) {

			if (node.getLeftNode().getNodeValue() == TreeNode.NILIT) {
				this.appendOpcodeString(InstructionSet.LV0);
				this.appendOpcodeFourBytesDigit(Integer.parseInt(node.getLeftNode().getSymbol().getName()));
			} else if (node.getLeftNode().getNodeValue() == TreeNode.NSIMV) {
				this.appendOpcodeString(InstructionSet.LV1);
				this.appendOpcodeFourBytesDigit(node.getLeftNode().getSymbol().getAddress());
			}

			if (node.getRightNode().getNodeValue() == TreeNode.NILIT) {
				this.appendOpcodeString(InstructionSet.LV0);
				this.appendOpcodeFourBytesDigit(Integer.parseInt(node.getRightNode().getSymbol().getName()));
			} else if (node.getNodeValue() == TreeNode.NSIMV) {
				this.appendOpcodeString(InstructionSet.LV1);
				this.appendOpcodeFourBytesDigit(node.getRightNode().getSymbol().getAddress());
			}

			this.appendOpcodeString(InstructionSet.SUB);
			this.appendOpcodeString(InstructionSet.ST);
		}

		if (node.getNodeValue() == TreeNode.NMUL) {

			if (node.getLeftNode().getNodeValue() == TreeNode.NILIT) {
				this.appendOpcodeString(InstructionSet.LV0);
				this.appendOpcodeFourBytesDigit(Integer.parseInt(node.getLeftNode().getSymbol().getName()));
			} else if (node.getLeftNode().getNodeValue() == TreeNode.NSIMV) {
				this.appendOpcodeString(InstructionSet.LV1);
				this.appendOpcodeFourBytesDigit(node.getLeftNode().getSymbol().getAddress());
			}

			if (node.getRightNode().getNodeValue() == TreeNode.NILIT) {
				this.appendOpcodeString(InstructionSet.LV0);
				this.appendOpcodeFourBytesDigit(Integer.parseInt(node.getRightNode().getSymbol().getName()));
			} else if (node.getNodeValue() == TreeNode.NSIMV) {
				this.appendOpcodeString(InstructionSet.LV1);
				this.appendOpcodeFourBytesDigit(node.getRightNode().getSymbol().getAddress());
			}

			this.appendOpcodeString(InstructionSet.MUL);
			this.appendOpcodeString(InstructionSet.ST);
		}

		if (node.getNodeValue() == TreeNode.NDIV) {

			if (node.getLeftNode().getNodeValue() == TreeNode.NILIT) {
				this.appendOpcodeString(InstructionSet.LV0);
				this.appendOpcodeFourBytesDigit(Integer.parseInt(node.getLeftNode().getSymbol().getName()));
			} else if (node.getLeftNode().getNodeValue() == TreeNode.NSIMV) {
				this.appendOpcodeString(InstructionSet.LV1);
				this.appendOpcodeFourBytesDigit(node.getLeftNode().getSymbol().getAddress());
			}

			if (node.getRightNode().getNodeValue() == TreeNode.NILIT) {
				this.appendOpcodeString(InstructionSet.LV0);
				this.appendOpcodeFourBytesDigit(Integer.parseInt(node.getRightNode().getSymbol().getName()));
			} else if (node.getNodeValue() == TreeNode.NSIMV) {
				this.appendOpcodeString(InstructionSet.LV1);
				this.appendOpcodeFourBytesDigit(node.getRightNode().getSymbol().getAddress());
			}

			this.appendOpcodeString(InstructionSet.DIV);
			this.appendOpcodeString(InstructionSet.ST);
		}

		if (node.getNodeValue() == TreeNode.NPRLN || node.getNodeValue() == TreeNode.NPRINT) {
			if (node.getLeftNode().getNodeValue() == TreeNode.NSIMV) {
				this.appendOpcodeString(InstructionSet.LV1);
				this.appendOpcodeFourBytesDigit(node.getLeftNode().getSymbol().getAddress());
			}

			this.appendOpcodeString(InstructionSet.VALPR);
		}

		if (node.getNodeValue() == TreeNode.NPRLN) {
			this.appendOpcodeString(InstructionSet.NEWLN);
		}

	}

}