#
# DSA Final Assessment Question 5 - Q5GraphTest.py
#
# Name : 
# ID   :
#
# 
from Q5Graph import *
    

print("\n##### Question 5: Testing Graphs #####\n")

g = Q5Graph()

g.addVertex("one")
g.addVertex("two")
g.addVertex("three")
g.addVertex("four")

g.addEdge("one", "two", 3)
g.addEdge("one", "three", 4)
g.addEdge("one", "four", 5)
g.addEdge("four", "two", 6)
g.addEdge("four", "three", 7)

g.displayAsList()

print("\n##### Tests Complete #####\n")


