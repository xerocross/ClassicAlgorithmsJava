package graphpractice;

import static org.junit.Assert.*;

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
	
	

}
