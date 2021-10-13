//README This program finds the shortest path between A and B passing through a node C if such a path exists
// Uses BFS-search from a starting index to a midpoint, followed by a BFS-search from midpoint to destination
// i.e A-C-B
package lab4;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment3 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("starting location: ");
            String start = scanner.next();
            System.out.println("midpoint: ");
            String midpoint = scanner.next();
            System.out.println("destination: ");
            String destination = scanner.next();

            SymbolGraph sg = new SymbolGraph("usa.txt", " ");
            BreadthFirst search = new BreadthFirst(sg.graph(), sg.index(start));
            BreadthFirst search2 = new BreadthFirst(sg.graph(), sg.index(midpoint));

            System.out.println((start + " to " + sg.name(sg.index(destination)) + ": "));
            if (search.hasPathTo(sg.index(midpoint)) && search2.hasPathTo(sg.index(destination))) {
                for (int x : search.pathTo(sg.index(midpoint))) {
                    if (x == sg.index(start)) System.out.print(sg.name(x));
                    else System.out.print("-" + sg.name(x));
                }
                for (int x : search2.pathTo(sg.index(destination))) {
                    if (x != sg.index(midpoint)) System.out.print("-" + sg.name(x));
                }
            } else System.out.println("there is no path");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
