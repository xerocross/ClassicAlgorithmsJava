package crossutil;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class BSTBuilder
{
	BST<Integer,Integer> bst;
	List<Integer> keys;
	List<Integer> values;
	public static BSTBuilder random(int size)
	{
		Integer[] inputs = getPermutation(size);
		BSTBuilder builder = new BSTBuilder(inputs);
		builder.keys = Arrays.asList(inputs);
		return builder;
	}
	public BST<Integer,Integer> getBST()
	{
		return bst;
	}
	public List<Integer> getKeys()
	{
		return this.keys;
	}
	public BSTBuilder(List<Integer> keyList)
	{
		bst = new BST<>();
		this.keys = keyList;
		int size = keyList.size();
		for (int i = 0; i < size; i++)
			bst.put(keyList.get(i), keyList.get(i));
	}
	public BSTBuilder(Integer[] keys)
	{
		bst = new BST<>();
		this.keys = Arrays.asList(keys);
		for (int i = 0; i < keys.length; i++)
			bst.put(keys[i], keys[i]);
	}
	private static Integer[] getPermutation(int length)
	{
		Integer permutation[] = new Integer[length];
		for (int i = 0; i < length; i++)
			permutation[i] = i;
		Random rand = new Random();
		int m = length - 1;
		int numShuffle = 7*length;
		for (int i = 0; i < numShuffle; i++)
		{
			int r1 = rand.nextInt(m);
			int r2 = rand.nextInt(m);
			int placeholder = permutation[r1];
			permutation[r1] = permutation[r2];
			permutation[r2] = placeholder;
		}
			return permutation;
	}
}
