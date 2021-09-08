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
        if (isEmpty()) {
            Node newNode = new Node();
            newNode.item = item;
            newNode.next = newNode;
            newNode.previous = newNode;
            first = newNode; //points to first element instead of null, thus making it circular
            last = newNode;
            size++;
            return;
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first; //circular pointer from back to front
        first.previous = newNode; //circular pointer from first to new node ie back of queue
        newNode.previous = last;
        last.next = newNode;
        last = newNode;

        size++;
    }

    public void enqueueFront(String item) {
        last = first.previous;
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        newNode.previous = last; //circular pointer from front to back
        last.next = newNode; //circular pointer from back to front
        first.previous = newNode;
        first = newNode;
        size++;
    }

    public String dequeueFront() {
        String item = first.item;
        first = first.next;
        first.previous = last;
        last.next = first;
        size--;
        return item;

    }

    public String dequeueBack() {
        String item = last.item;
        last = last.previous;
        last.next = first;
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
        private boolean completedLoop;

        public boolean hasNext() {
            return !completedLoop;
        }

        public String next() {
            if (current.next == first)
                completedLoop = true;

            String item = current.item;
            current = current.next;
            return item;
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
        q.enqueueFront("front");
        q.enqueueFront("newfront");
        q.dequeueFront();
        StdOut.print(q + "\n");
        q.dequeueBack();
        StdOut.print(q + "\n");
        StdOut.println("(" + q.size() + " left on queue)");
    }
}