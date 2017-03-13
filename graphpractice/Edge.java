package graphpractice;

public class Edge 
{
	private Vertex left;
	private Vertex right;
	public void setLeft(Vertex left)
	{
		this.left = left;
	}
	public void setRight(Vertex right)
	{
		this.right = right;
	}
	public Vertex getLeft()
	{
		return left;
	}
	public Vertex getRight()
	{
		return right;
	}
	
	public Edge(Vertex left, Vertex right)
	{
		this.left = left;
		this.right = right;
	}
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o instanceof Edge)
		{
			Edge that = (Edge) o;
			if (that.left == this.left && that.right == this.right)
				return true;
			if (that.left == this.right && that.right == this.left)
				return true;
		}
		return false;
	}
	public int hashCode()
	{
		return (this.left.hashCode()/2) + (this.right.hashCode()/2);
	}
}
