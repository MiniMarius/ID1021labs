//README uses the first thousand words from a txt file and measure running time of BST algorithm and outputs the
//measured results to a csv file for plotting in external program
//uses princeton's implementation of frequency counter and BST.
package lab3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.*;
import java.util.NoSuchElementException;

public class Assignment3<Key extends Comparable<Key>, Value> {
    private Node root;             // root of search tree

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    /**
     * Initializes binary search tree
     */
    public Assignment3() {
    }

    /**
     * Returns true if this tree is empty.
     * @return {@code true} if this tree is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of key-value pairs in this tree.
     * @return the number of key-value pairs in this tree
     */
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in Assignment3 rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    /**
     * Does this tree contain the given key?
     *
     * @param  key the key
     * @return {@code true} if this tree contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the tree
     *         and {@code null} if the key is not in the tree
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     * Inserts the specified key-value pair into the tree, overwriting the old
     * value with the new value if the tree already contains the specified key.
     * Deletes the specified key (and its associated value) from this tree
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Returns the smallest key in the tree.
     *
     * @return the smallest key in the tree
     * @throws NoSuchElementException if the tree is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty tree");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }

    /**
     * Returns the largest key in the tree.
     *
     * @return the largest key in the tree
     * @throws NoSuchElementException if the tree is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty tree");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else                 return max(x.right);
    }

    /**
     * Return the key in the tree of a given {@code rank}.
     * This key has the property that there are {@code rank} keys in
     * the tree that are smaller. In other words, this key is the
     * ({@code rank}+1)st smallest key in the tree.
     *
     * @param  rank the order statistic
     * @return the key in the tree of given {@code rank}
     * @throws IllegalArgumentException unless {@code rank} is between 0 and
     *        <em>n</em>–1
     */
    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    // Return key in Assignment3 rooted at x of given rank.
    // Precondition: rank is in legal range.
    private Key select(Node x, int rank) {
        if (x == null) return null;
        int leftSize = size(x.left);
        if      (leftSize > rank) return select(x.left,  rank);
        else if (leftSize < rank) return select(x.right, rank - leftSize - 1);
        else                      return x.key;
    }

    /**
     * Return the number of keys in the tree strictly less than {@code key}.
     *
     * @param  key the key
     * @return the number of keys in the tree strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else              return size(x.left);
    }

    /**
     * Returns all keys in the tree as an {@code Iterable}.
     * To iterate over all of the keys in the tree named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     *
     * @return all keys in the tree
     */
    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<Key>();
        return keys(min(), max());
    }

    /**
     * Returns all keys in the tree in the given range,
     * as an {@code Iterable}.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return all keys in the tree between {@code lo}
     *         (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *         is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    /**
     * Appends a Long integer to a3performance csv file
     * @param timedPerformance the Long integer to be appended
     */
    private static void outputSTperformance(Long timedPerformance) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("a3performance" + ".csv", true))) {
            writer.write(String.valueOf(timedPerformance + ';'));
            writer.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/lab3/polishedText.txt"));
        long startTime = System.currentTimeMillis();
        int distinct = 0, words = 0;
        Assignment3<String, Integer> st = new Assignment3<>();

        // compute frequency counts
        while (!StdIn.isEmpty() && words < 1000) {
            String key = StdIn.readString().toLowerCase();
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        long endTime = System.currentTimeMillis();
        outputSTperformance(endTime - startTime);
    }
}
