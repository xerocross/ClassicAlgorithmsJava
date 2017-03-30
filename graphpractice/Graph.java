package graphpractice;
import java.util.*;

public interface Graph 
{
	void addVertex(Vertex v);
	void addEdge(Edge e);
	boolean containsVertex(Vertex v);
	Set<Vertex> getAdjacentVertices(Vertex v);
	Set<Edge> getAdjacentEdges(Vertex v);
	int size();
	void removeVertex(Vertex v);
	boolean removeEdge(Vertex a, Vertex b);
	boolean removeEdge(Edge e);
	Set<Vertex> getVertices();
	Set<Edge> getEdges();
	Vertex getAnyVertex();
}
