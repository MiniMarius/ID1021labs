package lab4;

import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;

    /**
     * Checks if digraph has a directed cycle
     */
    public DirectedCycle(DirectedGraph G, int v) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        if (!marked[v]) dfs(G, v);
    }

    // run DFS to find a directed cycle
    private void dfs(DirectedGraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {


            if (cycle != null) return;

            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

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
