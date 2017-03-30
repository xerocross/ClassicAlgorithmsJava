package crossutil;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public abstract class Arrays {
	public static ArrayList<Integer> randomIntegerArrayList(int size, int max)
	{
		ArrayList<Integer> arr = new ArrayList<>(size);
		for (int i = 0; i < size; i++)
		{
			arr.add(ThreadLocalRandom.current().nextInt(0, max));
		}
		return arr;
	}
	
	//merge two sorted arrays into a new sorted array
	public static <E extends Comparable<E>> ArrayList<E> mergeSortedArrayLists(ArrayList<E> arr1, ArrayList<E> arr2)
	{
		int len1 = arr1.size();
		int len2 = arr2.size();
		int newLength = len1 + len2;
		ArrayList<E> newarray = new ArrayList<E>(newLength);
		int index1 = 0;
		int index2 = 0;
		while (index1 < len1 && index2 < len2) {
			if (arr1.get(index1).compareTo(arr2.get(index2)) < 0 ){
				newarray.add(arr1.get(index1));
				index1++;
			} else {
				newarray.add(arr2.get(index2));
				index2++;
			}
		}
		while (index1 < len1)
		{
			newarray.add(arr1.get(index1++));
		}
		while (index2 < len2)
		{
			newarray.add(arr2.get(index2++));
		}
		return newarray;
	}
	
	public static <E extends Comparable<E>> ArrayList<E> mergesort(ArrayList<E> arr) 
	{
		int len = arr.size();
		if (len > 1) 
		{
			int half = len/2;
			ArrayList<E> frontHalf = new ArrayList<E>(arr.subList(0,half));
			ArrayList<E> backHalf = new ArrayList<E>(arr.subList(half,arr.size()));
			frontHalf = mergesort(frontHalf);
			backHalf = mergesort(backHalf);
			arr = mergeSortedArrayLists(frontHalf,backHalf);
		}
		return arr;
	}
    
	private static <E> void arrayListSwap(ArrayList<E> arr, int indexA, int indexB)
	{
		E placeholder = arr.get(indexB);
		arr.set(indexB, arr.get(indexA));
		arr.set(indexA, placeholder);
	}
	
	public static <E extends Comparable<E>> ArrayList<E> bubblesort(ArrayList<E> arr)
	{
		int len = arr.size();
		if (len <= 1)
			return arr;
		int partition = len; 
		//everything to the right of partition is in order
		while (partition > 0)
		{
			int antepartition = partition - 1;
			for (int i = 0; i < antepartition; i++) {
				if (arr.get(i).compareTo(arr.get(i+1)) > 0)
					arrayListSwap(arr, i,i+1);
			}
			partition--;
		}
		return arr;
	}
	
	public static <E extends Comparable<E>> ArrayList<E> insertionsort(ArrayList<E> arr)
	{
		//the array is sorted up through the index before the partition index
		int len = arr.size();
		if (len <= 1)
			return arr;
		for(int partition = 1; partition < len; partition++)
		{
			E itemToInsert = arr.get(partition);
			int itemConsideredIndex = partition - 1;
			while (itemConsideredIndex > -1 && arr.get(itemConsideredIndex).compareTo(itemToInsert) > 0)
			{
				arr.set(itemConsideredIndex + 1, arr.get(itemConsideredIndex));
				//shift item at this index forward by one, overwriting
				itemConsideredIndex--;
			}
			arr.set(itemConsideredIndex + 1, itemToInsert);
		}
		
		return arr;
	}
	
	public static <E extends Comparable<E>> ArrayList<E> heapsortArrayList(ArrayList<E> arr)
	{
		BinaryHeap<E> heap = new BinaryHeap<E>(arr);
		int size = arr.size();
		int partition = size;
		for (; partition > 0; partition--) 
		{
			heap.heapify(0, partition);
			Arrays.arrayListSwap(heap, 0, partition-1);	
		}
		return heap;
	}
	
	public static <E extends Comparable<E>> int partition (ArrayList<E> arr, int lo, int hi)
	{
		E pivot = arr.get(lo);
		int i = lo - 1;
		int j = hi + 1;
		while (true)
		{
			do
			{
				i = i+1;
			} while (arr.get(i).compareTo(pivot) < 0);
			do
			{
				j = j - 1;
			} while (arr.get(j).compareTo(pivot) > 0);
			if (i >= j)
				return j;
			Arrays.arrayListSwap(arr, i, j);
		}
	}
	
	public static <E extends Comparable<E>> ArrayList<E> quicksort(ArrayList<E> arr, int lo, int hi)
	{
		if (lo < hi)
		{
			int p = partition(arr, lo, hi);
			quicksort(arr,lo, p);
			quicksort(arr,p+1,hi);
		}
		
		return arr;
	}
	public static <E extends Comparable<E>> boolean isSorted(List<E> arr)
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
	private static Integer[] shuffle(Integer[] array)
	{
		int len, half, frontLen, backLen;
		len = array.length;
		half = len/2;
		List<Integer> list = java.util.Arrays.asList(array);
		List<Integer> frontHalf = list.subList(0, half);
		frontLen = frontHalf.size();
		List<Integer> backHalf = list.subList(half, len);
		backLen = backHalf.size();
		Integer[] newArray = new Integer[len];
		int index = 0;
		for (int i = 0; i < half; i++)
		{
			
			newArray[index++] = frontHalf.get(i);
			System.out.println(frontHalf.get(i));
			newArray[index++] = backHalf.get(i);
			System.out.println(backHalf.get(i));
		}
		if (len % 2 == 1)
			newArray[index] = backHalf.get(half);
		for (int i = 0; i < len; i++)
			array[i] = newArray[(half + i) % len];
		
		return array;
	}
	
	public static <T> List<T> asList(T[] array)
	{
		int len = array.length;
		List<T> list = new ArrayList<>(len);
		for (int i = 0; i < len; i++)
		{
			list.add(array[i]);
		}
		return list;
	}
	
	public static Integer[] getPermutation(int length)
	{
		Integer permutation[] = new Integer[length];
		for (int i = 0; i < length; i++)
			permutation[i] = i;
		Random rand = new Random();
		int m = length - 1;
		int numShuffle = 7*length;
		for (int i = 0; i < length; i++)
		{
			permutation = shuffle(permutation);

		}
		return permutation;
	}
	
}
