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
	public void putColorTest() 
	{
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		rbt.put(3,3);
		assertTrue(rbt.root.red);
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
		result = rbt.balance(apple);
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
	
}
