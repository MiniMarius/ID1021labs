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

    public static Long getInsertionSortPerformance(Integer[] a) {
        long startTime = System.currentTimeMillis();
        Insertion.sort(a);
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }

    public static Long getMergeSortPerformance(Integer[] a) {
        long startTime = System.currentTimeMillis();
        System.currentTimeMillis();
        Merge.sort(a);
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }


    public static Long getQuickSortPerformance(Integer[] a) {
        long startTime = System.currentTimeMillis();
        System.currentTimeMillis();
        Quick.sort(a);
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }

    public static void outputToCsv(Long millis, String filename) {
        try (PrintWriter writer = new PrintWriter(new File(filename + ".csv"))) {

            StringBuilder sb = new StringBuilder();
            sb.append(millis);
            sb.append(',');
            writer.write(sb.toString());
            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] fileNameList = {"src/inputFiles/1000ints.txt", "src/inputFiles/10000ints.txt", "src/inputFiles/1000000ints.txt", "src/inputFiles/10000000ints.txt"};
        for (String file : fileNameList) {
            Scanner in = new Scanner(new FileReader(file));
            Integer[] numbers = new Integer[in.nextInt()];
            for (int i = 0; i < numbers.length; i++) {
                if (in.hasNextInt()) {
                    numbers[i] = in.nextInt();
                }
            }
            outputToCsv(getInsertionSortPerformance(numbers), "insertion");
            outputToCsv(getQuickSortPerformance(numbers), "quicksort");
            outputToCsv(getMergeSortPerformance(numbers), "mergesort");
        }
    }
}
