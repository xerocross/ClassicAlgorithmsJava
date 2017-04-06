package crossutil;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import crossutil.Quicksort.QuickSorter;

import java.util.List;

public class QuicksortTest 
{

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void demark()
	{
		System.out.print("*");
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
		Quicksort.QuickSorter<Integer>  sorter = new QuickSorter<Integer>();
		sorter.list = list;
		int size = list.size();
		IndexValuePair<Integer> pivot = sorter.getPivot(1, 6);
		assertTrue(pivot.value == 53);
	
		pivot = sorter.getPivot(0, size);
		assertTrue(pivot.value == 50);
	}
	@Test
	public void getPivot_outOfBoundsRight() throws RuntimeException
	{
		List<Integer> list = java.util.Arrays.asList(new Integer[] {26, 16, 81});
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
		Quicksort.QuickSorter<Integer>  sorter = new QuickSorter<Integer>();
		sorter.list = list;
		thrown.expect(RuntimeException.class);
		sorter.getPivot(-1,2);
	}
	@Test
	public void getNextInversionPairTest()
	{
		List<Integer> list = java.util.Arrays.asList(new Integer[] {26, 16, 81, 53, 54, 68, 11, 98, 20, 45, 7, 33, 29, 13, 50});
		Quicksort.QuickSorter<Integer> sorter = new QuickSorter<Integer>();
		sorter.list = list;
		IndexValuePair<Integer> pivot = new IndexValuePair<Integer>(9,45);
		IndexPair invPair;
		invPair = null;
		invPair = sorter.getNextInversionPair(invPair, 0, list.size(), pivot);
		assertTrue(invPair.left == 2);
		assertTrue(invPair.right == 13);
	}
	@Test
	public void getNextInversionPairTest_leftEdge()
	{
		List<Integer> list = java.util.Arrays.asList(new Integer[] {26, 16, 81, 53, 54, 68, 11, 98, 20, 45, 7, 33, 29, 13, 50});
		Quicksort.QuickSorter<Integer> sorter = new QuickSorter<Integer>();
		sorter.list = list;
		IndexValuePair<Integer> pivot = new IndexValuePair<Integer>(0,26);
		IndexPair invPair;
		invPair = null;
		invPair = sorter.getNextInversionPair(invPair, 0, list.size(), pivot);
		assertTrue(invPair.left == 2);
		assertTrue(invPair.right == 13);
	}
	
	@Test
	public void getNextInversionPairTest_differentPartitions()
	{
		List<Integer> list = java.util.Arrays.asList(new Integer[] {26, 16, 81, 53, 54, 68, 11, 98, 20, 45, 7, 33, 29, 13, 50});
		Quicksort.QuickSorter<Integer> sorter = new QuickSorter<Integer>();
		sorter.list = list;
		IndexValuePair<Integer> pivot;
		IndexPair invPair;
		int cutIndex = 7; //at value 98
		
		pivot = sorter.getPivot(0, cutIndex);
		//pivot is 53, occurs at index 3
		invPair = null;
		invPair = sorter.getNextInversionPair(invPair, 0, cutIndex, pivot);
		assertTrue(invPair.left == 2);
		assertTrue(invPair.right == 6);
		
		pivot = sorter.getPivot(cutIndex, list.size());
		//pivot is 50, occurs at index 14
		invPair = null;
		invPair = sorter.getNextInversionPair(invPair, cutIndex, list.size(), pivot);
		IndexPair expectedPair = new IndexPair(7, 13);
		assertTrue(invPair.equals(expectedPair));
		assertTrue(invPair.left == 7);
		assertTrue(invPair.right == 13);
	}
	
	@Test
	public void getNextInversionPairTest_iterated()
	{
		List<Integer> list = java.util.Arrays.asList(new Integer[] {26, 16, 81, 53, 54, 68, 11, 98, 20, 45, 7, 33, 29, 13, 50});
		Quicksort.QuickSorter<Integer> sorter = new QuickSorter<Integer>();
		sorter.list = list;
		IndexValuePair<Integer> pivot;
		IndexPair invPair;
		int startIndex = 7; //at value 98
		int endIndex = list.size();
		pivot = sorter.getPivot(startIndex, endIndex);
		//pivot is 33 at 11
		invPair = null;
		invPair = sorter.getNextInversionPair(invPair, startIndex, endIndex, pivot);
		// 7, 13
		sorter.swap(invPair.left,invPair.right);
		invPair = sorter.getNextInversionPair(invPair,startIndex, endIndex, pivot);
		//9, 12
		IndexPair expectedPair = new IndexPair(13, 12);
		assertTrue(invPair.equals(expectedPair));
	}

	private boolean isPartitioned(List<Integer> list, int startIndex, int endIndex, int pivotIndex)
	{
		Integer pivotValue = list.get(pivotIndex);
		for (int i = startIndex; i < pivotIndex; i++)
		{
			if (list.get(i) > pivotValue)
			{
				return false;
			}
		}
		for (int i = pivotIndex; i < endIndex; i++)
		{
			if (list.get(i) < pivotValue)
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
		Quicksort.QuickSorter<Integer> sorter = new QuickSorter<Integer>();
		sorter.list = list;
		int startIndex = 0, endIndex = list.size();
		int pivotIndex = sorter.partitionTheSublist(startIndex, endIndex);
		boolean isPartitioned = isPartitioned(list, startIndex, endIndex, pivotIndex);
		assertTrue(isPartitioned);
	}
	
	@Test
	public void partitionRecursiveTest()
	{
		int numRounds = 40;
		for (int i = 0; i < numRounds; i++)
		{
			List<Integer> list = Arrays.randomIntegerArrayList(15+10*i, 100+20*i);
			Quicksort.QuickSorter<Integer> sorter = new QuickSorter<Integer>();
			sorter.list = list;
			int startIndex = 0;
			int endIndex = list.size();
			sorter.sort(startIndex, endIndex);
			boolean isSorted = Arrays.isSorted(list);
			assertTrue(isSorted);
		}
	}
	@Test
	public void sortTestforList()
	{
		int numRounds = 40;
		for (int i = 0; i < numRounds; i++)
		{
			List<Integer> list = Arrays.randomIntegerArrayList(1+3*i, 100+20*i);
			Quicksort.sort(list);
			boolean isSorted = Arrays.isSorted(list);
			assertTrue(isSorted);
		}
	}
}
