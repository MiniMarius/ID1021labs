//README this class implements breadth-first search
package lab4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadthFirst {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    /**
     * constructor for the class
     * @param G the graph to search into
     * @param s the source index to start at
     */
    public BreadthFirst(GraphInterface G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(s);
        bfs(G, s);
    }

    // breadth-first search from a source s
    private void bfs(GraphInterface G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    q.enqueue(w);
                }
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
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
