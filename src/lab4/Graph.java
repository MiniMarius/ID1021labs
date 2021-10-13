//README this implements an undirected graph.
package lab4;

import edu.princeton.cs.algs4.Bag;

/**
 * class that contains vertices and edges and manipulation of these
 */
public class Graph implements GraphInterface {

    private final int V;        // number of vertices in graph
    private int E;              // number of edges in graph
    private Bag<Integer>[] adj; // linked lists of adjacent vertices

    /**
     * Constructor that initializes an array of linked lists of size V
     * @param V the number of vertices
     */
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     *
     * @return the nubmer of vertices in graph
     */
    public int V() {
        return V;
    }

    /**
     *
     * @return the number of edges in graph
     */
    public int E() {
        return E;
    }

    /**
     * Checks that vertex is between 0 and v-1
     * @param v the vertex to check
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds an undirected edge between the two vertices
     * @param v first vertex
     * @param w second vertex
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * Return an iterable bag object
     * @param v the index of bag to get
     * @return an iterable bag object
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * @return string representation of graph
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
