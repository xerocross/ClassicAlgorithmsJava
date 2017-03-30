package graphpractice;

// directed weighted graph
public abstract class DWG extends DirectedGraph
{
	abstract double getEdgeWeight(Vertex a, Vertex b);
	abstract boolean setEdgeWeight(Vertex a, Vertex b);
	
}
