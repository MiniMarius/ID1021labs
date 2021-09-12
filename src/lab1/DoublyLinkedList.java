package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class GenericFIFOQueue<Item> implements Iterable<Item> {
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
        Node<Item> newNode = new Node<>();
        newNode.item = item;
        Node<Item> last = head.previous;
        head.previous = newNode;
        newNode.next = head; //circular pointer from back to front
        last.next = newNode;
        newNode.previous = last;
        size++;
    }

    public Item dequeue() {
        // removes item from beginning of the list/queue
        if (isEmpty())
            return null;
        Item item = head.item;
        if (size() == 1) {
            head.next = null;
            head.previous = null;
            head = null;
        } else {
            Node<Item> temp = head;
            while (temp.previous != head) {
                temp = temp.previous;
            }
            Node<Item> first =
            head.previous = first;
            head = temp;

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
            if (item.equals(head.item)) {
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
        GenericFIFOQueue<String> q = new GenericFIFOQueue<>();
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
