package crossutil;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a standard implementation of the classic 
 * mergesort algorithm.  Contrast this implementation
 * with the one found in MergeSort1.  The one found 
 * here is more memory efficient and faster. We achieve 
 * this by creating a single List on the heap for 
 * storage used while merging sorted sub-lists.  This
 * single List gets passed around, so the merge method
 * does not create a new List every time.
 * 
 * @author Adam Cross
 */
public class MergeSort2
{
	/**
	 * Given a list and two adjacent sorted sublists 
	 * bounded by startIndex, half and half, endIndex,
	 * mergeSorted mutates the list to merge these sublists
	 * and also returns a copy of the mutated list
	 * 
	 * @param list the list which is to be sorted
	 * @param storage extra space allocated for temporary storage
	 * @param startIndex first element in first sorted sublist
	 * @param half terminal index of first sorted sublist and first element of second sorted sublist
	 * @param endIndex terminal index of second sorted sublist
	 * @return the List with sorted sublists merged
	 */
	private static <T extends Comparable<T>> List<T> mergeSorted(List<T> list, List<T> storage, int startIndex, int half, int endIndex) {
        int findex = startIndex;
        int bindex = half;
        int outIndex = startIndex;
        while (findex < half && bindex < endIndex) {
            T fitem = list.get(findex);
            T bitem = list.get(bindex);
            if (fitem.compareTo(bitem) > 0) {// <- compare keys
                storage.set(outIndex++, bitem);
                bindex++;
            } else {
            		storage.set(outIndex++, fitem);
                findex++;
            }
        }
        while (findex < half) 
            storage.set(outIndex++, list.get(findex++));
        while (bindex < endIndex) 
        		storage.set(outIndex++, list.get(bindex++));
        for (int i = startIndex; i < endIndex; i++)
        		list.set(i, storage.get(i));
        return list;
    }

    /**
     * Sort the List list in the sublist from startIndex
     * to endIndex.
     * 
     * @param list the list to be sorted
     * @param storage storage space allocated for merging sorted sublists
     * @param startIndex the starting index
     * @param endIndex the ending index
     * @return the list sorted between startIndex and endIndex
     */
    public static <T extends Comparable<T>> List<T> sort(List<T> list, List<T> storage, int startIndex, int endIndex) {
        int size = endIndex - startIndex;
        if (size < 2)
            return list;
        int half = startIndex + size/2;
        sort(list, storage, startIndex, half);
        sort(list, storage, half, endIndex);
        return mergeSorted(list, storage, startIndex, half, endIndex);
    }
    
    /**
     * Sort the List list.  Mutates the List and also 
     * returns a copy.
     * @param list
     * @return the sorted list
     */
    public static <T extends Comparable<T>> List<T> sort(List<T> list) {
    		int size = list.size();
    		ArrayList<T> storage = new ArrayList<>(size);
    		for (int i = 0; i < size; i++)
    			storage.add(null);
    		return sort(list, storage, 0, size);
    }
}