package crossutil;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;

public class BST_Test {
	
	@Before
	public void demark()
	{
		System.out.println("%%%%%%%%%%%%%%%%%%");
		System.out.println("TEST");
	}
	

	
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
		putSizeTest();
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
	
	
	/*
	 * Test that the size method works
	 * properly with the put method
	 */
	private void putSizeTest()
	{
		BST<Integer, Integer> bst = new BST<>();
		assertTrue(bst.size() == 0);
		bst.put(20, 20);
		assertTrue(bst.size() == 1);
		assertTrue(bst.size()  == 1);
		bst.put(15, 15);
		assertTrue(bst.size()  == 2);
		bst.put(27, 27);
		assertTrue(bst.size()  == 3);
		bst.put(37, 37);
		assertTrue(bst.size()  == 4);
		int expectedSize = 4;
		for (int i = 1; i < 15; i++)
		{
			bst.put(i, i);
			assertTrue(bst.size()  == (++expectedSize));
		}
		for (int i = 28; i < 36; i++)
		{
			bst.put(i, i);
			assertTrue(bst.size()  == (++expectedSize));
		}
	}
	
	@Test
	public void containsTest()
	{
		for (int i = 0; i < 20; i++)
		{
			containsTestRandom(10+10*i);
		}
		
		
		Integer[] keys = new Integer[] {20,15,27,11,37,31};
		BST<Integer, Integer> bst = new BSTBuilder(keys).getBST();
		for (Integer key : keys)
			assertTrue(bst.contains(key));
		assertFalse(bst.contains(9));
		assertFalse(bst.contains(12));
	}
	
	
	
	
	
	/*
	 * Test that the keys method works and
	 * produces an Iterable as expected and
	 * that it iterates over all the elements
	 * in BST and nothing else.
	 */
	@Test
	public void keysTest()
	{
		List<Integer> keys = Arrays.asList(new Integer[] {20,15,27,11,37,31});
		BST<Integer, Integer> bst = new BSTBuilder(keys).getBST();
		Set<Integer> keySet = new TreeSet<Integer>(keys);
		Iterable<Integer> it = bst.keys();
		Iterator<Integer> itr = it.iterator();
		Integer next;
		while (itr.hasNext())
		{
			next = itr.next();
			assertTrue(keySet.contains(next));
			keySet.remove(next);
		}
		assertTrue(keySet.isEmpty());
	}
	
	
	private void deleteMinTest_minNotEqualBase()
	{
		List<Integer> keys = Arrays.asList(new Integer[] {20,15,27,11,37,31});
		BST<Integer, Integer> bst = new BSTBuilder(keys).getBST();
		Set<Integer> keySet = new TreeSet<>(keys);
		bst.deleteMin(bst.root);
		assertFalse(bst.contains(11));
		keySet.remove(11);
		Iterable<Integer> it = bst.keys();
		Iterator<Integer> itr = it.iterator();
		while (itr.hasNext())
			assertTrue(keySet.contains(itr.next()));
	}
	
	
	@Test
	public void deleteMinTest()
	{
		deleteMinRecursionTest_root();
		deleteMinRecursionTest();
		deleteMin_removesRandom();
		List<Integer> keys = Arrays.asList(new Integer[] {20,15,27,11,37,31});
		BST<Integer, Integer> bst = new BSTBuilder(keys).getBST();
		Set<Integer> keySet = new TreeSet<>(keys);
		bst.deleteMin(bst.root);
		assertFalse(bst.contains(11));
		keySet.remove(11);
		Iterable<Integer> it = bst.keys();
		Iterator<Integer> itr = it.iterator();
		while (itr.hasNext())
			assertTrue(keySet.contains(itr.next()));
		deleteMinWorks_when_MinIsRoot();
		deleteMinUpdatesSize();
		deleteMinTest_minNotEqualBase();
		deleteMin_sizeTest();
	}
	
	
	/*
	 * Test the protected minNode(Node) method
	 */
	@Test
	public void minNodeTest()
	{
		System.out.println("minNodeTest");
		BST<Integer, Integer> bst = new BST<>();
		List<Integer> inputs = Arrays.asList(new Integer[] {20,15,27,11,37,22});
		for (Integer i : inputs)
			bst.put(i, i);
		assertTrue(bst.root.right.val == 27);
		assertTrue(bst.root.right.left.val == 22);
		assertTrue(bst.minNode(bst.root.right) == bst.root.right.left);
	}
	
