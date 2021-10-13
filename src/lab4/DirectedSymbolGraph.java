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

    /**
     * Initializes a Symbol table and reads a txt file, initializes a new graph and does a
     * reverse mapping of graph vertex to string names in symbol table
     * @param filename the name of the file to open
     * @param delimiter the delimiter to use during for splitting the strings
     * @throws FileNotFoundException
     */
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

    /**
     * checks if symbol table contains the string name
     * @param s string name to be used for checking in symbol table
     * @return true if name exists in symbol table
     */
    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * Returns the integer associated with name string
     * @param s the string to use for searhing in symbol table
     * @return index associated with name string
     */
    public int index(String s) {
        return st.get(s);
    }

    /**
     * returns the name associated with vertex integer from graph
     * @param v vertex integer to get name from
     * @return name of vertex
     */
    public String name(int v) {
        validateVertex(v);
        return keys[v];
    }

    /**
     * returns underlying graph
     * @return the underlying graph
     */
    public DirectedGraph digraph() {
        return graph;
    }

    /**
     * Checks that vertex is between 0 and v-1
     * @param v the vertex to check
     */
    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
}
