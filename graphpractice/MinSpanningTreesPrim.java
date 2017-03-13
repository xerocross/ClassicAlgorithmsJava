package graphpractice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;

public abstract class MinSpanningTreesPrim 
{
	public Graph minimalSpanningTreePrim(UndirectedWeightedGraph graph)
	{
		int size = graph.size();
		if (size == 0)
			return null;
		ArrayList<Vertex> vertices = graph.getVertices();
		Set<Edge> edges = graph.getEdges();
		Vertex initial = vertices.get(0);
		Graph minSpanTree = new UndirectedWeightedGraph();
		minSpanTree.addVertex(initial);
		PriorityQueue<Edge> pq = new PriorityQueue<>(edges.size(),new Comparator<Edge>(){
			   @Override
			   public int compare(final Edge lhs,Edge rhs) {
			     return (int) Math.floor(graph.getEdgeWeight(rhs) - graph.getEdgeWeight(lhs));
			   }
			 });
		
		
		for (int i = 1; i < size; i++)
		{

		}
		return null;
	}
	
	
	

}
