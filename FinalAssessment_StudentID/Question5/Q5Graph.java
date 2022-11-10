/**
 * DSA Final Assessment Question 5 - Q5Graph.java                             4
 *
 * Name : 
 * ID   :
 *
 **/
import java.util.*;

public class Q5Graph 
{
    private int maxsize;
    private int wmatrix[][];
    private String labels[];
    private int vertexCount;

    public Q5Graph() 
    {
        maxsize = 20;
        wmatrix = new int[maxsize][maxsize];
        labels = new String[maxsize];
        for(int i=0; i < maxsize; i++)
            for (int j=0; j< maxsize; j++)
            { 
                wmatrix[i][j] = 0;
            }
        vertexCount = 0;
    }

    public void addVertex(String label)
    {
        if (vertexCount == maxsize)
	{
		// do nothing, but should throw exception
        throw new IllegalArgumentException("Graph is already at max size");
	}
	else if (!(hasVertex(label))) 
        {
            labels[vertexCount] = label;
            vertexCount++;
        }
    }

    public void addEdge(String label1, String label2, int weight)
    {
        int v1, v2;

        v1 = getIndex(label1); 
        v2 = getIndex(label2);   

        wmatrix[v1][v2] = weight;
    }

    public boolean hasVertex(String label) 
    {
        boolean has = false;
        for(int i=0; i < vertexCount; i++) 
        {
            if (labels[i].equals(label))
            {
                has = true;
            }

        }
        return has;
    }

    public int getIndex(String label) 
    {
        int theVertex = -1;
        for (int i=0; i < vertexCount; i++) 
        {
            if (labels[i].equals(label))
            {
                theVertex = i;
            }  
        }
	    return theVertex;    
	}

    public void displayAsList() 
    {
		System.out.println("Adjacency List display (stub)");
    }

	// put your methods here
}  
