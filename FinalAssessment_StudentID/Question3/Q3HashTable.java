/**
 * DSA Final Assessment Question 3 - Q3HashTable.java
 *
 * Name : Jackson Mowatt Gok
 * ID   : 20568818
 *
 **/
import java.util.*;
import java.io.*;

public class Q3HashTable {


    public static void main(String[] args)
    {
        Q3HashTable hTable = new Q3HashTable(100);
        readInHTable("6degrees.csv", hTable);
        /*System.out.println(hTable.get(args[0]));*/
    }



    public static void readInHTable(String file, Q3HashTable hTable)
    {
        try{
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                System.out.println(data);
                String[] sArray = processLine(data);
                hTable.resizeCheck();
                hTable.put(sArray[0], sArray[1]);
                //System.out.println(sArray[0]);
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

    
    protected class HashEntry
    {
        //class fields
        private String key;
        private Object value; 
        private int state; //0 = never used/free,  1 = used / not free

        //default constructor
        public HashEntry() {

            key = "";
            value = null;
            state = 0;
        }

        //alternate constructor
        public HashEntry(String inKey, Object inValue) 
        {

            key = inKey;
            value = inValue;
            state = 1;
        }

        public String getKey() 
        {
            return this.key;
        }

        public Object getValue() 
        {
            return this.value;
        }


        public int getState() 
        {
            return this.state;
        }

        public void setKey(String inKey) {
            this.key = inKey;
        }

        public void setValue(Object inValue) {
            this.value = inValue;
        }

        public void setState() {
            this.state = -1;
        }
    }

    private HashEntry[] hashArray; 
    private int hashCount; 

    public Q3HashTable(int tableSize) {

        int actualSize = nextPrime(tableSize - 1); 
        hashArray = new HashEntry[actualSize];

        for (int i = 0; i < (actualSize); i++) {
            hashArray[i] = new HashEntry(); 
        }
    } 
    
    public void put(String inKey, Object inValue) {

        int hashIdx = hash(inKey);
		int initIdx = hashIdx;
        int i = 1;

        while (hashArray[hashIdx] != null && !hashArray[hashIdx].getKey().equals(inKey)) {

            if (!hashArray[hashIdx].getKey().equals(inKey)) { 
                if (hashArray[hashIdx].getState() == 1) { 
                    hashIdx = (initIdx + i) % hashArray.length; 
                }

                if (hashArray[hashIdx].getState() < 1) { 
                    hashArray[hashIdx] = new HashEntry(inKey, inValue); 
                    hashCount++; 
                }
            }
            i++; 
        } 

    }




    public int stepHash(String inKey)
    {
        int key = 0;
        for(int i = 0; i < inKey.length(); i++)
        {
            key += (int)inKey.charAt(i);
        }
        int max_step = 13;
        int hashStep = max_step - (key % max_step);
        return hashStep;
    }

    public Object get(String inKey)
    {
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp = false;

        while(!found && !giveUp)
        {
            if(hashArray[hashIdx].state == 0)
            {
                giveUp = true;
                //System.out.println("\nState equals zero");
            }
            else if(hashArray[hashIdx].key.equals(inKey))
            {
                found = true;
            }
            else
            {
                hashIdx = (hashIdx + stepHash(inKey));
                if(hashIdx >= hashArray.length)
                {
                    hashIdx = hashIdx - hashArray.length;
                }
                if(hashIdx == origIdx)
                {
                    giveUp = true;
                    //System.out.println("\n== orig");
                }
            }
        }
        if(!found)
        {
            throw new IllegalArgumentException("Can't find");
        }
        return hashArray[hashIdx].value;
    }

    public boolean hasKey(String inKey)
    {
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp = false;

        while(!found && !giveUp)
        {
            if(hashArray[hashIdx].state == 0)
            {
                giveUp = true;
            }
            else if(hashArray[hashIdx].key.equals(inKey))
            {
                found = true;
            }
            else
            {
                hashIdx = (hashIdx + stepHash(inKey));
                if(hashIdx >= hashArray.length)
                {
                    hashIdx = hashIdx - hashArray.length;
                }
                if(hashIdx == origIdx)
                {
                    giveUp = true;
                }
            }
        }
    
        return found;
    }

    public double getLoadFactor() {
        
        double loadFactor;
        loadFactor = (double)hashCount / (double)hashArray.length;

        return loadFactor;
    }


    public void display() 
    {    
        for (int i = 0; i < hashArray.length; i++) {   
            try 
            {
                if (hashArray[i].getValue() != null)
                {
                    System.out.println("\t\t" + i + "\t" + hashArray[i].getKey());
                }

            } 
            catch (NullPointerException e) 
            {
                System.out.println("Null pointer at element: " + i);
            } 
        }
    }

    private int hash(String inKey) 
	{
        int hashIdx = 0;

        for (int i = 0; i < inKey.length(); i++) 
        {  
            hashIdx = hashIdx + inKey.charAt(i);
        } 
        return hashIdx % hashArray.length;
    }

   private int nextPrime(int inNum) 
   {
        int prime;
        boolean isPrime = false;

        if (inNum % 2 == 0) {
            prime = inNum - 1; 
        } else {
            prime = inNum;
        }

        do { 
            prime = prime + 2; 
            int i = 3;
            isPrime = true;
            double rootVal = Math.sqrt(prime); 
            
            do{
                if ((prime % i) == 0) { 
                    isPrime = false;
                } else {
                    i = i + 2; 
                }
            } while ((i <= rootVal) && (isPrime));
        } while (!isPrime);

        return prime;
    }

    public int getArrayLength() 
	{
        return hashArray.length;
    }


    public void resizeCheck()
    {
        if(shouldResize() == 1)
        {
            growResize();
        }
        else if(shouldResize() == 2)
        {
            shrinkResize();
        }
    }


    public int shouldResize()
    {
        int counter = 0;
        for(int i = 0; i < hashArray.length; i++)
        {
           if(hashArray[i].state == 1)
           {
            counter++;
           } 
        }
        if(counter >= (hashArray.length/2))
        {
            return 1;
        }
        if(counter <= (hashArray.length)/10)
        {
            return 2;
        }
        return 0;
    }

    public void growResize()
    {
        HashEntry[] tempArr = hashArray;
        int actualSize = nextPrime((hashArray.length)*2);
        hashArray = new HashEntry[actualSize];
        //System.out.print(" GROWN TO " + hashArray.length + "!!!");
        for(int i = 0; i<actualSize;i++)
        {
            hashArray[i] = new HashEntry();
        }
        for(int i = 0; i<tempArr.length;i++)
        {
            if(tempArr[i].state == 1)
            {
                put(tempArr[i].key, tempArr[i].value);
            }
        }
    }

    public void shrinkResize()
    {
        HashEntry[] tempArr = hashArray;
        int actualSize = nextPrime((hashArray.length)/2);
        hashArray = new HashEntry[actualSize];
        //System.out.print(" SHRUNK TO " + hashArray.length + "!!!");
        for(int i = 0; i<actualSize;i++)
        {
            hashArray[i] = new HashEntry();
        }
        for(int i = 0; i<tempArr.length;i++)
        {
            if(tempArr[i].state == 1)
            {
                put(tempArr[i].key, tempArr[i].value);
            }
        }
    }



} 
