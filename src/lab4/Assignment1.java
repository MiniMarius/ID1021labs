package lab4;


import java.io.FileNotFoundException;

public class Assignment1 {

    public static void main(String[] args) {
        try {
            SymbolGraph sg = new SymbolGraph("usa.txt", " ");
            DepthFirstSearchClass df = new DepthFirstSearchClass(sg.graph(), 0);
            for (int v = 0; v < sg.graph().V(); v++) {
                if (df.marked(v))
                    System.out.println(sg.name(v) + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
