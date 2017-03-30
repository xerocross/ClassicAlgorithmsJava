package graphpractice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class FloydsAlgorithmTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
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
		FloydsAlgorithm.minimalPathCostFloyd(dwg);
	}
	
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

		Double[][] sp = FloydsAlgorithm.minimalPathCostFloyd(dwg);
		//these values were computed manually
		assertTrue(sp[0][1] == 1.);
		assertTrue(sp[0][3] == 4.);
		assertTrue(sp[1][4] == 4.);
		assertTrue(sp[2][4] == 5.);
	}

	
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
		Double[][] minCost = FloydsAlgorithm.minimalPathCostFloyd(dwg);
		System.out.println("the graph has " + dwg.size() + " vertices");
		System.out.println("the path is from vertex 0 to vertex " + maxPathIndex);
		System.out.println("number of edges in graph: " + dwg.getEdges().size());
		System.out.println("number of edges expected: " + totalEdges);
		System.out.println("number of edges in local edges: " + edges.size());
		assertTrue(dwg.getEdges().size() == totalEdges);
		assertTrue(minCost[0][maxPathIndex] == optimalPath.cost);
	}
}
