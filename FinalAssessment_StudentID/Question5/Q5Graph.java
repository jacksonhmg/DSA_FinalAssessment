/**
 * DSA Final Assessment Question 5 - Q5Graph.java                             4
 *
 * Name : Jackson Mowatt Gok
 * ID   : 20568818
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
        readInGraph("testdata.csv", graph); /* sample data created */
        System.out.println("(1) displayActorMovies or (2) displayMovieActors or (3) Show movies, actors or roles or (4) displayAllCostars");
        String entryType = sc.nextLine();
        if(entryType.equals("1"))
        {
            System.out.println("Enter an actor from the test data file");
            graph.displayActorsMovies(sc.nextLine());
        }
        else if(entryType.equals("2"))
        {
            System.out.println("Enter a movie from the test data file");
            graph.displayMovieActors(sc.nextLine());
        }
        else if(entryType.equals("3"))
        {
            System.out.println("Enter the word Movie, Actor or Role");
            graph.printCategories(sc.nextLine());
        }
        else if(entryType.equals("4"))
        {
            System.out.println("Enter an actor from the test data file");
            graph.displayAllCostars(sc.nextLine());
        }
    }



    private int maxsize;
    private int wmatrix[][];
    private String labels[];
    private String values[]; /* added this to check what type of label they were, (i.e. movie, actor, role). it mirrors the labels array */
    private int vertexCount;

    public Q5Graph() 
    {
        maxsize = 20;
        wmatrix = new int[maxsize][maxsize];
        labels = new String[maxsize];
        values = new String[maxsize];
        for(int i=0; i < maxsize; i++)
        {
            for (int j=0; j< maxsize; j++)
            { 
                wmatrix[i][j] = 0;
            }
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
            values[vertexCount] = value; /* as previously mentioned, values entries mirror labels entries, just defining their type instead */
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
            if(wmatrix[v1][v2] != 0)
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
            if (labels[i].equals(label) && values[i].equals(value)) /* added this because there can be multiple labels for different values (i.e. Jane Eyre the movie and Jane Eyre the role. you need to specify the exact entry you're looking for) */
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
    { /* for "Show movies, actors or roles". finds any entry with the matching entrytype */
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
            if(hasEdge(labels[i],searchString)) /* because of how graph is read in, the 2d edges array positon is check with the movie index as the first index */
            { /* if there is an entry that has an edge with the actor */
                if(values[i].equals("Movie"))
                { /* if the connected entry is a movie */
                    System.out.println(labels[i]);
                }
            }
        }
    }

    public void displayMovieActors(String searchString)
    {
        for(int i = 0; i < vertexCount; i++)
        {
            if(hasEdge(searchString,labels[i])) /* as previously mentioned, the movies index is used first when checking the 2d edges array position */
            { /* if there is an entry that has an edge with the movie */
                if(values[i].equals("Actor"))
                { /* if the connected entry is an actor */
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
            { /* found a movie connected with the actor */
                System.out.println(labels[i]); /* print here so that you have one movie header. If you printed it on the line above where the costars are printed, each actor would have its own movie heading, even if there's duplicates */
                for(int j = 0; j < vertexCount; j++)
                {
                    if(hasEdge(labels[i],labels[j]))
                    { /* if the movie has connected edges... */
                        if(values[j].equals("Actor"))
                        { /* and those connected edges are actors... */
                            if(!labels[j].equals(searchString))
                            { /* and that actor isn't the one we're originally finding costars for... */
                                System.out.println("\t" + labels[j]); /* print costar with tab */
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
                graph.addVertex(sArray[3].trim(), "Actor"); /* trim() needed because file has trailing whitespaces after actors */
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
