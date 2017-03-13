package graphpractice;

public class DirectedEdge extends Edge
{
	public DirectedEdge(Vertex initial, Vertex terminal)
	{
		super(initial,terminal);
	}
	public Vertex getInitial()
	{
		return super.getLeft();
	}
	public Vertex getTerminal()
	{
		return super.getRight();
	}
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		if (o instanceof DirectedEdge)
		{
			DirectedEdge that = (DirectedEdge) o;
			if (that.getInitial() == this.getInitial() && that.getTerminal() == this.getTerminal())
				return true;
		}
		return false;
	}
}