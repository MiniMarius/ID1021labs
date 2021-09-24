//README this implements mergesort. Used for comparison between different cut-off values
package lab2;

import edu.princeton.cs.algs4.Insertion;

import java.util.Arrays;

public class Assignment5 {
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sortMerge(Comparable[] a, Comparable[] aux, int lo, int hi, Integer cutoff) {
        if (hi <= lo + cutoff - 1) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sortMerge(a, aux, lo, mid, cutoff);
        sortMerge(a, aux, mid + 1, hi, cutoff);
        merge(a, aux, lo, mid, hi);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sortMerge(Comparable[] a, Integer cutoff) {
        Comparable[] aux = new Comparable[a.length];
        sortMerge(a, aux, 0, a.length-1, cutoff);
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 120, 340, 50, 70, 10, 70, 20, 70, 40, 1240, 670, 4};
        sortMerge(arr, 1);
        System.out.println(Arrays.toString(arr));

    }
}
