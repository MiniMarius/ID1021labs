//README this implements quicksort. Used for comparison between median of three and first element of each sub-array
//as partitioning element
package lab2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Assignment6 {

    // quicksort the subarray from a[lo] to a[hi]
    private static void sortQuick(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sortQuick(a, lo, j-1);
        sortQuick(a, j+1, hi);
        assert isSorted(a, lo, hi);
    }

    public static void sortQuick(Comparable[] a) {
        StdRandom.shuffle(a);
        sortQuick(a, 0, a.length - 1);
        assert isSorted(a);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {

            // find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 120, 340, 50, 70, 10, 70, 20, 70, 40, 1240, 670, 4};
        sortQuick(arr);
        System.out.println(Arrays.toString(arr));

    }
}
