//README this class uses a depth first search in order to find directed cycles inside a graph
package lab4;

import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo; //previous vertex on path to v
    private boolean[] onStack;
    private Stack<Integer> cycle; //the found directed circle

    /**
     * Checks if digraph has a directed cycle
     */
    public DirectedCycle(DirectedGraph G, int v) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        if (!marked[v]) dfs(G, v);
    }

    /**
     * recursive method that uses a depth-first method for finding directed cycles
     * @param G the graph to search into
     * @param v the vertex to be searched from
     */
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

    /**
     * Tells if a cycle has been found or not
     * @return
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * returns the iterable stack object cycle
     * @return cycle
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }
}
