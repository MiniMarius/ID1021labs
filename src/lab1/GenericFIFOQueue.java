package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class GenericFIFOQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Item item;
        private Node next;
    }


    public void enqueue(Item item) {
        if (isEmpty()) {
            Node newNode = new Node();
            newNode.item = item;
            newNode.next = newNode;
            first = newNode; //points to first element instead of null, thus making it circular
            last = newNode;
            size++;
            return;
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first; //circular pointer from back to front
        last.next = newNode;
        last = newNode;
        size++;
    }

    public Item dequeue() {
        // removes item from beginning of the list/queue
        if (isEmpty())
            return null;
        Item item = first.item;
        first = first.next;
        if (size() == 1) {
            first.next = null;
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
        for (Item item : this) {
            if (item.equals(last.item)) {
                s.append("[");
                s.append(item);
                s.append("]");
            }
            else {
                s.append("[");
                s.append(item);
                s.append("]");
                s.append(", ");
            }
        }
        return s.toString();
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        private boolean completedLoop = false;

        public boolean hasNext() {
            return current != null && !completedLoop;
        }

        public Item next() {
            if (current.next == first)
                completedLoop = true;

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        GenericFIFOQueue<String> q = new GenericFIFOQueue<String>();
        StdOut.print("Enter strings to be added to back of queue");
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            q.enqueue(item);
            StdOut.print(q + "\n");
        }
        StdOut.println("(" + q.size() + " left on queue)");
        StdOut.print(q + "\n");

        while (!(q.isEmpty())) {
            q.dequeue();
            StdOut.print(q + "\n");
        }
    }
}
