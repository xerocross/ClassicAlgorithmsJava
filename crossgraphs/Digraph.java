package crossgraphs;

public interface Digraph 
{
	public void addEdge(Integer head, Integer tail);
	public Iterable<Integer> adj(int head);
}
