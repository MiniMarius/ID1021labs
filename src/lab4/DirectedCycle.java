package lab4;

import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {
    private boolean[] marked;        // has vertex v been marked?
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)

    /**
     * Determines whether the digraph has a directed cycle
     */
    public DirectedCycle(DirectedGraph G, int v) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        if (!marked[v]) dfs(G, v);
    }

    // run DFS and find a directed cycle (if one exists)
    private void dfs(DirectedGraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {

            // short circuit if directed cycle found
            if (cycle != null) return;

                // found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
