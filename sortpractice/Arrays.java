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
				newarray.add(arr2.get(index2++));
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
}
