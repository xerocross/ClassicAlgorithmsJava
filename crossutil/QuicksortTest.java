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
}
