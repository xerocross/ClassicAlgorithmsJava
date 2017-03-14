package graphpractice;

public class WeightedEdge extends Edge implements Comparable<WeightedEdge>
{
	private Double weight;
	public WeightedEdge(Vertex left, Vertex right)
	{
		super(left,right);
	}
	public WeightedEdge(Edge edge, Double weight)
	{
		super(edge.getLeft(),edge.getRight());
		this.weight = weight;
	}
	public void setWeight(Double weight)
	{
		this.weight = weight;
	}
	
	public Double getWeight()
	{
		return weight;
	}
	
	public int compareTo (WeightedEdge o)
	{
		return (int)(this.weight - o.weight);
	}
	
}