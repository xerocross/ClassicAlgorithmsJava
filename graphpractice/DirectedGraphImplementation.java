package graphpractice;
import java.util.*;

public class DirectedGraphImplementation extends Graph
{
	Set<Edge> edges;

	public DirectedGraphImplementation()
	{
		super();
		edges = super.edges;
		vertices = super.vertices;
		
	}
	public DirectedGraphImplementation(ArrayList<Vertex> vertices)
	{
		super(vertices);
		edges = super.edges;
		vertices = super.vertices;
	}
	
	public Set<Vertex> getAdjacentVertices(Vertex v)
	{
		Set<Vertex> adjacentVertices = new HashSet<Vertex>();
		for (Edge edge : edges)
		{
			DirectedEdge dEdge = (DirectedEdge) edge;
			if (dEdge.getInitial() == v)
				adjacentVertices.add(dEdge.getTerminal());
		}
		return adjacentVertices;
	}
	
	public Edge addEdge(Vertex initial, Vertex terminal)
	{
		if (!vertices.contains(initial))
			throw new IllegalArgumentException();
		if (!vertices.contains(terminal))
			throw new IllegalArgumentException();
		DirectedEdge edge = new DirectedEdge(initial,terminal);
		if (edges.contains(edge))
			return null;
		edges.add(edge);
		return edge;
	}
	
	
	public Edge addEdge(DirectedEdge edge)
	{
		return addEdge(edge.getInitial(),edge.getTerminal());
	}
	public Set<Edge> getAdjacentEdges(Vertex v)
	{
		
		Set<Edge> adjEdges = new HashSet<>();
		for (Edge edge : edges)
		{
			if (edge.getLeft() == v )
				adjEdges.add(edge);
		}
		return adjEdges;
	}
}
