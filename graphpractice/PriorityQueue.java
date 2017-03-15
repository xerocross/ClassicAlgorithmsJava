package graphpractice;

import java.util.*;



class ObjectIndexPair<E extends Comparable<E>> implements Comparable<ObjectIndexPair<E>>
{
    public E object;
    public int index;
    
    public ObjectIndexPair(E object, int index)
    {
        this.object = object;
        this.index = index;
    }
    
    public int compareTo(ObjectIndexPair<E> o)
    {
    		return this.object.compareTo(o.object);
    }
}


/**
* This is an amateur class implemented
* for practice.  It is not intended for
* production use.
* @author Adam Cross
*/
public class PriorityQueue<E extends Comparable<E>>
{
    private ArrayList<E> heap;
    
    public PriorityQueue()
    {
        heap = new ArrayList<>();
    }
    
    public int size()
    {
    		return heap.size();
    }
    public void add(E newElt)
    {
        heap.add(newElt);
        int index = heap.size() - 1;
        bubbleUp(index);
    }
    
    public E peek()
    {
        return heap.get(0);
    }
    public E poll()
    {
    		if (heap.size() == 0)
    			return null;
        E min = heap.get(0);
        swapHeapElementsByIndex(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        if (heap.size() > 1)
        		heapify();
        return min;
    }
    private void swapHeapElementsByIndex(int indexA, int indexB)
    {
        E placeholder = heap.get(indexA);
        heap.set(indexA,heap.get(indexB));
        heap.set(indexB,placeholder);
    }
    private <U> void swap(U[] arr, int indexA, int indexB)
    {
        U placeholder = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = placeholder;
    }
    
    private int getMinOfThree(int indexA, int indexB, int indexC)
    {
    		Integer[] indices = {indexA, indexB, indexC};

        if (heap.get(indices[0]).compareTo(heap.get(indices[1])) > 0) {
            swap(indices, 0, 1);
        }
        if (heap.get(indices[1]).compareTo(heap.get(indices[2])) > 0) {
            swap(indices, 1, 2);
        }
        if (heap.get(indices[0]).compareTo(heap.get(indices[1])) > 0) {
            swap(indices, 0, 1);
        }
        return indices[0];
        
    }
    private Integer getLargestParentIndex()
    {
    		int maxElt = heap.size() - 1;
        return getParentIndex(maxElt);
    }
    private int leftChildIndex(int heapIndex) {return 2*heapIndex+1;}
    private int rightChildIndex(int heapIndex) {return 2*heapIndex+2;}
    private void reHeap(int heapIndex)
    {
        int leftChildIndex = leftChildIndex(heapIndex);
        int rightChildIndex = rightChildIndex(heapIndex);
        if (leftChildIndex >= heap.size())
            return;
        if (rightChildIndex >= heap.size())
        {
        		if (heap.get(leftChildIndex).compareTo(heap.get(heapIndex)) < 0)
        			swapHeapElementsByIndex(heapIndex, leftChildIndex);
        		return;
        }
        
        int indexOfMin = getMinOfThree(heapIndex, leftChildIndex, rightChildIndex);
        
        if (indexOfMin != heapIndex)
        {
            swapHeapElementsByIndex(heapIndex, indexOfMin);
            reHeap(indexOfMin);
        }
    }
    
    private void heapify()
    {
        Integer largestParentIndex = getLargestParentIndex();
        if (largestParentIndex != null)
        {
	        for (int i = largestParentIndex; i > -1; i--)
	            reHeap(i);
        }
    }
    
    private Integer getParentIndex(int index)
    {
    		int pIndex = (index + 1)/2 - 1;
    		if (pIndex >= 0)
    			return pIndex;
    		else
    			return null;
    }
    
    private void bubbleUp(int eltIndex)
    {
        if (eltIndex <= 0)
            return;
        int parentIndex = getParentIndex(eltIndex);
        E base = heap.get(eltIndex);
        E parent = heap.get(parentIndex);
        if (base.compareTo(parent) < 0) 
        {
            swapHeapElementsByIndex(eltIndex,parentIndex);
            bubbleUp(parentIndex);
        }
    }
    
}