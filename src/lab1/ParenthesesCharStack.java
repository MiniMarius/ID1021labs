package lab1;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ParenthesesCharStack implements Iterable<Character> {
    private Node first;
    private int n;


    private static class Node {
        private Character item;
        private Node next;
    }

    public ParenthesesCharStack() {
        first = null;
        n = 0;
    }

    public void push(Character item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Character pop() {
        Character item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Character peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    public static boolean isStringBalanced(ParenthesesCharStack stack, String inputString) {
        for (char c : inputString.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
                continue;
            }

            if (c == '}' || c == ')' || c == ']') {
                if (stack.isEmpty())
                    return false;

                if (c == '}' && stack.peek() == '{' || c == ')' && stack.peek() == '(' || c == ']' && stack.peek() == '[')
                    stack.pop();
                else
                    return false;
            }
        }
        return stack.isEmpty();
    }

    boolean isEmpty() {
        return size() == 0;
    }

    int size() {
        return n;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Character item : this) {
            s.append("[");
            s.append(item);
            s.append("]");
            s.append(", ");
        }
        return s.toString();
    }


    public Iterator<Character> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Character> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Character next() {
            Character item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        ParenthesesCharStack stack = new ParenthesesCharStack();
        StdOut.print("enter brackets, parentheses and braces to be tested for balance");
        StdOut.print(isStringBalanced(stack, StdIn.readString()));
    }
}
