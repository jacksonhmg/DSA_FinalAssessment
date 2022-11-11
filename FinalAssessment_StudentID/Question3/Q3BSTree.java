/**
 * DSA Final Assessment Question 3 - Q3BSTree.java
 *
 * Name : Jackson Mowatt Gok
 * ID   : 20568818
 *
 **/
import java.util.*;
import java.io.*;

public class Q3BSTree {   

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
        Q3BSTree BST = new Q3BSTree();
        /*System.out.println(hTable.get(args[0]));*/
        System.out.println("Enter which type of entry you want to receive (Movie,Year,Actor,Role)");
        String entryType = sc.nextLine();
        readInBST("6degrees.csv", BST, entryType);
        System.out.println("Enter string to search for (i.e. 'Jane Eyre' (the movie) or 'Jamie Bell' (the actor). Don't include the quotes)");
        String searchString = sc.nextLine();
        long startTime = System.nanoTime();
        BST.inOrderTraverse(searchString);
        long endTime = System.nanoTime();
        double runningTotal = (int)((double)(endTime - startTime) / 1000.0);	// Convert to microsecs
        System.out.println("Time taken: " + runningTotal);
	}


	// Inner class TreeNode
	private class TreeNode
    {
        private String m_key;
        private Object m_value;
        private TreeNode m_leftChild;
        private TreeNode m_rightChild;

        public TreeNode(String inKey, Object inVal)
        {
            if(inKey == null)
            {
                throw new IllegalArgumentException("Key cannot be null");
            }
            m_key = inKey;
            m_value = inVal;
            m_rightChild = null;
            m_leftChild = null;
        }

        public String getKey()
        {
            return m_key;
        }

        public Object getValue()
        {
            return m_value;
        }

        public TreeNode getLeft()
        {
            return m_leftChild;
        }

        public void setLeft(TreeNode newLeft)
        {
            m_leftChild = newLeft;
        }

        public TreeNode getRight()
        {
            return m_rightChild;
        }

        public void setRight(TreeNode newRight)
        {
            m_rightChild = newRight;
        }
    }
	// End Inner class
	// Class Q3BSTree
	private TreeNode m_root;

    public Q3BSTree()
    {
        m_root = null;
    }
	
	public Q3BSTree(String inKey, Object inValue)
    {
        m_root = new TreeNode(inKey, inValue);
    }

	public void insert(String key, Object value)
    {
		if(m_root == null)
		{
			m_root = new TreeNode(key, value);
		}
		else
		{
			insRec(key, value, m_root);
		}
    	
    }

    private TreeNode insRec(String key, Object data, TreeNode currNode)
    {
        TreeNode updateNode = currNode;
        if(currNode == null)
        {
            TreeNode newNode = new TreeNode(key, data);
            updateNode = newNode;
        }
        else if(key.compareTo(currNode.getKey()) < 0)
        {
            currNode.setLeft(insRec(key, data, currNode.getLeft()));
        }
        else
        {
            currNode.setRight(insRec(key, data, currNode.getRight()));
        }

        return updateNode;
    }

	public void inOrderTraverse(String searchString)
    {
        inOrderRec(m_root, searchString);
    }

    private void inOrderRec(TreeNode currNode, String searchString)
    {
        if(currNode == null)
        {
            return;
        }
        else
        {
            inOrderRec(currNode.getLeft(), searchString);
            if(currNode.getKey().equals(searchString))
			{
				System.out.println(currNode.getValue());
			}
            inOrderRec(currNode.getRight(), searchString);
        }
    }

	public static void readInBST(String file, Q3BSTree BST, String entryType)
    {
        try{
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                String[] sArray = processLine(data);
                if(entryType.equals("Movie"))
                {
                    BST.insert(sArray[0], data);
                }
                if(entryType.equals("Year"))
                {
                    BST.insert(sArray[1], data);
                }
                if(entryType.equals("Actor"))
                {
                    BST.insert(sArray[3].trim(), data); /* space at end of every actors name, need to trim */
                }
                if(entryType.equals("Role"))
                {
                    BST.insert(sArray[5], data);
                }
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
  
}
