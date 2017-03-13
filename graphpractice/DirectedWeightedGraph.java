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
		super(vertices);
		edgeWeights = new HashMap<>();

	};
	
	public void setEdgeWeight(DirectedEdge edge, Double weight)
	{
		
		if (!super.edges.contains(edge))
			throw new IllegalArgumentException();
		edgeWeights.put(edge,weight);
	}
	public Double getEdgeWeight(DirectedEdge edge)
	{
		return edgeWeights.get(edge);
	}
	public Edge addEdge(Vertex initial, Vertex terminal, double weight)
	{
		Edge edge = super.addEdge(initial, terminal);
		edgeWeights.put((DirectedEdge)edge, weight);
		return edge;
	}
	public Edge addEdge(DirectedEdge edge, double weight)
	{
		super.addEdge(edge.getInitial(), edge.getTerminal());
		edgeWeights.put((DirectedEdge)edge, weight);
		return edge;
	}

}
