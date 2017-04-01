package crossutil;

import java.util.List;

class IndexValuePair<T extends Comparable<T>> implements Comparable<IndexValuePair<T>>
{
	public T value;
	public int index;
	public IndexValuePair(int index, T value)
	{
		this.index = index;
		this.value = value;
	}
	public IndexValuePair(List<T> list, int index)
	{
		this.index = index;
		this.value = list.get(index);
	}
	public int compareTo(IndexValuePair<T> o)
	{
		return this.value.compareTo(o.value);
	}
}
