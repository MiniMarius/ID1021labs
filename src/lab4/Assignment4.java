//README this program uses DFS to find the directed path from x to y based on input. Parts of code based off of princeton.
// Prints a list of vertices
// traversed from x to y if there is a path
package lab4;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment4 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("starting location: ");
            String place = scanner.next();
            System.out.println("destination: ");
            String destination = scanner.next();
            DirectedSymbolGraph sg = new DirectedSymbolGraph("usa.txt", " ");
            DepthFirstSearch search = new DepthFirstSearch(sg.digraph(), sg.index(place));

            System.out.println((place + " to " + sg.name(sg.index(destination)) + ": "));
            if (search.hasPathTo(sg.index(destination))) {
                for (int x : search.pathTo(sg.index(destination))) {
                    if (x == sg.index(place)) System.out.print(sg.name(x));
                    else System.out.print("-" + sg.name(x));
                }
            } else System.out.println("path not found");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
