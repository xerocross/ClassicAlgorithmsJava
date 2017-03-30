package crossutil;

import java.util.*;

import crossutil.Arrays;

class SortTest 
{
	public static void main(String[] args)
	{
		List<Integer> list = Arrays.randomIntegerArrayList(15,100);
		System.out.println(list);
		
	}
	
	
	public static <U extends Comparable<U>> boolean testSort(List<U> list, Sorter sorter)
	{
		List<U> sortResult = sorter.sort(list);
		boolean isEqual = isEqual(list, sortResult);
		System.out.println(sortResult);
		return isSorted(sortResult) && isEqual;
	}
	
	public static <U extends Comparable<U>> boolean isEqual(List<U> listA, List<U> listB)
	{
		int size = listA.size();
		if (size != listB.size())
			return false;
		Map<U,Integer> mapA = new TreeMap<>();
		Map<U,Integer> mapB = new TreeMap<>();
		for (int i = 0; i < size; i++)
		{
			U key = listA.get(i);
			if (mapA.containsKey(key))
				mapA.put(key,mapA.get(key) + 1);
			else mapA.put(key,1);
			
			key = listB.get(i);
			if (mapB.containsKey(key))
				mapB.put(key,mapB.get(key) + 1);
			else mapB.put(key,1);
		}
		return mapA.equals(mapB);
	}
	
	public static <U extends Comparable<U>> boolean isSorted(List<U> list)
	{
		int size = list.size();
		int largestIndex = size - 1;
		for (int i = 0; i < largestIndex; i++)
			if (list.get(i).compareTo(list.get(i+1)) > 0)
				return false;
		return true;
	}
}
