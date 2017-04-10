package crossutil;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a standard implementation
 * of the classic mergesort algorithm.
 * 
 * @author Adam Cross
 */
public class MergeSort1
{
	/*
	 * Merge two sorted lists into a single sorted list
	 * containing the elements from both input lists.  No
	 * behavior is guaranteed if input lists are not sorted.  
	 */
    private static <T extends Comparable<T>> List<T> mergeSorted(List<T> flist, List<T> blist) {
    		ArrayList<T> newSorted = new ArrayList<T>();
        int fsize = flist.size();
        int bsize = blist.size();
        int findex = 0, bindex = 0;
        while (findex < fsize && bindex < bsize) {
            T fitem = flist.get(findex);
            T bitem = blist.get(bindex);
            if (fitem.compareTo(bitem) > 0) {// <- compare keys
                newSorted.add(bitem);
                bindex++;
            } else {
                newSorted.add(fitem);
                findex++;
            }
        }
        while (findex < fsize) 
            newSorted.add(flist.get(findex++));
        while (bindex < bsize) 
            newSorted.add(blist.get(bindex++));
        return newSorted;
    }

    /**
     * 
     * @param list A java.util.List containing comparable elements
     * to be sorted.
     * @return A List containing the elements of the input list
     * in sorted order from lesser to greater.
     */
    public static <T extends Comparable<T>> List<T> sort(List<T> list) {
        int size = list.size();
        if (size < 2)
            return list;
        int half = size/2;
        List<T> frontHalf = list.subList(0,half);
        List<T> backHalf = list.subList(half,size);
        return mergeSorted(sort(frontHalf), sort(backHalf));
    }
}