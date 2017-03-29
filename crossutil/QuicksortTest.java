package crossutil;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import crossutil.Quicksort.Pair;
import crossutil.Quicksort.QuickSorter;

import java.util.List;

public class QuicksortTest 
{

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void demark()
	{
		System.out.println("*********************");
	}
	@Test
	public void getMedianOfTest()
	{
		assertTrue(Quicksort.getMedianOf(12, 15, 20) == 15);
		assertTrue(Quicksort.getMedianOf(22, 2, 99) == 22);
		assertTrue(Quicksort.getMedianOf(5, 25, 7) == 7);
	}
	@Test
	public void getPivotTest()
	{
		List<Integer> list = java.util.Arrays.asList(new Integer[] {26, 16, 81, 53, 54, 68, 11, 98, 20, 45, 7, 33, 29, 13, 50});
		System.out.println(list);
		Quicksort.QuickSorter<Integer>  sorter = new QuickSorter<Integer>();
		sorter.list = list;
		int size = list.size();
		Pair<Integer> pivot = sorter.getPivot(1, 6);
		assertTrue(pivot.value == 53);
	
		pivot = sorter.getPivot(0, size);
		assertTrue(pivot.value == 50);
	}
	@Test
	public void getPivot_outOfBoundsRight() throws RuntimeException
	{
		List<Integer> list = java.util.Arrays.asList(new Integer[] {26, 16, 81});
		System.out.println(list);
		Quicksort.QuickSorter<Integer>  sorter = new QuickSorter<Integer>();
		sorter.list = list;
		assertTrue(sorter.getPivot(0,3).value == 26);
		thrown.expect(RuntimeException.class);
		sorter.getPivot(0,4);
	}
	
	@Test
	public void getPivot_outOfBoundsLeft() throws RuntimeException
	{
		List<Integer> list = java.util.Arrays.asList(new Integer[] {26, 16, 81});
		System.out.println(list);
		Quicksort.QuickSorter<Integer>  sorter = new QuickSorter<Integer>();
		sorter.list = list;
		thrown.expect(RuntimeException.class);
		sorter.getPivot(-1,2);
	}

	@Test
	public void getPivot_short()
	{
		List<Integer> list = java.util.Arrays.asList(new Integer[] {26, 16});
		System.out.println(list);
		Quicksort.QuickSorter<Integer>  sorter = new QuickSorter<Integer>();
		sorter.list = list;
		assertTrue(sorter.getPivot(0,2).value == 26);
		
		list = java.util.Arrays.asList(new Integer[] {26});
		System.out.println(list);
		sorter = new QuickSorter<Integer>();
		sorter.list = list;
		assertTrue(sorter.getPivot(0,1).value == 26);
	}
	
	@Test
	public void getNextInversionPairTest()
	{
		System.out.println("getNextInversionPairTest");
		List<Integer> list = java.util.Arrays.asList(new Integer[] {26, 16, 81, 53, 54, 68, 11, 98, 20, 45, 7, 33, 29, 13, 50});
		System.out.println(list);
		Quicksort.QuickSorter<Integer> sorter = new QuickSorter<Integer>();
		sorter.list = list;
		Pair<Integer> pivot = new Pair<Integer>(9,45);
		Quicksort.IndexPair invPair;
		invPair = null;
		invPair = sorter.getNextInversionPair(invPair, 0, list.size(), pivot);
		System.out.format("found invPair: %s , %s%n", invPair.left, invPair.right);
		assertTrue(invPair.left == 2);
		assertTrue(invPair.right == 13);
	}
	@Test
	public void getNextInversionPairTest_leftEdge()
	{
		System.out.println("getNextInversionPairTest");
		List<Integer> list = java.util.Arrays.asList(new Integer[] {26, 16, 81, 53, 54, 68, 11, 98, 20, 45, 7, 33, 29, 13, 50});
		System.out.println(list);
		Quicksort.QuickSorter<Integer> sorter = new QuickSorter<Integer>();
		sorter.list = list;
		Pair<Integer> pivot = new Pair<Integer>(0,26);
		Quicksort.IndexPair invPair;
		invPair = null;
		invPair = sorter.getNextInversionPair(invPair, 0, list.size(), pivot);
		System.out.format("found invPair: %s , %s%n", invPair.left, invPair.right);
		assertTrue(invPair.left == 2);
		assertTrue(invPair.right == 13);
	}
	
