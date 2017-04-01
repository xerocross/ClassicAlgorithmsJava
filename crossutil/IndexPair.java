package crossutil;

class IndexPair
{
	int left;
	int right;
	public IndexPair(int left, int right)
	{
		this.left = left;
		this.right = right;
	}
	public boolean equals(Object o)
	{
		if (o == null)
			return false;
		else if (o instanceof IndexPair)
		{
			IndexPair that = (IndexPair) o;
			return (left == that.left && right == that.right);
			
		} else
			return false;
	}
}