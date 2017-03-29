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
}
