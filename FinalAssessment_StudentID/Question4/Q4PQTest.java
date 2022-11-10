import java.util.*;
public class Q4PQTest {
    public static void main(String args[])
    {
        // Creating empty priority queue
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
  
        // Adding items to the pQueue using add()
        pQueue.add(10);
        pQueue.add(20);
        pQueue.add(15);
  
        // Printing the top element of PriorityQueue
        System.out.println(pQueue.peek());
  
        // Printing the top element and removing it
        // from the PriorityQueue container
        System.out.println(pQueue.poll());
  
        // Printing the top element again
        System.out.println(pQueue.peek());





                
        int iNumPassed = 0;
        int iNumTests = 0;

        System.out.println("---------------------------------");
        System.out.println("Testing All Shuffling Queue Features");

        // TEST 1 : Constructor
        try
        {
            iNumTests ++;
            System.out.print("Testing if new queue is empty: ");

            iNumPassed++;
            System.out.println("Passed!");
        } catch(Exception e){
            System.out.println("Failed!");
        }

        //TEST 2 : Enqueue
        try
        {
            iNumTests++;
            System.out.print("Testing enqueue: ");
            
            iNumPassed++;
            System.out.println("Passed!");
        } catch (Exception e){
            System.out.println("Failed!");
        }


        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests);


    }
}