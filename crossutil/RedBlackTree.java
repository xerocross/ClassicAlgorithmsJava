package crossutil;

/**
 * The RedBlackTree class is an implementation
 * of the classic red-black binary search tree
 * data structure.  My primary goal in writing 
 * this class is/was readability.  The algorithms
 * are rather confusing if not formulated and
 * written with a human reader in mind.
 * 
 * A secondary goal was to build logic and
 * argument into the methods so that the
 * algorithms prove themselves mathematically---
 * or nearly so.  
 * 
 * Still to do:
 * > implement delete
 * 
 * 
 * @author Adam Cross
 * @param <Key> 
 * @param <Value>
 */
public class RedBlackTree <Key extends Comparable<Key>, Value>
{
	

	
	Node root;
	public void put(Key key, Value val)
	{
		root = put(root, key, val);
		root.red = false;
	}
	public Value get(Key key)
	{
		Node result = get(root, key);
		return (result == null) ? null : result.value;
	}
	public boolean contains(Key key)
	{
		return (get(root, key) == null ? false : true);
	}
	public int size()
	{
		return (root == null) ? 0 : root.size;
	}
	Node put(Node node, Key key, Value value)
	{
		if (node == null) 
			return new Node(key, value);
		switch(getRelation(node.key,key))
		{
		case LESS:
			node.right = put(node.right, key, value);
			break;
		case GREATER:
			node.left = put(node.left, key, value);
			break;
		case EQUAL:
			node.value = value;
			break;
		}
		node = balance(node);
		updateSize(node);
		return node;
	}
	Relation getRelation(Key x, Key y)
	{
		int cmp = x.compareTo(y);
		return (cmp == 0 ? Relation.EQUAL : ((cmp > 0) ? Relation.GREATER : Relation.LESS));
	}
	void updateSize(Node node)
	{
		node.size = (node.left == null ? 0 : node.left.size) 
				+ (node.right == null ? 0 : node.right.size) 
				+ 1;
	}
	Node get(Node node, Key key)
	{
		if (node == null)
			return null;
		switch(getRelation(node.key, key))
		{
		case LESS:
			return get(node.right, key);
		case GREATER:
			return get(node.left, key);
		case EQUAL:
			return node;
		default:
			throw new RuntimeException();
		}
	}
	
	void attachLeft(Node parent, Node child)
	{
		if (parent == null)
			throw new IllegalArgumentException();
		if (child == null)
		{
			parent.left = null;
			return;
		}
		parent.left = child;
		child.parent = parent;
	}
	void attachRight(Node parent, Node child)
	{
		if (parent == null)
			throw new IllegalArgumentException();
		if (child == null)
		{
			parent.right = null;
			return;
		}
		parent.right = child;
		child.parent = parent;
	}
	
	
	/*
	 *  rotateLeft(Node) performs a local rotation
	 *  as illustrated here.
	 *         T                   T
	 *  		   |                   |
	 *         A                   B
	 *           \               /
	 *             B  ->       A
	 *            /              \
	 *          C                 C
	 *  Colors are updated so as to preserved the
	 *  colors of links.  One should think of the 
	 *  color of a node as encoding the color of
	 *  the link between that node and its parent.
	 *  Thus, when nodes A and B get flipped, the
	 *  color of A is changed to the color of B
	 *  thereby preserving the color of the link
	 *  between A and B.
	 *  
	 *  One can also show that the black count is
	 *  preserved in the following sense.  Assuming
	 *  node A has a well-defined black count n before
	 *  the rotation, then after the rotation B has
	 *  a well-defined black count, and it is n. Thus
	 *  from the point of view of T above black count
	 *  has not been affected.
	 */
	Node rotateLeft(Node node)
	{
		if (node.right == null)
			throw new IllegalArgumentException();
		Node newTop = node.right;
		Node gamma = newTop.left;
		boolean topIsRed = node.red;
		boolean rightLinkIsRed = node.right.red;
		
		
		
		attachLeft(newTop, node);
		if (exists(gamma))
			attachRight(node, gamma);
		else node.right = null;
		updateSize(node);
		newTop.red = topIsRed;
		newTop.left.red = rightLinkIsRed;
		updateSize(newTop);
		return newTop;
	}
	/*
	 * rotateRight(Node) performs a local rotation
	 *  as illustrated here.
	 *           T                   T
	 *  	         |                   |
	 *           B                   A
	 *         /                       \
	 *      A            ->              B
	 *       \                          /
	 *        C                        C
	 *        
	 * The properties are similar to rotateLeft.  See
	 * comments there.
	 */
	Node rotateRight(Node node) {
		if (node.left == null)
			throw new IllegalArgumentException();
		Node A, B, C;
		boolean colorA, colorB;
		A = node;
		B = node.left;
		C = node.left.right;
		colorA = A.red;
		colorB = B.red;
		
		Node top;
		top = B;
		attachRight(top, A);
		top.right.red = colorB;
		top.red = colorA;
		attachLeft(top.right, C);
		updateSize(top.right);
		updateSize(top);
		return top;
	}
	boolean exists(Node node) {
		return (node != null);
	}
	/*
	 * Suppose we apply flipColor to a node
	 * A positioned as in the figure.
	 *             T
	 *             |
	 *             A
	 *           /   \
 	 *          B     C
 	 * If T has a well-defined black count,
 	 * then note that the black count is not
 	 * affected by this color flip.
	 * 
	 */
	void flipColor(Node node) {
		assert !node.red;
		assert (node.left.red && node.right.red);
		node.left.red = false;
		node.right.red = false;
		node.red = true;
	}
	
