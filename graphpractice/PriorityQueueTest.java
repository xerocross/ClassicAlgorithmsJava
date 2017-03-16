package graphpractice;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

public class PriorityQueueTest {

	@Test
	public void addPeekPollTest() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(3);
		pq.add(1);
		pq.add(5);
		pq.add(17);
		pq.add(2);
		assertTrue(pq.size() == 5);
		assertTrue(pq.peek() == 1);
		assertTrue(pq.poll() == 1);
		assertTrue(pq.peek() == 2);
		assertTrue(pq.poll() == 2);
		assertTrue(pq.poll() == 3);
		assertTrue(pq.poll() == 5);
		assertTrue(pq.poll() == 17);
		assertTrue(pq.poll() == null);
	}
	
	boolean isHeapHelper(PriorityQueue<Integer> pq, int index)
	{

		int leftChildIndex = pq.leftChildIndex(index);
		int rightChildIndex = pq.rightChildIndex(index);
		if (pq.size() > leftChildIndex) 
		{
			if (pq.get(leftChildIndex).compareTo(pq.get(index)) < 0)
				return false;
			isHeapHelper(pq,leftChildIndex);
		}
		if (pq.size() > rightChildIndex) 
		{
			if (pq.get(rightChildIndex).compareTo(pq.get(index)) < 0)
				return false;
			isHeapHelper(pq,rightChildIndex);
		}
		return true;
	}
	
	ArrayList<Integer> randomIntegerList(int size)
	{
		ArrayList<Integer> ls = new ArrayList<>();
		for (int i = 0; i < size; i++)
		{
			int max = 10*size;
			int next = ThreadLocalRandom.current().nextInt(0,max);
			ls.add(next);
		}
		return ls;
	}
	
	@Test
	public void isHeapTest()
	{
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int size = 10;
		ArrayList<Integer> randList = randomIntegerList(size);
		for (int i = 0; i < size; i++)
		{
			pq.add(randList.get(i));
		}
		for (int i = 0; i < size; i++)
			System.out.print(pq.get(i) + " ");
		System.out.println("");
		assertTrue(isHeapHelper(pq,0));
	}
	
	@Test
	public void pollTestRandomized()
	{
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int size = 10;
		ArrayList<Integer> randList = randomIntegerList(size);
		for (int i = 0; i < size; i++)
		{
			pq.add(randList.get(i));
		}
		ArrayList<Integer> receivedList = new ArrayList<>();
		for (int i = 0; i < size; i++)
		{
			receivedList.add(pq.poll());
		}
		int beforeLast = size - 1;
		for (int i = 0; i < beforeLast; i++)
		{
			assertTrue(receivedList.get(i) <= receivedList.get(i+1));
		}
	}
	
	@Test
	public void pollTestWorstCase()
	{
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int size = 10;
		for (int i = size; i > 0; i--)
		{
			pq.add(i);
		}
		assertTrue(isHeapHelper(pq,0));
		ArrayList<Integer> receivedList = new ArrayList<>();
		for (int i = 0; i < size; i++)
		{
			receivedList.add(pq.poll());
		}
		assertTrue(pq.poll() == null);
		assertTrue(pq.poll() == null);
		int beforeLast = size - 1;
		for (int i = 0; i < beforeLast; i++)
		{
			assertTrue(receivedList.get(i) <= receivedList.get(i+1));
		}
	}
	
	
}
