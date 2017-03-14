package graphpractice;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MinSpanningTreesPrimTest {

	@Test
	public void test() {
		UndirectedWeightedGraph g = new UndirectedWeightedGraph();
		Vertex v0 = new Vertex(0+"");
		Vertex v1 = new Vertex(1+"");
		Vertex v2 = new Vertex(2+"");
		Vertex v3 = new Vertex(3+"");
		g.addVertex(v0);
		g.addVertex(v1);
		g.addVertex(v2);
		g.addVertex(v3);
		
		Edge e1 = g.addEdge(v0, v1);
		g.setEdgeWeight(e1, 12.);
		Edge e2 = g.addEdge(v0, v2);
		g.setEdgeWeight(e2, 2.);
		Edge e3 = g.addEdge(v2, v3);
		g.setEdgeWeight(e3, 37.);
		Edge e4 = g.addEdge(v1, v3);
		g.setEdgeWeight(e4, 1.);
		
		Graph tree = MinSpanningTreesPrim.minimalSpanningTreePrim(g);
		ArrayList<Vertex> vertices = tree.getVertices();
		System.out.println("treesize: " + vertices.size());
		System.out.println(vertices);
		assertTrue(vertices.contains(v0));
		assertTrue(vertices.contains(v2));
		assertTrue(MinSpanningTreesPrim.isTree(tree));
		assertTrue(!MinSpanningTreesPrim.isTree(g));
	}
	
	
	private void randomTreeHelper(UndirectedWeightedGraph graph, Vertex v, int depth, int maxRamify, double maxWeight)
	{
		int maxDepth = 3;
		if (depth > maxDepth)
			return;
		int numBranches = ThreadLocalRandom.current().nextInt(2,maxRamify+1);
		for (int i = 0; i < numBranches; i++)
		{
			Vertex w = new Vertex(v + ":" + i + ";");
			graph.addVertex(w);
			Edge e = graph.addEdge(v, w);
			double weight = ThreadLocalRandom.current().nextDouble(0,maxWeight);
			graph.setEdgeWeight(e, weight);
			randomTreeHelper(graph, w, depth+1, maxRamify, maxWeight);
		}
	}
	
	@Test
	public void buildRandomTree()
	{
		int maxRamify = 4;
		double maxWeight = 5;
		Vertex root = new Vertex("0");
		UndirectedWeightedGraph graph = new UndirectedWeightedGraph();
		graph.addVertex(root);
		randomTreeHelper(graph, root, 0, maxRamify, maxWeight);
		double treeTotalWeight = graph.getSumEdgeWeights();
		assertTrue(MinSpanningTreesPrim.isTree(graph));
		Set<Edge> edges = graph.getEdges();
		int size = graph.size();
		System.out.println("size of created graph : " + graph.size());
		double largeEdgeCost = treeTotalWeight + 1;
		int numNewEdges = ThreadLocalRandom.current().nextInt(1,size);
		int newEdgesCreated = 0;
		Edge newEdge;
		while (newEdgesCreated < numNewEdges)
		{
			int left = ThreadLocalRandom.current().nextInt(0,size);
			int right = ThreadLocalRandom.current().nextInt(0,size);
			if (left == right)
				continue;
			newEdge = new Edge(graph.vertices.get(left),graph.vertices.get(right));
			if (edges.contains(newEdge))
				continue;
			Edge e = graph.addEdge(newEdge.getLeft(), newEdge.getRight());
			graph.setEdgeWeight(e, largeEdgeCost);
			newEdgesCreated++;
		}
		assertTrue(!MinSpanningTreesPrim.isTree(graph));
		System.out.println("sum of edge weights full graph : " + graph.getSumEdgeWeights());
		System.out.println("size of graph : " + graph.size());
		UndirectedWeightedGraph minTree = MinSpanningTreesPrim.minimalSpanningTreePrim(graph);
		assertTrue(MinSpanningTreesPrim.isTree(minTree));
		System.out.println("size of minTree: " + minTree.size());
		double sumWeights = minTree.getSumEdgeWeights();
		System.out.println("sum of edge weights minTree : " + sumWeights);
		System.out.println("sum of edge weights for created tree : " + treeTotalWeight);
		assertTrue(sumWeights == treeTotalWeight);
	}
	
	
	
	
	

}
