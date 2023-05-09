/**
 * A disjoint set data-structure implementing the UnionFind algorithm. 
 * 
 * NOTES: You may implement path compression (though you should not 
 * do it recursive due to StackOverflow exceptions). You _should_ implement
 * union with rank.
 * 
 * @author Kelly Ortega
 */

public class DisjointSet {
//which tree should become the child of which tree's root
    int[] parent; // root nodes
    int[] rank; // how many vertices in each tree

    /**
     * Initializes a DisjointSet object with n initial disjoint sets. 
     * @param n The number of initial disjoint sets. 
     */
    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * @return The representative index for the set containing i. 
     * the root node for i
     */
    public int find(int i) {
        int current = i;
        while(parent[current] != current) {
            current = parent[current];
        }
        return current; 
    }

    /**
     * Unions the sets containing i and j. based on rank
     * @param i An item in one of the sets. 
     * @param j An item in one of the sets. 
     */
    public void union(int i, int j) {
        int root1 = find(i);     // Find root of node a
        int root2 = find(j);     // Find root of node b
        if(differ(i, j)) { //only merge if they differ
            if (rank[root1] < rank[root2]) {          // Merge two trees
                parent[root1] = root2;
                rank[root2] = rank[root2] + rank[root1];
            } else if(rank[root1] > rank[root2]){
                parent[root2] = root1;
                rank[root1] = rank[root2] + rank[root1];
            } else {
                if(root1 < root2) {
                    parent[root2] = root1;
                    rank[root1] = rank[root2] + rank[root1];
                } else {
                    parent[root1] = root2;
                    rank[root2] = rank[root2] + rank[root1];
                }
            }
        }
        
    }

    /**
     * @param i A member of a set. 
     * @param j A member of a set. 
     * @return true if i and j are in different sets, false otherwise. 
     */
    public boolean differ(int i, int j) {
        return find(i) != find(j);
    }
}