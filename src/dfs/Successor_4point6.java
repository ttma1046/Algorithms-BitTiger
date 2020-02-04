/*
Successor: Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a
binary search tree. You may assume that each node has a link to its parent.

*/

class Successor_4point6 {
    public TreeNode FindNextNodeInOrder(TreeNode node) {
    	if (node == null) {
    		return null;
    	}

    	if (node.right != null) {
    		return lookForFarLeft(node.right);
    	} else {
    		TreeNode current = node;
    		TreeeNode parent = current.parent;

    		while(parent != null && parent.left != current) {
    			current = parent;
    			parent = parent.parent;
    		}

    		return parent;
    	}
    }

    private TreeNode LookForFarLeft(TreeNode root) {
    	if (root == null) {
    		return null;    		
    	}

    	TreeNode current = root;

    	While(current.left != null) {
    		current = current.left;
    	}

    	return current;
    }

    void findNext(TreeNode node) {
    	if (node == null) { 
    		return null;    		
    	}

    	if (node.right != null) {
    		return farLeft(node.right);
    	} else {
    		TreeNode current = node;
    		TreeNode parent = node.parent;

    		while(parent != null && parent.left != current) {
    			current = parent;
    			parent = parent.parent;
    		}

    		return parent;
    	}

    }
}

