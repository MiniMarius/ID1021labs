package lab1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class GenericFIFOQueue {
    private Node first;
    private Node last;
    private int n;

    private static class Node {
        private String item;
        private Node next;
    }


    public void enqueue(String item) {
        // adds item to end of list/queue
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;

    }

    public String dequeue() {
        // removes item from beginning of the list/queue
        String item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        n--;
        return item;

    }

    public boolean isEmpty() {
        return first == null;

    }

    public int size() {
        return n;
    }

    public static void main(String[] args) {
        GenericFIFOQueue q = new GenericFIFOQueue();
        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            q.enqueue(item);
        }
        StdOut.println("(" + q.size() + " left on queue)");
        while (!q.isEmpty())
            StdOut.print(q.dequeue() + " ");
    }
}
