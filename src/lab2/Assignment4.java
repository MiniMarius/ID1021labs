package lab2;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Quick;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
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

    public static void outputToCsv(Long millis, String filename) {
        try (PrintWriter writer = new PrintWriter(new File(filename + ".csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append(millis);
            sb.append(',');
            writer.write(sb.toString());
            System.out.println("file created");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Integer[] getInputFile(Integer inputSize) {
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
        Integer[] arr = getInputFile(10000);
        Integer insertionSortPerformance = getInsertionSortPerformance(arr);
        Integer mergeSortPerformance = getMergeSortPerformance(arr);
        Integer quickSortPerformance = getQuickSortPerformance(arr);
        System.out.println("average insertion time: " + insertionSortPerformance);
        System.out.println("average merge time: " + mergeSortPerformance);
        System.out.println("average quicksort time: " + quickSortPerformance);
    }
}
