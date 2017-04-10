package crossutil;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a standard implementation
 * of the classic mergesort algorithm.
 * 
 * @author Adam Cross
 */
public class MergeSort2
{
	/*
	 * Merge two sorted lists into a single sorted list
	 * containing the elements from both input lists.  No
	 * behavior is guaranteed if input lists are not sorted.  
	 */
    private static <T extends Comparable<T>> List<T> mergeSorted(List<T> list, List<T> outList, int startIndex, int half, int endIndex) {
        int findex = startIndex;
        int bindex = half;
        int outIndex = startIndex;
        while (findex < half && bindex < endIndex) {
            T fitem = list.get(findex);
            T bitem = list.get(bindex);
            if (fitem.compareTo(bitem) > 0) {// <- compare keys
                outList.set(outIndex++, bitem);
                bindex++;
            } else {
            		outList.set(outIndex++, fitem);
                findex++;
            }
        }
        while (findex < half) 
            outList.set(outIndex++, list.get(findex++));
        while (bindex < endIndex) 
        		outList.set(outIndex++, list.get(bindex++));
        for (int i = startIndex; i < endIndex; i++)
        		list.set(i, outList.get(i));
        return list;
    }

    /**
     * 
     * @param list A java.util.List containing comparable elements
     * to be sorted.
     * @return A List containing the elements of the input list
     * in sorted order from lesser to greater.
     */
    public static <T extends Comparable<T>> List<T> sort(List<T> list, List<T> outList, int startIndex, int endIndex) {
        int size = endIndex - startIndex;
        if (size < 2)
            return list;
        int half = startIndex + size/2;
        sort(list, outList, startIndex, half);
        sort(list, outList, half, endIndex);
        return mergeSorted(list, outList, startIndex, half, endIndex);
    }
    
    public static <T extends Comparable<T>> List<T> sort(List<T> list) {
    		int size = list.size();
    		ArrayList<T> storage = new ArrayList<>(size);
    		for (int i = 0; i < size; i++)
    			storage.add(null);
    		return sort(list, storage, 0, size);
    }
}