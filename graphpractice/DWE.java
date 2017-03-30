package graphpractice;

/**
 * DWE is a Directed Weighted Edge
 * @author Adam Cross
 *
 */
public class DWE extends DirectedEdge
{
	double weight;
	public DWE(Vertex initial, Vertex terminal, double weight)
	{
		super(initial, terminal);
		this.weight = weight;
	}
	public DWE(Vertex initial, Vertex terminal)
	{
		super(initial, terminal);
		this.weight = 0;
	}
	public void setWeight(Double weight)
	{
		this.weight = weight;
	}
	
	public Double getWeight()
	{
		return weight;
	}
	@Override
	public DWE clone()
	{
		return new DWE(this.getInitial(),this.getTerminal(),this.weight);
	}
}
