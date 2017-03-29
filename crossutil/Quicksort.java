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
	
	public  static  synchronized <T extends Comparable<T>> List<T> quicksort(List<T> list)
	{
		QuickSorter<T> sorter = new QuickSorter<>();
		return sorter.sort(list);
	}
}
