package graphpractice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TopSortTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	@Test
	public void topSortTest() 
	{
		System.out.println("topSortTest");
		DirectedGraph dg = new DirectedGraph();
		int maxNumVertices = 20;
		int minNumVertices = 8;
		int numVertices = ThreadLocalRandom.current().nextInt(minNumVertices,maxNumVertices);
		System.out.println("generating a random DirectedGraph with " + numVertices +" vertices...");
		Vertex[] vertices = new Vertex[numVertices];
		for (int i = 0; i < numVertices; i++)
		{
			vertices[i] = new Vertex(Integer.toString(i));
			assertTrue(vertices[i] != null);
			dg.addVertex(vertices[i]);
		}
		int maxNumEdges = 2*numVertices;
		int minNumEdges = numVertices;
		int numEdges = ThreadLocalRandom.current().nextInt(minNumEdges, maxNumEdges);
		Set<DirectedEdge> edges = new HashSet<DirectedEdge>();
		while (edges.size() < numEdges)
		{
			int initial = ThreadLocalRandom.current().nextInt(0,numVertices-1);
			int terminal = ThreadLocalRandom.current().nextInt(initial+1,numVertices);
			DirectedEdge someEdge = new DirectedEdge(vertices[initial],vertices[terminal]);
			if (edges.contains(someEdge))
				continue;
			edges.add(someEdge);
			dg.addEdge(someEdge);
			System.out.println("An edge was created: "+ initial + "->" + terminal);
		}
		ArrayList<Vertex> topSortedList = TopSort.topSort(dg);
		int maxIndex = numVertices - 1;
		System.out.println("top-sorted list:");
		for (int i = 0; i < maxIndex; i++)
			System.out.print(topSortedList.get(i) + "->");
		System.out.println(topSortedList.get(maxIndex));
		for (DirectedEdge edge : edges)
		{
			assertTrue(topSortedList.indexOf(edge.getInitial()) < topSortedList.indexOf(edge.getTerminal()));
		}
	}

	@Test
	public void topSortGraphWithCyclesShouldThrowException() throws IllegalArgumentException
	{
		System.out.println("topSortGraphWithCyclesShouldThrowException");
		DirectedGraph dg = new DirectedGraph();
		int numVertices = 4;
		Vertex[] vertices = new Vertex[numVertices];
		for (int i = 0; i < numVertices; i++)
		{
			vertices[i] = new Vertex(Integer.toString(i));
			dg.addVertex(vertices[i]);
		}
		dg.addEdge(vertices[0], vertices[1]);
		dg.addEdge(vertices[1], vertices[2]);
		dg.addEdge(vertices[2], vertices[3]);
		dg.addEdge(vertices[3], vertices[1]);
		thrown.expect(IllegalGraphException.class);
		TopSort.topSort(dg);
	}
}
