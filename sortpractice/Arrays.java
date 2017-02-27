package sortpractice;
import java.util.ArrayList;

public abstract class Arrays {

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
}
