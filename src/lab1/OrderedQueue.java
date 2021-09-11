package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class OrderedQueue implements Iterable<Integer> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Integer item;
        private Node next;
        private Node previous;
    }


    public void enqueue(Integer item) {
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

    public Integer dequeue() {
        Integer item = first.item;
        first = first.next;
        first.previous = last;
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
        for (Integer item : this) {
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

    public Iterator<Integer> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Integer> {
        private Node current = first;
        private boolean completedLoop;

        public boolean hasNext() {
            return !completedLoop;
        }

        public Integer next() {
            if (current.next == first)
                completedLoop = true;

            Integer item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        OrderedQueue q = new OrderedQueue();
        StdOut.print("Enter strings to be added to back of queue");
        while (!StdIn.isEmpty()) {
            Integer item = StdIn.readInt();
            q.enqueue(item);
            StdOut.print(q + "\n");
        }
        StdOut.print("dequeue one item");
        q.dequeue();
        StdOut.print(q + "\n");
        StdOut.print("dequeue another item");
        q.dequeue();
        StdOut.print(q + "\n");
        StdOut.println("(" + q.size() + " left on queue)");
    }
}