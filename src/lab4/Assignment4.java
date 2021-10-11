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
            DepthFirstSearchClass search = new DepthFirstSearchClass(sg.digraph(), sg.index(place));

            System.out.println((place + " to " + sg.name(sg.index(destination)) + ": "));
            if (search.hasPathTo(sg.index(destination)))
                for (int x : search.pathTo(sg.index(destination)))
                    if (x == sg.index(place)) System.out.print(sg.name(x));
                    else System.out.print("-" + sg.name(x));
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
