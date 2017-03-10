package graphpractice;
import java.util.*;

public class DirectedGraph {
	
	private ArrayList<Vertex> vertices;
	
	public DirectedGraph(){
		vertices = new ArrayList<Vertex>();
	};
	
	public DirectedGraph(ArrayList<Vertex> vertices){
		vertices = new ArrayList<Vertex>();
		for (Vertex v : vertices)
			this.add(v);
	};
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
	public Vertex getVertex(int index)
	{
		return vertices.get(index);
	}
	public boolean validateEdges()
	{
		for (Vertex someVertex : vertices)
		{
			Set<Vertex> adjacentVertices = someVertex.getAdjacent();
			for (Vertex ref : adjacentVertices)
				if (!vertices.contains(ref))
					return false;
		}
		return true;
	}
}
