package lab1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class GeneralizedQueue<Item> implements Iterable<Item> {
    private Node last;
    private Node first;
    private int N;

    private class Node {
        private Node next;
        private Item item;
    }

    private Boolean isEmpty() {
        return first == null;
    }

    private void insert(Item item) {
        Node node = new Node();
        node.item = item;
        if (last == null) {
            first = node;
            last = node;
            N++;
            return;
        }
        last.next = node;
        last = node;
        N++;
    }

    private Item delete(int index) {
        Node current = first;
        Node previous = null;
        int count;
        for (count = 1; count < index; count++){
            previous = current;
            current = current.next;
        }

        return current.item;
    }

    public String toString() {
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
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        GeneralizedQueue<Character> q = new GeneralizedQueue<Character>();
        q.insert('A');
        StdOut.print(q + "\n");
        q.insert('B');
        StdOut.print(q + "\n");
        q.insert('C');
        StdOut.print(q + "\n");
        StdOut.print(q.delete(3));
    }
}
