package lab1;


import java.util.NoSuchElementException;
import java.util.Scanner;

public class ParenthesesChecker {
    private Node first;
    private int n;


    private class Node {
        private Character item;
        private Node next;
    }

    public ParenthesesChecker() {
        first = null;
        n = 0;
    }

    private void push(Character item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    private Character pop() {
        Character item = first.item;
        first = first.next;
        n--;
        return item;
    }

    private Character peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    public boolean isStringBalanced(String inputString) {
        for (char c : inputString.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                this.push(c);
                continue;
            }

            if (c == '}' || c == ')' || c == ']') {
                if (this.isEmpty())
                    return false;

                if (c == '}' && this.peek() == '{' || c == ')' && this.peek() == '(' || c == ']' && this.peek() == '[')
                    this.pop();
                else
                    return false;
            }
        }
        return this.isEmpty();
    }

    private boolean isEmpty() {
        return size() == 0;
    }

    private int size() {
        return n;
    }

    public static void main(String[] args) {
        ParenthesesChecker parenthesesChecker = new ParenthesesChecker();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter brackets, parentheses and braces to be tested for balance");
        System.out.println(parenthesesChecker.isStringBalanced(scanner.next()));
    }
}

