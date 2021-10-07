package lab4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Assignment1 {
    private static String[] getInputFile() {
        try {
            Scanner in = new Scanner(new FileReader("src/lab4/usa.txt"));
            int numberOfStrings = 0;
            while(in.hasNext()) {
                in.next();
                numberOfStrings++;
            }
            System.out.println("number of strings: " + numberOfStrings);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        getInputFile();
    }
}
