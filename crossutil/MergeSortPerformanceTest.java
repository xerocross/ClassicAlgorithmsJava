package crossutil;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MergeSortPerformanceTest {

	@Test
	public void test() {
		Long timeMergeSort1 = 0L;
		Long timeMergeSort2 = 0L;
		Long start, stop;
		for (int i = 0; i < 100; i++)
		{
			List<Integer> listA = Arrays.randomIntegerArrayList(20_000, 400_000);
			List<Integer> listB = new ArrayList<>(listA);
			start = System.currentTimeMillis();
			MergeSort.sort(listA);
			stop = System.currentTimeMillis();
			timeMergeSort1+= (stop - start);
			
			start = System.currentTimeMillis();
			MergeSort2.sort(listB);
			stop = System.currentTimeMillis();
			timeMergeSort2+= (stop - start);
		}
		System.out.format("MergeSort1: %d; MergeSort2: %d",timeMergeSort1, timeMergeSort2);
	}

}
