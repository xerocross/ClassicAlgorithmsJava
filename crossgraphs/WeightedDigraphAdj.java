package crossgraphs;

import java.util.*;

public class WeightedDigraphAdj implements WeightedDigraph
{
	ArrayList<LinkedList<Edge>> adjacencyList;
	public WeightedDigraphAdj(int size) {
		adjacencyList = new ArrayList<>(size);
		for (int i = 0; i < size; i++)
			adjacencyList.add(new LinkedList<Edge>());
	}
	public void addEdge(int head, int tail, double weight) {
		Edge edge = new Edge(head, tail, weight);
		for (Edge e : adjacencyList.get(head))
			if (e.equals(edge)) {
				e.setWeight(weight);
				return;
			}
		adjacencyList.get(head).add(edge);
	}
	public Iterable<Integer> adj(int i) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		for (Edge e : adjacencyList.get(i))
			queue.offer(e.tail);
		return queue;
	}
	
	class Edge
	{
		int head;
		int tail;
		double weight;
		public Edge (int head, int tail, double weight)
		{
			this.head = head;
			this.tail = tail;
			this.weight = weight;
		}
		public boolean equals(Object o)
		{
			if (o == null)
				return false;
			if (o instanceof Edge)
				return head == ((Edge)o).head && tail == ((Edge)o).tail;
			else
				return false;
		}
		public void setWeight(double weight)
		{
			this.weight = weight;
		}
	}
}
