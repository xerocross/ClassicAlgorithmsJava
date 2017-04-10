package crossutil;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import crossutil.RedBlackTree.LocalBranchCase;

public class RedBlackTreeProofs {
	RedBlackTree<Integer, TestValue> rbt;
	RedBlackTree<Integer, TestValue>.Node A, B, C, result;
	
	@Before
	public void build()
	{
		rbt = new RedBlackTree<>();
		A = rbt.new Node();
		B = rbt.new Node();
		C = rbt.new Node();
		
	}
	
	@Test
	public void rotateRightProp1() {
		// if case(node) == TWO_LEFT_RED
		// then case(rotateRight(node)) == BOTH_CHILDREN_RED
		rbt.attachLeft(A, B);
		rbt.attachLeft(B, C);
		B.red = true;
		C.red = true;
		LocalBranchCase nodeCase = rbt.getCase(A);
		assert nodeCase.equals(LocalBranchCase.TWO_LEFT_RED);
		result = rbt.rotateRight(A);
		assertEquals(rbt.getCase(result), LocalBranchCase.BOTH_CHILDREN_RED);
	}

}
