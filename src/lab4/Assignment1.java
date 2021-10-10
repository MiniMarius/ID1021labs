package lab4;


import java.io.FileNotFoundException;

public class Assignment1 {

    public static void main(String[] args) {
        try {
            SymbolGraph sg = new SymbolGraph("usa.txt", " ");
            String place = "TN";
            DepthFirstSearchClass search = new DepthFirstSearchClass(sg.graph(), sg.index(place));
            for (int v = 0; v < sg.graph().V(); v++) {
                System.out.println((place + " to " + sg.name(v) + ": "));
                if (search.hasPathTo(v))
                    for (int x : search.pathTo(v))
                        if (x == sg.index(place)) System.out.print(x);
                        else System.out.print("-" + x);
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