	boolean isRed(Node node) {
		return exists(node) && node.red;
	}
	
	// mutually exclusive exhaustive enumeration
	// of cases describing
	// the situation at any node.
	// null can take the place of a black
	enum LocalBranchCase {
		RIGHT_ONLY_RED, 
		TWO_LEFT_RED, 
		BOTH_CHILDREN_RED, 
		LEFT_RED_BLACK, 
		BOTH_CHILDREN_BLACK, 
		NULL}
	
	/*
	 * THIS DESCRIPTION COMMENT STILL IN PROGRESS
	 * The balance(Node) method gets called after
	 * a new node has been put into the tree.  The
	 * put method recurses down to find where node
	 * should go and then it calls balance(Node)
	 * on each node as it goes back up the tree.
	 * 
	 * We follow the techniques discussed in Sedgewick
	 * and Wayne's Algorithms (4th edition).  
	 * 
	 * If node A has a left link to a red node B
	 *              A
	 *            /
	 *          B[R]
	 * we call {A,B} collectively a "3-node" because
	 * there are three places where another node may
	 * be inserted: left of B, right of B, and right
	 * of A.
	 * 
	 * If A.left is black or null, then A is a 2-node.
	 * 
	 * If isRed(node.right) then balance(node)
	 * was passed up to node after being called
	 * on node.right OR node.right was just
	 * inserted and both its child nodes are null.
	 * 
	 * If isRed(node.left) and isRed(node.left.left)
	 * then balance(Node) was passed up from the left
	 * side.
	 * 
	 * Balance is called on a bottom-level node
	 * just after a new node is inserted beneath it,
	 * and then it propagates up.  Assume without 
	 * loss of generality that the tree was properly
	 * balanced before the insertion.  Properly
	 * balanced means
	 *   1) no red node has a red node attached at left;
	 *   2) no red node is attached to its parent on the
	 *      right;
	 *   3) every path from the root down to a null child
	 *      contains the same number of black nodes.
	 * 
	 * If balance is called on a node A then either a
	 * new child was just attached to A at left or at
	 * right or balance was just called on A.left
	 * or A.right (but not both) and returned.
	 * 
	 * If A.right is red, then balance is propagating
	 * up from the right because otherwise the tree
	 * was not balanced before the insertion.
	 * 
	 * This includes the possibility that both A.right
	 * and A.left are red, which we come back to 
	 * in a moment.
	 * 
	 * If A.right is black and A.left is red, then 
	 * either A.left was just attached or balance
	 * was called on A.left and returned the node 
	 * attached there now.  It follows that A.right
	 * does not have a red child attached at right.
	 * A.right may have a red child attached at left.
	 */

	
	Node balance(Node node)
	{
		assert isWellFormed(node.left);
		assert isWellFormed(node.right);
		int initialBlackCount = blackCount(node);
		assert initialBlackCount > -1;
		
		if (isRed(node.left) && isRed(node.left.left) && isRed(node.right))
			throw new IllegalArgumentException("tree is ill-posed");
		switch (getCase(node))
		{
		case RIGHT_ONLY_RED:
			//               node
			//              /    \
			//            /        \
			//    [black/null]      red
			node = rotateLeft(node);
			assert this.getCase(node).equals(LocalBranchCase.LEFT_RED_BLACK);
			break;
		case TWO_LEFT_RED:
			//               node
			//              /    \
			//            red    [?]
			//            /
			//          red
			node = rotateRight(node);
			flipColor(node);
			assert this.getCase(node).equals(LocalBranchCase.BOTH_CHILDREN_BLACK);
			break;
		case BOTH_CHILDREN_RED:
			//               node
			//              /    \
			//            red     red
			flipColor(node);
			assert this.getCase(node).equals(LocalBranchCase.BOTH_CHILDREN_BLACK);
			break;
		case NULL:
		case BOTH_CHILDREN_BLACK:
		case LEFT_RED_BLACK:
			break;
		}
		assert isWellFormed(node);
		assert initialBlackCount == blackCount(node);
		return node;
	}
	boolean isWellFormed(Node node)
	{
		LocalBranchCase initCaseRight = getCase(node);
		return initCaseRight.equals(LocalBranchCase.NULL) ||
				initCaseRight.equals(LocalBranchCase.LEFT_RED_BLACK) ||
				initCaseRight.equals(LocalBranchCase.BOTH_CHILDREN_BLACK);
	}
	int blackCount(Node node)
	{
		if (node == null)
			return 0;
		int left = blackCount(node.left);
		int right = blackCount(node.right);
		if (left == right)
			return node.red ? left : left + 1;
		else
			return -1;
	}
	LocalBranchCase getCase (Node node)
	{
		if (node == null)
			return LocalBranchCase.NULL;
		if (isRed(node.right))
		{
			if (isRed(node.left))
				return LocalBranchCase.BOTH_CHILDREN_RED;
			else
				return LocalBranchCase.RIGHT_ONLY_RED;
		} else if (isRed(node.left))
			if (isRed(node.left.left))
				return LocalBranchCase.TWO_LEFT_RED;
			else 
				return LocalBranchCase.LEFT_RED_BLACK;
		else 
			return LocalBranchCase.BOTH_CHILDREN_BLACK;
	}
	
	class Node implements Comparable<Node>
	{
		Node left;
		Node right;
		Node parent;
		boolean red;
		public Key key;
		public Value value;
		public int size; // cardinality of subtree rooted at node
		public Node(){}
		public Node(Key key, Value value)
		{
			this.size = 1;
			this.key = key;
			this.value = value;
			this.red = true;
		}
		@Override
		public String toString()
		{
			return String.format("(%s,%s)", (key == null ? "null" : key.toString()), (value == null ? "null" : value.toString()));
		}
		public int compareTo(Node o)
		{
			return this.key.compareTo(o.key);
		}
		Relation compare(Node o)
		{
			int cmp = this.compareTo(o);
			return (cmp == 0 ? Relation.EQUAL : ((cmp > 0) ? Relation.GREATER : Relation.LESS));
		}
		Relation compare(Key key)
		{
			int cmp = this.key.compareTo(key);
			return (cmp == 0 ? Relation.EQUAL : ((cmp > 0) ? Relation.GREATER : Relation.LESS));
		}
	}
}
