package crossutil;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;


public class ArraysTest 
{
	private boolean isPermutation(Integer[] a)
	{
		int len = a.length;
		List<Integer> list = Arrays.asList(a);
		for (int i = 0; i < len; i++)
			if (list.contains(new Integer(i)) == false)
				return false;
		return true;
	}
	
	@Test
	public void permutationTest_isPermutation()
	{
		Integer[] p = Arrays.permutation(20);
		System.out.println(Arrays.asList(p));
		boolean isPermutation = isPermutation(p);
		assertTrue(isPermutation);
	}
}
