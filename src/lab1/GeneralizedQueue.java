package lab1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class GeneralizedQueue<Item> implements Iterable<Item> {
    private Node<Item> head;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    private Boolean isEmpty() {
        return head == null;
    }

    private void insert(Item item) {
        if (isEmpty()) {
            Node<Item> newNode = new Node<>();
            newNode.item = item;
            newNode.next = newNode; //points to head element instead of null, thus making it circular
            newNode.previous = newNode; //points to head element instead of null, thus making it circular
            head = newNode;
            size++;
            return;
        }
        Node<Item> newLast = new Node<>();
        newLast.item = item;
        Node<Item> last = head.previous;
        head.previous = newLast;
        newLast.next = head; //circular pointer from back to front
        last.next = newLast;
        newLast.previous = last;
        size++;
    }

    private Item delete(int index) {
        Node<Item> current = head;
        for(int i = size; i > index; i--) {
            current = current.next;
        }
        if (index == size) {
            Node last = current.previous;
            head = current.next;
            last.next = head;
        }
        else {
            current.previous.next = current.next;
            current.next.previous.previous = current.previous;
        }
        return current.item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
                s.append("[");
                s.append(item);
                s.append("]");
                s.append(", ");
        }
        return s.toString();
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node<Item> current = head;
        private boolean completedLoop;

        public boolean hasNext() {
            return !completedLoop;
        }

        public Item next() {
            if (current.next == head)
                completedLoop = true;

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        GeneralizedQueue<String> q = new GeneralizedQueue<>();
        q.insert("a");
        StdOut.print(q + "\n");
        q.insert("b");
        StdOut.print(q + "\n");
        q.insert("c");
        StdOut.print(q + "\n");
        q.delete(3);
        StdOut.print(q + "\n");
    }
}
