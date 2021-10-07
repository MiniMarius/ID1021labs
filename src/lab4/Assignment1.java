package lab4;

import java.io.FileNotFoundException;

public class Assignment1 {

    public static void main(String[] args) {
        try {
            SymbolGraph sg = new SymbolGraph("usa.txt", " ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
