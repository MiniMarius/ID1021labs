package lab2;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Quick;

import java.io.*;
import java.util.Scanner;

public class Assignment4 {

    public static Integer getInsertionSortPerformance(Integer[] a) {
        long startTime = System.currentTimeMillis();
        Insertion.sort(a);
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }

    public static Integer getMergeSortPerformance(Integer[] a) {
        long startTime = System.currentTimeMillis();
        Merge.sort(a);
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }


    public static Integer getQuickSortPerformance(Integer[] a) {
        long startTime = System.currentTimeMillis();
        Quick.sort(a);
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }

    public static void outputToCsv(Integer performance, String sortName, Integer inputSize) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(sortName + inputSize + ".csv", true))) {
            writer.write(String.valueOf(performance) + ',');
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

        for(int i = 0; i < 10; i++) {
            if (inputSize <= 100000) {
                Integer insertionSortPerformance = getInsertionSortPerformance(arr);
                outputToCsv(insertionSortPerformance, "insertion", inputSize);
            }

            Integer mergeSortPerformance = getMergeSortPerformance(arr);
            outputToCsv(mergeSortPerformance, "merge", inputSize);

            Integer quickSortPerformance = getQuickSortPerformance(arr);
            outputToCsv(quickSortPerformance, "quicksort", inputSize);
            System.out.println(i + " out of 10 loops finished");
        }
    }
}
