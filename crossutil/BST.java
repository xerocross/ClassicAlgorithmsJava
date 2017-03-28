package crossutil;
import java.util.ArrayDeque;
import java.util.Queue;

// binary search tree
public class BST<Key extends Comparable<Key>, Value>
{
	Node root;
	enum Relation {LESS,GREATER,EQUAL}
	
	class Node implements Comparable<Node>
	{
		public Node left;
		public Node right;
		public Key key;
		public Value val;
		public int size; // cardinality of subtree rooted at node
		public Node(Key key, Value val)
		{
			this.size = 1;
			this.key = key;
			this.val = val;
		}
		@Override
		public String toString()
		{
			return key == null ? "null" : key.toString();
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
		root = delete(root, key);
	}
	public boolean contains(Key key)
	{
		return (get(root, key) == null ? false : true);
	}
	public int size()
	{
		return (root == null) ? 0 : root.size;
	}
	public boolean isEmpty()
	{
		return (this.size() == 0) ? true : false;
	}
	public Iterable<Key> keys()
	{
		ArrayDeque<Key> iterable = new ArrayDeque<>();
		return (root == null) ? iterable : generateIterable(root, iterable);
	}

	private Node put(Node node, Key key, Value val)
	{
		if (node == null) 
			return new Node(key, val);
		Relation rel = getRelation(node.key,key);
		switch(rel)
		{
		case LESS:
			node.right = put(node.right, key, val);
			break;
		case GREATER:
			node.left = put(node.left, key, val);
			break;
		case EQUAL:
			node.val = val;
			break;
		}
		updateSize(node);
		return node;
	}
	Node get(Node node, Key key)
	{
		if (node == null)
			return null;
		Relation rel = getRelation(node.key, key);
		switch(rel)
		{
		case LESS:
			return get(node.right, key);
		case GREATER:
			return get(node.left, key);
		case EQUAL:
			return node;
		default:
			assert false;
			return null;
		}
	}
	private Queue<Key> generateIterable(Node node, Queue<Key> queue)
	{
		queue.offer(node.key);
		if (node.left != null)
			generateIterable(node.left, queue);
		if (node.right!= null)
			generateIterable(node.right, queue);
		return queue;
	}
	
	Node minNode(Node node)
	{
		if (node.left == null)
			return node;
		else 
			return minNode(node.left);
	}
	
	Node deleteMinHelper(Node node)
	{
		boolean nodeIsMin = (node.left == null);
		if (nodeIsMin)
			return node.right;
		else 
		{
			Node result = deleteMinHelper(node.left);
			node.left = result;
			updateSize(node);
		}
		return node;
	}
	
	Node deleteMinRecursion(Node top, Node subtreeTop)
	{
		Relation dir =  subtreeTop.compare(top);
		switch (dir)
		{
		case LESS:
			if (top.left == subtreeTop) {
				top.left = deleteMinHelper(subtreeTop);
			}
			else 
				top.left = deleteMinRecursion(top.left, subtreeTop);
			break;
		case GREATER:
			if (top.right == subtreeTop) {
				top.right = deleteMinHelper(subtreeTop);
			}
			else 
				top.right = deleteMinRecursion(top.right, subtreeTop);
			break;
		case EQUAL:
			if (top == root)
			{
				Node result = deleteMinHelper(root);
				root = result;
				updateSize(root);
				return root;
			}
			else 
				throw new RuntimeException();
		}
		updateSize(top);
		return top;
	}
	Node deleteMin(Node node)
	{
		Node result = deleteMinRecursion(root, node);
		root = result;
		return node;
	}
	private Node successor(Node node)
	{
		if (node.right == null)
			throw new IllegalArgumentException();
		return minNode(node.right);
	}
	
	Relation getRelation(Key x, Key y)
	{
		int cmp = x.compareTo(y);
		return (cmp == 0 ? Relation.EQUAL : ((cmp > 0) ? Relation.GREATER : Relation.LESS));
	}
	
	private Node delete(Node node, Key key)
	{
		if (node == null)
		{
			return null;
		}
		Relation rel = getRelation(node.key, key);
		switch(rel)
		{
		case EQUAL:
			Node right = node.right;
			Node left = node.left;
			if (right == null)
				if (left == null)
					return null;
				else
					return left;
			Node successor = successor(node);
			if (successor != right)
			{
				deleteMinRecursion(node, right);
				successor.right = right;
				updateSize(right);
			}
			successor.left = node.left;
			updateSize(successor);
			return successor;
		case GREATER:
			node.left = delete(node.left,key);
			break;
		case LESS:
			node.right = delete(node.right,key);
			break;
		}
		updateSize(node);
		return node;
	}
	
	private void updateSize(Node node)
	{
		node.size = (node.left == null ? 0 : node.left.size) 
				+ (node.right == null ? 0 : node.right.size) 
				+ 1;
	}
}
