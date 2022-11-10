/**
 * DSA Final Assessment Question 3 - Q3HashTest.java
 *
 * Name : 
 * ID   :
 *
 **/

public class Q3HashTest
{
	public static void main(String args[])
	{
		System.out.println("\n##### Question 3: Testing Hash Tables #####\n");

		Q3HashTable tab = new Q3HashTable(20);

		String[] data = {"11111110", "11111101", "11111011", "11110111", "11101111", "11011111", "10111111", "01111111"};

        for (int i=0; i < data.length; i++)
		{
			tab.put(data[i], "O"+data[i]);
		}		

		System.out.println("Table size is: " + tab.getArrayLength() );
		
		tab.display();
		
		System.out.println("Load Factor is: " + tab.getLoadFactor() );
		
		System.out.println("\n##### Tests Complete #####\n");
	}
}
