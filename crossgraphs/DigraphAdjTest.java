package crossgraphs;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DigraphAdjTest {
	DigraphAdj dg;
	

	
	@Test
	public void constructorTest() {
		int size = 10;
		dg = new DigraphAdj(size);
	}
	@Test
	public void addEdgeTest() {
		int size = 10;
		dg = new DigraphAdj(size);
		dg.addEdge(0,5);
		assertTrue(dg.AdjacencyList.get(0).contains(5));
		assertTrue(!dg.AdjacencyList.get(0).contains(4));
	}
	@Test
	public void adjTest()
	{
		int size = 10;
		dg = new DigraphAdj(size);
		dg.addEdge(2,5);
		dg.addEdge(2,6);
		dg.addEdge(2,7);
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(5);
		expected.add(6);
		expected.add(7);
		Iterable<Integer> itr = dg.adj(2);
		for (Integer i : itr)
			assertTrue(expected.contains(i));
	}

}
