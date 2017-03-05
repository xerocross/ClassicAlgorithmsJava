package graphpractice;

public class DirectedEdge
{
	public int first;
	public int second;
	public DirectedEdge(int first, int second) 
	{
		this.first = first;
		this.second = second;
	}
	
	public boolean equals (Object o) 
	{
		if (this == o)
			return true;
		if ( !(o instanceof DirectedEdge) ) 
			return false;
		DirectedEdge that = (DirectedEdge) o;
		if (this.first == that.first && this.second == that.second)
			return true;
		else
			return false;
	}
	
	public int hashCode() 
	{
		return (int) (Math.pow(2, this.first)*Math.pow( 3,this.second));
	}
}