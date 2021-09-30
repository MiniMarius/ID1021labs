//README this program shows how evenly the hashcodes are distributed
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
        Map<String, Integer> map = new HashMap<>();

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            words++;
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
                distinct++;
            }
        }
        // find a key with the highest frequency count
        String max = "";
        map.put(max, 0);
        for (String word : map.keySet()) {
            if (map.get(word) > map.get(max))
                max = word;
        }

        for (String word : map.keySet()) {
            System.out.println("Hash code is: " + word.hashCode());
        }

        StdOut.println(max + " " + map.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
}
