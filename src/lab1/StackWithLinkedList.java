package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class StackWithLinkedList implements Iterable<Character> {
    private Node first;
    private int n;


    private static class Node {
        private Character item;
        private Node next;
    }

    public StackWithLinkedList() {
        first = null;
        n = 0;
    }

    public void push(Character item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Character pop() {
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


    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Character item : this) {
            s.append("[");
            s.append(item);
            s.append("]");
            s.append(", ");
        }
        return s.toString();
    }


    public Iterator<Character> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Character> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Character next() {
            Character item = current.item;
            current = current.next;
            return item;
        }
    }



    public static void main(String[] args) {
        StackWithLinkedList stack = new StackWithLinkedList();
        StdOut.print("Enter characters to be reversed\n");
        while (!StdIn.isEmpty()) {
            Character item = StdIn.readChar();
            stack.push(item);
        }
        StdOut.println("(" + stack.size() + " left on stack)");
        StdOut.print(stack);

        while (!stack.isEmpty()) {
            StdOut.print(stack.pop());
        }
    }
}
