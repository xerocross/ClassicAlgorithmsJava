package crossutil;
import java.util.List;


public interface Sorter 
{
	public <U extends Comparable<U>> List<U> sort(List<U> list);
	public <U extends Comparable<U>> U[] sort(U[] list); 
}
