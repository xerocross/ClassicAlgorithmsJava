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
		System.out.println("baseIndex is " + baseIndex);
		if (hasRightChild(baseIndex))
		{
			int rChildIndex = getRightChildIndex(baseIndex);
			if (get(rChildIndex).compareTo(get(baseIndex)) > 0) 
			{
				System.out.println("swap with right child");
				System.out.println("right child index: " + rChildIndex);
				swap(rChildIndex,baseIndex);
				heapify(rChildIndex);
			}
		}
		System.out.println(this);
		if (hasLeftChild(baseIndex))
		{
			int lChildIndex = getLeftChildIndex(baseIndex);
			System.out.println("swap with left child");
			System.out.println("left child index: " + lChildIndex);
			E a = get(lChildIndex);
			System.out.println("a" + a);
			E b = get(baseIndex);
			System.out.println("b" + b);
			if (a.compareTo(b) > 0)
				System.out.println("W");
			if (get(lChildIndex).compareTo(get(baseIndex)) > 0) 
			{
				swap(lChildIndex,baseIndex);
				heapify(lChildIndex);
			}
		}
	}
	
	
	
}
