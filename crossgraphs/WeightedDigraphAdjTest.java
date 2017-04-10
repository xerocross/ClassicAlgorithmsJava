package crossgraphs;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeightedDigraphAdjTest {
	WeightedDigraphAdj graph;
	
	@Test
	public void constructorTest() {
		graph = new WeightedDigraphAdj(10);
	}
	
	@Test
	public void addEdgeTest() {
		graph = new WeightedDigraphAdj(10);
		graph.addEdge(0, 5, 4.0);
		assertTrue(graph.adjacencyList.get(0).size() == 1);
	}

}
