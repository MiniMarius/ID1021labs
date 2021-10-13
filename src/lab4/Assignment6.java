//README this program allows the user to find the shortest path between two nodes passing through
// a third node. Outputs an ordered list of nodes traversed from A to B.
package lab4;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment6 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("starting location: ");
            int start = scanner.nextInt();
            System.out.println("midpoint: ");
            int midpoint = scanner.nextInt();
            System.out.println("destination: ");
            int destination = scanner.nextInt();

            DirectedGraph sg = new DirectedGraph("NYC.txt");
            BreadthFirst search = new BreadthFirst(sg, start);
            BreadthFirst search2 = new BreadthFirst(sg,midpoint);

            System.out.println((start + " to " + destination + ": "));
            if (search.hasPathTo(midpoint) && search2.hasPathTo(destination)) {
                for (int x : search.pathTo(midpoint)) {
                    if (x ==start) System.out.print(x);
                    else System.out.print("-" + x);
                }
                for (int x : search2.pathTo(destination)) {
                    if (x !=midpoint) System.out.print("-" + x);
                }
            } else System.out.println("there is no path");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
