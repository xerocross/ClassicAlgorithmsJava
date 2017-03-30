package crossutil;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class BST_PerformanceTest {

	BST<Integer,Integer> bst1, bst2, bst3;
	final Random R = new Random();
	
	@Before
	public void buildBSTs()
	{
		bst1 = BSTBuilder.random(5000).getBST();
		bst2 = BSTBuilder.random(10_000).getBST();
		bst3 = BSTBuilder.random(10_0000).getBST();
	}
	
	
	private int testBST(BST<Integer,Integer> bst, int numGetCalls)
	{
		int size = bst.size();
		//int numGetCalls = 1_000_000;
		Integer[] calls = new Integer[numGetCalls];
		for(int i = 0; i < numGetCalls; i++)
			calls[i] = R.nextInt(size);
		
		Long t_init = System.currentTimeMillis();
		for(int i = 0; i < numGetCalls; i++)
		{
			bst1.get(calls[i]);
		}
		Long t_final = System.currentTimeMillis();
		int elapsed = (int)(t_final - t_init);
		//System.out.format("time for %d get calls on %d items: %d ms%n",numGetCalls,size, elapsed);
		return elapsed;
	}
	
	
	//@Test
	public void test5000() {
		testBST(bst1, 100_000);
		testBST(bst1, 1_000_000);
		testBST(bst1, 10_000_000);
	}
	
	//@Test
	public void test10_000() {
		testBST(bst2, 100_000);
		testBST(bst2, 1_000_000);
		testBST(bst2, 10_000_000);
	}
	
	@Test
	public void test100_000() {
		bst3 = BSTBuilder.random(10_0000).getBST();
		List<Integer> times = new ArrayList<>();
		List<Double> averages = new ArrayList<>();
		
		int numGets = 100_000;
		
		for (int i = 0; i < 10; i++)
		{
			times.add(testBST(bst3, numGets));
		}
		Double avg = times.stream().collect(Collectors.averagingInt(x->x));
		double comparison = Math.log((double) numGets/1000)*2;
		System.out.println("avg " + avg + " " + comparison);
		averages.add(avg);
		
		numGets = 500_000;
		times = new ArrayList<>();
		for (int i = 0; i < 10; i++)
		{
			times.add(testBST(bst3, numGets));
		}
		avg = times.stream().collect(Collectors.averagingInt(x->x));
		comparison = Math.log((double) numGets/1000)*2;
		System.out.println("avg2 " + avg+ " " + comparison);
		averages.add(avg);
		
		numGets = 2_500_000;
		times = new ArrayList<>();
		for (int i = 0; i < 10; i++)
		{
			times.add(testBST(bst3, numGets));
		}
		avg = times.stream().collect(Collectors.averagingInt(x->x));
		comparison = Math.log((double) numGets/1000)*2;
		System.out.println("avg3 " + avg+ " " + comparison);
		averages.add(avg);
		
		
		numGets = 5_000_000;
		times = new ArrayList<>();
		for (int i = 0; i < 10; i++)
		{
			times.add(testBST(bst3, numGets));
		}
		avg = times.stream().collect(Collectors.averagingInt(x->x));
		comparison = Math.log((double) numGets/1000)*2;
		System.out.println("avg3 " + avg+ " " + comparison);
		averages.add(avg);
		
		
		//testBST(bst3, 1_000_000);
		//testBST(bst3, 10_000_000);
	}
}
