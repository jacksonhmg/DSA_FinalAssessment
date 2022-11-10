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
			System.out.println("\n##### Question 5: Testing Graphs #####\n");

			// put your code here
			Q5Graph g = new Q5Graph();
			
			g.addVertex("one");
			g.addVertex("two");
			g.addVertex("three");
			g.addVertex("four");
			
			g.addEdge("one", "two", 3);
			g.addEdge("one", "three", 4);
			g.addEdge("one", "four", 5);
			g.addEdge("four", "two", 6);
			g.addEdge("four", "three", 7);
			
			g.displayAsList();
			
			System.out.println("\n##### Tests Complete #####\n");

	}
	
}
