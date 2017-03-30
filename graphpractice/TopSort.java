package graphpractice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


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

public class TopSort {

	public static ArrayList<Vertex> topSort(DirectedGraph graph)
	{
		ArrayList<VertexData> vertexDataList = buildVertexData(graph);
		populateInVertices(graph, vertexDataList);
		ArrayDeque<VertexData> readyVertices = new ArrayDeque<>();
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
	
	static void updateReadyStack(ArrayList<VertexData> vertexDataList, ArrayDeque<VertexData> readyVertices)
	{
		ArrayDeque<VertexData> removeThese = new ArrayDeque<>();
		for (VertexData vDat : vertexDataList)
			if (vDat.inVertices.size() == 0)
			{
				readyVertices.push(vDat);
				removeThese.push(vDat);
			}
		while(!removeThese.isEmpty())
			vertexDataList.remove(removeThese.pop());
	}
	
	
	private static ArrayList<VertexData> buildVertexData(DirectedGraph graph)
	{
		return graph.getVertices().stream()
			.map((v)->new VertexData(v))
			.collect(Collectors.toCollection(()->new ArrayList<VertexData>()));
	}
	
	private static void populateInVertices(DirectedGraph g, ArrayList<VertexData> vertexDataList)
	{
		for (VertexData a : vertexDataList)
			a.inVertices.addAll(
					vertexDataList.stream()
					.filter(b->g.isEdge(b.vertex,a.vertex))
					.map(x->x.vertex)
					.collect(Collectors.toList())
			);
	}
}
