package graphpractice;

public abstract class DirectedGraph extends AbstractGraph
{
	public abstract void addEdge(DirectedEdge edge);
	public abstract void removeEdge(DirectedEdge edge);
	@Override
	public abstract void addEdge(Edge edge);
}
