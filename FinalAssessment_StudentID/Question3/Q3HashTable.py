#
# DSA Final Assessment Question 4 - Q4HashTable.py
#
# Name : 
# ID   :
#
# 
import numpy as np
import math

class Q4HashTable():
    # Inner class Hash Entry
    class Q4HashEntry():
        #0 = never used/free,  1 = used / not free

        def __init__(self, inKey="", value=None):
            self.key = inKey
            self.value = value
            if self.key == "":
                self.state = 0
            else:
                self.state = 1
    # End inner class
    
    def __init__(self, tableSize):

        self.actualSize = self.nextPrime(tableSize - 1);
        self.hashArray = np.zeros(self.actualSize, dtype=object)

        for i in range(0, self.actualSize):
            self.hashArray[i] = self.Q4HashEntry()
        self.hashCount = 0
    
    def put(self, inKey, inValue):
        hashIdx = self.hash(inKey)
        initIdx = hashIdx
        i = 1

        while (self.hashArray[hashIdx] != None and  not self.hashArray[hashIdx].key == inKey):
            if ( not self.hashArray[hashIdx].key == inKey):
                if (self.hashArray[hashIdx].state == 1): 
                    hashIdx = (initIdx + i) % len(self.hashArray) 
                if (self.hashArray[hashIdx].state < 1): 
                    self.hashArray[hashIdx] = self.Q4HashEntry(inKey, inValue)
                    self.hashCount = self.hashCount + 1
            i += 1

    def getLoadFactor(self):
        
       loadFactor = self.hashCount / len(self.hashArray)

       return loadFactor


    def display(self):
        for i in range(0, len(self.hashArray)): 
            if (self.hashArray[i].value != None):
                    print("\t\t" + str(i) + "\t" + str(self.hashArray[i].key))


    def hash(self, inKey):
        hashIdx = 0
        for i in range(0, len(inKey)): 
            hashIdx = hashIdx + ord(inKey[i])
        retVal = hashIdx % len(self.hashArray)
        return retVal

    def nextPrime(self, inNum):

        isPrime = False

        if (inNum % 2 == 0):
            prime = inNum - 1 
        else:
            prime = inNum

        while (not isPrime):
            prime = prime + 2
            i = 3
            isPrime = True
            rootVal = math.sqrt(prime) 
            
            
            while ((i <= rootVal) and (isPrime)):
                if ((prime % i) == 0): 
                    isPrime = False
                else:
                    i = i + 2 
            
        return prime

    def getArrayLength(self):
        return len(self.hashArray)
