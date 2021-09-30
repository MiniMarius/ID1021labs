package lab3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Assignment5 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/lab3/theText.txt"));
        int distinct = 0, words = 0;
        Map<String, Integer> st = new HashMap<>();

        // compute frequency counts
        while (!StdIn.isEmpty() && words < 1000) {
            String key = StdIn.readString();
            words++;
            if (st.containsKey(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
        }
        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keySet()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
}
