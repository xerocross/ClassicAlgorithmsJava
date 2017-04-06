package crossutil;

import crossutil.BST.Relation;

public class RedBlackTree <Key extends Comparable<Key>, Value>
{
	Node root;
	
	
	public void put(Key key, Value val)
	{
		root = put(root, key, val);
	}
	public Value get(Key key)
	{
		Node result = get(root, key);
		return (result == null) ? null : result.value;
	}
	Node put(Node node, Key key, Value value)
	{
		if (node == null) 
			return new Node(key, value);
		Relation rel = getRelation(node.key,key);
		switch(rel)
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
		parent.left = child;
		child.parent = parent;
	}
	void attachRight(Node parent, Node child)
	{
		parent.right = child;
		child.parent = parent;
	}
	Node rotateLeft(Node node)
	{
		Node newTop = node.right;
		Node gamma = newTop.left;
		boolean topIsRed = node.red;
		boolean rightLinkIsRed = node.right.red;
		attachLeft(newTop, node);
		//newTop.left = node;
		if (exists(gamma))
			attachRight(node, gamma);
		//node.right = gamma;
		newTop.red = topIsRed;
		newTop.left.red = rightLinkIsRed;
		return newTop;
	}
	Node rotateRight(Node node)
	{
		Node newTop = node.left;
		Node gamma = newTop.right;
		boolean topIsRed = node.red;
		boolean rightLinkIsRed = node.left.red;
		attachRight(newTop, node);
		//newTop.right = node;
		if (exists(gamma))
			attachLeft(node, gamma);
		//node.left = gamma;
		newTop.red = topIsRed;
		newTop.right.red = rightLinkIsRed;
		return newTop;
	}
	boolean exists(Node node)
	{
		return (node != null);
	}
	void flipColor(Node node)
	{
		node.left.red = false;
		node.right.red = false;
		node.red = true;
	}
	Node balance(Node node)
	{
		if (exists(node.left) && node.left.red && exists(node.left.right) && node.left.right.red)
			node.left = rotateLeft(node.left);
		if (exists(node.left) && node.left.red && exists(node.left.left) && node.left.left.red)
			node = rotateRight(node);
		if (exists(node.left) && node.left.red && exists(node.right) && node.right.red)
			flipColor(node);
		return node;
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
		public Relation compare(Node o)
		{
			int cmp = this.compareTo(o);
			return (cmp == 0 ? Relation.EQUAL : ((cmp > 0) ? Relation.GREATER : Relation.LESS));
		}
		public Relation compare(Key key)
		{
			int cmp = this.key.compareTo(key);
			return (cmp == 0 ? Relation.EQUAL : ((cmp > 0) ? Relation.GREATER : Relation.LESS));
		}
	}

}
