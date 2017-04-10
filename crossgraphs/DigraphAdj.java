package crossgraphs;
import java.util.*;

public class DigraphAdj implements Digraph
{
	ArrayList<LinkedList<Integer>> AdjacencyList;
	public DigraphAdj(int size)
	{
		AdjacencyList = new ArrayList<>(size);
		for (int i = 0; i < size; i++)
			AdjacencyList.add(new LinkedList<Integer>());
	}
	public void addEdge(Integer head, Integer tail) {
		if (!AdjacencyList.get(head).contains(tail))
			AdjacencyList.get(head).add(tail);
	}
	public Iterable<Integer> adj(int head)
	{
		return AdjacencyList.get(head);
	}
}
