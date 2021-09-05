package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class IterativeReverseClass {

    public static void main(String[] args) {
        StackClass stack = new StackClass();
        StdOut.print("Enter characters to be reversed");
        while (!StdIn.isEmpty())
            stack.push(StdIn.readChar());
        while (!StdIn.isEmpty())
            StdOut.print(stack.pop());
    }

    private static class StackClass {
            private char[] array;
            private int items;

        public StackClass() {
            array = new char[8];
            items = 0;
        }

        public void push(char c) {
            array[items] = c;
            items++;
        }

        public char pop() {
            items--;
            return array[items];
        }

        boolean isEmpty() {
            return items == 0;
        }

        int size() {
            return items;
        }
    }
}
