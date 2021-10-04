//README this program shows how evenly the hashcodes are distributed by modular hashing and output to csv file for
//plotting in excel. Uses parts of princeton's frequency counter code.
package lab3;

import edu.princeton.cs.algs4.StdIn;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Assignment5 {


    private static void outputSTperformance(String hashcode) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("a5distribution" + ".csv", true))) {
            writer.write(hashcode);
            writer.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/lab3/polishedText.txt"));
        Map<String, Integer> map = new HashMap<>();

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString().toLowerCase();
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        // This code masks off the sign bit to turn the 32-bit number into a 31-bit nonnegative
        //integer. Disperses the keys between 0 and 96
        for (String word : map.keySet()) {
            System.out.println("Hash code is: " + ((word.hashCode() & 0x7fffffff) % 97));
            outputSTperformance(String.valueOf((word.hashCode() & 0x7fffffff) % 97));
        }
    }
}
