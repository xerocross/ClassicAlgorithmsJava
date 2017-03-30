package crossutil;

import java.util.ArrayList;

public class MergeSort
{
    
    public static void main(String[] args)
    {
        ArrayList<Integer> testList = new ArrayList<>();
        testList.add(5);
        testList.add(12);
        testList.add(2);
        testList.add(99);
        testList.add(1);
        testList.add(42);
        testList.add(7);
        ArrayList<Integer> sorted = MergeSort.mergesort(testList);
        System.out.println(sorted);
   }
    
    
    private static <T extends Comparable<T>> ArrayList<T> mergeSorted(ArrayList<T> a, ArrayList<T> b)
    {
        ArrayList<T> newSorted = new ArrayList<T>();
        int sizeA = a.size();
        int sizeB = b.size();
        int indexA = 0;
        int indexB = 0;
        
        while (indexA < sizeA && indexB < sizeB)
        {
            T itemA = a.get(indexA);
            T itemB = b.get(indexB);
            if (itemA.compareTo(itemB) > 0)
            {
                newSorted.add(itemB);
                indexB++;
            } else {
                newSorted.add(itemA);
                indexA++;
            }
        }
        while (indexA < sizeA)
        {
            newSorted.add(a.get(indexA++));
        }
        while (indexB < sizeB)
        {
            newSorted.add(b.get(indexB++));
        }
        return newSorted;
    }


    public static <T extends Comparable<T>> ArrayList<T> mergesort(ArrayList<T> arr)
    {
        int size = arr.size();
        if (size < 2)
            return arr;
        int half = size/2;
        ArrayList<T> frontHalf = new ArrayList<>(arr.subList(0,half));
        ArrayList<T> backHalf = new ArrayList<>(arr.subList(half,size));
        frontHalf = mergesort(frontHalf);
        backHalf = mergesort(backHalf);
        return mergeSorted(frontHalf, backHalf);
    }



}