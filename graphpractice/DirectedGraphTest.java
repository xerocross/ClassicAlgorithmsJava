package graphpractice;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class DirectedGraphTest {

	@Test
	public void addEdgeTest() 
	{
		DirectedGraph dg = new DirectedGraph(9);
		dg.addEdge(0, 2, 1);
		dg.addEdge(0, 3, 2);
		dg.addEdge(2, 5, 7);
		assertTrue(dg.isEdgeExists(0, 2));
		assertTrue(dg.isEdgeExists(0, 3));
		assertTrue(dg.isEdgeExists(2, 5));
		Set<DirectedEdge> edges = dg.getEdges();
		assertTrue(edges.size() == 3);
	}

	@Test
	public void getCostTest() 
	{
		DirectedGraph dg = new DirectedGraph(9);
		dg.addEdge(0, 2, 1);
		dg.addEdge(0, 3, 2);
		dg.addEdge(2, 5, 7);
		assertTrue(dg.getEdgeCost(0, 2) == 1);
		assertTrue(dg.getEdgeCost(0, 3) == 2);
		assertTrue(dg.getEdgeCost(2, 5) == 7);
	}
	
	@Test
	public void removeEdgeTest() 
	{
		DirectedGraph dg = new DirectedGraph(9);
		dg.addEdge(0, 2, 1);
		dg.addEdge(0, 3, 2);
		dg.addEdge(2, 5, 7);
		dg.removeEdge(0, 2);
		assertTrue(!(dg.isEdgeExists(0, 2)));
		Set<DirectedEdge> edges = dg.getEdges();
		assertTrue(edges.size() == 2);
	}

	@Test
	public void attemptInvalidInput()
	{
		DirectedGraph dg = new DirectedGraph(9);
		assertTrue(!dg.addEdge(0, 12, 15));
		assertTrue(!dg.addEdge(-1, 5, 15));
		assertTrue(!dg.addEdge(0, 2, -1));
		Set<DirectedEdge> edges = dg.getEdges();
		assertTrue(edges.size() == 0);
	}
	
	
}
