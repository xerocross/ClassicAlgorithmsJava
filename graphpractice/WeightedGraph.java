package graphpractice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * The class WeightedGraph is a replacement
 * for the previous UndirectedWeightedGraph.
 * It represents a graph of weighted, undirected
 * edges among its vertices.
 * This class is an amateur attempt written
 * for practice.  It is not intended for
 * production use.
 * @author Adam Cross
 *
 */
public class WeightedGraph 
{
	ArrayList<Vertex> vertices;
	Map<Vertex,LinkedList<WeightedEdge>> adjacencyMap;
	
	public WeightedGraph()
	{
		vertices = new ArrayList<Vertex>();
		adjacencyMap = new HashMap<Vertex,LinkedList<WeightedEdge>>();
	};
	public WeightedGraph(ArrayList<Vertex> vertices){
		
		this.vertices = vertices;
		adjacencyMap = new HashMap<Vertex,LinkedList<WeightedEdge>>();
		for (Vertex v : vertices) 
		{
			this.addVertex(v);
			adjacencyMap.put(v, new LinkedList<WeightedEdge>());
		}
	};
	public boolean isEdge(Vertex vA, Vertex vB)
	{
		WeightedEdge edge = getEdge(vA,vB);
		if (edge == null)
			return false;
		else
			return true;
	}
	private WeightedEdge getEdge(Vertex base, Vertex toLocate)
	{
		LinkedList<WeightedEdge> lst = adjacencyMap.get(base);
		for (WeightedEdge edge : lst)
		{
			if (edge.getOtherSide(base).equals(toLocate))
			{	
				return edge;
			}
		}
		return null;
	}
	
	public boolean removeEdge(Vertex vA, Vertex vB)
	{
		if (!contains(vA) || !contains(vB))
			return false;
		WeightedEdge edge = getEdge(vA,vB);
		LinkedList<WeightedEdge> lstA = adjacencyMap.get(vA);
		LinkedList<WeightedEdge> lstB = adjacencyMap.get(vB);
		lstA.remove(edge);
		lstB.remove(edge);
		return true;
	}
	public Set<Vertex> getAdjacentVertices(Vertex v)
	{
		if (!vertices.contains(v))
			return null;
		Set<Vertex> adjacentVertices = new HashSet<Vertex>();
		LinkedList<WeightedEdge> lst = adjacencyMap.get(v);
		Iterator<WeightedEdge> it = lst.iterator();
		while (it.hasNext())
		{
			Vertex next = it.next().getOtherSide(v);
			adjacentVertices.add(next);
		}
		return adjacentVertices;
	}

	public int size()
	{
		return vertices.size();
	}
	
	public ArrayList<Vertex> getVertices()
	{
		ArrayList<Vertex> verticesCopy = new ArrayList<Vertex>();
		for (Vertex v : vertices)
			verticesCopy.add(v);
		return verticesCopy;
	}
	
	public boolean contains(Object o)
	{
		return vertices.contains(o);
	}
	
	public void addVertex(Vertex newVertex) 
	{
		if (vertices.contains(newVertex))
			return;
		vertices.add(newVertex);
		adjacencyMap.put(newVertex, new LinkedList<WeightedEdge>());
	}
	public void removeVertex(Vertex vertex) 
	{
		Set<Vertex> keys = adjacencyMap.keySet();
		for (Vertex key : keys)
		{
			LinkedList<WeightedEdge> ls = adjacencyMap.get(key);
			WeightedEdge edge = getEdge(key,vertex);
			ls.remove(edge);
		}
		vertices.remove(vertex);
		adjacencyMap.remove(vertex);
	}

	public Vertex getVertex(int index)
	{
		if (index < vertices.size())
			return vertices.get(index);
		else
			return null;
	}
	public boolean addEdge(WeightedEdge edge)
	{
		Vertex left = edge.getLeft();
		Vertex right = edge.getRight();
		if (!vertices.contains(left))
			throw new IllegalArgumentException();
		if (!vertices.contains(right))
			throw new IllegalArgumentException();
		if (edge.getWeight() == null)
			throw new IllegalArgumentException();
		if (isEdge(left,right))
			return false;
		adjacencyMap.get(left).add(edge);
		adjacencyMap.get(right).add(edge);
		return true;
	}
	public WeightedEdge addEdge(Vertex left, Vertex right, Double weight)
	{
		if (!vertices.contains(left))
			throw new IllegalArgumentException();
		if (!vertices.contains(right))
			throw new IllegalArgumentException();
		if (weight == null)
			throw new IllegalArgumentException();
		WeightedEdge edge = new WeightedEdge(left,right,weight);
		adjacencyMap.get(left).add(edge);
		adjacencyMap.get(right).add(edge);
		return edge;
	}
	public int getNumEdges()
	{
		return getRealEdges().size();
	}
	public Set<WeightedEdge> getAdjacentEdges(Vertex v)
	{
		Set<WeightedEdge> edges = new HashSet<WeightedEdge>();
		LinkedList<WeightedEdge> lst = adjacencyMap.get(v);
		Iterator<WeightedEdge> itr = lst.iterator();
		while (itr.hasNext())
		{
			edges.add(itr.next());
		}
		return edges;
	}
	private Set<WeightedEdge> getRealEdges()
	{
		Set<WeightedEdge> edges = new HashSet<>();
		Set<Vertex> keys = adjacencyMap.keySet();
		for (Vertex key : keys)
		{
			edges.addAll(getAdjacentEdges(key));
		}
		return edges;
	}
	
	public Set<WeightedEdge> getEdges()
	{
		Set<WeightedEdge> edgesCopy = new HashSet<>();
		Set<WeightedEdge> realEdges = getRealEdges();
		for (WeightedEdge re : realEdges)
		{
			edgesCopy.add(re.clone());
		}
		return edgesCopy;
	}
	public Double getEdgeWeight(Vertex vA, Vertex vB)
	{
		WeightedEdge edge = getEdge(vA,vB);
		return edge.getWeight();
	}
	public Double getSumEdgeWeights()
	{
		Double sumWeight = 0.;
		Set<WeightedEdge> edges = getRealEdges();
		for (WeightedEdge edge : edges)
		{
			sumWeight+=edge.getWeight();
		}
		return sumWeight;
	}
}
