package graphpractice;
import java.util.*;

public class Vertex 
{
	private Set<Vertex> adjacentVertices;
	public String name;
	
	public Set<Vertex> getAdjacent()
	{
		Set<Vertex> adjacentCopy = new HashSet<>();
		for (Vertex v : adjacentVertices)
		{
			adjacentCopy.add(v);
		}
		return adjacentCopy;
	}
	
	public Vertex() 
	{
		adjacentVertices = new HashSet<Vertex>();
	}
	
	public Vertex(String name) 
	{
		adjacentVertices = new HashSet<Vertex>();
		this.name = name;
	}
	
	private void addAdjacent(Vertex o)
	{
		if (adjacentVertices.contains(o))
			return;
		else
			adjacentVertices.add(o);
	}
	private void removeAdjacent(Vertex o)
	{
		if (adjacentVertices.contains(o))
			adjacentVertices.remove(o);
	}
	public String toString()
	{
		return name;
	}
}
