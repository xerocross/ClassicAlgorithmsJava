package graphpractice;

import crossutil.PriorityQueue;

import java.util.*;

/**
 * This class MinSpanningTreesPrim is an 
 * amateur implementation of Prim's algorithm
 * for finding a minimal spanning tree in an
 * undirected weighted graph.  It was written
 * for practice and it is not intended for
 * production use.
 * @author Adam Cross
 *
 */
public abstract class MinSpanningTrees 
{

	// this method is O(|availableEdges|)
	private static Set<Edge> getAdjacentEdgesFromSet(Set<Edge> availableEdges, Vertex vertex)
	{
		Set<Edge> adjEdges = new HashSet<>();
		for (Edge edge : availableEdges)
		{
			if (edge.contains(vertex))
				adjEdges.add(edge);
		}
		return adjEdges;
	}
	
	private static void addAdjacentEdgesToPriorityQueue(WeightedGraph graph, Set<Edge> availableEdges, Vertex base, PriorityQueue<WeightedEdge> pq)
	{
		Set<Edge> adjEdges = getAdjacentEdgesFromSet(availableEdges,base);
		//above method call is order O(availableEdges);
		
		for (Edge edge : adjEdges)
		{
			pq.add((WeightedEdge)edge);
			// this add operation is order...O(log|pq|)
		}
	}
	/*
	 * this method appears to be O(|visited|)
	 */
	private static Vertex getUnvisitedSide(Set<Vertex> visited, Edge edge)
	{
		if (!visited.contains(edge.getLeft()))
		{
			return edge.getLeft();
		} else if (!visited.contains(edge.getRight()))
		{
			return edge.getRight();
		}
		else {
			return null;
		}
	}
	
	public static WeightedGraph minimalSpanningTree(WeightedGraph graph)
	{
		int size = graph.size();
		if (size == 0)
			return null;
		
		PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();
		//pq holds edges arranged by edge weight
		
		
		Set<Vertex> visited = new HashSet<Vertex>(2*size);
		
		Set<Edge> unusedEdges = graph.getEdges();
		/*
		 * this method has to iterate through every
		 * key-value pair in the adjacency map and
		 * make a copy of each edge.  That appears 
		 * to be O(E) where E is the number of edges.
		 */

		/*
		 * Prims algorithm allows us to start with any
		 * vertex.
		 */
		Vertex current = graph.getAnyVertex();
		WeightedGraph minSpanTree = new WeightedGraph();
		
		minSpanTree.addVertex(current);
		// O(|minSpanTree|)
		
		visited.add(current);
		// O(|minSpanTree|)
		
		addAdjacentEdgesToPriorityQueue(graph, unusedEdges, current, pq);
		
		//following loop will run through all edges
		do
		{
			WeightedEdge nextEdge = pq.poll();
			//^ order O(log(|pq|))
			current = getUnvisitedSide(visited, nextEdge);
			if (current == null)
			{
				System.out.println("found current = null");
				continue;
			}
			minSpanTree.addVertex(current);
			minSpanTree.addEdge(nextEdge);
			unusedEdges.remove(nextEdge);
			visited.add(current);
			addAdjacentEdgesToPriorityQueue(graph, unusedEdges, current, pq);
		} while (pq.size() > 0);
		
		
		if (minSpanTree.size() < size)
			throw new IllegalGraphException("graph is not connected");
		
		return minSpanTree;
	}
}
