package sortpracticetest;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;
import sortpractice.Arrays;
import java.util.ArrayList;

public class ArraysTest {

	
	public ArrayList<Integer> randomIntegerArrayList(int size, int max)
	{
		ArrayList<Integer> arr = new ArrayList<>(size);
		for (int i = 0; i < size; i++)
		{
			arr.add(ThreadLocalRandom.current().nextInt(0, max));
		}
		return arr;
	}
	
	public ArrayList<Integer> sortedIntegerArrayList(int size)
	{
		ArrayList<Integer> arr = new ArrayList<>();
		int current = 0;
		int randIncrementMax = 10;
		for (int i = 0; i < size; i++)
		{
			current = current + ThreadLocalRandom.current().nextInt(0, randIncrementMax);
			arr.add(current);
		}
		return arr;
	}
	
	
	public <E extends Comparable<E>> boolean isSorted(ArrayList<E> arr)
	{
		int len = arr.size();
		int antemax = len - 1;
		for (int i = 0; i < antemax; i++) 
		{
			if (arr.get(i).compareTo(arr.get(i+1)) > 0 )
				return false;
		}
		return true;
	}
	
	
	
	@Test
	public void mergedArrayListsShouldBeSorted() {
		int testIterations = 40;
		int maxLenArr1 = 50;
		int maxLenArr2 = 50;
		for (int it = 0; it < testIterations; it++)
		{
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println("iteration " + it);

			int len1 = ThreadLocalRandom.current().nextInt(2, maxLenArr1);
			int len2 = ThreadLocalRandom.current().nextInt(2, maxLenArr2);
			ArrayList<Integer> arr1 = sortedIntegerArrayList(len1);
			System.out.println("array 1");
			System.out.println(arr1);
			ArrayList<Integer> arr2 = sortedIntegerArrayList(len2);
			System.out.println("array 2");
			System.out.println(arr2);
			assertTrue(isSorted(arr1));
			assertTrue(isSorted(arr2));
			if (arr1.size() > arr2.size()){
				System.out.println("Array 1 is longer");
			} else {
				System.out.println("Array 2 is longer");
			}
			ArrayList<Integer> mergedArray = Arrays.mergeSortedArrayLists(arr1,arr2);
			
			System.out.println("merged array");
			System.out.println(mergedArray);
			boolean result = isSorted(mergedArray);
			assertTrue(mergedArray.size() == arr1.size() + arr2.size());
			
			assertTrue(result);
		}
		
	}

	
}
