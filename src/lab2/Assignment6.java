//README this implements princeton's quicksort. Used for comparison between median of three and first element of each sub-array
//as partitioning element
package lab2;

import edu.princeton.cs.algs4.StdRandom;

import java.io.*;
import java.util.Scanner;

public class Assignment6 {

    /**
     * return the index of the median element among a[i], a[j], and a[k]
     * @param a the inputted array
     * @return the median of a given array
     */
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
                (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
                (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

    /**
     * quicksortMOT the subarray from a[lo] to a[hi]
     */
    private static void sortMOT(Comparable[] a, int lo, int hi) {
        int n = hi - lo + 1;
        if (hi <= lo) return;
        int m = median3(a, lo, lo + n / 2, hi);
        exch(a, m, lo);
        int j = partition(a, lo, hi);
        sortMOT(a, lo, j - 1);
        sortMOT(a, j + 1, hi);
    }

    /**
     * //starting method of MOT quicksort
     *
     * @param a the array to be sorted
     */
    public static void sortMOT(Comparable[] a) {
        StdRandom.shuffle(a);
        sortMOT(a, 0, a.length - 1);
    }

    /**
     * quicksort the subarray from a[lo] to a[hi]
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    /**
     * starting method of normal quicksort
     */
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
     * and return the index j.
     *
     * @param a  the array to be sorted
     * @param lo first index of subarray
     * @param hi highest index of subarray
     * @return a correctly partitioned subarray
     */

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

    /**
     * exchange a[i] and a[j]
     */
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * Sorts an array and returns the timed performance
     *
     * @param a the array to be copied and then sorted
     * @return the timed sorting performance for quick sort
     */
    private static Long getQuickSortPerformance(Integer[] a) {
        Integer[] copyArr = a.clone();
        long startTime = System.currentTimeMillis();
        sort(copyArr);
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }

    /**
     * Sorts an array and returns the timed performance
     *
     * @param a the array to be copied and then sorted
     * @return the timed sorting performance for quick sort median of three
     */
    private static Long getQuickSortMOTPerformance(Integer[] a) {
        Integer[] copyArr = a.clone();
        long startTime = System.currentTimeMillis();
        sortMOT(copyArr);
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }


    /**
     * Calls methods responsible for timing the different sorting methods
     * and writes the data to csv file
     *
     * @param arr       the array to be sorted
     * @param inputSize the size of input array
     */
    private static void outputSortPerformance(Integer[] arr, Integer inputSize) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("A6quicksort" + ".csv", true))) {
            writer.write("quickMOT" + inputSize + ";");
            for (int i = 0; i < 10; i++) {
                writer.write(String.valueOf(getQuickSortMOTPerformance(arr)) + ';');
                System.out.println(i + 1 + " out of 10 quicksortMOTs done");
            }
            writer.println();
            writer.write("quicknormal" + inputSize + ";");
            for (int i = 0; i < 10; i++) {
                writer.write(String.valueOf(getQuickSortPerformance(arr)) + ';');
                System.out.println(i + 1 + " out of 10 quick sorts done");
            }
            writer.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a .txt file corresponding to the wanted size of random integers
     *
     * @param inputSize the size of input array
     * @return returns the parsed file containing an array with the amount of random integers equal to inputSize.
     */
    private static Integer[] getInputFileBySize(Integer inputSize) {
        try {
            String inputFile = "src/inputFiles/" + inputSize + "ints.txt";
            Scanner in = new Scanner(new FileReader(inputFile));
            Integer[] numbers = new Integer[in.nextInt()];
            for (int i = 0; i < numbers.length; i++) {
                if (in.hasNextInt()) {
                    numbers[i] = in.nextInt();
                }
            }
            return numbers;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input size");
        Integer inputSize = scanner.nextInt();
        Integer[] arr = getInputFileBySize(inputSize);
        outputSortPerformance(arr, inputSize);
    }
}
