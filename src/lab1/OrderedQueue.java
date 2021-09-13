package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class OrderedQueue implements Iterable<Integer> {
    private Node head;
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
            newNode.next = newNode; //points to head element instead of null, thus making it circular
            newNode.previous = newNode; //points to head element instead of null, thus making it circular
            head = newNode;
            size++;
            return;
        }
        Node newLast = new Node();
        newLast.item = item;
        Node last = head.previous;
        head.previous = newLast;
        newLast.next = head; //circular pointer from back to front
        last.next = newLast;
        newLast.previous = last;
        size++;
    }

    public Integer dequeue() {
        Integer item = head.item;
        if (size() == 1) {
            head = null;
        } else {
            Node last = head.previous;
            last.previous.next = head;
            head.previous = last.previous;
        }
        size--;
        return item;
    }

    public boolean isEmpty() {
        return head == null;

    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Integer item : this) {
                s.append("[");
                s.append(item);
                s.append("]");
                s.append(", ");
        }
        return s.toString();
    }

    public Iterator<Integer> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Integer> {
        private Node current = head;
        private boolean completedLoop;

        public boolean hasNext() {
            return !completedLoop;
        }

        public Integer next() {
            if (current.next == head)
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