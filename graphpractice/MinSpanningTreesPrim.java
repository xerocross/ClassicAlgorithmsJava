package graphpractice;

import java.util.ArrayList;
import java.util.*;




public abstract class MinSpanningTreesPrim 
{
	
	
	private static boolean isTreeHelper(Graph graph, Set<Vertex> vertices, Vertex v, Vertex previous)
	{
		vertices.add(v);
		//System.out.println("inside isTreeHelper");
		Set<Vertex> adj = graph.getAdjacentVertices(v);
		//System.out.println("adj: " + adj);
		adj.remove(previous);
		for (Vertex w : adj)
		{
			//System.out.println("vertex: " + w);
			//System.out.println("vertices visited: " + vertices);
			if (vertices.contains(w)) {
				//System.out.println("found duplicate path to " + w);
				return false;
			} else {
				vertices.add(w);
				isTreeHelper(graph, vertices, w, v);
			}
		}
		return true;
	}
	public static boolean isTree(Graph graph)
	{
		int size = graph.size();
		if (size == 0)
			return true;
		Set<Vertex> visited = new HashSet<>();
		Vertex v = graph.getVertices().get(0);
		return isTreeHelper(graph, visited, v, null);
	}
	
	private static Set<Edge> getAdjacentEdges(Set<Edge> edges, Vertex vertex)
	{
		Set<Edge> adjEdges = new HashSet<>();
		for (Edge edge : edges)
		{
			if (edge.getLeft() == vertex || edge.getRight() == vertex)
				adjEdges.add(edge);
		}
		return adjEdges;
	}
	
	
	public static UndirectedWeightedGraph minimalSpanningTreePrim(UndirectedWeightedGraph graph)
	{
		int size = graph.size();
		if (size == 0)
			return null;
		ArrayList<Vertex> unvisitedVertices = graph.getVertices();
		Set<Edge> unusededges = graph.getEdges();
		
		
		Vertex current = unvisitedVertices.get(0);
		UndirectedWeightedGraph minSpanTree = new UndirectedWeightedGraph();
		minSpanTree.addVertex(current);
		PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();
		Set<Vertex> visited = new HashSet<Vertex>(Math.min(size/5, 5));
		visited.add(current);
		unvisitedVertices.remove(current);
		Edge previousEdge = null;
		do
		{
			//System.out.println("current: " + current);
			Set<Edge> adjEdges = getAdjacentEdges(unusededges,current);
			adjEdges.remove(previousEdge);
			//System.out.println("adjacent edges: " + adjEdges);
			Set<WeightedEdge> weightedAdjEdges = new TreeSet<>();
			for (Edge edge : adjEdges)
			{
				WeightedEdge we = new WeightedEdge(edge,graph.getEdgeWeight(edge));
				
				weightedAdjEdges.add(we);
				pq.add(we);
			}
			WeightedEdge nextEdge = pq.poll();
			unusededges.remove(nextEdge);
			//System.out.println("newEdge: (" + nextEdge.getLeft() + "," + nextEdge.getRight() + ")");
			if (visited.contains(nextEdge.getLeft()))
			{
				current = nextEdge.getRight();
			} else if (visited.contains(nextEdge.getRight()))
			{
				current = nextEdge.getLeft();
			}
			else
				throw new RuntimeException();
			minSpanTree.addVertex(current);
			Edge newEdge = minSpanTree.addEdge(nextEdge.getLeft(), nextEdge.getRight());
			minSpanTree.setEdgeWeight(newEdge, nextEdge.getWeight());
			visited.add(current);
			unvisitedVertices.remove(current);
			previousEdge = nextEdge;
		} while (unvisitedVertices.size() > 0);
		return minSpanTree;
	}
}
