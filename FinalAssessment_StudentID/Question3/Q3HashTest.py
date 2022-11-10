#
# DSA Final Assessment Question 3 - Q4HashTest.py
#
# Name : 
# ID   :
#
# 
from Q3HashTable import *

print("\n##### Question 3: Testing Hash Tables #####\n")

tab = Q3HashTable(20)
data = ["11111110", "11111101", "11111011", "11110111", "11101111", "11011111", "10111111", "01111111"]
print("Table size is: " + str(tab.getArrayLength()) )

for i in range (0, len(data)):
	tab.put(data[i], "O"+data[i])	

tab.display()
print("Load Factor is: " + str(tab.getLoadFactor()) )

print("\n##### Tests Complete #####\n")
