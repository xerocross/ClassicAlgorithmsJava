package crossutil;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;


public class BinaryHeapTest {
@Test
	public void binaryHeapConstructorShouldWork() {
		ArrayList<Integer> arr = Arrays.randomIntegerArrayList(20, 30);
		
		BinaryHeap<Integer> heap = new BinaryHeap<>(arr);
		assertTrue(!(heap == null));
	}
	@Test
	public void heapifyMethodShouldHeapify()
	{
		System.out.println("Testing heapify");
		
		int iterations = 40;
		for (int itr = 0; itr < iterations; itr ++) 
		{
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			ArrayList<Integer> arr = Arrays.randomIntegerArrayList(7, 30);
			BinaryHeap<Integer> heap = new BinaryHeap<>(arr);
			System.out.println(heap);
			int size = heap.size();
			heap.heapify(0, size);
			assertTrue(BinaryHeaps.isHeap(heap));
			System.out.println(heap);	
		}
	}
}
