package lab4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

import java.util.Scanner;

public class SymbolGraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private GraphClass graph;             // the underlying graph

    public SymbolGraph(String filename, String delimiter) {
        st = new ST<String, Integer>();

        Scanner in = new Scanner(filename);
        while (!in.hasNext()) {
            String[] a = in.nextLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }

        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        graph = new GraphClass(st.size());
        in = new Scanner(filename);
        while (in.hasNextLine()) {
            String[] a = in.next().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public int indexOf(String s) {
        return st.get(s);
    }

    public String name(int v) {
        validateVertex(v);
        return keys[v];
    }

    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    public GraphClass G() {
        return graph;
    }

    public GraphClass graph() {
        return graph;
    }

    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
