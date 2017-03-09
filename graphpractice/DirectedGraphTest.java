package graphpractice;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class DirectedGraphTest {

	@Test
	public void AddAndAccessVerticesTest() {
		DirectedGraph dg = new DirectedGraph();
		Vertex[] v = new Vertex[9];
		for (int i = 0; i < 9; i++)
		{
			v[i] = new Vertex();
			assertTrue(v[i] != null);
			dg.add(v[i]);
		}
		v[0].addAdjacent(v[1]);
		v[1].addAdjacent(v[3]);
		v[1].addAdjacent(v[4]);
		v[1].addAdjacent(v[6]);
		v[2].addAdjacent(v[5]);
		v[3].addAdjacent(v[8]);
		v[4].addAdjacent(v[7]);
		v[5].addAdjacent(v[7]);
		assertTrue(dg.validateEdges());
		assertTrue(dg.getVertices().size()==9);
	}

}
