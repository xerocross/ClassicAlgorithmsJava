package graphpractice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DirectedGraphTest.class, 
        DirectedEdgeTest.class, 
        DirectedWeightedGraphTest.class, 
        MinimalCostPathTest.class,
        MinSpanningTreesTest.class,
        TopSortTest.class,
        WeightedGraphTest.class})

public class TestAll {

}
