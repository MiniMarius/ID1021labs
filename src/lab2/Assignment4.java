//README this uses princeton's implementations of insertion, merge and quicksort
// Used for speed comparison between the different sorting algorithms
package lab2;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Quick;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Assignment4 {

    public static Integer getInsertionSortPerformance(Integer[] a) {
        Integer[] copyArr = a.clone();
        long startTime = System.currentTimeMillis();
        Insertion.sort(copyArr);
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }

    public static Integer getMergeSortPerformance(Integer[] a) {
        Integer[] copyArr = a.clone();
        long startTime = System.currentTimeMillis();
        Merge.sort(copyArr);
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }


    public static Integer getQuickSortPerformance(Integer[] a) {
        Integer[] copyArr = a.clone();
        long startTime = System.currentTimeMillis();
        Quick.sort(copyArr);
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }

    public static void outputSortPerformance(Integer[] arr, Integer inputSize) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("sortperformance" + ".csv", true))) {
            if (inputSize <= 100000) {
                writer.write("insertion" + inputSize + ";");
                for (int i = 0; i < 10; i++) {
                    writer.write(String.valueOf(getInsertionSortPerformance(arr)) + ';');
                }
            }
            writer.println();
            writer.write("merge" + inputSize + ";");
            for (int i = 0; i < 10; i++) {
                writer.write(String.valueOf(getMergeSortPerformance(arr)) + ';');
            }
            writer.println();
            writer.write("quick" + inputSize + ";");
                for (int i = 0; i < 10; i++) {
                    writer.write(String.valueOf(getQuickSortPerformance(arr)) + ';');
                }
            writer.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static Integer[] getInputFileBySize(Integer inputSize) {
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
