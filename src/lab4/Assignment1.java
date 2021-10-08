package lab4;

import edu.princeton.cs.algs4.StdOut;

import java.io.FileNotFoundException;

public class Assignment1 {

    public static void main(String[] args) {
        try {
            SymbolGraph sg = new SymbolGraph("usa.txt", " ");
            DepthFirstSearchClass df = new DepthFirstSearchClass(sg.graph(), 0);
            for (int v = 0; v < sg.graph().V(); v++) {
                if (df.marked(v))
                    System.out.println(v + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
