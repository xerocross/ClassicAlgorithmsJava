package crossutil;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {
	RedBlackTree<Integer, TestValue> rbt;
	final Integer [] perm1 = {2, 29, 1, 20, 25, 21, 26, 15, 9, 13, 27, 24, 23, 5, 12, 22, 7, 14, 3, 6, 4, 17, 10, 19, 0, 16, 8, 11, 18, 28};
	RedBlackTree<Integer, TestValue>.Node apple, banana, cherry, result;
	
	
	private void testInputRearrangement(Integer[] in, Integer[] expected)
	{
		java.util.List<Integer> outExpectedList = java.util.Arrays.asList(expected);
		rbt = new RedBlackTree<>();
		RedBlackTreeTestHelper.put(rbt, in);
		ArrayList<Integer> outActual = RedBlackTreeTestHelper.toList(rbt);
		assertEquals(outActual, outExpectedList);
	}
	
	class TestPair {
		Integer [] input;
		Integer [] expectedOutput;
		public TestPair(Integer [] input, Integer [] expectedOutput) {
			this.input = input;
			this.expectedOutput = expectedOutput;
		}
	}
	

	
	@Before
	public void setUp() {
		rbt = new RedBlackTree<>();
	}
	
	
	@Test
	public void putInsertsAndGetRetrieves()
	{
		for (int j = 0; j < 30; j++)
		{
			int length = 20 + 11*j;
			rbt = new RedBlackTree<>();
			Integer [] permutation = Arrays.permutation(length);
			int half = length/2;
			for (int i = 0; i < half; i++)
				rbt.put(permutation[i], new TestValue());
			TestValue testValue = new TestValue();
			rbt.put(permutation[half], testValue);
			for (int i = half + 1; i < length; i++)
				rbt.put(permutation[i], new TestValue());
			assertTrue(rbt.get(permutation[half]) == testValue);
		}
	}
	
	@Test
	public void attachLeftUpdatesLinksCorrectly() 
	{
		RedBlackTree<Integer, TestValue>.Node apple, banana;
		rbt = new RedBlackTree<>();
		apple = rbt.new Node();
		banana = rbt.new Node();
		rbt.attachLeft(banana, apple);
		assertTrue(banana.left == apple && apple.parent == banana);
	}
	@Test
	public void attachRightUpdatesLinksCorrectly() 
	{
		RedBlackTree<Integer, TestValue>.Node apple, banana;
		rbt = new RedBlackTree<>();
		apple = rbt.new Node();
		banana = rbt.new Node();
		rbt.attachRight(banana, apple);
		assertTrue(banana.right == apple && apple.parent == banana);
	}
	
	@Test
	public void rotateLeftUpdatesLinksCorrectly()
	{
		RedBlackTree<Integer, TestValue>.Node apple, banana, cherry, result;
		rbt = new RedBlackTree<>();
		apple = rbt.new Node();
		banana = rbt.new Node();
		cherry = rbt.new Node();
		rbt.attachRight(apple, cherry);
		rbt.attachLeft(cherry, banana);
		result = rbt.rotateLeft(apple);
		assertTrue(result == cherry && result.left == apple && apple.parent == result && result.left.right == banana && banana.parent == result.left);
	}
	@Test
	public void rotateLeftUpdatesColorsCorrectly()
	{
		RedBlackTree<Integer, TestValue>.Node apple, banana, cherry, result;
		rbt = new RedBlackTree<>();
		for(int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
			{
				apple = rbt.new Node();
				banana = rbt.new Node();
				cherry = rbt.new Node();
				rbt.attachRight(apple, cherry);
				rbt.attachLeft(cherry, banana);
				apple.red = (i % 2 == 0);
				cherry.red = (j % 2 == 0);
				boolean isRed1 = apple.red;
				boolean isRed2 = cherry.red;
				result = rbt.rotateLeft(apple);
				assertTrue(result.red == isRed1 && result.left.red == isRed2);
			}
	}
	@Test
	public void rotateRightUpdatesLinksCorrectly()
	{
		RedBlackTree<Integer, Integer>.Node apple, banana, cherry, result;
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		apple = rbt.new Node();
		banana = rbt.new Node();
		cherry = rbt.new Node();
		rbt.attachLeft(cherry, apple);
		rbt.attachRight(apple, banana);
		result = rbt.rotateRight(cherry);
		assertTrue(result == apple && result.right == cherry && cherry.parent == result && result.right.left == banana && banana.parent == result.right);
	}
	@Test
	public void rotateRightUpdatesColorsCorrectly()
	{
		RedBlackTree<Integer, TestValue>.Node apple, banana, cherry, result;
		rbt = new RedBlackTree<>();
		for(int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
			{
				apple = rbt.new Node();
				banana = rbt.new Node();
				cherry = rbt.new Node();
				rbt.attachLeft(cherry, apple);
				rbt.attachRight(apple, banana);
				apple.red = (i % 2 == 0);
				cherry.red = (j % 2 == 0);
				boolean isRed1 = cherry.red;
				boolean isRed2 = apple.red;
				result = rbt.rotateRight(cherry);
				assertTrue(result.red == isRed1 && result.right.red == isRed2);
			}
	}
	@Test
	public void insertInto3NodeTest_left()
	{
		//build 3-node
		RedBlackTree<Integer, Integer>.Node apple, banana, cherry, result;
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		apple = rbt.new Node(10,10);
		banana = rbt.new Node(8,8);
		banana.red = true;
		rbt.root = apple;
		apple.red = false;
		rbt.attachLeft(apple, banana);
		//attach new node at left
		cherry = rbt.new Node(7,7);
		rbt.attachLeft(banana, cherry);
		cherry.red = true;
		//balance
		result = rbt.balance(apple);
		assertTrue(result == banana && result.left == cherry && result.right == apple);
	}
	@Test
	public void insertInto3NodeTest_middle()
	{
		//build 3-node
		RedBlackTree<Integer, Integer>.Node apple, banana, cherry, result;
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		apple = rbt.new Node(10,10);
		banana = rbt.new Node(8,8);
		banana.red = true;
		rbt.root = apple;
		apple.red = false;
		rbt.attachLeft(apple, banana);
		//attach new node at middle
		cherry = rbt.new Node(9,9);
		rbt.attachRight(banana, cherry);
		cherry.red = true;
		//balance
		apple.left = rbt.balance(banana);
		System.out.println(apple.left.key);
		result = rbt.balance(apple);
		System.out.println(result.key);
		assertTrue(result == cherry && cherry.left == banana && cherry.right == apple);
	}
	@Test
	public void insertInto3NodeTest_right()
	{
		//build 3-node
		RedBlackTree<Integer, Integer>.Node apple, banana, cherry, result;
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		apple = rbt.new Node(10,10);
		banana = rbt.new Node(8,8);
		banana.red = true;
		rbt.root = apple;
		apple.red = false;
		rbt.attachLeft(apple, banana);
		//attach new node at right
		cherry = rbt.new Node(11,11);
		rbt.attachRight(apple, cherry);
		cherry.red = true;
		// balance
		result = rbt.balance(apple);
		assertTrue(result == apple && !banana.red && !cherry.red && apple.red);
	}
	
	@Test
	public void rootAlwaysBlackAfterPut()
	{
		rbt = new RedBlackTree<>();
		int len = perm1.length;
		for (int i = 0; i < len; i++)
		{
			rbt.put(perm1[i], new TestValue());
			assertFalse(rbt.root.red);
		}
	}
	
	
	@Test
	public void randomTreeIsBalanced()
	{
		int length = 30;
		for (int j = 0; j < 40; j++)
		{
			RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
			Integer[] permutation = Arrays.permutation(length + 17*j);
			System.out.println(Arrays.asList(permutation));
			for (Integer i : permutation)
				rbt.put(i, i);
			System.out.print(RedBlackTreeTestHelper.printNode(rbt.root));
			assertTrue(RedBlackTreeTestHelper.isBalanced(rbt.root));
			int blackCount = RedBlackTreeTestHelper.blackCount(rbt.root);
			System.out.println(blackCount);
			assertTrue(blackCount != -1);
		}
	}
	
	
	@Test
	public void rotateLeftUpdatesSize()
	{
		rbt = new RedBlackTree<>();
		apple = rbt.new Node();
		banana = rbt.new Node();
		cherry = rbt.new Node();
		rbt.attachRight(apple, banana);
		rbt.attachLeft(banana, cherry);
		cherry.size = 1;
		banana.size = 2;
		apple.size = 3;
		result = rbt.rotateLeft(apple);
		assertEquals(banana.size, 3);
		assertEquals(apple.size, 2);
		assertEquals(cherry.size, 1);
	}
	@Test
	public void rotateRightUpdatesSize()
	{
		rbt = new RedBlackTree<>();
		apple = rbt.new Node();
		banana = rbt.new Node();
		cherry = rbt.new Node();
		
		rbt.attachLeft(banana, apple);
		rbt.attachRight(apple, cherry);
		cherry.size = 1;
		banana.size = 3;
		apple.size = 2;
		result = rbt.rotateRight(banana);
		assertEquals(banana.size, 2);
		assertEquals(apple.size, 3);
		assertEquals(cherry.size, 1);
	}
	@Test
	public void putMethodUpdatesSizeCorrectly()
	{
		rbt = new RedBlackTree<>();
		int len = perm1.length;
		int expectedSize = 0;
		for (int i = 0; i < len; i++)
		{
			rbt.put(perm1[i], new TestValue());
			expectedSize++;
			assertEquals(rbt.size(), expectedSize);
		}
	}
	
	private ArrayList<TestPair> buildPutTestTable() {
		ArrayList<TestPair> putTestTable = new ArrayList<>();
		putTestTable.add(new TestPair(
				new Integer[] {20,30}, 
				new Integer[] {30,20}
			));
		putTestTable.add(new TestPair(
				new Integer[] {20,10}, 
				new Integer[] {20,10}
			));
		putTestTable.add(new TestPair(
				new Integer[] {20,15,10}, 
				new Integer[] {15,10,20}
			));
		putTestTable.add(new TestPair(
				new Integer[] {20,15,30}, 
				new Integer[] {20,15,30}
			));
		putTestTable.add(new TestPair(
				new Integer[] {20,15,17}, 
				new Integer[] {17,15,20}
			));
		return putTestTable;
	}
	@Test
	public void putBalanceTest() {
		ArrayList<TestPair> putTestTable = buildPutTestTable();
		for (TestPair tp : putTestTable)
			testInputRearrangement(tp.input, tp.expectedOutput);
	}
}