	private boolean deleteTestRandom(int length)
	{
		int sizeBefore, sizeAfter;
		BSTBuilder builder = BSTBuilder.random(length);
		BST<Integer, Integer> bst = builder.getBST();
		List<Integer> keyList = builder.getKeys();
		System.out.println(keyList);
		Set<Integer> keySet = new TreeSet<>(keyList);
		Random rand = new Random();
		int r1 = rand.nextInt(length - 1);
		Integer deleteKey = keyList.get(r1);
		System.out.format("attempting to delete key: %s%n",deleteKey);
		boolean testEltPresentBefore = bst.contains(deleteKey);
		System.out.format("contains %s: %s%n",deleteKey,testEltPresentBefore);
		
		sizeBefore = bst.size();
		bst.delete(deleteKey);
		sizeAfter = bst.size();
		
		boolean testEltRemoved = !bst.contains(deleteKey);
		System.out.format("contains %s: %s%n",deleteKey,testEltPresentBefore);
		keySet.remove(deleteKey);
		Iterable<Integer> it = bst.keys();
		Iterator<Integer> itr = it.iterator();
		while (itr.hasNext())
		{
			assertTrue(keySet.contains(itr.next()));
		}
		
		assertTrue(testEltPresentBefore);
		assertTrue(testEltRemoved);
		assertTrue(sizeAfter == sizeBefore - 1);
		return true;
	}
	
	private void deleteRootTest()
	{
		BSTBuilder builder = new BSTBuilder(new Integer[]{20,15,30});
		BST<Integer, Integer> bst = builder.getBST();
		bst.delete(20);
		assertTrue(bst.root.val == 30);
		assertTrue(bst.root.left.val == 15);
	}
	
