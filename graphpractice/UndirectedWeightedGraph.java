package graphpractice;

import java.util.*;

public class UndirectedWeightedGraph extends Graph
{
	private Map<Edge,Double> edgeWeights;
	
	public UndirectedWeightedGraph()
	{
		super();
		edgeWeights = new HashMap<Edge,Double>();
	}
	
	public void setEdgeWeight(Edge edge, double weight)
	{
		if (super.edges.contains(edge))
			edgeWeights.put(edge, weight);
	}
	public Double getEdgeWeight(Edge edge)
	{
		if (edgeWeights.keySet().contains(edge))
			return edgeWeights.get(edge);
		else
			return null;
	}
	public Double getSumEdgeWeights()
	{
		Double sum = 0.;
		Set<Edge> keys = edgeWeights.keySet();
		for (Edge edge : keys)
		{
			sum+=edgeWeights.get(edge);
		}
		return sum;
	}
}
