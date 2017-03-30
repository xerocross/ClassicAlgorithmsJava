package graphpractice;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;





public class MinimalCostPathTest {
	ArrayList<Vertex> vertices;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	class Path
	{
		public ArrayList<Vertex> path;
		public double cost;
		
		public Path(ArrayList<Vertex> path, double cost)
		{
			this.path = path;
			this.cost = cost;
		}
	}
	

	
	
	private void addRandomEdges(ArrayList<Vertex> vertexList, DirectedWeightedGraph dwg, Set<Edge> edgesAlreadyCreated, int numRandomEdges, Path optimalPath)
	{
		double pathCost = optimalPath.cost;
		int numEdgesCreated = 0;
		int variability = 20;
		int numVertices = dwg.size();
		int pathLength = optimalPath.path.size();
		Edge testEdge;
		while (numEdgesCreated < numRandomEdges)
		{
			int initial = ThreadLocalRandom.current().nextInt(0, numVertices);
			int terminal = ThreadLocalRandom.current().nextInt(0, numVertices);
			if (initial == terminal)
				continue;
			DWE edge = new DWE(vertexList.get(initial),vertexList.get(terminal));
			if (edgesAlreadyCreated.contains(edge))
				continue;
			double edgeWeight = (double) ThreadLocalRandom.current().nextInt((int) pathCost, (int) (pathCost + variability));
			System.out.print("making random edge " + initial + "->" + terminal + " with weight " + edgeWeight);
			if (initial < pathLength || terminal < pathLength)
				System.out.println("<== connected to path");
			else 
				System.out.println("");
			testEdge = dwg.addEdge(edge.getInitial(),edge.getTerminal(), edgeWeight);
			assertTrue(testEdge instanceof DWE);
			edgesAlreadyCreated.add(edge);
			numEdgesCreated++;
		}
	}
	
	
	
	
	private Path buildOptimalSubpath(ArrayList<Vertex> vertexList, DirectedWeightedGraph dwg, int pathLength, Set<Edge> edges)
	{
		assertTrue(edges.size()==0);
		int maxPathIndex = pathLength - 1;
		ArrayList<Vertex> path = new ArrayList<Vertex>(vertexList.subList(0, pathLength));
		double pathCost = 0.;
		int smallPathMax = 5;
		Edge testEdge;
		for (int i = 0; i < maxPathIndex; i++)
		{
			Double cost = (double) ThreadLocalRandom.current().nextInt(0, smallPathMax);
			cost = 1.;
			DWE edge = new DWE(path.get(i),path.get(i+1));
			System.out.format("set cost from %d to %d as %f %n",i,i+1, cost);
			edges.add(edge);
			testEdge = dwg.addEdge(edge.getInitial(),edge.getTerminal(), cost);
			assertTrue(testEdge instanceof DWE);
			assertTrue(dwg.getEdgeWeight(vertexList.get(i),vertexList.get(i+1)) == 1);
			pathCost += cost;
		}
		Path optimalPath = new Path(path,pathCost);
		System.out.println("cost of created optimal path: " + pathCost);
		return optimalPath;
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
		int maxNumVertices = 60;
		int minNumVertices = 40;
		int minPathLength = 10;
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
		Set<Edge> edges = new HashSet<>(); 
		//to keep track of the edges we add
		
		
		//we will examine a particular path through
		//the graph that has been constructed
		//to be a minimal-cost path between its
		//end points
		int pathLength = ThreadLocalRandom.current().nextInt(minPathLength, maxPathLength+1);
		Path optimalPath = buildOptimalSubpath(vertexList, dwg,pathLength,edges);
		int maxPathIndex = pathLength - 1;
		totalEdges+=pathLength-1;
		
		assertTrue(edges.size() == totalEdges);
		
		
		//now that the path is constructed, we
		// throw in some random edges
		int numRandomEdges = 2*numVertices;
		addRandomEdges(vertexList, dwg, edges, numRandomEdges, optimalPath);
		totalEdges+=numRandomEdges;
		assertTrue(edges.size() == totalEdges);
		
		
		
		//guarantee there are some inferior paths
		//between vertex 0 and vertex maxPathIndex
		{
			System.out.println("building a higher cost path from vertex 0 to vertex " + maxPathIndex);
			ArrayList<Vertex> highCostPath = new ArrayList<>();
			highCostPath.add(vertexList.get(0));
			int length = pathLength;
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
			
			DirectedEdge edge;
			int variability = 20;
			double edgeWeight;

			Edge testEdge;
			for (int i = 0; i < secondToLast; i++)
			{
				edge = new DWE(highCostPath.get(i),highCostPath.get(i+1));
				System.out.print(edge.getInitial() + "->");
				if (!edges.contains(edge)) 
				{
					edgeWeight = (double) ThreadLocalRandom.current().nextInt((int) optimalPath.cost, (int) (optimalPath.cost + variability));
					testEdge = dwg.addEdge(edge.getInitial(), edge.getTerminal(), edgeWeight);
					edges.add(edge);
					assertTrue(testEdge instanceof DWE);
					totalEdges++;
				}
			}
			System.out.println(highCostPath.get(length-1));
		}
		Map<Vertex,Map<Vertex,Double>> minCost = MinimalPathCost.minimalPathCost(dwg);
		System.out.println("the graph has " + dwg.size() + " vertices");
		System.out.println("the path is from vertex 0 to vertex " + maxPathIndex);
		System.out.println("number of edges in graph: " + dwg.getEdges().size());
		System.out.println("number of edges expected: " + totalEdges);
		System.out.println("number of edges in local edges: " + edges.size());
		assertTrue(dwg.getEdges().size() == totalEdges);
		double minCostOptimal = minCost.get(vertexList.get(0)).get(vertexList.get(maxPathIndex));
		System.out.println("computed minCost: " + minCostOptimal);
		System.out.println("recorded cost: " + optimalPath.cost);
		assertTrue(minCostOptimal == optimalPath.cost);
	}
	
	
	@Test
	public void minimalPathCost_NegativeCycle() throws IllegalArgumentException
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
		MinimalPathCost.minimalPathCost(dwg);
	}

}
