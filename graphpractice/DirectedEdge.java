package graphpractice;

public class DirectedEdge
{
	public Vertex initial;
	public Vertex terminal;
	
	public DirectedEdge(Vertex initial, Vertex terminal)
	{
		this.initial = initial;
		this.terminal = terminal;
	}
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o instanceof DirectedEdge)
		{
			DirectedEdge that = (DirectedEdge) o;
			if (that.initial == this.initial && that.terminal == this.terminal)
				return true;
		}
		return false;
	}
	public int hashCode()
	{
		return (this.initial.hashCode()/2) + (this.terminal.hashCode()/2);
	}
}