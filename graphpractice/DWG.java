package graphpractice;

// directed weighted graph
public interface DWG extends DirectedGraph, WeightedGraph
{
	void addEdge(DWE edge);
}
