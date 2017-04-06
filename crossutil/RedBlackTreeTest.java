package crossutil;

import static org.junit.Assert.*;

import org.junit.Test;

public class RedBlackTreeTest {

	@Test
	public void putTest() 
	{
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		rbt.put(3,3);
		assertTrue(rbt.root.value == 3);
	}
	@Test
	public void getTest() 
	{
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		rbt.put(3,3);
		rbt.put(2,2);
		rbt.put(4,4);
		rbt.put(7,7);
		assertTrue(rbt.get(7).equals(7));
	}
	@Test
	public void attachLeftTest() 
	{
		RedBlackTree<Integer, Integer>.Node apple, banana;
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		apple = rbt.new Node();
		banana = rbt.new Node();
		rbt.attachLeft(banana, apple);
		assertTrue(banana.left == apple && apple.parent == banana);
	}
	@Test
	public void attachRightTest() 
	{
		RedBlackTree<Integer, Integer>.Node apple, banana;
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		apple = rbt.new Node();
		banana = rbt.new Node();
		rbt.attachRight(banana, apple);
		assertTrue(banana.right == apple && apple.parent == banana);
	}
	@Test
	public void rotateLeftTest_form()
	{
		RedBlackTree<Integer, Integer>.Node apple, banana, cherry, result;
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		apple = rbt.new Node();
		banana = rbt.new Node();
		cherry = rbt.new Node();
		rbt.attachRight(apple, cherry);
		rbt.attachLeft(cherry, banana);
		result = rbt.rotateLeft(apple);
		assertTrue(result == cherry && result.left == apple && apple.parent == result && result.left.right == banana && banana.parent == result.left);
	}
	@Test
	public void rotateLeftTest_color()
	{
		RedBlackTree<Integer, Integer>.Node apple, banana, cherry, result;
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		
		for(int i = 0; i < 2; i++)
		{
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
	}
	@Test
	public void rotateRightTest_form()
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
	public void rotateRightTest_color()
	{
		RedBlackTree<Integer, Integer>.Node apple, banana, cherry, result;
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		
		for(int i = 0; i < 2; i++)
		{
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
	public void putRootColorTest()
	{
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		rbt.put(10, 10);
		assertTrue(rbt.root.red == false);
	}
	
	@Test
	public void insertToRootRight()
	{
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		rbt.put(10, 10);
		rbt.put(12, 12);
		boolean t1, t2, t3, t4;
		t1 = rbt.root.key == 12;
		t2 = rbt.root.left.key == 10;
		t3 = rbt.root.left.red;
		t4 = !rbt.root.red;
		if (!(t1 && t2 && t3 && t4))
			System.out.format("%s %s %s %s", t1, t2, t3, t4);
		assertTrue(t1 && t2 && t3 && t4);
	}
	@Test
	public void putAndBalanceTest_left_atRoot()
	{
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		rbt.put(10, 10);
		rbt.root.red = false;
		rbt.put(8, 8);
		// put left left
		rbt.put(7, 7);
		assertTrue(rbt.root.key == 8
				&& rbt.root.left.key == 7
				&& rbt.root.right.key == 10
				&& !rbt.root.red
				&& !rbt.root.left.red
				&& !rbt.root.right.red);
	}
	
	boolean isBalanced(RedBlackTree<Integer, Integer>.Node node)
	{
		if (node == null)
			return true;
		//System.out.println("node " + node.key);
		boolean left = isBalanced(node.left);
		boolean right = isBalanced(node.right);
		boolean test = left && right;
		test ^= (node.right == null || !node.right.red);
		test ^= (node.left == null || !(node.left.red && node.red));
		return test;
	}
	int blackCount(RedBlackTree<Integer, Integer>.Node node)
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
	
	
	@Test
	public void isBalancedTest()
	{
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		{
			//two left reds in a row
			RedBlackTree<Integer, Integer>.Node apple, banana, cherry;
			apple = rbt.new Node();
			banana = rbt.new Node();
			banana.red = true;
			apple.red = true;
			cherry = rbt.new Node();
			cherry.red = false;
			rbt.attachRight(apple, cherry);
			rbt.attachLeft(apple, banana);
			assertTrue(!isBalanced(apple));
		}
		{
			//right red
			RedBlackTree<Integer, Integer>.Node apple, banana, cherry;
			apple = rbt.new Node();
			cherry = rbt.new Node();
			cherry.red = true;
			rbt.attachRight(apple, cherry);
			assertTrue(!isBalanced(apple));
		}
	}
	
	String printNode(RedBlackTree<Integer, Integer>.Node node)
	{
		if (node == null)
			return "N";
		if (node.left == null & node.right == null)
			return String.format("%s%d", (node.red ? "R" : ""), node.key);
		String l = printNode(node.left);
		String r = printNode(node.right);
		
		return String.format("%s%d [%s | %s]", (node.red ? "R" : "") ,node.key, l , r);
	}
	
	@Test
	public void balanceTest()
	{
		//RedBlackTree<Integer, Integer>.Node apple
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		Integer[] permutation = Arrays.permutation(30);
		System.out.println(Arrays.asList(permutation));
		
		for (Integer i : permutation)
			rbt.put(i, i);
		System.out.print(printNode(rbt.root));
		assertTrue(isBalanced(rbt.root));
		int blackCount = blackCount(rbt.root);
		System.out.println(blackCount);
		assertTrue(blackCount != -1);
	}
	
}
