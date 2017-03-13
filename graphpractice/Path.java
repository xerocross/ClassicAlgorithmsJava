package graphpractice;

import java.util.*;

public class Path 
{
	private DirectedGraph graph;
	private ArrayList<Vertex> pathVertices;
	
	public Path(DirectedGraph graph)
	{
		pathVertices = new ArrayList<Vertex>();
		this.graph = graph;
	}
	
	public void add(Vertex v)
	{
		if (graph.contains(v))
			pathVertices.add(v);
	}
	
	
}
