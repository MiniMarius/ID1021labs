package lab4;

import edu.princeton.cs.algs4.StdOut;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment5 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("starting location: ");
            String place = scanner.next();
            System.out.println("destination: ");
            String destination = scanner.next();
            DirectedSymbolGraph sg = new DirectedSymbolGraph("usa.txt", " ");
            DirectedCycle search = new DirectedCycle(sg.digraph());
            System.out.println((place + " to " + sg.name(sg.index(destination)) + ": "));
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
