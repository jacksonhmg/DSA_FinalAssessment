/**
 * DSA Final Assessment Question 3 - Q3HashTable.java
 *
 * Name : 
 * ID   :
 *
 **/

public class Q3HashTable {
    
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
} 
