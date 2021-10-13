package lab4;

/**
 * This interface depicts a graph
 */
public interface GraphInterface {
    /**
     *
     * @return the amount of vertices in a graph
     */
    int V();
    /**
     *
     * @return the amount of edges in a graph
     */
    int E();

    /**
     * Adds an edge between two vertices
     * @param v first vertex
     * @param w second vertex
     */
    void addEdge(int v, int w);

    /**
     * Return an iterable object in order to iterate
     * @return an iterable object
     */
    Iterable<Integer> adj(int v);
}
