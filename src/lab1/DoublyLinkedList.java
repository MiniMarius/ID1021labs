package lab1;


import java.util.Iterator;
import java.util.Scanner;

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
        if (isEmpty())
            return null;
        Item item = head.item;
        if (size() == 1) {
            head = null;
        } else {
            Node<Item> last = head.previous;
            last.next = head.next;
            head.next.previous = last;
            head = head.next;
        }
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size() == 0;

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
            if(size() == 1 || item.equals(head.previous.item)) {
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
        DoublyLinkedList<String> q = new DoublyLinkedList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: enqueue, del: dequeue, s: size of queue, q: quit");
            String input = scanner.nextLine();
            switch (input) {
                case ("add"):
                    System.out.println("enter item to be added");
                    q.enqueue(scanner.next());
                    System.out.println(q + "\n");
                    break;
                case ("del"):
                    q.dequeue();
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
