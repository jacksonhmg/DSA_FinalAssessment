#
# DSA Final Assessment Question 3 - Q3BSTree.py
#
# Name : 
# ID   :
#
# 

class Q3BSTree():
    # Inner Treenode class
    class Q2TreeNode():
        def __init__(self, value):
            self.value = value
            self.left = None
            self.right = None
    # End inner class

    def __init__(self):
        self.root = None
    
    def insert(self, val):
        if (self.isEmpty()):
            self.root = self.Q2TreeNode(val)
        else:
            self.root = self.insertRec(val, self.root)

    def isEmpty(self):
        return self.root == None

    def insertRec(self, inVal, cur):
        if (cur == None):
            cur = self.Q2TreeNode(inVal)
        else:
            if (inVal < cur.value):
                cur.left = self.insertRec(inVal, cur.left)
            else:
                cur.right = self.insertRec(inVal, cur.right)
        return cur

