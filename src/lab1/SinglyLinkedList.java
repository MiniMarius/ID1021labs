package lab1;


import java.util.Iterator;
import java.util.Scanner;

public class SinglyLinkedList<Item> implements Iterable<Item> {
    private Node<Item> head;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }


    public void enqueueBack(Item item) {
        if (isEmpty()) {
            Node<Item> newNode = new Node<Item>();
            newNode.item = item;
            newNode.next = newNode; //points to itself instead of null, thus making it circular
            head = newNode;
            size++;
            return;
        }
        Node<Item> newNode = new Node<>();
        newNode.item = item;
        newNode.next = head; //circular pointer from back to front
        Node<Item> last = getLast();
        last.next = newNode;
        size++;
    }

    public void enqueueFront(Item item) {
        if (isEmpty()) {
            Node<Item> newNode = new Node<>();
            newNode.item = item;
            newNode.next = newNode; //points to itself instead of null, thus making it circular
            head = newNode;
            size++;
            return;
        }
        Node<Item> newNode = new Node<>();
        newNode.item = item;
        newNode.next = head;
        Node<Item> last = getLast();
        head = newNode;
        last.next = head;
        size++;
    }

    public Item dequeueFront() {
        if (isEmpty())
            return null;
        Item item = head.item;
        if (size() == 1) {
            head = null;
        } else {
            Node<Item> last = getLast();
            head = head.next;
            last.next = head;
        }
        size--;
        return item;
    }

    public Item dequeueBack() {
        if (isEmpty())
            return null;
        Item item = null;
        if (size() == 1) {
            head = null;
        } else {
            Node<Item> last = head;
            while (last.next.next != head) {
                last = last.next;
            }
            item = last.next.item;
            last.next = head;
        }
        size--;
        return item;
    }

    private Node<Item> getLast() {
        Node<Item> current = head;
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
        for (Item item : this) {
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
        SinglyLinkedList<String> q = new SinglyLinkedList<>();
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