	@Test
	public void getNextInversionPairTest_differentPartitions()
	{
		System.out.println("getNextInversionPairTest_differentPartitions");
		List<Integer> list = java.util.Arrays.asList(new Integer[] {26, 16, 81, 53, 54, 68, 11, 98, 20, 45, 7, 33, 29, 13, 50});
		System.out.println(list);
		Quicksort.QuickSorter<Integer> sorter = new QuickSorter<Integer>();
		sorter.list = list;
		Pair<Integer> pivot;
		Quicksort.IndexPair invPair;
		int cutIndex = 7; //at value 98
		
		pivot = sorter.getPivot(0, cutIndex);
		//pivot is 53, occurs at index 3
		invPair = null;
		invPair = sorter.getNextInversionPair(invPair, 0, cutIndex, pivot);
		System.out.format("found invPair: %s , %s%n", invPair.left, invPair.right);
		
		assertTrue(invPair.left == 2);
		assertTrue(invPair.right == 6);
		
		pivot = sorter.getPivot(cutIndex, list.size());
		System.out.println("pivot value: " + pivot.value);
		//pivot is 50, occurs at index 14
		invPair = null;
		invPair = sorter.getNextInversionPair(invPair, cutIndex, list.size(), pivot);
		System.out.format("found invPair: %s , %s%n", invPair.left, invPair.right);
		Quicksort.IndexPair expectedPair = new Quicksort.IndexPair(7, 13);
		assertTrue(invPair.equals(expectedPair));
		assertTrue(invPair.left == 7);
		assertTrue(invPair.right == 13);
	}
	
	@Test
	public void getNextInversionPairTest_iterated()
	{
		List<Integer> list = java.util.Arrays.asList(new Integer[] {26, 16, 81, 53, 54, 68, 11, 98, 20, 45, 7, 33, 29, 13, 50});
		System.out.println(list);
		Quicksort.QuickSorter<Integer> sorter = new QuickSorter<Integer>();
		sorter.list = list;
		Pair<Integer> pivot;
		Quicksort.IndexPair invPair;
		int startIndex = 7; //at value 98
		int endIndex = list.size();
		pivot = sorter.getPivot(startIndex, endIndex);
		System.out.println("pivot value: " + pivot.value);
		//pivot is 33 at 11
		invPair = null;
		invPair = sorter.getNextInversionPair(invPair, startIndex, endIndex, pivot);
		// 7, 13
		sorter.swap(invPair);
		System.out.println(list);
		invPair = sorter.getNextInversionPair(invPair,startIndex, endIndex, pivot);
		//9, 12
		Quicksort.IndexPair expectedPair = new Quicksort.IndexPair(13, 12);
		assertTrue(invPair.equals(expectedPair));
		System.out.format("found invPair: %s , %s%n", invPair.left, invPair.right);
	}

	private boolean isPartitioned(List<Integer> list, Pair<Integer> pivot, int startIndex, int endIndex)
	{
		for (int i = startIndex; i < pivot.index; i++)
		{
			if (list.get(i) > pivot.value)
			{
				return false;
			}
		}
		for (int i = pivot.index; i < endIndex; i++)
		{
			if (list.get(i) < pivot.value)
			{
				return false;
			}
		}
		return true;
	}
	
	
	@Test
	public void partitionTest()
	{
		List<Integer> list = Arrays.randomIntegerArrayList(15, 100);
		System.out.println(list);
		Quicksort.QuickSorter<Integer> sorter = new QuickSorter<Integer>();
		sorter.list = list;
		int startIndex = 0, endIndex = list.size();
		Pair<Integer> pivot;
		pivot = sorter.partition(startIndex, endIndex);
		boolean isPartitioned = isPartitioned(list, pivot, startIndex, endIndex);
		System.out.println(list);
		System.out.println("pivot value: "+  pivot.value);
		assertTrue(isPartitioned);
	}
	
	@Test
	public void partitionRecursiveTest()
	{
		int numRounds = 40;
		for (int i = 0; i < numRounds; i++)
		{
			List<Integer> list = Arrays.randomIntegerArrayList(15+10*i, 100+20*i);
			System.out.println(list);
			Quicksort.QuickSorter<Integer> sorter = new QuickSorter<Integer>();
			sorter.list = list;
			int startIndex = 0;
			int endIndex = list.size();
			sorter.partitionRecursive(startIndex, endIndex);
			boolean isSorted = Arrays.isSorted(list);
			System.out.println(list);
			assertTrue(isSorted);
		}
	}
	@Test
	public void sortTestforList()
	{
		int numRounds = 40;
		for (int i = 0; i < numRounds; i++)
		{
			List<Integer> list = Arrays.randomIntegerArrayList(15+11*i, 100+20*i);
			System.out.println(list);
			Quicksort.sort(list);
			boolean isSorted = Arrays.isSorted(list);
			System.out.println(list);
			assertTrue(isSorted);
		}
	}
}
