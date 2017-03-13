package graphpractice;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

public class DirectedEdgeTest {

	@Test
	public void test() {
		Set<Edge> edges = new HashSet<Edge>();
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		DirectedEdge e1 = new DirectedEdge(v1,v2);
		DirectedEdge e2 = new DirectedEdge(v2,v1);
		System.out.println(e1.equals(e2));
		System.out.println("initial is equal? " + (e1.getInitial() == e2.getInitial()));
		assertTrue(!e1.equals(e2));
		assertTrue(!e2.equals(e1));
		edges.add(e1);
		assertTrue(edges.add(e2));
		
		
	}

}
