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

class VertexData
{
	Vertex vertex;
	int inDegree;
	public Set<Vertex> inVertices;
	
	VertexData(Vertex vertex) 
	{
		this.vertex = vertex;
		 inVertices = new HashSet<Vertex>();
	}
}


public abstract class Graphs 
{
	
	public static ArrayList<Vertex> topSort(DirectedGraph graph)
	{
		ArrayList<VertexData> vertexDataList = buildVertexData(graph);
		populateInVertices(vertexDataList);
		Stack<VertexData> readyVertices = new Stack<>();
		updateReadyStack(vertexDataList,readyVertices);
		ArrayList<Vertex> topSortedList = new ArrayList<>();
		while (!readyVertices.isEmpty())
		{
			VertexData next = readyVertices.pop();
			topSortedList.add(next.vertex);
			for (VertexData vDat : vertexDataList)
				vDat.inVertices.remove(next.vertex);
			updateReadyStack(vertexDataList,readyVertices);
		}
		
		if (topSortedList.size() != graph.size())
			throw new IllegalGraphException("graph could not be top-sorted");
		return topSortedList;
	}
	
	//this method used for top sort
	static void updateReadyStack(ArrayList<VertexData> vertexDataList, Stack<VertexData> readyVertices)
	{
		Stack<VertexData> removeThese = new Stack<>();
		for (VertexData vDat : vertexDataList)
		{
			if (vDat.inVertices.size() == 0)
			{
				readyVertices.push(vDat);
				removeThese.push(vDat);
			}
		}
		while(!removeThese.isEmpty())
			vertexDataList.remove(removeThese.pop());
	}
	
	
	//this method used for top sort
	private static ArrayList<VertexData> buildVertexData(DirectedGraph graph)
	{
		ArrayList<Vertex> vertices =  graph.getVertices();
		ArrayList<VertexData> vertexDataList = new ArrayList<VertexData>();
		for (Vertex v : vertices)
		{
			vertexDataList.add(new VertexData(v));
		}
		return vertexDataList;
	}
	
	//this method used for top sort
	private static void populateInVertices(ArrayList<VertexData> vertexDataList)
	{
		Set<Vertex> adjacentVerticies;
		for (VertexData vDatOuter : vertexDataList)
		{
			for (VertexData vDatInner : vertexDataList)
			{
				if (vDatOuter == vDatInner) 
				{
					continue;
				}
				adjacentVerticies = vDatInner.vertex.getAdjacent();
				if (adjacentVerticies.contains(vDatOuter.vertex))
				{ // the vDatInner vertex points to vDatOuter
					vDatOuter.inVertices.add(vDatInner.vertex);
				}
			}
		}
	}
	
	//this method is used for minimalPathCost
	private static Double computeInfinity(DirectedWeightedGraph graph)
	{
		Double inf = 0.;
		Set<DirectedEdge> keys = graph.getEdges();
		for (DirectedEdge key : keys)
		{
			inf+=graph.getEdgeWeight(key);
		}
		return inf;
	}
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
	public static Double[][] minimalPathCost(DirectedWeightedGraph graph)
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
		}
		return floydMatrix;
	}
}
