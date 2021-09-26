//README this uses princeton's implementations of insertion, merge and quicksort
// Used for speed comparison between the different sorting algorithms
// every sorting algorithm is run 10 times for more trustworthy results
// if run outside project folder with own input files, inputFile String must be changed
// to whatever path you use
package lab2;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Quick;

import java.io.*;
import java.util.Scanner;

public class Assignment4 {

    private static Long getInsertionSortPerformance(Integer[] a) {
        Integer[] copyArr = a.clone();
        long startTime = System.nanoTime();
        Insertion.sort(copyArr);
        long endTime = System.nanoTime();
        return (endTime - startTime);
    }

    private static Long getMergeSortPerformance(Integer[] a) {
        Integer[] copyArr = a.clone();
        long startTime = System.nanoTime();
        Merge.sort(copyArr);
        long endTime = System.nanoTime();
        return (endTime - startTime);
    }


    private static Long getQuickSortPerformance(Integer[] a) {
        Integer[] copyArr = a.clone();
        long startTime = System.nanoTime();
        Quick.sort(copyArr);
        long endTime = System.nanoTime();
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
        try (PrintWriter writer = new PrintWriter(new FileWriter("sortperformance" + ".csv", true))) {
            if (inputSize <= 100000) {
                writer.write("insertion" + inputSize + ";");
                for (int i = 0; i < 10; i++) {
                    writer.write(String.valueOf(getInsertionSortPerformance(arr)) + ';');
                    System.out.println(i + 1 + " out of 10 insertion sorts done");
                }
            }
            writer.println();
            writer.write("merge" + inputSize + ";");
            for (int i = 0; i < 10; i++) {
                writer.write(String.valueOf(getMergeSortPerformance(arr)) + ';');
                System.out.println(i + 1 + " out of 10 merge sorts done");
            }
            writer.println();
            writer.write("quick" + inputSize + ";");
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