	@Test
	public void deleteTest()
	{
		int size = 11;
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 300; j++)
				deleteTestRandom(size);
			size+=13;
		}
		deleteRootTest();
	}
	
	@Test
	public void delete_root_test()
	{
		System.out.println("delete_root_test");
		BST<Integer, Integer> bst = new BST<>();
		bst.put(20,null);
		bst.put(15,null);
		bst.put(27,null);
		bst.delete(20);
		assertFalse(bst.contains(20));
	}
	

	/*
	 * Test that deleteMin(Node) and size()
	 * are working together 
	 */
	private void deleteMin_sizeRandom(int size)
	{
		BSTBuilder builder = BSTBuilder.random(size);
		BST<Integer,Integer> bst = builder.getBST();
		Integer randIndex = new Random().nextInt(size);
		Integer randomKey = builder.getKeys().get(randIndex);
		BST<Integer,Integer>.Node base = bst.get(bst.root, randomKey);
		int presize = base.size;
		int expectedSize = (bst.minNode(base) == base ? presize : presize - 1);
		bst.deleteMin(base);
		base.size = (base.left == null ? 0 : base.left.size)
				+ (base.right == null ? 0 : base.right.size)
				+ 1;
		assertTrue(base.size == expectedSize);
	}
	/*
	private void deleteMin_sizeSpecificTest(Integer key, Integer...array)
	{
		//System.out.println("deleteMin_sizeTest");
		///BST<Integer,Integer> bst = randomBST(size);
		BST<Integer,Integer> bst = generateBST(array);
		//Integer r = new Random().nextInt(size);
		//Integer r = 7;
		BST<Integer,Integer>.Node base = bst.get(bst.root, key);
		int presize = base.size;
		int expectedSize = bst.minNode(base) == base ? presize : presize - 1;
		bst.deleteMin(base);
		base.size = (base.left == null ? 0 : base.left.size)
				+ (base.right == null ? 0 : base.right.size)
				+ 1;
		//System.out.println("expected " + expectedSize);
		//System.out.println("found " + base.size);
		assertTrue(base.size == expectedSize);
	}
	*/
	

	private void deleteMin_sizeTest()
	{
		for (int i = 0; i < 10; i++)
			deleteMin_sizeRandom(10 + 100*i);
	}
	
	
	private void deleteMinRecursionTest()
	{
		List<Integer> keys = Arrays.asList(new Integer[] {20,15,27,11,37,31});
		BST<Integer, Integer> bst = new BSTBuilder(keys).getBST();
		BST<Integer, Integer>.Node base = bst.get(bst.root, 27);
		bst.deleteMinRecursion(bst.root, base);
		assertTrue(bst.root.val == 20);
		assertTrue(bst.root.left.val == 15);
		assertTrue(bst.root.right.val == 37);
		assertTrue(bst.root.right.left.val == 31);
		assertTrue(bst.root.right.right == null);
	}
	
	
	private void deleteMinRecursionTest_root()
	{
		List<Integer> keys = Arrays.asList(new Integer[] {20,15,27,11,37,31});
		BST<Integer, Integer> bst = new BSTBuilder(keys).getBST();
		BST<Integer, Integer>.Node base = bst.get(bst.root, 20);
		bst.deleteMinRecursion(bst.root, base);
		System.out.println(bst.root.val);
		assertFalse(bst.contains(11));
	}
	
	
	

	private void deleteMin_removesRandom()
	{
		System.out.println("deleteMin_removesRandom");
		int size = 50, increment = 50, numTests = 100, randIndex;
		Integer randKey;
		BST<Integer, Integer> bst;
		BSTBuilder builder;
		BST<Integer, Integer>.Node randElt, min;
		Random R = new Random();
		for (int i = 0; i < numTests; i++)
		{
			size+=increment;
			builder = BSTBuilder.random(size);
			bst = builder.getBST();
			randIndex = R.nextInt(size);
			List<Integer> keys = builder.getKeys();
			System.out.println(keys);
			randKey = keys.get(randIndex);
			randElt = bst.get(bst.root, randKey);
			min = bst.minNode(randElt);
			bst.deleteMin(randElt);
			assertFalse(bst.contains(min.key));
		}
	}

	private void deleteMinWorks_when_MinIsRoot()
	{
		List<Integer> keys = Arrays.asList(new Integer[] {20,31});
		BST<Integer, Integer> bst = new BSTBuilder(keys).getBST();
		BST<Integer, Integer>.Node base = bst.get(bst.root, 20);
		bst.deleteMin(base);
		System.out.format("root: %s%n",bst.root);
		assertFalse(bst.contains(20));
		assertTrue(bst.root.val == 31);
	}
	

	private void deleteMinUpdatesSize()
	{
		int size = 20;
		BSTBuilder builder = BSTBuilder.random(size);
		BST<Integer,Integer> bst = builder.getBST();
		Integer randIndex = new Random().nextInt(size);
		Integer randomKey = builder.getKeys().get(randIndex);
		BST<Integer,Integer>.Node base = bst.get(bst.root, randomKey);
		int initSize = bst.size();
		assertTrue(initSize == size);
		bst.deleteMin(base);
		int finalSize = bst.size();
		assertTrue(finalSize == initSize - 1);
	}

	private void containsTestRandom(int size)
	{
		BST<Integer,Integer> bst = BSTBuilder.random(size).getBST();
		Random R = new Random();
		Integer inThere = R.nextInt(size);
		Integer notInThere = R.nextInt(size) + size;
		boolean containsTestPositive = bst.contains(inThere);
		boolean containsTestNegative = !bst.contains(notInThere);
		System.out.format("contains %s: %s%n", inThere, containsTestPositive);
		System.out.format("does not contain %s: %s%n", notInThere, containsTestNegative);
		assertTrue(containsTestPositive);
		assertTrue(containsTestNegative);
	}
}
