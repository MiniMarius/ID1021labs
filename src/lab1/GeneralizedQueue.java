package lab1;


import java.util.Iterator;
import java.util.Scanner;

public class GeneralizedQueue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    private Boolean isEmpty() {
        return first == null;
    }

    private void insert(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        size++;
    }

    private Item delete(int index) {
        if (isEmpty())
            return null;
        Node<Item> current = first;
        for (int i = size; i - 1 > index; i--) {
            current = current.next;
        }
        if (current.equals(first)) first = first.next;
        else if (current.next.equals(last)) current.next = null;
        else current.next = current.next.next;
        size--;
        return current.item;
    }

    private Integer size() {
        return size;
    }

    public String toString() {
        if (isEmpty()) {
            return "Queue is empty";
        }
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            if (size() == 1 || item.equals(last.item)) {
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
        private Node<Item> current = first;

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
                    System.out.println("current item:" + q.delete(index));
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
