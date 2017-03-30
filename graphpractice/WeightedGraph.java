package graphpractice;

public interface WeightedGraph extends Graph
{
	Double getEdgeWeight(Vertex a, Vertex b);
	Double getEdgeWeight(Edge edge);
	boolean setEdgeWeight(Vertex a, Vertex b, Double weight);
	boolean setEdgeWeight(Edge edge, Double weight);
}
