package lab4;

import java.io.FileNotFoundException;

public class Assignment1 {

    public static void main(String[] args) {
        try {
            SymbolGraph sg = new SymbolGraph("usa.txt", " ");
            System.out.println(sg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
