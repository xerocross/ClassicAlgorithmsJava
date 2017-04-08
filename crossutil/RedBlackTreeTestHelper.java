package crossutil;
import java.util.*;

public class RedBlackTreeTestHelper {
	static <U extends Comparable<U>,V> void toList(RedBlackTree<U,V>.Node node, int index, ArrayList<U> list)
	{
		while (list.size() <= index)
			list.add(null);
		list.set(index, node.key);
		
		if (node.left != null)
			toList(node.left, 2*(index+1) - 1, list);
		if (node.right != null)
			toList(node.right, 2*(index+1), list);
	}
	static <U extends Comparable<U>,V> ArrayList<U> toList(RedBlackTree<U,V> rbtree)
	{
		ArrayList<U> list = new ArrayList<>(rbtree.size()+1);
		toList(rbtree.root, 0, list);
		return list;
	}
	static int blackCount(RedBlackTree<?, ?>.Node node)
	{
		if (node == null)
			return 0;
		int left = blackCount(node.left);
		int right = blackCount(node.right);
		left = (node.left == null || node.left.red) ? left : left + 1;
		right = (node.right == null || node.right.red) ? right : right + 1;
		if (left == right)
			return left;
		else
			return -1;
	}
	static boolean isBalanced(RedBlackTree<?, ?>.Node node)
	{
		if (node == null)
			return true;
		boolean left = isBalanced(node.left);
		boolean right = isBalanced(node.right);
		boolean test = left && right;
		test ^= (node.right == null || !node.right.red);
		test ^= (node.left == null || !(node.left.red && node.red));
		return test;
	}
	static String printNode(RedBlackTree<Integer, ?>.Node node)
	{
		if (node == null)
			return "N";
		if (node.left == null & node.right == null)
			return String.format("%s%d", (node.red ? "R" : ""), node.key);
		String l = printNode(node.left);
		String r = printNode(node.right);
		return String.format("%s%d [%s | %s]", (node.red ? "R" : "") ,node.key, l , r);
	}
}
