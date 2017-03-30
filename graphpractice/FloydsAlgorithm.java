package graphpractice;

import java.util.Set;

public class FloydsAlgorithm 
{

	//this method is used for minimalPathCost
		private static Double[][] FloydMatrix(int extraNode, Double[][] costMatrix)
		{
			int size = costMatrix.length; //number of vertices
			Double[][] floydMatrix = new Double[size][size];
			
			for (int row = 0; row < size; row++)
			{
				for (int col = 0; col < size; col++)
				{
					Double cost = costMatrix[row][col];
					Double newPathCost = costMatrix[row][extraNode] + costMatrix[extraNode][col];
					floydMatrix[row][col] = Math.min(cost, newPathCost);
				}
			}
			return floydMatrix;
		}
		
		//this method is used for minimalPathCost
		private static Double computeInfinity(DirectedWeightedGraph graph)
		{
			Double inf = 0.;
			Set<Edge> keys = graph.getEdges();
			for (Edge key : keys)
			{
				inf+=graph.getEdgeWeight((DirectedEdge) key);
			}
			return inf;
		}
		/**
		 * This method minimalPathCost returns a matrix of 
		 * the form <code>Double[numNodes][numNodes]</code> 
		 * where the entry <code>Double[i][j]</code> is the cost of the 
		 * path between node <code>i</code> and node <code>j</code> having minimal cost
		 * among all possible paths.
		 * @param graph a directed graph with costs assigned to edges
		 * @return a matrix <code>Double[i][j]</code> that stores the cost 
		 * of the path between node <code>i</code> and node <code>j</code> having minimal cost
		 * among all possible paths.
		 */
		public static Double[][] minimalPathCostFloyd(DirectedWeightedGraph graph)
		{
			int size = graph.size();
			Double[][] costMatrix = new Double[size][size];
			// implement Floyd's algorithm
			Double cost;
			Double inf = computeInfinity(graph);
			for (int row = 0; row < size; row++)
			{
				for (int col = 0; col < size; col++)
				{
					DirectedEdge pEdge = new DirectedEdge(graph.getVertex(row),graph.getVertex(col));
					cost = graph.getEdgeWeight(pEdge);
					if (cost == null)
						costMatrix[row][col] = inf;
					else if (row == col) 
						costMatrix[row][col] = 0.;
					else
						costMatrix[row][col] = cost;
				}
			}
			Double[][] floydMatrix = costMatrix;
			for (int node = 0; node < size; node++) 
			{
				floydMatrix = FloydMatrix(node,floydMatrix);
				for (int k = 0; k < size; k++) 
				{
					if (floydMatrix[k][k] < 0)
						throw new IllegalGraphException("graph contains a negative cycle");
				}
			}
			return floydMatrix;
		}
	
	
}
