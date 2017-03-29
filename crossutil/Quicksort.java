package crossutil;

import java.util.Arrays;
import java.util.List;


public abstract class Quicksort
{
	static class QuickSorter<T extends Comparable<T>>
	{
		List<T> list;
		
		public QuickSorter()
		{
		}
		public synchronized List<T> sort(List<T> list)
		{
			this.list = list;
			partitionRecursive(0, list.size());
			return list;
		}
		public synchronized List<T> sort(T[] array)
		{
			return null;
		}
		
		synchronized Pair<T> partition(int beginIndex, int endIndex)
		{
			Pair<T> pivot = getPivot(beginIndex, endIndex);
			IndexPair invPair = getNextInversionPair(null, beginIndex, endIndex, pivot);
			while (invPair.left < invPair.right)
			{
				swap(invPair);
				invPair = getNextInversionPair(invPair, beginIndex, endIndex, pivot );
			}
			int pivotLowerBound = invPair.right;
			int pivotUpperBound = invPair.left;
			if (pivotLowerBound >= pivotUpperBound)
				throw new RuntimeException();
			pivot = pivotToFinalPosition(pivot, pivotLowerBound, pivotUpperBound);
			return pivot;
		}
		
		void partitionRecursive(int beginIndex, int endIndex)
		{
			int length = endIndex - beginIndex;
			if (length <= 10)
			{
				insertionSort(list, beginIndex, endIndex);
				return;
			}
			Pair<T> pivot = partition(beginIndex, endIndex);
			
			int partitionIndex = pivot.index;
			if (partitionIndex > beginIndex)
				partitionRecursive(beginIndex, partitionIndex);
			if (endIndex > partitionIndex )
				partitionRecursive(partitionIndex + 1, endIndex);
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
		
		IndexPair getNextInversionPair(IndexPair inversionPair, int startIndex, int endIndex, Pair<T> pivot)
		{
			int leftIndex, rightIndex;
			if (inversionPair == null)
			{
				leftIndex = startIndex;
				rightIndex = endIndex - 1;
			} else {
				leftIndex = inversionPair.left;
				rightIndex = inversionPair.right - 1;
			}
			int lowerBound = startIndex - 1;
			int upperBound = endIndex;
			
			while (leftIndex < upperBound && list.get(leftIndex).compareTo(pivot.value) <= 0)
				leftIndex++;
			while (rightIndex > lowerBound && list.get(rightIndex).compareTo(pivot.value) >= 0)
				rightIndex--;
			IndexPair nextInversionPair = new IndexPair(leftIndex, rightIndex);
			return nextInversionPair;
		}
		void swap(IndexPair p)
		{
			swap(p.left,p.right);
		}
		void swap(int i, int j)
		{
			T placeholder = list.get(i);
			list.set(i, list.get(j));
			list.set(j, placeholder);
		}
		Pair<T> pivotToFinalPosition(Pair<T> pivot,  int leftBound, int rightBound)
		{
			boolean pivotIsTooFarLeft = (pivot.index < leftBound);
			boolean pivotIsTooFarRight = (pivot.index > rightBound);
			if (pivotIsTooFarLeft) {
				swap(pivot.index,leftBound);
				return new Pair<T>(leftBound, pivot.value);
			} else if (pivotIsTooFarRight)
			{
				swap(pivot.index,rightBound);
				return new Pair<T>(rightBound, pivot.value);
			} else {
				return pivot;
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

	static <T extends Comparable<T>> void insertionSort(List<T> list, int beginIndex, int endIndex)
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
	
	static class IndexPair
	{
		int left;
		int right;
		public IndexPair(int left, int right)
		{
			this.left = left;
			this.right = right;
		}
		public boolean equals(Object o)
		{
			if (o == null)
				return false;
			else if (o instanceof Quicksort.IndexPair)
			{
				Quicksort.IndexPair that = (Quicksort.IndexPair) o;
				return (left == that.left && right == that.right);
				
			} else
				return false;
		}
	}
}
