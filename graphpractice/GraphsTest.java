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
		dwg.addEdge(new DirectedEdge(vertices[0],vertices[1]), 1.);
		dwg.addEdge(new DirectedEdge(vertices[0],vertices[2]), 2.);
		dwg.addEdge(new DirectedEdge(vertices[1],vertices[3]), 3.);
		dwg.addEdge(new DirectedEdge(vertices[2],vertices[3]), 4.);
		dwg.addEdge(new DirectedEdge(vertices[1],vertices[4]), 7.);
		dwg.addEdge(new DirectedEdge(vertices[3],vertices[4]), 1.);
		dwg.addEdge(new DirectedEdge(vertices[2],vertices[5]), 2.);
		dwg.addEdge(new DirectedEdge(vertices[2],vertices[1]), 1.);

		Double[][] sp = Graphs.minimalPathCostFloyd(dwg);
		//these values were computed manually
		assertTrue(sp[0][1] == 1.);
		assertTrue(sp[0][3] == 4.);
		assertTrue(sp[1][4] == 4.);
		assertTrue(sp[2][4] == 5.);
	}
	
	private class Path
	{
		public ArrayList<Vertex> path;
		public double cost;
		
		public Path(ArrayList<Vertex> path, double cost)
		{
			this.path = path;
			this.cost = cost;
		}
		
	}
	
	private Path buildOptimalSubpath(DirectedWeightedGraph dwg, int pathLength, Set<DirectedEdge> edges)
	{
		assertTrue(edges.size()==0);
		int maxPathIndex = pathLength - 1;
		ArrayList<Vertex> path = new ArrayList<Vertex>(dwg.vertices.subList(0, pathLength));
		double pathCost = 0.;
		int smallPathMax = 5;
		for (int i = 0; i < maxPathIndex; i++)
		{
			Double cost = (double) ThreadLocalRandom.current().nextInt(0, smallPathMax);
			DirectedEdge edge = new DirectedEdge(path.get(i),path.get(i+1));
			edges.add(edge);
			dwg.addEdge(edge.getInitial(),edge.getTerminal(), cost);
			pathCost += cost;
			
		}
		Path optimalPath = new Path(path,pathCost);
		return optimalPath;
	}
	
	private void addRandomEdges(DirectedWeightedGraph dwg, Set<DirectedEdge> edgesAlreadyCreated, int numRandomEdges, Path optimalPath)
	{
		double pathCost = optimalPath.cost;
		int numEdgesCreated = 0;
		int numVertices = dwg.size();
		int pathLength = optimalPath.path.size();
		while (numEdgesCreated < numRandomEdges)
		{
			int initial = ThreadLocalRandom.current().nextInt(0, numVertices);
			int terminal = ThreadLocalRandom.current().nextInt(0, numVertices);
			if (initial == terminal)
				continue;
			DirectedEdge edge = new DirectedEdge(dwg.vertices.get(initial),dwg.vertices.get(terminal));
			if (edgesAlreadyCreated.contains(edge))
				continue;
			int variability = 20;
			double edgeWeight = (double) ThreadLocalRandom.current().nextInt((int) pathCost, (int) (pathCost + variability));
			System.out.print("making random edge " + initial + "->" + terminal + " with weight " + edgeWeight);
			if (initial < pathLength || terminal < pathLength)
				System.out.println("<== connected to path");
			else 
				System.out.println("");
			dwg.addEdge(edge.getInitial(),edge.getTerminal(), edgeWeight);
			edgesAlreadyCreated.add(edge);
			numEdgesCreated++;
		}
	}
	
	/**
	 * This method generates a randomized graph as
	 * follows: it builds a variable number of vertices.
	 * Then it chooses a small initial sublist to
	 * be an optimal path.  These are the vertices
	 * indexed <code>0</code> through <code>maxPathIndex</code>.
	 * We build edges between them in order with 
	 * small weights.  We sum these weights to get a
	 * <code>pathCost</code>.  After this, the method
	 * randomly generates many more edges with each
	 * edge having a weight at least as great as 
	 * <code>pathCost</code>.  The method also 
	 * guarantees the existence of at least one
	 * other path from <code>0</code> to <code>maxPathIndex</code>
	 * having a greater cost.
	 * The method then plugs this graph into 
	 * <code>Graphs.minimalPathCost</code> and checks
	 * that the minimum cost of a path from <code>0</code> to 
	 * <code>maxPathIndex</code> is <code>pathCost</code>.
	 */
	@Test
	public void minimalPathCostFloydTestRandomized() 
	{
		System.out.println("minimalPathCostTestRandomized");
		//first step is building the vertices
		int maxNumVertices = 40;
		int minNumVertices = 30;
		int minPathLength = 5;
		int maxPathLength = 20;
		int totalEdges = 0;
		int numVertices = ThreadLocalRandom.current().nextInt(minNumVertices, maxNumVertices+1);
		ArrayList<Vertex> vertexList = new ArrayList<>();
		for (int i = 0; i < numVertices; i++)
		{
			vertexList.add(new Vertex(i+""));
		}
		DirectedWeightedGraph dwg = new DirectedWeightedGraph(vertexList);
		assertTrue(dwg.size() == numVertices);
		Set<DirectedEdge> edges = new HashSet<>(); 
		//to keep track of the edges we add
		
		
		//we will examine a particular path through
		//the graph that has been constructed
		//to be a minimal-cost path between its
		//end points
		int pathLength = ThreadLocalRandom.current().nextInt(minPathLength, maxPathLength+1);
		Path optimalPath = buildOptimalSubpath(dwg,pathLength,edges);
		int maxPathIndex = pathLength - 1;
		totalEdges+=pathLength-1;
		assertTrue(edges.size() == totalEdges);
		
		
		//now that the path is constructed, we
		// throw in some random edges
		int numRandomEdges = 2*numVertices;
		addRandomEdges(dwg, edges, numRandomEdges, optimalPath);
		totalEdges+=numRandomEdges;
		assertTrue(edges.size() == totalEdges);
		
		
		
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
			
			//int i = 0;
			DirectedEdge edge;// = new DirectedEdge(highCostPath.get(i),highCostPath.get(i+1));
			//System.out.print(edge.getInitial() + "->");
			int variability = 20;
			double edgeWeight;
			/*
			if (!edges.contains(edge))
			{
				edgeWeight = (double) ThreadLocalRandom.current().nextInt((int) optimalPath.cost, (int) (optimalPath.cost + variability));
				dwg.addEdge(edge.getInitial(), edge.getTerminal(), edgeWeight);
				totalEdges++;
			}
			*/
			for (int i = 0; i < secondToLast; i++)
			{
				edge = new DirectedEdge(highCostPath.get(i),highCostPath.get(i+1));
				System.out.print(edge.getInitial() + "->");
				if (!edges.contains(edge)) 
				{
					edgeWeight = (double) ThreadLocalRandom.current().nextInt((int) optimalPath.cost, (int) (optimalPath.cost + variability));
					dwg.addEdge(edge.getInitial(), edge.getTerminal(), edgeWeight);
					totalEdges++;
				}
			}
			System.out.println(highCostPath.get(length-1));
		}
		Double[][] minCost = Graphs.minimalPathCostFloyd(dwg);
		System.out.println("the graph has " + dwg.size() + " vertices");
		System.out.println("the path is from vertex 0 to vertex " + maxPathIndex);
		System.out.println("number of edges in graph: " + dwg.getEdges().size());
		System.out.println("number of edges expected: " + totalEdges);
		System.out.println("number of edges in local edges: " + edges.size());
		assertTrue(dwg.getEdges().size() == totalEdges);
		assertTrue(minCost[0][maxPathIndex] == optimalPath.cost);
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
		ArrayList<Vertex> topSortedList = Graphs.topSort(dg);
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
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
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
		Graphs.topSort(dg);
	}
	
	
	@Test
	public void minimalPathCostFloydOnGraphWithNegativeCycle() throws IllegalArgumentException
	{
		DirectedWeightedGraph dwg = new DirectedWeightedGraph();
		int numVertices = 4;
		Vertex[] vertices = new Vertex[numVertices];
		for (int i = 0; i < numVertices; i++)
		{
			vertices[i] = new Vertex(Integer.toString(i));
			dwg.addVertex(vertices[i]);
		}
		dwg.addEdge(vertices[0],vertices[1],3.);
		dwg.addEdge(vertices[1],vertices[2],1.);
		dwg.addEdge(vertices[2],vertices[3],1.);
		dwg.addEdge(vertices[3],vertices[1],-3.);
		thrown.expect(IllegalGraphException.class);
		thrown.expectMessage("graph contains a negative cycle");
		Graphs.minimalPathCostFloyd(dwg);
	}
	
}
