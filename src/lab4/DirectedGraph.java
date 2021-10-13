package lab4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.ST;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class DirectedGraph implements GraphInterface {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;        // number of vertices in graph
    private int E;              // number of edges in graph
    private Bag<Integer>[] adj; // linked lists of adjacent vertices

    public DirectedGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a DirectedGraph must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * constructor for assignment 6
     * Reads a txt file, initializes adj array + bags in it and fills them up.
     * @param filename the name of the file to be read
     * @throws FileNotFoundException if file not found
     */
    public DirectedGraph(String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader("src/lab4/" + filename));
        V = in.nextInt();
        in.nextLine();
        E = in.nextInt();
        in.nextLine();
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
        while (in.hasNextLine()) {
            int v = in.nextInt();
            int w = in.nextInt();
            in.nextLine();
            addEdge(v, w);
        }
    }


    public int V() {
        return V;
    }

    public int E() {
        return E;
    }


    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
