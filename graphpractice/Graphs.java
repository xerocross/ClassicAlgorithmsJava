package graphpractice;
import java.util.*;
/**
 * This class is for performing operations on
 * graph objects---notably using Floyd's algorithm
 * for finding the least-cost paths.  This was written
 * by an amateur for practice, and it is not intended
 * for production use.
 * @author Adam Cross
 *
 */
public abstract class Graphs 
{
	private static Integer computeInfinity(DirectedGraph graph)
	{
		Integer inf = 0;
		Set<DirectedEdge> keys = graph.getEdges();
		for (DirectedEdge key : keys)
		{
			inf+=graph.getEdgeCost(key.initial, key.terminal);
		}
		return inf;
	}
	private static Integer[][] FloydMatrix(int extraNode, Integer[][] costMatrix)
	{
		int size = costMatrix.length;
		Integer[][] floydMatrix = new Integer[size][size];
		
		for (int row = 0; row < size; row++)
		{
			for (int col = 0; col < size; col++)
			{
				int cost = costMatrix[row][col];
				int newPathCost = costMatrix[row][extraNode] + costMatrix[extraNode][col];
				floydMatrix[row][col] = Math.min(cost, newPathCost);
			}
		}
		return floydMatrix;
	}
	/**
	 * This method minimalPathCost returns a matrix of 
	 * the form <code>Integer[numNodes][numNodes]</code> 
	 * where the entry Integer[i][j] is the cost of the 
	 * path between node i and node j having minimal cost
	 * among all possible paths.
	 * @param graph a directed graph with costs assigned to edges
	 * @return a matrix Integer[i][j] that stores the cost 
	 * of the path between node i and node j having minimal cost
	 * among all possible paths.
	 */
	public static Integer[][] minimalPathCost(DirectedGraph graph)
	{
		int size = graph.size();
		Integer[][] costMatrix = new Integer[size][size];
		// implement Floyd's algorithm
		Integer cost;
		Integer inf = computeInfinity(graph);
		for (int row = 0; row < size; row++)
		{
			for (int col = 0; col < size; col++)
			{
				cost = graph.getEdgeCost(row, col);
				if (cost == null)
					costMatrix[row][col] = inf;
				else if (row == col) 
					costMatrix[row][col] = 0;
				else
					costMatrix[row][col] = cost;
			}
		}
		Integer[][] floydMatrix = costMatrix;
		for (int node = 0; node < size; node++) 
		{
			floydMatrix = FloydMatrix(node,floydMatrix);
		}
		return floydMatrix;
	}
}
