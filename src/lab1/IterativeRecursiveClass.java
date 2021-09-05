package lab1;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class IterativeRecursiveClass {

    public static void main(String[] args) {
        StackClass stack = new StackClass();
        while (!StdIn.isEmpty())
            stack.push(StdIn.readChar());
            StdOut.println(stack.first);
    }

    public static String recursiveReverseMethod(String string) {
        if (string.isEmpty())
            return string;
        return recursiveReverseMethod(string.substring(1)) + string.charAt(0);
    }

    private static class StackClass {
        private Node<Character> first;
        private int n;

        public StackClass() {
            first = null;
            n = 0;
        }

        private static class Node<Character> {
            private Character item;
            private StackClass.Node<Character> next;
        }
        private void push(Character c) {
            StackClass.Node<Character> oldfirst = first;
            first = new StackClass.Node<Character>();
            first.item = c;
            first.next = oldfirst;
            n++;

        }

        private Character pop() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            Character item = first.item;        // save item to return
            first = first.next;            // delete first node
            n--;
            return item;

        }

        private boolean isEmpty() {
            return first == null;
        }

        private int size() {
            return n;
        }
    }


}
