package graphpractice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class GraphTest {

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
		Graph g = new Graph(vertices);
		g.addEdge(v[0],v[1]);
		g.addEdge(v[0],v[2]);
		g.addEdge(v[0],v[3]);
		Set<Vertex> adj = new HashSet<Vertex>();
		adj.add(v[1]);
		adj.add(v[2]);
		adj.add(v[3]);
		assertTrue(g.getAdjacentVertices(v[0]).equals(adj));
		assertTrue(g.getAdjacentVertices(v[1]).size() == 1);
		assertTrue(g.getAdjacentVertices(v[2]).size() == 1);
		assertTrue(g.getAdjacentVertices(v[3]).size() == 1);
	}

}
