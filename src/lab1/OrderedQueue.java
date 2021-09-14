package lab1;

import java.util.Iterator;
import java.util.Scanner;

public class OrderedQueue implements Iterable<Integer> {
    private Node head;
    private int size;

    private class Node {
        private Integer item;
        private Node next;
        private Node previous;
    }


    public void enqueue(Integer item) {
        if (isEmpty()) {
            Node newNode = new Node();
            newNode.item = item;
            newNode.next = newNode; //points to head element instead of null, thus making it circular
            newNode.previous = newNode; //points to head element instead of null, thus making it circular
            head = newNode;
            size++;
            return;
        }

        Node newNode = new Node();
        newNode.item = item;
        Node last = head.previous;
        if (item > last.item) {
            head.previous = newNode;
            newNode.next = head; //circular pointer from back to front
            last.next = newNode;
            newNode.previous = last;
        }
        else if (item < last.item && size() == 1) {
            newNode.next = last;
            newNode.previous = last;
            last.previous = newNode;
            head = newNode;
            last.next = newNode;
        }
        else if (item > last.previous.item) {
            newNode.next = last;
            last.previous.next = newNode;
            newNode.previous = last.previous;
        } else {
            Node current = head;
            while (current.next != head) {
                if (item < current.item) {
                    current.previous.next = newNode;
                    newNode.previous = current.previous;
                    newNode.next = current;
                    current.previous = newNode;
                    if (current == head) {
                        head = newNode;
                    }
                    break;
                }
                current = current.next;
            }
        }
        size++;
    }

    public Integer dequeue() {
        if (isEmpty())
            return null;
        Integer item = head.item;
        if (size() == 1) {
            head = null;
        } else {
            Node last = head.previous;
            last.next = head.next;
            head.next.previous = last;
            head = head.next;
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
        for (Integer item : this) {
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

    public Iterator<Integer> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Integer> {
        private Node current = head;
        private boolean completedLoop;

        public boolean hasNext() {
            return !completedLoop;
        }

        public Integer next() {
            if (current.next == head)
                completedLoop = true;

            Integer item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        OrderedQueue q = new OrderedQueue();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: enqueue, del: dequeue, s: size of queue, q: quit");
            String input = scanner.nextLine();
            switch (input) {
                case ("add"):
                    System.out.println("enter integer to be added at requested index");
                    q.enqueue(scanner.nextInt());
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