package graphpractice;
import java.util.*;

public class DirectedGraph {
	
	ArrayList<Vertex> vertices;
	
	public DirectedGraph(){
		vertices = new ArrayList<Vertex>();
	};
	
	public ArrayList<Vertex> getVertices()
	{
		return vertices;
	}
	
	public void add(Vertex newVertex) 
	{
		if (!vertices.contains(newVertex))
			vertices.add(newVertex);
	}
	public void remove(Vertex vertex) 
	{
		if (vertices.contains(vertex))
			vertices.remove(vertex);
	}
	public boolean validateEdges()
	{
		for (Vertex someVertex : vertices)
		{
			ArrayList<Vertex> adjacentVertices = someVertex.adjacentVertices;
			for (Vertex ref : adjacentVertices)
				if (!vertices.contains(ref))
					return false;
		}
		return true;
	}
}
