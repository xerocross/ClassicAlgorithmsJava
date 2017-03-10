package graphpractice;

import static org.junit.Assert.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class GraphsTest {

	@Test
	public void minimalPathCostTest() {
		int numVertices = 6;
		ArrayList<Vertex> vertexList = new ArrayList<>();
		Vertex[] vertices = new Vertex[numVertices];
		Vertex placeholderVertex;
		for (int i = 0; i < numVertices; i++) {
			placeholderVertex = new Vertex(Integer.toString(i));
			vertexList.add(placeholderVertex);
			vertices[i] = placeholderVertex;
		}
		DirectedWeightedGraph dwg = new DirectedWeightedGraph(vertexList);
		dwg.addDirectedEdge(new DirectedEdge(vertices[0],vertices[1]), 1.);
		dwg.addDirectedEdge(new DirectedEdge(vertices[0],vertices[2]), 2.);
		dwg.addDirectedEdge(new DirectedEdge(vertices[1],vertices[3]), 3.);
		dwg.addDirectedEdge(new DirectedEdge(vertices[2],vertices[3]), 4.);
		dwg.addDirectedEdge(new DirectedEdge(vertices[1],vertices[4]), 7.);
		dwg.addDirectedEdge(new DirectedEdge(vertices[3],vertices[4]), 1.);
		dwg.addDirectedEdge(new DirectedEdge(vertices[2],vertices[5]), 2.);
		dwg.addDirectedEdge(new DirectedEdge(vertices[2],vertices[1]), 1.);

		Double[][] sp = Graphs.minimalPathCost(dwg);
		//these values were computed manually
		assertTrue(sp[0][1] == 1.);
		assertTrue(sp[0][3] == 4.);
		assertTrue(sp[1][4] == 4.);
		assertTrue(sp[2][4] == 5.);
	}
	
	@Test
	public void minimalPathCostTestRandomized() 
	{
		System.out.println("minimalPathCostTestRandomized");
		//first step is building the vertices
		int maxNumVertices = 40;
		int minNumVertices = 30;
		int minPathLength = 5;
		int maxPathLength = 20;
		int numVertices = ThreadLocalRandom.current().nextInt(minNumVertices, maxNumVertices+1);
		ArrayList<Vertex> vertexList = new ArrayList<>();
		for (int i = 0; i < numVertices; i++)
		{
			vertexList.add(new Vertex(i+""));
		}
		DirectedWeightedGraph dwg = new DirectedWeightedGraph(vertexList);
		//we will examine a particular path through
		//the graph that has been constructed
		//to be a minimal-cost path between its
		//end points
		int pathLength = ThreadLocalRandom.current().nextInt(minPathLength, maxPathLength+1);
		ArrayList<Vertex> path = new ArrayList<Vertex>(vertexList.subList(0, pathLength));
		Set<DirectedEdge> edges = new HashSet<>();
		double pathCost = 0.;
		int smallPathMax = 5;
		int maxPathIndex = pathLength - 1;
		int totalEdges = 0;
		for (int i = 0; i < maxPathIndex; i++)
		{
			Double cost = (double) ThreadLocalRandom.current().nextInt(0, smallPathMax);
			DirectedEdge edge = new DirectedEdge(path.get(i),path.get(i+1));
			edges.add(edge);
			dwg.addDirectedEdge(edge, cost);
			totalEdges++;
			pathCost += cost;
		}
		assertTrue(dwg.size() == numVertices);
		//now that the path is constructed, we
		// throw in some random edges
		int numRandomEdges = 2*numVertices;
		
		int numEdgesCreated = 0;
		while (numEdgesCreated < numRandomEdges)
		{
			int initial = ThreadLocalRandom.current().nextInt(0, numVertices);
			int terminal = ThreadLocalRandom.current().nextInt(0, numVertices);
			if (initial == terminal)
				continue;
			DirectedEdge edge = new DirectedEdge(vertexList.get(initial),vertexList.get(terminal));
			if (edges.contains(edge))
				continue;
			int variability = 20;
			double edgeWeight = (double) ThreadLocalRandom.current().nextInt((int) pathCost, (int) (pathCost + variability));
			System.out.print("making random edge " + initial + "->" + terminal + " with weight " + edgeWeight);
			if (initial < pathLength || terminal < pathLength)
				System.out.println("<== connected to path");
			else 
				System.out.println("");
			dwg.addDirectedEdge(edge, edgeWeight);
			totalEdges++;
			edges.add(edge);
			numEdgesCreated++;
		}
		//guarantee there are some inferior paths
		//between vertex 0 and vertex maxPathIndex
		{
			System.out.println("building a higher cost path from vertex 0 to vertex " + maxPathIndex);
			ArrayList<Vertex> highCostPath = new ArrayList<>();
			highCostPath.add(vertexList.get(0));
			int length = 10;
			int secondToLast = length - 1;
			int vIndex;
			Vertex v;
			for (int i = 1; i < secondToLast; i++) 
			{
				vIndex = ThreadLocalRandom.current().nextInt(pathLength,numVertices);
				v = vertexList.get(vIndex);
				highCostPath.add(v);
			}
			highCostPath.add(vertexList.get(maxPathIndex));
			
			int i = 0;
			DirectedEdge edge = new DirectedEdge(highCostPath.get(i),highCostPath.get(i+1));
			System.out.print(edge.initial + "->");
			int variability = 20;
			double edgeWeight;
			if (!edges.contains(edge))
			{
				edgeWeight = (double) ThreadLocalRandom.current().nextInt((int) pathCost, (int) (pathCost + variability));
				dwg.addDirectedEdge(edge, edgeWeight);
				totalEdges++;
			}
			for (i = 1; i < secondToLast; i++)
			{
				edge = new DirectedEdge(highCostPath.get(i),highCostPath.get(i+1));
				System.out.print(edge.initial + "->");
				if (!edges.contains(edge)) 
				{
					edgeWeight = (double) ThreadLocalRandom.current().nextInt((int) pathCost, (int) (pathCost + variability));
					dwg.addDirectedEdge(edge, edgeWeight);
					totalEdges++;
				}
			}
			System.out.println(highCostPath.get(length-1));
		}
		
		
		
		
		Double[][] minCost = Graphs.minimalPathCost(dwg);
		System.out.println("the graph has " + dwg.size() + " vertices");
		System.out.println("the path is from vertex 0 to vertex " + maxPathIndex);
		System.out.println("number of edges in graph: " + dwg.getEdges().size());
		System.out.println("number of edges expected: " + totalEdges);
		assertTrue(dwg.getEdges().size() == totalEdges);
		
		assertTrue(minCost[0][maxPathIndex] == pathCost);
	}
	
	
	@Test
	public void edgeEqualityTest()
	{
		int numVertices = 2;
		Vertex[] vertices = new Vertex[numVertices];
		for (int i = 0; i < numVertices; i++)
		{
			vertices[i] = new Vertex(Integer.toString(i));
		}
		DirectedEdge edge1 = new DirectedEdge(vertices[0],vertices[1]);
		DirectedEdge edge1copy = new DirectedEdge(vertices[0],vertices[1]);
		assertTrue(edge1.equals(edge1copy));
		Set<DirectedEdge> edges = new HashSet<>();
		edges.add(edge1);
		assertTrue(edges.contains(edge1copy));
	}
	
	@Test
	public void topSortTest() 
	{
		
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
			dg.add(vertices[i]);
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
			vertices[initial].addAdjacent(vertices[terminal]);
			System.out.println("An edge was created: "+ initial + "->" + terminal);
		}
		ArrayList<Vertex> topSortedList = Graphs.topSort(dg);
		int maxIndex = numVertices - 1;
		System.out.println("top-sorted list:");
		for (int i = 0; i < maxIndex; i++)
			System.out.print(topSortedList.get(i) + "->");
		System.out.println(topSortedList.get(maxIndex));
		for (DirectedEdge edge : edges)
		{
			assertTrue(topSortedList.indexOf(edge.initial) < topSortedList.indexOf(edge.terminal));
		}
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void topSortGraphWithCyclesShouldThrowException() throws IllegalArgumentException
	{
		DirectedGraph dg = new DirectedGraph();
		int numVertices = 4;
		Vertex[] vertices = new Vertex[numVertices];
		for (int i = 0; i < numVertices; i++)
		{
			vertices[i] = new Vertex(Integer.toString(i));
			dg.add(vertices[i]);
		}
		vertices[0].addAdjacent(vertices[1]);
		vertices[1].addAdjacent(vertices[2]);
		vertices[2].addAdjacent(vertices[3]);
		vertices[3].addAdjacent(vertices[1]);
		thrown.expect(IllegalGraphException.class);
		Graphs.topSort(dg);
	}
	
}
