package sortpractice;

public class BinaryHeaps {

	public static <E extends Comparable<E>> boolean  isHeap(BinaryHeap<E> heap) 
	{
		int size = heap.size();
		int half = size/2;
		int antehalf = half - 1;
		int i = 0;
		for (; i < antehalf; i++){
			if (heap.get(i).compareTo(heap.getLeftChild(i)) < 0)
				return false;
			if (heap.get(i).compareTo(heap.getRightChild(i)) < 0)
				return false;
		}
		if (heap.get(i).compareTo(heap.getLeftChild(i)) < 0)
			return false;
		if (heap.hasRightChild(i) && heap.get(i).compareTo(heap.getRightChild(i)) < 0)
			return false;
		return true;
	}
	
	
	
	
}
