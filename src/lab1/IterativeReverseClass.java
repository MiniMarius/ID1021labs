package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class IterativeReverseClass {

    public static void main(String[] args) {
        StackOfChars stack = new StackOfChars();
        StdOut.print("Enter characters to be reversed");
        while (!StdIn.isEmpty())
            stack.push(StdIn.readChar());
        while (!StdIn.isEmpty())
            StdOut.print(stack.pop());
    }

    private static class StackOfChars {
            private Node first;
            private int n;
        private static class Node {
            private char item;
            Node next;
        }

        public StackOfChars() {
            first = new Node();
            n = 0;
        }

        public void push(char item) {
            Node oldfirst = first;
            first.item = item;
            first.next = oldfirst;
        }

        public char pop() {
            Character item = first.item;
            first = first.next;
            return item;
        }

        boolean isEmpty() {
            return n == 0;
        }

        int size() {
            return n;
        }
    }


}
