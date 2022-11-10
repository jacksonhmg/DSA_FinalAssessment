import java.util.*;
public class Q4PQTest {
    public static void main(String args[])
    {
        // Creating empty priority queue
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
                
        int iNumPassed = 0;
        int iNumTests = 0;

        System.out.println("---------------------------------");
        System.out.println("Testing All Queue Features");

        //TEST 1 : add
        try
        {
            iNumTests++;
            System.out.print("Testing add: ");
            pQueue.add(10);
            pQueue.add(20);
            pQueue.add(15);
            iNumPassed++;
            System.out.println("Passed!");
        } catch (Exception e){
            System.out.println("Failed!");
        }


        // TEST 2 : Peek
        try
        {
            iNumTests ++;
            System.out.print("Testing peek method: ");
            if(pQueue.peek() != 10)
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("Passed!");
        } catch(Exception e){
            System.out.println("Failed!");
        }

        //TEST 3 : size
        try
        {
            iNumTests++;
            System.out.print("Testing size: ");
            if(pQueue.size() != 3)
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("Passed!");
        } catch (Exception e){
            System.out.println("Failed!");
        }

        //TEST 4 : Remove
        try
        {
            iNumTests++;
            System.out.print("Testing remove: ");
            pQueue.remove(10);
            if(pQueue.peek() != 15)
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("Passed!");
        } catch (Exception e){
            System.out.println("Failed!");
        }

        //TEST 5 : Poll
        try
        {
            iNumTests++;
            System.out.print("Testing poll: ");
            pQueue.poll();
            if(pQueue.peek() != 20)
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("Passed!");
        } catch (Exception e){
            System.out.println("Failed!");
        }

        //TEST 6 : isEmpty
        try
        {
            iNumTests++;
            System.out.print("Testing isEmpty: ");
            pQueue.poll();
            if(!pQueue.isEmpty())
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("Passed!");
        } catch (Exception e){
            System.out.println("Failed!");
        }

        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests);


    }
}