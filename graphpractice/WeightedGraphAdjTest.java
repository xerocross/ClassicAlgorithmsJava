package graphpractice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class WeightedGraphAdjTest {

	
	@Test
	public void addVertexTest()
	{
		Vertex vA = new Vertex("A");
		Vertex vB = new Vertex("B");
		WeightedGraphAdj g = new WeightedGraphAdj();
		g.addVertex(vA);
		assertTrue(g.containsVertex(vA));
		assertTrue(!g.containsVertex(vB));
	}
	
	@Test
	public void containsTest()
	{
		Vertex vA = new Vertex();
		Vertex vB = new Vertex();
		WeightedGraphAdj g = new WeightedGraphAdj();
		g.addVertex(vA);
		assertTrue(g.containsVertex(vA));
		assertTrue(!g.containsVertex(vB));
	}
	
	@Test
	public void addEdgeByVerticesTest()
	{
		Vertex vA = new Vertex("A");
		Vertex vB = new Vertex("B");
		WeightedGraphAdj g = new WeightedGraphAdj();
		g.addVertex(vA);
		g.addVertex(vB);
		g.addEdge(vA,vB,1.);
		assertTrue(g.isEdge(vA, vB));
		assertTrue(g.isEdge(vB, vA));
	}
	
	@Test
	public void addWeightedEdgeObjectTest()
	{
		Vertex vA = new Vertex("A");
		Vertex vB = new Vertex("B");
		WeightedEdge edge = new WeightedEdge(vA,vB,1.);
		WeightedGraphAdj g = new WeightedGraphAdj();
		g.addVertex(vA);
		g.addVertex(vB);
		g.addEdge(edge);
		assertTrue(g.isEdge(vA, vB));
		assertTrue(g.isEdge(vB, vA));
	}
	
	
	private Vertex[] populateGraph(WeightedGraphAdj graph, int numVertices)
	{
		Vertex w;
		Vertex[] v = new Vertex[numVertices];
		for (int i = 0; i < numVertices; i++)
		{
			w = new Vertex(i+"");
			v[i] = w;
			graph.addVertex(v[i]);
		}
		return v;
	}
	
	@Test
	public void getEdgesTest()
	{
		int numVertices = 4;
		WeightedGraphAdj graph = new WeightedGraphAdj();
		Vertex[] v = populateGraph(graph,numVertices);
		Set<WeightedEdge> createdEdges = new HashSet<WeightedEdge>();
		createdEdges.add(graph.addEdge(v[0],v[1],1.));
		createdEdges.add(graph.addEdge(v[0],v[2],2.));
		createdEdges.add(graph.addEdge(v[1],v[2],3.));
		assertTrue(createdEdges.equals(graph.getEdges()));
	}
	
	@Test
	public void getAdjacentVerticesTest()
	{
		int numVertices = 5;
		WeightedGraphAdj g = new WeightedGraphAdj();
		Vertex[] v = populateGraph(g,numVertices);
		g.addEdge(new WeightedEdge(v[0],v[1],1.));
		g.addEdge(new WeightedEdge(v[0],v[2],1.));
		g.addEdge(new WeightedEdge(v[0],v[3],1.));
		Set<Vertex> adj = new HashSet<Vertex>();
		adj.add(v[1]);
		adj.add(v[2]);
		adj.add(v[3]);
		assertTrue(g.getAdjacentVertices(v[0]).equals(adj));
		assertTrue(g.getAdjacentVertices(v[1]).size() == 1);
		assertTrue(g.getAdjacentVertices(v[2]).size() == 1);
		assertTrue(g.getAdjacentVertices(v[3]).size() == 1);
	}
	
	@Test
	public void removeVertexTest()
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
		WeightedGraphAdj g = new WeightedGraphAdj(vertices);
		
		Set<Vertex> adj = new HashSet<Vertex>();
		for (int i = 1; i < numVertices; i++)
		{
			g.addEdge(new WeightedEdge(v[0],v[i],1.));
			adj.add(v[i]);
		}
		Set<Vertex> adjVertices = g.getAdjacentVertices(v[0]);
		assertTrue(adjVertices.size() == numVertices - 1);
		g.removeVertex(v[2]);
		adjVertices = g.getAdjacentVertices(v[0]);
		assertTrue(!adjVertices.contains(v[2]));
		assertTrue(adjVertices.size() == numVertices - 2);
		adj.remove(v[2]);
		assertTrue(g.getAdjacentVertices(v[0]).equals(adj));
	}
	
	@Test
	public void isEdgeTest()
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
		Vertex extra = new Vertex("extra");
		WeightedGraphAdj g = new WeightedGraphAdj(vertices);
		g.addEdge(new WeightedEdge(v[0],v[1],1.));
		assertTrue(g.isEdge(v[0], v[1]));
		assertTrue(g.isEdge(v[1], v[0]));
		assertTrue(!g.isEdge(v[0], v[2]));
		assertTrue(!g.isEdge(v[0], extra));
	}

	@Test
	public void removeEdgeTest()
	{
		Vertex vA = new Vertex();
		Vertex vB = new Vertex();
		WeightedGraphAdj g = new WeightedGraphAdj();
		g.addVertex(vA);
		g.addVertex(vB);
		g.addEdge(new WeightedEdge(vA,vB,.1));
		assertTrue(g.isEdge(vA, vB));
		g.removeEdge(vA, vB);
		assertTrue(!g.isEdge(vA, vB));
		assertTrue(g.size() == 2);
	}

	@Test
	public void getWeightTest()
	{
		Vertex vA = new Vertex();
		Vertex vB = new Vertex();
		WeightedGraphAdj g = new WeightedGraphAdj();
		g.addVertex(vA);
		g.addVertex(vB);
		g.addEdge(new WeightedEdge(vA,vB,.1));
		assertTrue(g.getEdgeWeight(vA, vB) == .1);
		assertTrue(g.getEdgeWeight(vB, vA) == .1);
	}
	

}
