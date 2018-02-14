import java.lang.Math;
import java.util.*;

public class BinaryTree {

	private TreeNode myRoot;

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	public void print() {
		if (myRoot != null) {
			myRoot.print(0);
		}
	}

	// Print the values in the tree in preorder: root value first,
	// then values in the left subtree (in preorder), then values
	// in the right subtree (in preorder).
	public void printPreorder() {
		if (myRoot == null) {
			System.out.println("(empty tree)");
		} else {
			myRoot.printPreorder();
			System.out.println();
		}
	}

	// Print the values in the tree in inorder: values in the left
	// subtree first (in inorder), then the root value, then values
	// in the right subtree (in inorder).
	public void printInorder() {
		if (myRoot == null) {
			System.out.println("(empty tree)");
		} else {
			myRoot.printInorder();
			System.out.println();
		}
	}

	public void fillSampleTree1() {
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
	}

	public void fillSampleTree2() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
				new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
	}

	public void fillSampleTree3() {
		myRoot = new TreeNode("d", new TreeNode("b", new TreeNode("a"),
				new TreeNode("c")), new TreeNode("f", new TreeNode("e"),
				new TreeNode("g")));
	}

	public int height() {
		if (myRoot != null) {
			return myRoot.height();
		}
		return 0;
	}

	public boolean isCompletelyBalanced() {
		if (myRoot == null) {
			return true;
		} else {
			return myRoot.isCompletelyBalanced();
		}
	}

	private ArrayList<TreeNode> alreadySeen;

	public boolean check() {
		alreadySeen = new ArrayList<TreeNode>();
		try {
			isOK(myRoot);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	private void isOK(TreeNode t) throws IllegalStateException {

		if (t.myRight != null) {
			if (alreadySeen.contains(t.myRight)) {
				throw new IllegalStateException();
			}
			alreadySeen.add(t.myRight);
			isOK(t.myRight);
		}
		if (t.myLeft != null) {
			if (alreadySeen.contains(t.myLeft)) {
				throw new IllegalStateException();
			}
			alreadySeen.add(t.myLeft);
			isOK(t.myLeft);
		}

	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper(int n) {
		TreeNode zero = new TreeNode(0);
		TreeNode one = new TreeNode(1);
		if (n == 0) {
			return zero;
		} else if (n == 1) {
			return one;
		} else {
			int value = (int) fibTreeHelper(n - 1).myItem
					+ (int) fibTreeHelper(n - 2).myItem;
			return new TreeNode(value, fibTreeHelper(n - 1),
					fibTreeHelper(n - 2));
		}
	}

	public static BinaryTree exprTree(String s) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.exprTreeHelper(s);
		return result;
	}

	// Return the tree corresponding to the given arithmetic expression.
	// The expression is legal, fully parenthesized, contains no blanks,
	// and involves only the operations + and *.
	private TreeNode exprTreeHelper(String expr) {
		if (expr.charAt(0) != '(') {
			try {
				return new TreeNode(Integer.parseInt(expr));
			} catch (NumberFormatException e) {
				return new TreeNode(expr.charAt(0));
			}// you fill this in
		} else {
			// expr is a parenthesized expression.
			// Strip off the beginning and ending parentheses,
			// find the main operator (an occurrence of + or * not nested
			// in parentheses, and construct the two subtrees.
			int nesting = 0;
			int opPos = 0;
			for (int k = 1; k < expr.length() - 1; k++) {
				if (expr.charAt(k) == '(') {
					nesting++;
				} else if (expr.charAt(k) == ')') {
					nesting--;
				} else if (nesting == 0
						&& (expr.charAt(k) == '*' || expr.charAt(k) == '+')) {
					opPos = k;
					break;
				}
			}
			String opnd1 = expr.substring(1, opPos);
			String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
			String op = expr.substring(opPos, opPos + 1);
			System.out.println("expression = " + expr);
			System.out.println("operand 1  = " + opnd1);
			System.out.println("operator   = " + op);
			System.out.println("operand 2  = " + opnd2);
			System.out.println();
			return new TreeNode(op, exprTreeHelper(opnd1),
					exprTreeHelper(opnd2)); // you fill this in
		}
	}

	public void optimize() {
		if (myRoot != null) {
			optimizeHelper(myRoot);
		}
	}

	private void optimizeHelper(TreeNode t) {
		if (t != null) {
			Object item = t.myItem;
			if (item instanceof String) {
				if (t.myLeft.myItem.equals("+") || t.myLeft.myItem.equals("*")) {
					optimizeHelper(t.myLeft);
				}
				if (t.myRight.myItem.equals("+")
						|| t.myRight.myItem.equals("*")) {
					optimizeHelper(t.myRight);
				}
				if (t.myLeft.myItem instanceof Integer
						&& t.myRight.myItem instanceof Integer) {
					if (item.equals("+")) {
						t.myItem = (Integer) t.myLeft.myItem
								+ (Integer) t.myRight.myItem;
					} else {
						t.myItem = (Integer) t.myLeft.myItem
								* (Integer) t.myRight.myItem;
					}
					t.myLeft = null;
					t.myRight = null;
				}
			}
		}
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		t.fillSampleTree1();
		print(t, "sample tree 1");
		t.fillSampleTree2();
		print(t, "sample tree 2");
		t.print();
		t.fillSampleTree3();
		print(t, "sample tree 3");
		System.out.println(t.check());

		BinaryTree t2 = BinaryTree.fibTree(5);
		t2.print();

		BinaryTree t3 = BinaryTree.exprTree("((a+(5*(9+1)))+(6*5))");
		t3.optimize();
		t3.print();

	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}

	private static class TreeNode {

		public Object myItem;
		public TreeNode myLeft;
		public TreeNode myRight;

		public TreeNode(Object obj) {
			myItem = obj;
			myLeft = myRight = null;
		}

		public TreeNode(Object obj, TreeNode left, TreeNode right) {
			myItem = obj;
			myLeft = left;
			myRight = right;
		}

		private void printPreorder() {
			System.out.print(myItem + " ");
			if (myLeft != null) {
				myLeft.printPreorder();
			}
			if (myRight != null) {
				myRight.printPreorder();
			}
		}

		private void printInorder() {
			if (myLeft != null) {
				myLeft.printInorder();
			}
			System.out.print(myItem + " ");
			if (myRight != null) {
				myRight.printInorder();
			}
		}

		private int height() {
			int current = 1;
			if (myLeft == null) {
				if (myRight == null) {
					return 1;
				}
				current = 1 + myRight.height();
			} else if (myRight == null) {
				current = 1 + myLeft.height();
			} else {
				current = 1 + Math.max(myLeft.height(), myRight.height());
			}
			return current;
		}

		private boolean isCompletelyBalanced() {
			if (myLeft == null) {
				if (myRight == null) {
					return true;
				}
				return false;
			} else if (myRight == null) {
				return false;
			} else {
				return myLeft.isCompletelyBalanced()
						&& myRight.isCompletelyBalanced();
			}
		}

		// In TreeNode
		private static final String indent1 = "    ";

		private void print(int indent) {
			if (myRight != null) {
				myRight.print(indent + 1);
			}
			println(myItem, indent);
			if (myLeft != null) {
				myLeft.print(indent + 1);
			}
		}

		private static void println(Object obj, int indent) {
			for (int k = 0; k < indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}
	}
}
