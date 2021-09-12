package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class DoublyLinkedList<Item> implements Iterable<Item> {
    private Node<Item> head;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    public void enqueue(Item item) {
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

    public Item dequeue() {
        Item item = head.item;
        if (size() == 1) {
            head = null;
        } else {
            Node<Item> last = head.previous;
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
        if (isEmpty()) {
            return "Queue is empty";
        }
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
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = head;
        private boolean completedLoop = false;

        public boolean hasNext() {
            return current != null && !completedLoop;
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
        DoublyLinkedList<String> q = new DoublyLinkedList<>();
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
