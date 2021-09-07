package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class CircularLinkedList implements Iterable<String> {
    private Node first;
    private Node last;
    private int size;

    private static class Node {
        private String item;
        private Node next;
        private Node previous;
    }


    public void enqueueBack(String item) {
        if (first == null) {
            Node newNode = new Node();
            newNode.item = item;
            newNode.next = newNode;
            newNode.previous = newNode;
            first = newNode; //points to first element instead of null, thus making it circular
            return;
        }
        Node newNode = new Node();
        last = first.previous;
        newNode.item = item;
        newNode.next = first; //next of new node is the first since we're adding behind the first one
        first.previous = newNode; //previous of first one is this new node we're adding
        newNode.previous = last; // previous of this new node is the last one
        last.next = newNode; //next of old last is this new node

        size++;
    }

    public void enqueueFront(String item) {
        last = first.previous;

        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        newNode.previous = last;

        last.next = newNode;
        first.previous = newNode;
        first = newNode;
    }

    public String dequeueFront() {
        // removes item from beginning of the list/queue
        String item = first.item;
        first = first.next;
        size--;
        return item;

    }

    public String dequeueBack() {
        // removes item from beginning of the list/queue
        String item = last.item;
        last = last.previous;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;

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
        private Node current = last;

        public boolean hasNext() {
            return current != null;
        }

        public String next() {
            String item = current.item;
            current = current.next;
            return item + " ";
        }
    }

    public static void main(String[] args) {
        CircularLinkedList q = new CircularLinkedList();
        StdOut.print("Enter strings to be added to back of queue");
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            q.enqueueBack(item);
            StdOut.print(q + "\n");
        }

        StdOut.println("(" + q.size() + " left on queue)");
        StdOut.print(q + "\n");

    }
}