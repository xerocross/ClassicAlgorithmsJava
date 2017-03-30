package graphpractice;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class MinSpanningTreesTest {

	private int randInt(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min,max);
	}
	
	private void addHighWeightEdges(WeightedGraph graph)
	{
		double treeTotalWeight = graph.getSumEdgeWeights();
		Set<Edge> edges = graph.getEdges();
		double largeEdgeWeight = treeTotalWeight + 1;
		int size = graph.size();
		int numNewEdges = randInt(1,size);
		int newEdgesCreated = 0;
		WeightedEdge newEdge;
		ArrayList<Vertex> v = graph.getVertexList();
		while (newEdgesCreated < numNewEdges)
		{
			int left = randInt(0,size);
			int right = randInt(0,size);
			if (left == right)
				continue;
			newEdge = new WeightedEdge(v.get(left),v.get(right),largeEdgeWeight);
			if (edges.contains(newEdge))
				continue;
			graph.addEdge(newEdge);
			newEdgesCreated++;
		}
	}
	
	@Test
	public void minSpanningTreeRandomTest()
	{
		int maxRamify = 4;
		double maxWeight = 5;
		int maxDepth = 3;
		WeightedGraph graph = Graphs.randomTree(maxRamify, maxDepth, maxWeight);
		double treeTotalWeight = graph.getSumEdgeWeights();
		assertTrue(graph.isTree());
		System.out.println("size of created graph : " + graph.size());
		addHighWeightEdges(graph);
		assertTrue(!graph.isTree());
		System.out.println("sum of edge weights full graph : " + graph.getSumEdgeWeights());
		WeightedGraph minTree = MinSpanningTrees.minimalSpanningTree(graph);
		//assertTrue(minTree.isTree());
		System.out.println("size of minTree: " + minTree.size());
		double sumWeights = minTree.getSumEdgeWeights();
		System.out.println("sum of edge weights minTree : " + sumWeights);
		System.out.println("sum of edge weights for created tree : " + treeTotalWeight);
		assertTrue(sumWeights == treeTotalWeight);
	}
}
