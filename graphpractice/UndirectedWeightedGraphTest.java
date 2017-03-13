package graphpractice;

import static org.junit.Assert.*;

import org.junit.Test;

public class UndirectedWeightedGraphTest {

	@Test
	public void constructorTest() {
		UndirectedWeightedGraph graph = new UndirectedWeightedGraph();
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addEdge(v1, v2);
		assertTrue(graph.size() == 2);
	}

}
