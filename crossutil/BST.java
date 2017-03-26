package crossutil;

// binary search tree
public class BST<Key extends Comparable<Key>, Value>
{
	Node root;
	class Node
	{
		public Node left;
		public Node right;
		public Key key;
		public Value val;
		public Node(Key key, Value val)
		{
			this.key = key;
			this.val = val;
		}
	}
	
	public Value get(Key key)
	{
		Node result = get(root, key);
		return (result == null) ? null : result.val;
	}
	public void put(Key key, Value val)
	{
		root = put(root, key, val);
	}
	public void delete(Key key)
	{
		
	}
	public boolean contains(Key key)
	{
		return false;
	}
	public int size()
	{
		return 0;
	}
	public boolean isEmpty()
	{
		return false;
	}
	public Iterable<Key> keys()
	{
		return null;
	}

	private Node put(Node node, Key key, Value val)
	{
		if (node == null)
			return new Node(key, val);
		int comp = node.key.compareTo(key);
		if (comp < 0) // node.key < key
		{
			node.right = put(node.right, key, val);
		}
		else if (comp > 0) // node.key > key
			node.left = put(node.left, key, val);
		else {
			node.val = val;
		}
		return node;
	}
	private Node get(Node node, Key key)
	{
		if (node == null)
			return null;
		int comp = node.key.compareTo(key);
		if (comp < 0) // node.key < key
			return get(node.right, key);
		else if (comp > 0) // node.key > key
			return get(node.left, key);
		else
			return node;
	}
}
