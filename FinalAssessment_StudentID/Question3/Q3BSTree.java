/**
 * DSA Final Assessment Question 3 - Q3BSTree.java
 *
 * Name : 
 * ID   :
 *
 **/

public class Q3BSTree {   
	// Inner class TreeNode
	private class TreeNode {
		public int value;
		public TreeNode left;
		public TreeNode right;
		
		public TreeNode(int inVal)
		{
			value = inVal;
			left = null;
			right = null;
		}
	}
	// End Inner class
	// Class Q3BSTree
	private TreeNode root;
	
	public Q3BSTree()
	{
		root = null;
	}
	
	public void insert(int val)
	{
		if (isEmpty())
		{
			root = new TreeNode(val);
		}
		else
		{
			root = insertRec(val, root);
		}
	}

	public Boolean isEmpty()
	{
		return root == null;
	}

	private TreeNode insertRec(int inVal, TreeNode cur)
	{
		if (cur == null)
		{
			cur = new TreeNode(inVal);
		}
		else
		{
			if (inVal < cur.value)
			{
				cur.left = insertRec(inVal, cur.left);
			}
			else	
			{
				cur.right = insertRec(inVal, cur.right);
			}
		}
		return cur;
	}
	
  
}
