package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class SinglyLinkedList implements Iterable<String> {
    private Node head;
    private int size;

    private class Node {
        private String item;
        private Node next;
    }


    public void enqueueBack(String item) {
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

    public void enqueueFront(String item) {
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
        newNode.next = head;
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        head = newNode;
        last.next = head;
        size++;
    }

    public String dequeueFront() {
        if (isEmpty())
            return null;
        String item = head.item;
        if (size() == 1) {
            head = null;
        } else {
            Node last = head;
            while (last.next != head) {
                last = last.next;
            }
            head = head.next;
            last.next = head;
        }
        size--;
        return item;
    }

    public String dequeueBack() {
        String item = "";
        if (isEmpty())
            return "underflow";
        if (size() == 1) {
            head.next = null;
            head = null;
        } else {
            Node last = head;
            while (last.next.next != head) {
                last = last.next;
            }
            item = last.next.item;
            last.next = head;
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
        SinglyLinkedList q = new SinglyLinkedList();
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