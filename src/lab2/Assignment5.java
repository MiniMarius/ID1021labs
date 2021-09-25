//README this implements princeton's mergesort. Used for comparison between different cut-off values
package lab2;

import java.util.Arrays;

public class Assignment5 {
    private static final int CUTOFF = 7;  // cutoff to insertion sort

    private static void merge(Comparable[] array, Comparable[] auxArray, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) auxArray[k] = array[j++];
            else if (j > hi) auxArray[k] = array[i++];
            else if (less(array[j], array[i])) auxArray[k] = array[j++];   // to ensure stability
            else auxArray[k] = array[i++];
        }
    }

    private static void sort(Comparable[] array, Comparable[] auxArray, int lo, int hi) {
        if (hi <= lo + CUTOFF) {
            insertionSort(auxArray, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(auxArray, array, lo, mid);
        sort(auxArray, array, mid + 1, hi);

        //copy from initial array
        if (!less(array[mid + 1], array[mid])) {
            for (int i = lo; i <= hi; i++) auxArray[i] = array[i];
            return;
        }
        merge(array, auxArray, lo, mid, hi);
    }

    /**
     * "Starting" method to sort the array in ascending order
     *
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
    }

    // sort array from a[lo] to a[hi] using insertion sort
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // is a[i] < a[j]?
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 120, 340, 50, 70, 10, 70, 20, 70, 40, 1240, 670};
        long startTime = System.currentTimeMillis();
        sort(arr);
        long endTime = System.currentTimeMillis();
        int totalTime = (int) (endTime - startTime);

        System.out.println(Arrays.toString(arr));
        System.out.println(totalTime);

    }
}
