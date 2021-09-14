package lab1;


import java.util.Iterator;
import java.util.Scanner;

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
        for (int i = size; i > index; i--) {
            current = current.next;
        }
        if (index == size) {
            Node last = current.previous;
            head = current.next;
            last.next = head;
        } else {
            current.previous.next = current.next;
            current.next.previous.previous = current.previous;
        }
        return current.item;
    }

    private Integer size() {
        return size;
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
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: enqueue, del: dequeue, s: size of queue, q: quit");
            String input = scanner.nextLine();
            switch (input) {
                case ("add"):
                    System.out.println("enter item to be added");
                    q.insert(scanner.next());
                    System.out.println(q + "\n");
                    break;
                case ("del"):
                    System.out.println("enter index to be removed from queue");
                    int index = scanner.nextInt();
                    q.delete(index);
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
