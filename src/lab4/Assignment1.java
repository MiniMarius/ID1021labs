package lab4;


import java.io.FileNotFoundException;

public class Assignment1 {

    public static void main(String[] args) {
        try {
            SymbolGraph sg = new SymbolGraph("usa.txt", " ");
            String place = "FL";
            //DepthFirstSearchClass df = new DepthFirstSearchClass(sg.graph(), sg.index(place));
            for (int w : sg.graph().adj(sg.index(place)))
                System.out.println(("   " + sg.name(w)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
