package graphpractice;

public class Vertex 
{
	public String name;
	public Vertex() {}
	public Vertex(String name) 
	{
		this.name = name;
	}
	@Override
	public String toString()
	{
		return name;
	}
}
