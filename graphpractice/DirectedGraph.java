package graphpractice;

public interface DirectedGraph extends Graph
{
	void addEdge(DirectedEdge edge);
	void removeEdge(DirectedEdge edge);
	@Override
	void addEdge(Edge edge);
}
