package lab1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class GeneralizedQueue implements Iterable<String> {
    private Node head;
    private int size;

    private class Node {
        private Node next;
        private String item;
    }

    private Boolean isEmpty() {
        return head == null;
    }

    private void insert(String item) {
        if (isEmpty()) {
            Node newNode = new Node();
            newNode.item = item;
            newNode.next = newNode; //points to itself instead of null, thus making it circular
            head = newNode;
            size++;
            return;
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = head; //circular pointer from back to front
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        last.next = newNode;
        size++;
    }

    private String delete(int index) {
        Node current = head;
        for(int i = 1; i <= index; i++) {

        }

        return "kebab";
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
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<String> {
        private Node current = head;
        private boolean completedLoop;

        public boolean hasNext() {
            return !completedLoop;
        }

        public String next() {
            if (current.next == head)
                completedLoop = true;

            String item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        GeneralizedQueue q = new GeneralizedQueue();
        q.insert("a");
        StdOut.print(q + "\n");
        q.insert("b");
        StdOut.print(q + "\n");
        q.insert("c");
        StdOut.print(q + "\n");
        StdOut.print(q.delete(2));
    }
}
