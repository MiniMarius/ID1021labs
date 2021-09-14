package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class RecursionReverse {

    public static void reverse() {
        char c;
        if(!StdIn.hasNextChar())
            return;
        c = StdIn.readChar(); //CTRL-D if stuck in console to stop stdin from reading chars
        reverse();
        StdOut.print("["+ c + "], " );
    }
    public static void main(String[] args) {
        StdOut.print("Enter characters to reversed\n");
        reverse();
    }
}
