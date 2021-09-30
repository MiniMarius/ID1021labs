package lab3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.*;

public class Assignment3 {



    private static void outputSTperformance(Long timedPerformance) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("a2performance" + ".csv", true))) {
            writer.write(String.valueOf(timedPerformance + ';'));
            writer.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/lab3/theText.txt"));
        long startTime = System.currentTimeMillis();
        int distinct = 0, words = 0;
        Assignment2<String, Integer> st = new Assignment2<>();

        // compute frequency counts
        while (!StdIn.isEmpty() && words < 1000) {
            String key = StdIn.readString();
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        long endTime = System.currentTimeMillis();
        outputSTperformance(endTime - startTime);
    }
}
