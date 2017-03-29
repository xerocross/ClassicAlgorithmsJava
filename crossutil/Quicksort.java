package crossutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class Quicksort
{
	static class QuickSorter<T extends Comparable<T>>
	{
		List<T> list;
		Pair<T> pivot;
		
		public QuickSorter()
		{
		}
		
		public synchronized List<T> sort(List<T> list)
		{
			return null;
		}
		public synchronized List<T> sort(T[] array)
		{
			return null;
		}
		
		Pair<T> getPivot(int beginIndex, int endIndex)
		{
			int length = endIndex - beginIndex;
			if (length <= 2)
				return new Pair<T>(list, beginIndex);
			else
			{
				int halfway = beginIndex + length/2;
				int maxIndex = endIndex - 1;
				Pair<T> first = new Pair<>(list, beginIndex);
				Pair<T> middle = new Pair<>(list, halfway);
				Pair<T> last = new Pair<>(list, maxIndex);
				return getMedianOf(first,middle,last);
			}
		}
		
	}
	
	static class Pair<T extends Comparable<T>> implements Comparable<Pair<T>>
	{
		public T value;
		public int index;
		public Pair(int index, T value)
		{
			this.index = index;
			this.value = value;
		}
		public Pair(List<T> list, int index)
		{
			this.index = index;
			this.value = list.get(index);
		}
		public int compareTo(Pair<T> o)
		{
			return this.value.compareTo(o.value);
		}
	}
	
	public  static  synchronized <T extends Comparable<T>> List<T> sort(List<T> list)
	{
		QuickSorter<T> sorter = new QuickSorter<>();
		return sorter.sort(list);
	}
	private static <T extends Comparable<T>> void insertionSort(List<T> list, int beginIndex, int endIndex)
	{
		if (endIndex - beginIndex <= 1)
			return;
		int maxIndex = endIndex - 1;
		for(int partition = beginIndex; partition < maxIndex; partition++)
		{
			T sortElement = list.get(partition + 1);
			int testIndex = partition;
			while (testIndex >= beginIndex && list.get(testIndex).compareTo(sortElement) > 0)
				list.set(testIndex + 1 , list.get(testIndex--));
			list.set(++testIndex, sortElement);
		}
	}
	
	static <U extends Comparable<U>> U getMedianOf(U a, U b, U c)
	{
		List<U> threeSortables = Arrays.asList(a, b, c);
		insertionSort(threeSortables, 0, 3);
		return threeSortables.get(1);
	}
}
