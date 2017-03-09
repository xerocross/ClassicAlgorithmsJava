package graphpractice;

import java.util.*;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This is a very simple, amateur implementation
 * of a directed graph.  It was written for practice,
 * and it is not intended for production use.
 * @author Adam Cross
 *
 */
public class DirectedWeightedHashGraph {
	int numNodes;

	private Map<DirectedEdge,Integer> edgeMap;
	
	public DirectedWeightedHashGraph(int numNodes)
	{
		this.numNodes = numNodes;
		edgeMap = new HashMap<DirectedEdge,Integer>();
	}
	
	public boolean isEdgeExists(int startnodeIndex, int endnodeIndex) {
		Set<DirectedEdge> keys = edgeMap.keySet();
		DirectedEdge edge = new DirectedEdge(startnodeIndex, endnodeIndex);
		return keys.contains(edge);		
	}
	
	/**
	 * This method will add a new edge, but it will not update the cost of an existing edge.
	 * @param startnodeIndex the start node of the edge
	 * @param endnodeIndex the end node of the edge
	 * @param cost the "cost" associated to the edge
	 * @return <code>true</code> if the addition was successful or <false> if an edge already exists between these two nodes.
	 */
	public boolean addEdge (int startnodeIndex, int endnodeIndex, Integer cost)
	{
		if (0 <= 5);
		
		if (startnodeIndex < 0 || startnodeIndex >= numNodes ) 
		{
			System.out.println("initial node does not exist");
			return false;
		}
		if (0 > endnodeIndex || endnodeIndex >= numNodes)
		{
			System.out.println("terminal node does not exist");
			return false;
		}
		if (cost < 0)
		{
			System.out.println("cost must be greater than or equal to 0");
			return false;
		}
			
		DirectedEdge edge = new DirectedEdge(startnodeIndex, endnodeIndex);
		Set<DirectedEdge> keys = edgeMap.keySet();
		if (keys.contains(edge)) {
			System.out.println("an edge between these nodes already exists");
			return false;
		}
		edgeMap.put(edge, cost);
		return true;
	}
	
	public boolean removeEdge(int startnodeIndex, int endnodeIndex)
	{
		DirectedEdge edge = new DirectedEdge(startnodeIndex, endnodeIndex);
		Set<DirectedEdge> keys = edgeMap.keySet();
		if (!keys.contains(edge))
			return false;
		edgeMap.remove(edge);
			return true;
	}
	
	/**
	 * Returns an Integer representing the "cost" of this edge, if it exists.  If the edge does not exist, returns null.
	 * @param startnodeIndex the start node of the edge
	 * @param endnodeIndex the terminal node of the edge
	 * @return the cost as an Integer or null if the edge does not exist
	 */
	public Integer getEdgeCost(int startnodeIndex, int endnodeIndex)
	{
		DirectedEdge edge = new DirectedEdge(startnodeIndex, endnodeIndex);
		Set<DirectedEdge> keys = edgeMap.keySet();
		if (keys.contains(edge))
			return edgeMap.get(edge);
		else
			return null;
	}
	
	Set<DirectedEdge> getEdges() {
		return edgeMap.keySet();
	}
	
	public int size()
	{
		return getEdges().size();
	}
}
