package lab4;

import edu.princeton.cs.algs4.ST;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * adds a string name to the vertexes of a directed graph
 * Serves as wrapper around graph-class in order to provide a mapping between the string vertex names and integers
 */
public class DirectedSymbolGraph {
    private ST<String, Integer> st;
    private String[] keys;
    private DirectedGraph graph;

    public DirectedSymbolGraph(String filename, String delimiter) throws FileNotFoundException {
        st = new ST<String, Integer>();
        Scanner in = new Scanner(new FileReader("src/lab4/" + filename));
        while (in.hasNextLine()) {
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

        graph = new DirectedGraph(st.size());
        in = new Scanner(new FileReader("src/lab4/" + filename));
        while (in.hasNextLine()) {
            String[] a = in.nextLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 0; i < a.length; i++) {
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

    public String name(int v) {
        validateVertex(v);
        return keys[v];
    }

    public DirectedGraph digraph() {
        return graph;
    }

    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
}
