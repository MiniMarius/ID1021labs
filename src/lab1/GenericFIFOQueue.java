package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class GenericFIFOQueue implements Iterable<String> {
    private Node first;
    private Node last;
    private int size;

    private static class Node {
        private String item;
        private Node next;
    }


    public void enqueue(String item) {
        // adds item to end of list/queue
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        size++;
    }

    public String dequeue() {
        // removes item from beginning of the list/queue
        String item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        size--;
        return item;
    }

    public boolean isEmpty() {
        return first == null;

    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (String item : this) {
            s.append("[");
            s.append(item);
            s.append("]");
            s.append(", ");
        }
        return s.toString();
    }

    public Iterator<String> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<String> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public String next() {
            String item = current.item;
            current = current.next;
            return item;
        }
    }


    public static void main(String[] args) {
        GenericFIFOQueue q = new GenericFIFOQueue();
        StdOut.print("Enter strings to be added to back of queue");
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            q.enqueue(item);
            StdOut.print(q + "\n");
        }
        StdOut.println("(" + q.size() + " left on queue)");
        StdOut.print(q + "\n");

        while (!q.isEmpty()) {
            q.dequeue();
            StdOut.print(q + "\n");
        }
    }
}
