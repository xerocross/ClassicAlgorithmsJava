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
	public boolean contains(Vertex v)
	{
		return (left == v || right == v);
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
	public String toString()
	{
		return "("+left + "," + right +")";
	}
	public Vertex getOtherSide(Vertex v)
	{
		if (this.left.equals(v))
			return this.right;
		else if (this.right.equals(v))
			return this.left;
		else
			return null;
	}
}
