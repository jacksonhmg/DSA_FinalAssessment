/**
 * DSA Final Assessment Question 5 - Q5Graph.java                             4
 *
 * Name : 
 * ID   :
 *
 **/
import java.util.*;
import java.io.*;

public class Q5Graph 
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Q5Graph graph = new Q5Graph();
        /*System.out.println(hTable.get(args[0]));*/
        readInGraph("testdata.csv", graph);    
        
        System.out.println("(1) displayActorMovies or (2) displayMovieActors or (3) Show movies, actors or roles or (4) displayAllCostars");
        String entryType = sc.nextLine();
        if(entryType.equals("1"))
        {
            graph.displayActorsMovies(sc.nextLine());
        }
        else if(entryType.equals("2"))
        {
            graph.displayMovieActors(sc.nextLine());
        }
        else if(entryType.equals("3"))
        {
            graph.printCategories(sc.nextLine());
        }
        else if(entryType.equals("4"))
        {
            graph.displayAllCostars(sc.nextLine());
        }
    }



    private int maxsize;
    private int wmatrix[][];
    private String labels[];
    private String values[];
    private int vertexCount;

    public Q5Graph() 
    {
        maxsize = 20;
        wmatrix = new int[maxsize][maxsize];
        labels = new String[maxsize];
        values = new String[maxsize];
        for(int i=0; i < maxsize; i++)
            for (int j=0; j< maxsize; j++)
            { 
                wmatrix[i][j] = 0;
            }
        vertexCount = 0;
    }

    public void addVertex(String label, String value)
    {
        if (vertexCount == maxsize)
        {
            // do nothing, but should throw exception
            throw new IllegalArgumentException("Graph is already at max size");
        }
        else if (!(hasVertex(label,value))) 
        {
            labels[vertexCount] = label;
            values[vertexCount] = value;
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

    public boolean hasEdge(String label1, String label2)
    {
        int v1 = getIndex(label1);
        int v2 = getIndex(label2);
        if(v1 > -1 && v2 > -1)
        {
            if(wmatrix[v1][v2] == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    public boolean hasVertex(String label, String value) 
    {
        boolean has = false;
        for(int i=0; i < vertexCount; i++) 
        {
            if (labels[i].equals(label) && values[i].equals(value))
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


    public void printCategories(String entryType)
    {
        for(int i = 0; i < vertexCount; i++)
        {
            if(values[i].equals(entryType))
            {
                System.out.println(labels[i]);
            }
        }
    }

    public void displayActorsMovies(String searchString)
    {
        for(int i = 0; i < vertexCount; i++)
        {
            if(hasEdge(labels[i],searchString))
            {
                if(values[i].equals("Movie"))
                {
                    System.out.println(labels[i]);
                }
            }
        }
    }

    public void displayMovieActors(String searchString)
    {
        for(int i = 0; i < vertexCount; i++)
        {
            if(hasEdge(searchString,labels[i]))
            {
                if(values[i].equals("Actor"))
                {
                    System.out.println(labels[i]);
                }
            }
        }
    }

    public void displayAllCostars(String searchString)
    {
        System.out.println("Costars for " + searchString + ": ");
        for(int i = 0; i < vertexCount; i++)
        {
            if(hasEdge(labels[i],searchString) && values[i].equals("Movie"))
            {
                System.out.println(labels[i]);
                for(int j = 0; j < vertexCount; j++)
                {
                    if(hasEdge(labels[i],labels[j]))
                    {
                        if(values[j].equals("Actor"))
                        {
                            if(!labels[j].equals(searchString))
                            {
                                System.out.println("\t" + labels[j]);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void readInGraph(String file, Q5Graph graph)
    {
        try{
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                String[] sArray = processLine(data);
                graph.addVertex(sArray[0], "Movie");
                graph.addVertex(sArray[3].trim(), "Actor");
                graph.addVertex(sArray[5], "Role");
                graph.addEdge(sArray[0], sArray[3].trim(), 1);
                graph.addEdge(sArray[0], sArray[5], 1);
                graph.addEdge(sArray[5], sArray[3].trim(), 1);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

	public static String[] processLine(String csvRow)
    {  //reading one row of a file at a time, separated by string.split method
        String[] splitLine;
        splitLine = csvRow.split(","); 
        return splitLine;
    }
  
}  
