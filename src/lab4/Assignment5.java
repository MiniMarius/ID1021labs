//README this program allows the user to input a starting vertex which is to be checked for if it
// is contained inside a cycle(loop). You can change filename variable to chosen file containing a graph/cycle.
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
            DirectedSymbolGraph sg = new DirectedSymbolGraph("a5.txt", " ");
            DirectedCycle search = new DirectedCycle(sg.digraph(), sg.index(place));
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
