package lab4;

import edu.princeton.cs.algs4.Stack;

public class DepthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    //constructor for undirected graph
    public DepthFirstSearch(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }
    //constructor for Directed graph
    public DepthFirstSearch(DirectedGraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        validateVertex(s);
        DirectedDfs(G, s);
    }

    // depth first search from v for undirected graph
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    // depth first search from v for a directed graph
    private void DirectedDfs(DirectedGraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                DirectedDfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns a path between the source vertex and vertex v
     *
     * @param v the vertex to find a path to
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
}