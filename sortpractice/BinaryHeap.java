package sortpractice;

import java.util.ArrayList;
import java.util.Iterator;

public class BinaryHeap<E extends Comparable<E>> extends ArrayList<E> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4111664651883160100L;

	public BinaryHeap()
	{
		super();
	}
	
	
	public BinaryHeap (ArrayList<E> arr)
	{
		Iterator<E> arrIterator = arr.iterator();
		while (arrIterator.hasNext())
			this.add(arrIterator.next());
	}
	
	
	private int translateIndex(int heapIndex)
	{
		return heapIndex - 1;
	}
	
	
	public boolean hasLeftChild(int index)
	{
		return (2*index + 1 < this.size());
	}
	
	public int getLeftChildIndex(int index) 
	{
		if (hasLeftChild(index))
			return 2*index + 1;
		else	
			return -1;
	}
	public boolean hasRightChild(int index)
	{
		return (2*index + 2 < this.size());
	}
	public int getRightChildIndex(int index) 
	{
		if (hasRightChild(index))
			return 2*index + 2;
		else	
			return -1;
	}
	

	public E getLeftChild(int index)
	{
		if (hasLeftChild(index))
			return get(getLeftChildIndex(index));
		else
			return null;
	}
	
	public E getRightChild(int index)
	{
		if (hasRightChild(index))
			return get(getRightChildIndex(index));
		else
			return null;
	}
	
	
	public void swap (int indexA, int indexB)
	{
		int size = this.size();
		if (indexA < size && indexB < size) 
		{
			E placeholder = get(indexA);
			set(indexA,get(indexB));
			set(indexB,placeholder);
		}
	}
	

	public void heapify(int baseIndex)
	{
		if (hasRightChild(baseIndex))
		{
			int rChildIndex = getRightChildIndex(baseIndex);
			if (get(rChildIndex).compareTo(get(baseIndex)) > 0) 
			{
				swap(rChildIndex,baseIndex);
				heapify(rChildIndex);
			}
		}
		if (hasLeftChild(baseIndex))
		{
			int lChildIndex = getLeftChildIndex(baseIndex);
			if (get(lChildIndex).compareTo(get(baseIndex)) > 0) 
			{
				swap(lChildIndex,baseIndex);
				heapify(lChildIndex);
			}
		}
	}
	
	
	
}
