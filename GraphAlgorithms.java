import java.util.*;

/**
 * @author Kelly Ortega
 */
public class GraphAlgorithms {


    /**
     * Helper class for storing weighted edges direction for getMinimumSpanningTree
     */
    public static class WeightedEdge implements Comparable<WeightedEdge> {
        int i, j;
        float w;
        public WeightedEdge(int i, int j, float w) {
            this.i = i;
            this.j = j;
            this.w = w;
        }

        /**
         * Compares this edge's weight to e's weight. This way you can use
         * Collections.sort to sort a list of edges by weight
         * @param e
         * @return
         */
        public int compareTo(WeightedEdge e) {
            return ((Float) w).compareTo(((Float) e.w));
        }
        
        @Override
        public boolean equals(Object o) {
            return w == ((WeightedEdge)o).w;
        }
    }

    /**
     * Implement Kruskal's algorithm to compute a minimum spanning tree.
     *
     * Note that the helper class WeightedEdge just lets you store a pair (i, j) as an
     * edge so that you can put it in a list.
     * @return A WeightedGraph representing the minimum spanning tree of g.
     */
    public static AdjacencyListGraph getMinimumSpanningTree(WeightedGraph g) {
        
        //sets up adjacency list containing the minimum spanning tree and the disjoint set for
        //building the tree
        AdjacencyListGraph result = new AdjacencyListGraph(g.getVertexCount());
        DisjointSet set = new DisjointSet(g.getVertexCount());
        
        //first, we need all valid edges, preferrably in order since we go from cheapest edge
        //weight, to the last edge weight
        
        List<WeightedEdge> edgeList = new ArrayList<WeightedEdge>();
        for(int i = 0; i < g.getVertexCount(); i++) { //iterates through i
            for(int j = 0; j < g.getAdjacentVertices(i).size(); j++) { //iterates through all   
                WeightedEdge edge = new WeightedEdge(i,                //adjacent vertices of i
                    g.getAdjacentVertices( i ).get( j ), g.getEdgeWeight( i, 
                        g.getAdjacentVertices( i ).get( j ) ));
                        edgeList.add(edge);
            }
        }
        Collections.sort( edgeList );
        
        //start looping through edges + building the tree
        for(int i = 0; i < edgeList.size(); i++) {
            if (set.differ( edgeList.get( i ).i, edgeList.get( i ).j ) ) { // checks to make sure the nodes are disjoint,
                set.union( edgeList.get( i ).i, edgeList.get( i ).j );  
                result.addEdge( edgeList.get( i ).i, edgeList.get( i ).j );// then unions them and unions and
            }                                                              // adds them if theyre not
        }
        return result;
    }


    /**
     * @param tree A tree given as a Graph object. (BREADTH FIRST,BUILD IT IN REVERSE THEN FLIP IT)
     * @param s 
     * @param t
     * @return A path in the graph g from the start vertex s to t as a sequence of vertices
     */
    public static List<Integer> getPath(Graph tree, int s, int t) {
        //1. get adjacent vertices on s
        //2. put each node in a list and mark it as visited
        //3. remember which node you came from: 2 arrays will do
        //4, check at each step if youre at T yet
        //5. once youre at T, check each node to see where you cme from, back to T
        //6. flip it
        List<Integer> result = new ArrayList<Integer>();
        boolean[] visited = new boolean[tree.getVertexCount()];
        int[] cameFrom = new int[tree.getVertexCount()];

        traverse(visited, cameFrom, tree, s, s); //mark every node, visit em all, get where 
                                                       //you came from, etc

        result.add( t ); //build the list in reverse
        int next = cameFrom[t];
        while(next != s) {
            result.add( next );
            next = cameFrom[next];
        }
        result.add( s );
        Collections.reverse( result );
        return result;
    }
    /**
     * the only way I could think of doing this is recursively
     * @param visited the list of what has been visited
     * @param cameFrom the list of what each node was visited from
     * @param tree the tree to traverse
     * @param current the current node
     * @param previous the node it came from
     */
    private static void traverse(boolean[] visited, int[] cameFrom, Graph tree, int current, int previous) {
        cameFrom[current] = previous;
        visited[current] = true;
        for(int i:tree.getAdjacentVertices( current )) {
            if(!visited[i] ) {
                traverse(visited, cameFrom, tree, i, current);
            }
        }
    }
}
