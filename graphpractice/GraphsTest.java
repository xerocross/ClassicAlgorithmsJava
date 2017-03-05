package graphpractice;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphsTest {

	@Test
	public void minimalPathCostTest() {
		DirectedGraph dg = new DirectedGraph(6);
		dg.addEdge(0, 1, 1);
		dg.addEdge(0, 2, 2);
		dg.addEdge(1, 3, 3);
		dg.addEdge(2, 3, 4);
		dg.addEdge(1, 4, 7);
		dg.addEdge(3, 4, 1);
		dg.addEdge(2, 5, 2);
		dg.addEdge(2, 1, 1);
		Integer[][] sp = Graphs.minimalPathCost(dg);
		//these values were computed manually
		assertTrue(sp[0][1] == 1);
		assertTrue(sp[0][3] == 4);
		assertTrue(sp[1][4] == 4);
		assertTrue(sp[2][4] == 5);
		System.out.println(sp[4][5]);
	}

}
