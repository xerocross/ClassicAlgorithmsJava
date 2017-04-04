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
	
	
	
}
