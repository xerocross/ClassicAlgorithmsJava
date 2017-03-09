package graphpractice;
import java.util.ArrayList;

public class Vertex 
{
	public ArrayList<Vertex> adjacentVertices;
	
	public Vertex() 
	{
		adjacentVertices = new ArrayList<Vertex>();
	}
	
	public void addAdjacent(Vertex o)
	{
		if (adjacentVertices.contains(o))
			return;
		else
			adjacentVertices.add(o);
	}
	public void removeAdjacent(Vertex o)
	{
		if (adjacentVertices.contains(o))
			adjacentVertices.remove(o);
	}
}
