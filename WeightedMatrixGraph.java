
import java.util.*;

/**
 *
 * This class is a weight-matrix implementation of a weighted, undirected graph. 
 * 
 * @author Kelly Ortega
 */

public class WeightedMatrixGraph implements WeightedGraph {
    
    private int vertexCount;
    private int edgeCount;
    
    private float[][] weightMatrix;
    /**
    * Initializes a weighted graph with n vertices and no edges. This should set
    * all of the values in weightMatrix to Float.POSITIVE_INFINITY
    */
    public WeightedMatrixGraph(int n) {
        this.vertexCount = n; //n vertices
        this.edgeCount = 0; //no edges
        this.weightMatrix = new float[n][n];
        for(float[] a: weightMatrix)
        Arrays.fill( a, Float.POSITIVE_INFINITY );
    }

    @Override
    public boolean hasEdge( int i, int j )
    {
        return weightMatrix[i][j] != Float.POSITIVE_INFINITY;
    }

    @Override
    public List< Integer > getAdjacentVertices( int i )
    {
        List< Integer > result = new ArrayList<Integer>();
        for(int j = 0; j < getVertexCount(); j++) {
            if(hasEdge(i, j)) {
                result.add( j );
            }
        }
        return result;
    }

    @Override
    public int getDegreeOf( int i )
    {
        return getAdjacentVertices(i).size();
    }

    @Override
    public int getTotalDegree()
    {
        int result = 0;
        for(int i = 0; i < getVertexCount(); i++) {
            result += getDegreeOf(i);
        }
        return result;
    }

    @Override
    public int getVertexCount()
    {
        return vertexCount;
    }

    @Override
    public int getEdgeCount()
    {
        return edgeCount;
    }

    @Override
    public void setEdgeWeight( int i, int j, float weight )
    {
        weightMatrix[i][j] = weightMatrix[j][i] = weight;
        edgeCount++;
    }

    @Override
    public float getEdgeWeight( int i, int j )
    {
        return weightMatrix[i][j];
    }

    // TODO Implement. Remember that this graph is undirected, which has
    // implications for your setEdgeWeight method. 

}
