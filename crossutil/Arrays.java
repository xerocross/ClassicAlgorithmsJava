package crossutil;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public abstract class Arrays {
	public static ArrayList<Integer> randomIntegerArrayList(int size, int max)
	{
		ArrayList<Integer> arr = new ArrayList<>(size);
		for (int i = 0; i < size; i++)
		{
			arr.add(ThreadLocalRandom.current().nextInt(0, max));
		}
		return arr;
	}

	

    
	static <E> void swap(E[] a, int i, int j)
	{
		E placeholder = a[j];
		a[j] = a[i];
		a[i] = placeholder;
	}
	
	static <E> void swap(List<E> list, int a, int b)
	{
		E placeholder = list.get(b);
		list.set(b, list.get(a));
		list.set(a, placeholder);
	}
	
	public static <E extends Comparable<E>> boolean isSorted(List<E> list)
	{
		int oneBeforeEnd = list.size() - 1;
		for (int i = 0; i < oneBeforeEnd; i++) 
			if (list.get(i).compareTo(list.get(i+1)) > 0)
				return false;
		return true;
	}
	
	public static Integer[] shuffle(Integer[] a)
	{
		Random R = new Random();
		int size = a.length;
		int gap = R.nextInt(size);
		int x;
		for (int i = 0; i < size; i++)
		{
			x = R.nextInt(size);
			swap(a,x, (x + gap) % size);
		}
		return a;
	}
	
	public static <E extends Comparable<E>> List<E> shuffle(List<E> list)
	{
		Random R = new Random();
		int size = list.size();
		int gap = R.nextInt(size);
		int x;
		for (int i = 0; i < size; i++)
		{
			x = R.nextInt(size);
			swap(list,x, (x + gap) % size);
		}
		return list;
	}
	
	public static <T> List<T> asList(T[] array)
	{
		int len = array.length;
		List<T> list = new ArrayList<>(len);
		for (int i = 0; i < len; i++)
		{
			list.add(array[i]);
		}
		return list;
	}
	
	public static Integer[] permutation(int length)
	{
		Integer permutation[] = new Integer[length];
		for (int i = 0; i < length; i++)
			permutation[i] = i;
		int numShuffle = 7;
		for (int i = 0; i < numShuffle; i++)
		{
			permutation = shuffle(permutation);
		}
		return permutation;
	}
}
