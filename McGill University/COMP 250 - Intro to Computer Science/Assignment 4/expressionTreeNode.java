import java.lang.Math.*;

// ALEXANDER BRATYSHKIN
// 260684228

class expressionTreeNode {
	private String value;
	private expressionTreeNode leftChild, rightChild, parent;

	expressionTreeNode() {
		value = null;
		leftChild = rightChild = parent = null;
	}

	// Constructor
	/*
	 * Arguments: String s: Value to be stored in the node expressionTreeNode l,
	 * r, p: the left child, right child, and parent of the node to created
	 * Returns: the newly created expressionTreeNode
	 */
	expressionTreeNode(String s, expressionTreeNode l, expressionTreeNode r, expressionTreeNode p) {
		value = s;
		leftChild = l;
		rightChild = r;
		parent = p;
	}

	/* Basic access methods */
	String getValue() {
		return value;
	}

	expressionTreeNode getLeftChild() {
		return leftChild;
	}

	expressionTreeNode getRightChild() {
		return rightChild;
	}

	expressionTreeNode getParent() {
		return parent;
	}

	/* Basic setting methods */
	void setValue(String o) {
		value = o;
	}

	// sets the left child of this node to n
	void setLeftChild(expressionTreeNode n) {
		leftChild = n;
		n.parent = this;
	}

	// sets the right child of this node to n
	void setRightChild(expressionTreeNode n) {
		rightChild = n;
		n.parent = this;
	}

	// Returns the root of the tree describing the expression s
	// Watch out: it makes no validity checks whatsoever!
	expressionTreeNode(String s) {
		// check if s contains parentheses. If it doesn't, then it's a leaf
		if (s.indexOf("(") == -1)
			setValue(s);
		else { // it's not a leaf

			/*
			 * break the string into three parts: the operator, the left
			 * operand, and the right operand.
			 ***/
			setValue(s.substring(0, s.indexOf("(")));
			// delimit the left operand 2008
			int left = s.indexOf("(") + 1;
			int i = left;
			int parCount = 0;
			// find the comma separating the two operands
			while (parCount >= 0 && !(s.charAt(i) == ',' && parCount == 0)) {
				if (s.charAt(i) == '(')
					parCount++;
				if (s.charAt(i) == ')')
					parCount--;
				i++;
			}
			int mid = i;
			if (parCount < 0)
				mid--;

			// recursively build the left subtree
			setLeftChild(new expressionTreeNode(s.substring(left, mid)));

			if (parCount == 0) {
				// it is a binary operator
				// find the end of the second operand.F13
				while (!(s.charAt(i) == ')' && parCount == 0)) {
					if (s.charAt(i) == '(')
						parCount++;
					if (s.charAt(i) == ')')
						parCount--;
					i++;
				}
				int right = i;
				setRightChild(new expressionTreeNode(s.substring(mid + 1, right)));
			}
		}
	}

	// Returns a copy of the subtree rooted at this node... 2014
	expressionTreeNode deepCopy() {
		expressionTreeNode n = new expressionTreeNode();
		n.setValue(getValue());
		if (getLeftChild() != null)
			n.setLeftChild(getLeftChild().deepCopy());
		if (getRightChild() != null)
			n.setRightChild(getRightChild().deepCopy());
		return n;
	}

	// Returns a String describing the subtree rooted at a certain node.
	public String toString() {
		String ret = value;
		if (getLeftChild() == null)
			return ret;
		else
			ret = ret + "(" + getLeftChild().toString();
		if (getRightChild() == null)
			return ret + ")";
		else
			ret = ret + "," + getRightChild().toString();
		ret = ret + ")";
		return ret;
	}

	// Returns the value of the expression rooted at a given node
	// when x has a certain value
	double evaluate(double x) {

		try {
			return Double.parseDouble(this.getValue());
		}

		catch (NumberFormatException | NullPointerException exp) {

			if (this.getValue().equals("add"))
				return getRightChild().evaluate(x) + getLeftChild().evaluate(x);
			
			else if(this.getValue().equals("minus"))
				return getRightChild().evaluate(x) - getLeftChild().evaluate(x);
			
			else if (this.getValue().equals("mult"))
				return getRightChild().evaluate(x) * getLeftChild().evaluate(x);

			else if (this.getValue().equals("cos"))
				return (Math.cos(getLeftChild().evaluate(x)));

			else if (this.getValue().equals("sin"))
				return (Math.sin(getLeftChild().evaluate(x)));

			else if (this.getValue().equals("exp"))
				return (Math.exp(getLeftChild().evaluate(x)));

			else
				return x;

		}

	}

	/*
	 * returns the root of a new expression tree representing the derivative of
	 * the original expression
	 */
	expressionTreeNode differentiate() {
		
		expressionTreeNode tree;
		
		try {
			Double.parseDouble(this.getValue());
			tree = new expressionTreeNode("0");
			return tree;
		}

		catch (NumberFormatException | NullPointerException exp) {
			if (this.getValue().equals("x")) {
				tree = new expressionTreeNode("1");
				return tree;
			}

			if (this.getValue().equals("add")) {
				tree = new expressionTreeNode(
						"add(" + getLeftChild().deepCopy().differentiate() + "," + getRightChild().deepCopy().differentiate() + ")");
				return tree;
			}

			else if (this.getValue().equals("minus")) {
				tree = new expressionTreeNode(
						"minus(" + getLeftChild().deepCopy().differentiate() + "," + getRightChild().deepCopy().differentiate() + ")");
				return tree;
			}

			else if (this.getValue().equals("mult")) {
				tree = new expressionTreeNode("add(mult(" + getLeftChild().deepCopy().differentiate() + "," + getRightChild().deepCopy()
						+ "),mult(" + getLeftChild().deepCopy() + "," + getRightChild().deepCopy().differentiate() + "))");
				return tree;

			}

			else if (this.getValue().equals("cos")) {
				tree = new expressionTreeNode(
						"minus(0,mult(" + getLeftChild().deepCopy().differentiate() + "," + "sin(" + getLeftChild().deepCopy() + ")))");
				return tree;
			}

			else if (this.getValue().equals("sin")) {
				tree = new expressionTreeNode(
						"mult(" + getLeftChild().deepCopy().differentiate() + "," + "cos(" + getLeftChild().deepCopy() + "))");
				return tree;
			}

			else if (this.getValue().equals("exp")) {
				tree = new expressionTreeNode("mult(" + getLeftChild().deepCopy().differentiate() + "," + value + ")");
				return tree;
			}

			else
				return null;

		}

	}

	public static void main(String args[]) {
		expressionTreeNode e = new expressionTreeNode("mult(add(2,x),cos(x))");
		System.out.println(e);
		System.out.println(e.evaluate(1));
		System.out.println(e.differentiate());
		System.out.println(e);

	}
}