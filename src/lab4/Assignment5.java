package lab4;

import edu.princeton.cs.algs4.StdOut;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment5 {
    public static void main(String[] args) {
        try {
            DirectedSymbolGraph sg = new DirectedSymbolGraph("usa.txt", " ");
            DirectedCycle search = new DirectedCycle(sg.digraph());
            if (search.hasCycle()) {
                StdOut.print("Directed cycle: ");
                for (int v : search.cycle()) {
                    StdOut.print(sg.name(v) + " ");
                }
                StdOut.println();
            }
            else {
                StdOut.println("No directed cycle");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
