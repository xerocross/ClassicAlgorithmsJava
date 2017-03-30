package sortpracticetest;

import static org.junit.Assert.*;


import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;
import sortpractice.Arrays;

import java.util.ArrayList;

public class ArraysTest {
	public final String DIVIDER = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
	
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
		int testIterations = 20;
		int maxLenArr1 = 50;
		int maxLenArr2 = 50;
		for (int it = 0; it < testIterations; it++)
		{
			System.out.println(DIVIDER);
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
	
	@Test
	public void mergesortedArrayShouldBeSorted() 
	{
		int maxSize = 1000;
		int maxInteger = 850;
		int iterations = 20;
		for (int it = 0; it < iterations; it++) {
			int size = ThreadLocalRandom.current().nextInt(2, maxSize);
			System.out.println(DIVIDER);
			System.out.println("Now testing mergesort algorithm");
	
			ArrayList<Integer> arr = Arrays.randomIntegerArrayList(size, maxInteger);
			System.out.println(arr);
			arr = Arrays.mergesort(arr);
			System.out.println(arr);
			boolean isSorted = isSorted(arr);
			assertTrue(isSorted);
		}
	}

	@Test
	public void bubblesortedArrayShouldBeSorted() 
	{
		int maxSize = 1000;
		int maxInteger = 1800;
		int iterations = 20;
		for (int it = 0; it < iterations; it++) {
			int size = ThreadLocalRandom.current().nextInt(2, maxSize);
			System.out.println(DIVIDER);
			System.out.println("Now testing bubblesort algorithm");
			ArrayList<Integer> arr = Arrays.randomIntegerArrayList(size, maxInteger);
			System.out.println(arr);
			arr = Arrays.bubblesort(arr);
			System.out.println(arr);
			boolean isSorted = isSorted(arr);
			assertTrue(arr.size() == size);
			assertTrue(isSorted);
		}	
	}
		
	@Test
	public void insertionsortedArrayListShouldBeSorted() 
	{
		int maxSize = 150;
		int maxInteger = 180;
		int iterations = 20;
		for (int it = 0; it < iterations; it++) {
			int size = ThreadLocalRandom.current().nextInt(2, maxSize);
			System.out.println(DIVIDER);
			System.out.println("Now testing insertionSort algorithm");
			ArrayList<Integer> arr = Arrays.randomIntegerArrayList(size, maxInteger);
			System.out.println(arr);
			arr = Arrays.insertionsort(arr);
			System.out.println(arr);
			boolean isSorted = isSorted(arr);
			assertTrue(arr.size() == size);
			assertTrue(isSorted);
		}
	}
	
	@Test
	public void heapsortedArrayListShouldBeSorted() 
	{
		int maxSize = 150;
		int maxInteger = 1800;
		int iterations = 20;
		for (int it = 0; it < iterations; it++) {
			int size = ThreadLocalRandom.current().nextInt(2, maxSize);
			System.out.println(DIVIDER);
			System.out.println("Now testing heapsort algorithm");
			ArrayList<Integer> arr = Arrays.randomIntegerArrayList(size, maxInteger);
			System.out.println(arr);
			arr = Arrays.heapsortArrayList(arr);
			System.out.println(arr);
			boolean isSorted = isSorted(arr);
			assertTrue(arr.size() == size);
			assertTrue(isSorted);
		}
	}
	@Test
	public void quicksortedArrayListShouldBeSorted() 
	{
		int maxSize = 150;
		int maxInteger = 580;
		int iterations = 20;
		
		
		for (int itr = 0; itr < iterations; itr++) {
			System.out.println(DIVIDER);
			System.out.println("Now testing quicksort algorithm");
			int size = ThreadLocalRandom.current().nextInt(2, maxSize);
			ArrayList<Integer> arr = Arrays.randomIntegerArrayList(size, maxInteger);
			System.out.println(arr);
			arr = Arrays.quicksort(arr, 0, arr.size()-1);
			System.out.println(arr);
			assertTrue(isSorted(arr));
		}
	}
	
	
	
	
	
}