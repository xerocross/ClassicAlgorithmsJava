package graphpractice;

public class WeightedEdge extends Edge implements Comparable<WeightedEdge>
{
	private Double weight;
	public WeightedEdge(Edge edge, Double weight)
	{
		super(edge.getLeft(),edge.getRight());
		this.weight = weight;
	}
	public WeightedEdge(Vertex left, Vertex right, Double weight)
	{
		super(left,right);
		if (weight == null)
			throw new IllegalArgumentException();
		this.weight = weight;
	}
	public WeightedEdge(Vertex left, Vertex right, int weight)
	{
		super(left,right);
		this.weight = (double) weight;
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
	public WeightedEdge clone()
	{
		return new WeightedEdge(super.getLeft(),super.getRight(),weight);
	}
	
}