package lab1;


import java.util.Iterator;
import java.util.Scanner;

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
        Node last = getLast();
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
        Node last = getLast();
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
            Node last = getLast();
            head = head.next;
            last.next = head;
        }
        size--;
        return item;
    }

    public String dequeueBack() {
        if (isEmpty())
            return null;
        String item = "";
        if (size() == 1) {
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

    private Node getLast() {
        Node current = head;
        while (current.next.next != head) {
            current = current.next;
        }
        return current.next;

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
            if (size() == 1 || item.equals(getLast().item)) {
                s.append("[");
                s.append(item);
                s.append("]");
            } else {
                s.append("[");
                s.append(item);
                s.append("]");
                s.append(", ");

            }
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
        SinglyLinkedList q = new SinglyLinkedList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("addf: add to front, addb: add to back delf: dequeue front, delb: dequeue back s: size of queue, q: quit");
            String input = scanner.nextLine();
            switch (input) {
                case ("addf"):
                    System.out.println("enter item to be added to front of queue");
                    q.enqueueFront(scanner.next());
                    System.out.println(q + "\n");
                    break;
                case ("addb"):
                    System.out.println("enter item to be added to back of queue");
                    q.enqueueBack(scanner.next());
                    System.out.println(q + "\n");
                    break;
                case ("delf"):
                    q.dequeueFront();
                    System.out.println(q + "\n");
                    break;
                case ("delb"):
                    q.dequeueBack();
                    System.out.println(q + "\n");
                    break;
                case ("q"):
                    System.exit(0);
                    break;
                case ("s"):
                    System.out.println(q.size());
                    break;
            }
        }
    }
}