#
# DSA Final Assessment Question 5 - Q5Graph.py
#
# Name : 
# ID   :
#
# 
import numpy as np
      
class Q5Graph():

    def __init__(self):
        self.maxsize = 20
        self.wmatrix = np.zeros((self.maxsize,self.maxsize), dtype=int)
        self.labels = np.zeros(self.maxsize, dtype=object)
        self.count = 0

    def addVertex(self, vname):
        if self.count == self.maxsize:
            raise PracExamException("graph is full")
        if not self.hasVertex(vname):
            self.labels[self.count] = vname
            self.count += 1

    def addEdge(self, vname1, vname2, weight):
        self.addVertex(vname1)  # won't add if already there
        self.addVertex(vname2)
        label1 = self.getIndex(vname1)
        label2 = self.getIndex(vname2)
        self.wmatrix[label1,label2] = weight
        
    def getIndex(self, vname):   ## Not on slides
        returnval = None
        for i in range(self.count):
            if self.labels[i] == vname:
                returnval = i
        return returnval
        
    def hasVertex(self, vname):
        returnval = False
        for v in self.labels:
            if v == vname:
                returnval = True
        return returnval
        
    def getVertexCount(self):
        return self.count
    

    def displayAsList(self): 
        ...

    # Put your methods here
        
