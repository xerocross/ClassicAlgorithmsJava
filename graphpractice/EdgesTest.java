package graphpractice;

import static org.junit.Assert.*;

import org.junit.Test;

public class EdgesTest {

	@Test
	public void edgeTest() 
	{
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		Edge e1 = new Edge(v1, v2);
		Edge e2 = new Edge(v2, v1);
		Edge e3 = new Edge(v1, v2);
		assertTrue(e1.equals(e2));
		assertTrue(e1.equals(e3));
		assertTrue(e2.equals(e3));
		assertTrue(e1.hashCode() == e2.hashCode());
	}
	
	@Test
	public void directedEdgeTest() 
	{
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		Edge e1 = new DirectedEdge(v1, v2);
		Edge e2 = new DirectedEdge(v2, v1);
		Edge e3 = new DirectedEdge(v1, v2);
		assertTrue(e1.equals(e3));
		assertTrue(e1.hashCode() == e3.hashCode());
		assertFalse(e1.equals(e2));
	}

}
