package crossutil;

import static org.junit.Assert.*;
import org.junit.Test;

public class BST_Test {
	@Test
	public void putTest() {
		BST<Integer, Integer> bst = new BST<>();
		bst.put(20, 20);
		BST<Integer, Integer>.Node root = bst.root;
		assertTrue(root.key.equals(20));
		assertTrue(root.left == null);
		assertTrue(root.right == null);
		bst.put(15, 15);
		assertTrue(root.left.key.equals(15));
		bst.put(27, 27);
		assertTrue(root.right.key.equals(27));
		bst.put(18, 18);
		assertTrue(root.left.right.key.equals(18));
		assertTrue(root.left.right.val.equals(18));
		bst.put(22, 22);
		assertTrue(root.right.left.key.equals(22));
	}
	
	@Test
	public void getTest()
	{
		BST<Integer, Integer> bst = new BST<>();
		bst.put(20, 20);
		bst.put(15, 15);
		bst.put(27, 27);
		assertTrue(bst.get(20) == 20);
		assertTrue(bst.get(15) == 15);
		assertTrue(bst.get(27) == 27);
		assertTrue(bst.get(39) == null);
		bst.put(18, 18);
		assertTrue(bst.get(18) == 18);
	}
}
