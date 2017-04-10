package crossutil;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

public class MergeSortTest {
	@Test
	public void mergesortTest()
	{
		int numRounds = 40;
		for (int i = 0; i < numRounds; i++)
		{
			List<Integer> list = Arrays.randomIntegerArrayList(10+3*i, 100+20*i);
			System.out.println(list);
			List<Integer> sortedList = MergeSort.sort(list);
			System.out.println(sortedList);
			System.out.println("***");
			boolean isSorted = Arrays.isSorted(sortedList);
			assertTrue(isSorted);
		}
	}
	@Test
	public void mergesort2Test()
	{
		int numRounds = 40;
		for (int i = 0; i < numRounds; i++)
		{
			List<Integer> list = Arrays.randomIntegerArrayList(10+3*i, 100+20*i);
			System.out.println(list);
			List<Integer> sortedList = MergeSort2.sort(list);
			System.out.println(sortedList);
			System.out.println("***");
			boolean isSorted = Arrays.isSorted(sortedList);
			assertTrue(isSorted);
		}
	}
}
