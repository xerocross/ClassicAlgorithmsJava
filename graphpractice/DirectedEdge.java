package graphpractice;

public class DirectedEdge
{
	public int initial;
	public int terminal;
	public DirectedEdge(int initial, int terminal) 
	{
		this.initial = initial;
		this.terminal = terminal;
	}
	
	public boolean equals (Object o) 
	{
		if (this == o)
			return true;
		if ( !(o instanceof DirectedEdge) ) 
			return false;
		DirectedEdge that = (DirectedEdge) o;
		if (this.initial == that.initial && this.terminal == that.terminal)
			return true;
		else
			return false;
	}
	
	public int hashCode() 
	{
		return (int) (Math.pow(2, this.initial)*Math.pow( 3,this.terminal));
	}
}