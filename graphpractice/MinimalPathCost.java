package graphpractice;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MinimalPathCost {
	
	/**
	 * @param a DirectedWeightedGraph with no negative cycles
	 * @return a map <code>Map<Vertex,Map<Vertex,Double>></code> that stores 
	 * for each pair of vertices (a, b) the minimal cost of a path from a
	 * to b.
	 */
	public static Map<Vertex,Map<Vertex,Double>> minimalPathCost(DirectedWeightedGraph graph)
	{
		int size = graph.size();
		ArrayList<Vertex> vertices = graph.getVertexList();
		Double[][] pathCost = buildCostMatrix(vertices,  graph);
		// pathCost contains the costs of going
		// directly from any vertex to any other
		// vertex, or else a large number "infinity"
		// representing impossibility
		
		
		// Update the pathCost matrix by allowing for
		// paths that travel through intermediate vertices.
		// Allowed intermediate vertices are added one at
		// a time.
		int newIntermediateVertex = 0;
		for (;newIntermediateVertex < size; newIntermediateVertex++) 
		{
			pathCost = updatePathCost(newIntermediateVertex,pathCost);
			negativeCycleTest(pathCost); //may throws IllegalArgumentException
		}
		return matrixToMap(vertices,pathCost);
	}
	
	/*
	 * This final conversion is necessary because the 
	 * DirectedWeightedGraph object does not index its
	 * vertices.  The indices used in this class are
	 * meaningless outside this class.
	 */
	private static Map<Vertex,Map<Vertex,Double>> matrixToMap(ArrayList<Vertex> vertexList, Double[][] costMatrix)
	{
		Map<Vertex,Map<Vertex,Double>> map = new HashMap<>();
		int numRows = costMatrix.length;
		for (int i = 0; i < numRows; i++)
		{
			Map<Vertex,Double> innerMap = new HashMap<Vertex,Double>();
			int numCols = costMatrix[i].length;
			map.put(vertexList.get(i), innerMap);
			for (int j = 0; j < numCols; j++)
				innerMap.put(vertexList.get(j), costMatrix[i][j]);
		}
		return map;
	}
	
	private static Double computeInfinity(DirectedWeightedGraph graph)
	{
		Double inf = 0.;
		Set<Edge> keys = graph.getEdges();
		for (Edge key : keys)
		{
			if (!(key instanceof DWE))
				System.out.println("edge " + key + " isn't DWE");
			inf+=graph.getEdgeWeight((DWE) key);
		}
		return inf;
	}
	
	
	private static Double[][] updatePathCost (int newVertexIndex, Double[][] costMatrix)
	{
		int size = costMatrix.length; //number of vertices
		Double[][] newPathCost = new Double[size][size];
		Double cost, comparisonCost;
		for (int row = 0; row < size; row++)
			for (int col = 0; col < size; col++)
			{
				cost = costMatrix[row][col];
				comparisonCost = costMatrix[row][newVertexIndex] + costMatrix[newVertexIndex][col];
				newPathCost[row][col] = comparisonCost < cost ? comparisonCost : cost;
			}
		return newPathCost;
	}
	private static Double[][] buildCostMatrix(ArrayList<Vertex> vertices, DirectedWeightedGraph graph)
	{
		int size = vertices.size();
		Double[][] costMatrix = new Double[size][size];
		Double weight;
		Double inf = computeInfinity(graph);
		for (int row = 0; row < size; row++)
			for (int col = 0; col < size; col++)
			{
				weight = graph.getEdgeWeight(vertices.get(row),vertices.get(col));
				costMatrix[row][col] = weight == null ? inf : weight;
			}
		for (int i = 0; i < size; i++)
			costMatrix[i][i] = 0.;
		return costMatrix;
	}
	
	private static void negativeCycleTest(Double[][] floydMatrix)
	{
		int size = floydMatrix.length;
		for (int k = 0; k < size; k++) 
			if (floydMatrix[k][k] < 0)
				throw new IllegalGraphException("graph contains a negative cycle");
	}
}
