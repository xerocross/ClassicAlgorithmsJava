package graphpractice;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

public class DirectedWeightedGraphTest {

	@Test
	public void constructorWithArrayListTest() {
		ArrayList<Vertex> vertices = new ArrayList<>();
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		vertices.add(v1);
		vertices.add(v2);
		DirectedWeightedGraph dwg = new DirectedWeightedGraph(vertices);
		assertTrue(dwg.size() == 2);
	}

}
