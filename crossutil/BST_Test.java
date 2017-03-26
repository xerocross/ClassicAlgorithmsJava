package crossutil;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
	
	@Test
	public void sizeTest()
	{
		BST<Integer, Integer> bst = new BST<>();
		assertTrue(bst.size() == 0);
		bst.put(20, 20);
		assertTrue(bst.size() == 1);
		assertTrue(bst.root.size == 1);
		bst.put(15, 15);
		assertTrue(bst.root.size == 2);
		bst.put(27, 27);
		assertTrue(bst.root.size == 3);
		bst.put(37, 37);
		assertTrue(bst.root.size == 4);
		int expectedSize = 4;
		for (int i = 1; i < 15; i++)
		{
			bst.put(i, i);
			assertTrue(bst.root.size == (++expectedSize));
		}
		for (int i = 28; i < 36; i++)
		{
			bst.put(i, i);
			assertTrue(bst.root.size == (++expectedSize));
		}
	}
	@Test
	public void containsTest()
	{
		BST<Integer, Integer> bst = new BST<>();
		bst.put(20, 20);
		bst.put(15, 15);
		bst.put(27, 27);
		bst.put(11, 11);
		bst.put(37, 37);
		bst.put(31, 31);
		assertTrue(bst.contains(31));
		assertTrue(bst.contains(20));
		assertTrue(bst.contains(15));
		assertTrue(bst.contains(27));
		assertTrue(bst.contains(11));
		assertTrue(bst.contains(37));
		assertFalse(bst.contains(9));
		assertFalse(bst.contains(12));
	}
	@Test
	public void keysTest()
	{
		BST<Integer, Integer> bst = new BST<>();
		Set<Integer> inputs = new HashSet<>();
		inputs.add(20);
		inputs.add(15);
		inputs.add(27);
		inputs.add(15);
		inputs.add(27);
		inputs.add(11);
		inputs.add(37);
		for (Integer i : inputs)
			bst.put(i, i);
		Iterable<Integer> it = bst.keys();
		Iterator<Integer> itr = it.iterator();
		while (itr.hasNext())
		{
			assertTrue(inputs.contains(itr.next()));
		}
		
	}
}
