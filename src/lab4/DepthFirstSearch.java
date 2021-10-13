//README this class implements depth first search.
package lab4;

import edu.princeton.cs.algs4.Stack;

public class DepthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    //constructor for graph
    public DepthFirstSearch(GraphInterface G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    /**
     * recursive method that uses a depth-first method of marking up the graph
     * @param G the graph to search into
     * @param v the vertex to be searched from
     */
    private void dfs(GraphInterface G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * Tells if vertex v has been marked or not
     * @return the boolean
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns a path between the source vertex and vertex v
     * by connecting edges of
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

    /**
     * Checks that vertex is between 0 and v-1
     * @param v the vertex to check
     */
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
}