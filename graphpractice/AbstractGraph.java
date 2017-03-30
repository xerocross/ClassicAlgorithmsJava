package graphpractice;
import java.util.*;

public abstract class AbstractGraph 
{
	public abstract void addVertex(Vertex v);
	public abstract void addEdge(Edge e);
	public abstract boolean containsVertex(Vertex v);
	public abstract Set<Vertex> getAdjacentVertices(Vertex v);
	public abstract Set<Edge> getAdjacentEdges(Vertex v);
	public abstract int size();
	public abstract void removeVertex(Vertex v);
	public abstract void removeEdge(Vertex a, Vertex b);
	public abstract void removeEdge(Edge e);
	public abstract Set<Vertex> getVertices();
}
