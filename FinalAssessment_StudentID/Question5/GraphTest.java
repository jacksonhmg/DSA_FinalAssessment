/**
 * DSA Final Assessment Question 5 - GraphTest.java
 *
 * Name : 
 * ID   :
 *
 **/
public class GraphTest
{
	public static void main(String args[])
	{
		// put your code here
		Q5Graph g = new Q5Graph();	

		int iNumPassed = 0;
        int iNumTests = 0;

        System.out.println("---------------------------------");
        System.out.println("Testing All Graph Features");


        //TEST 1 : addVertex
        try
        {
            iNumTests++;
            System.out.print("Testing addVertex: ");
			g.addVertex("one","num");
			g.addVertex("two","num");
			g.addVertex("three","num");
			g.addVertex("four","num");
            iNumPassed++;
            System.out.println("Passed!");
        } catch (Exception e){
            System.out.println("Failed!");
        }

        //TEST 2 : addEdge
        try
        {
            iNumTests++;
            System.out.print("Testing addEdge: ");
			g.addEdge("one", "two", 3);
			g.addEdge("one", "three", 4);
			g.addEdge("one", "four", 5);
			g.addEdge("four", "two", 6);
			g.addEdge("four", "three", 7);
            iNumPassed++;
            System.out.println("Passed!");
        } catch (Exception e){
            System.out.println("Failed!");
        }

		//TEST 3 : hasEdge
        try
        {
            iNumTests++;
            System.out.print("Testing hasEdge: ");
			if(!g.hasEdge("one", "two"))
			{
				throw new IllegalArgumentException("No edge found between one and two");
			}
            iNumPassed++;
            System.out.println("Passed!");
        } catch (Exception e){
            System.out.println("Failed! " + e);
        }

		//TEST 4 : hasVertex
        try
        {
            iNumTests++;
            System.out.print("Testing hasVertex: ");
			if(!g.hasVertex("one", "num"))
			{
				throw new IllegalArgumentException("No one vertex found");
			}
            iNumPassed++;
            System.out.println("Passed!");
        } catch (Exception e){
            System.out.println("Failed! " + e);
        }


        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests);
	}
	
}
