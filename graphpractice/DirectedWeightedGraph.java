package graphpractice;

import java.util.*;

public class DirectedWeightedGraph extends DirectedGraph
{
	private Map<DirectedEdge,Double> edgeWeights;
	
	public DirectedWeightedGraph()
	{
		super();
		edgeWeights = new HashMap<>();
	}
	
	public DirectedWeightedGraph(ArrayList<Vertex> vertices){
		super();
		edgeWeights = new HashMap<>();
		for (Vertex v : vertices)
			this.add(v);
	};
	
	public Set<DirectedEdge> getEdges()
	{
		return edgeWeights.keySet();
	}
	
	/**
	 * Adds a new vertex to the graph.  Any existing
	 * adjacency information contained in the vertex
	 * will be cleared.  Adjacency information should
	 * be added using the <code>addDirectedEdge</code>
	 * method.
	 * @param newVertex the new vertex to be added
	 */
	public void add(Vertex newVertex) 
	{
		super.add(newVertex);
		Set<Vertex> adjacentVertices = newVertex.getAdjacent();
		for (Vertex v : adjacentVertices)
			newVertex.removeAdjacent(v);
	}
	
	public void addDirectedEdge(DirectedEdge edge, Double weight)
	{
		if (!this.contains(edge.initial))
			throw new IllegalArgumentException();
		if (!this.contains(edge.terminal))
			throw new IllegalArgumentException();
		edgeWeights.put(edge,weight);
		edge.initial.addAdjacent(edge.terminal);
	}
	public Double getEdgeWeight(DirectedEdge edge)
	{
		return edgeWeights.get(edge);
	}

}
