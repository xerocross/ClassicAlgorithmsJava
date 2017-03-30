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
		populateInVertices(graph, vertexDataList);
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
	private static void populateInVertices(DirectedGraph g, ArrayList<VertexData> vertexDataList)
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
				adjacentVerticies = g.getAdjacentVertices(vDatInner.vertex);
				if (adjacentVerticies.contains(vDatOuter.vertex))
				{ // the vDatInner vertex points to vDatOuter
					vDatOuter.inVertices.add(vDatInner.vertex);
				}
			}
		}
	}
}
