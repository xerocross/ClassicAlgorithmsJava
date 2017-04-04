package crossutil;

import crossutil.BST.Node;
import crossutil.BST.Relation;

public class RedBlackTree <Key extends Comparable<Key>, Value>
{

	class Node implements Comparable<Node>
	{
		Node left;
		Node right;
		Node parent;
		public Key key;
		public Value value;
		public int size; // cardinality of subtree rooted at node
		public Node(Key key, Value value)
		{
			this.size = 1;
			this.key = key;
			this.value = value;
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
			throw new RuntimeException();
		}
	}
}
