package graphpractice;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class DirectedGraphTest {

	@Test
	public void AddAndAccessVerticesTest() {
		DirectedGraph dg = new DirectedGraph();
		Vertex[] v = new Vertex[9];
		for (int i = 0; i < 9; i++)
		{
			v[i] = new Vertex();
			assertTrue(v[i] != null);
			dg.addVertex(v[i]);
		}
		assertTrue(dg.getVertices().size()==9);
	}
	

	@Test
	public void DirectedGraphConstructorWithVerticesTest() 
	{
		
		ArrayList<Vertex> vertices = new ArrayList<>();
		int numVertices = 9;
		Vertex v;
		for (int i = 0; i < numVertices; i++)
		{
			v = new Vertex();
			vertices.add(v);
		}
		DirectedGraph dg = new DirectedGraph(vertices);
		assertTrue(dg.size() == numVertices);
	}
	
	@Test
	public void addEdgeTest()
	{
		ArrayList<Vertex> vertices = new ArrayList<>();
		int numVertices = 2;
		Vertex w;
		Vertex[] v = new Vertex[numVertices];
		for (int i = 0; i < numVertices; i++)
		{
			w = new Vertex(i+"");
			vertices.add(w);
			v[i] = w;
		}
		DirectedGraph dg = new DirectedGraph(vertices);
		dg.addEdge(v[0],v[1]);
		assertTrue(dg.getNumEdges() == 1);
	}
	
	@Test
	public void getAdjacentVerticesTest()
	{
		ArrayList<Vertex> vertices = new ArrayList<>();
		int numVertices = 5;
		Vertex w;
		Vertex[] v = new Vertex[numVertices];
		for (int i = 0; i < numVertices; i++)
		{
			w = new Vertex(i+"");
			vertices.add(w);
			v[i] = w;
		}
		DirectedGraph dg = new DirectedGraph(vertices);
		dg.addEdge(v[0],v[1]);
		dg.addEdge(v[0],v[2]);
		dg.addEdge(v[0],v[3]);
		Set<Vertex> adj = new HashSet<Vertex>();
		adj.add(v[1]);
		adj.add(v[2]);
		adj.add(v[3]);
		assertTrue(dg.getAdjacentVertices(v[0]).equals(adj));
		assertTrue(dg.getAdjacentVertices(v[1]).size() == 0);
		assertTrue(dg.getAdjacentVertices(v[2]).size() == 0);
		assertTrue(dg.getAdjacentVertices(v[3]).size() == 0);
	}
	@Test
	public void reverseDirectedEdgeShouldBeDistinct()
	{
		System.out.println("reverseDirectedEdgeShouldBeDistinct");
		ArrayList<Vertex> vertices = new ArrayList<>();
		int numVertices = 2;
		Vertex w;
		Vertex[] v = new Vertex[numVertices];
		for (int i = 0; i < numVertices; i++)
		{
			w = new Vertex(i+"");
			vertices.add(w);
			v[i] = w;
		}
		DirectedGraph dg = new DirectedGraph(vertices);
		DirectedEdge edge1 = (DirectedEdge) dg.addEdge(v[0],v[1]);
		DirectedEdge edge2 = (DirectedEdge) dg.addEdge(v[1],v[0]);
		System.out.println(edge1.getInitial());
		System.out.println(edge2.getInitial());
		assertTrue(!edge1.equals(edge2));
		System.out.println(dg.getNumEdges());
		assertTrue(dg.getNumEdges() == 2);
	}
}
