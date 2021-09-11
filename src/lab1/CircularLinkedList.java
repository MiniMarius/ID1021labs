package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class CircularLinkedList implements Iterable<String> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
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
        if (isEmpty())
            return null;
        String item = first.item;
        first = first.next;
        if (size() == 1) {
            first.next = null;
            first.previous = null;
            last.next = null;
            first = null;
            last = null;
        } else {
            first.previous = last;
            last.next = first;
        }
        size--;
        return item;
    }

    public String dequeueBack() {
        if (isEmpty())
            return null;
        String item = last.item;
        last = last.previous;
        if (size() == 1) {
            first.next = null;
            first.previous = null;
            last.next = null;
            first = null;
            last = null;
        } else
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
        if (isEmpty()) {
            return "Queue is empty";
        }
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

        StdOut.print("adding \"first\" to first of queue \n");
        q.enqueueFront("first");
        StdOut.print(q + "\n");

        StdOut.print("dequeuing 1 from front \n");
        q.dequeueFront();
        StdOut.print(q + "\n");
        StdOut.println("(" + q.size() + " left on queue)");
        StdOut.print("dequeuing whole circular list \n");
        while (!q.isEmpty()) {
            q.dequeueBack();
            StdOut.print(q + "\n");
        }
    }
}