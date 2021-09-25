//README this implements princeton's mergesort. Used for comparison between different cut-off values
package lab2;


import java.io.*;
import java.util.Scanner;

public class Assignment5 {
    private static int CUTOFF;  // cutoff to insertion sort

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

        //copy of initial array
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

    private static Long getMergeSortPerformance(Integer[] a) {
        Integer[] copyArr = a.clone();
        long startTime = System.nanoTime();
        sort(copyArr);
        long endTime = System.nanoTime();
        return (endTime - startTime);
    }

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

    private static void outputSortPerformance(Integer[] arr, Integer inputSize) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("mergecutoff" + ".csv", true))) {
            for (int CUTOFF = 0; CUTOFF <= 30; CUTOFF++) {
                writer.write("Cutoff" + CUTOFF + ";");
                for (int i = 0; i < 10; i++) {
                    writer.write(String.valueOf(getMergeSortPerformance(arr)) + ';');
                    System.out.println(i + 1 + " out of 10 loops for cutoff value: " + CUTOFF);
                }
                writer.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input size");
        Integer inputSize = scanner.nextInt();
        Integer[] arr = getInputFileBySize(inputSize);
        outputSortPerformance(arr, inputSize);
    }
}
