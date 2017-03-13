package graphpractice;

import java.util.ArrayList;
import java.util.*;

public class Graph  
{
	ArrayList<Vertex> vertices;
	Set<Edge> edges;
	
	public Graph(){
		vertices = new ArrayList<Vertex>();
		edges = new HashSet<Edge>();
	};
	public Graph(ArrayList<Vertex> vertices){
		this.vertices = vertices;
		for (Vertex v : vertices)
			this.addVertex(v);
		edges = new HashSet<Edge>();
	};
	
	public Set<Vertex> getAdjacentVertices(Vertex v)
	{
		Set<Vertex> adjacentVertices = new HashSet<Vertex>();
		for (Edge edge : this.edges)
		{
			if (edge.getLeft() == v)
				adjacentVertices.add(edge.getRight());
			else if (edge.getRight() == v)
				adjacentVertices.add(edge.getLeft());
		}
		return adjacentVertices;
	}
	public int size()
	{
		return vertices.size();
	}
	
	public ArrayList<Vertex> getVertices()
	{
		ArrayList<Vertex> verticesCopy = new ArrayList<Vertex>();
		for (Vertex v : vertices)
			verticesCopy.add(v);
		return verticesCopy;
	}
	
	public boolean contains(Object o)
	{
		return vertices.contains(o);
	}
	
	public void addVertex(Vertex newVertex) 
	{
		if (!vertices.contains(newVertex))
			vertices.add(newVertex);
	}
	public void removeVertex(Vertex vertex) 
	{
		if (vertices.contains(vertex))
			vertices.remove(vertex);
	}
	public Vertex getVertex(int index)
	{
		return vertices.get(index);
	}
	public Edge addEdge(Vertex vA, Vertex vB)
	{
		Edge edge = new Edge(vA,vB);
		if (edges.contains(edge))
			return null;
		if (!vertices.contains(edge.getLeft()))
			throw new IllegalArgumentException();
		if (!vertices.contains(edge.getRight()))
			throw new IllegalArgumentException();
		edges.add(edge);
		return edge;
	}
	public int getNumEdges()
	{
		return edges.size();
	}
	public Set<Edge> getAdjacentEdges(Vertex v)
	{
		Set<Edge> adjEdges = new HashSet<>();
		for (Edge edge : edges)
		{
			if (edge.getLeft() == v || edge.getRight() == v)
				adjEdges.add(edge);
		}
		return adjEdges;
	}
	public Set<Edge> getEdges()
	{
		Set<Edge> edgesCopy = new HashSet<>();
		for (Edge edge : edges)
			edgesCopy.add(edge);
		return edgesCopy;
	}
}
