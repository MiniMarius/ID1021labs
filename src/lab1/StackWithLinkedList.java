package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackWithLinkedList {
    private Node first;
    private int n;

    private static class Node {
        private char item;
        private Node next;
    }

    public StackWithLinkedList() {
        first = null;
        n = 0;
    }

    public void push(char item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public char pop() {
        Character item = first.item;
        first = first.next;
        n--;
        return item;
    }

    boolean isEmpty() {
        return n == 0;
    }

    int size() {
        return n;
    }

    public static void main(String[] args) {
        StackWithLinkedList stack = new StackWithLinkedList();
        StdOut.print("Enter characters to be reversed\n");
        while (!StdIn.isEmpty()) {
            char item = StdIn.readChar();
            stack.push(item);
        }
        StdOut.println("(" + stack.size() + " left on stack)");
        while (!stack.isEmpty()) {
            StdOut.print(stack.pop());
        }
    }
}
