package leetcode;
import java.util.Map;
import java.util.HashMap;

/*
Given the root of a binary tree, the depth of each node is the shortest distance to the root.

Return the smallest subtree such that it contains all the deepest nodes in the original tree.

A node is called the deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is tree consisting of that node, plus the set of all descendants of that node.

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation: We return the node with value 2, colored in yellow in the diagram.
The nodes coloured in blue are the deepest nodes of the tree.
Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 is the smallest subtree among them, so we return it.
Example 2:

Input: root = [1]
Output: [1]
Explanation: The root is the deepest node in the tree.
Example 3:

Input: root = [0,1,3,null,2]
Output: [2]
Explanation: The deepest node in the tree is 2, the valid subtrees are the subtrees of nodes 2, 1 and 0 but the subtree of node 2 is the smallest.


Constraints:

The number of nodes in the tree will be in the range [1, 500].
The values of the nodes in the tree are unique.
*/

class Smallest_Subtree_with_all_the_Deepest_Nodes {
    class Result {
        TreeNode node;
        int distance;

        Result(TreeNode n, int d) {
            node = node;
            distance = d;
        }
    }

    /* Solution 1 */
    public TreeNode subtreeWithAllDeepestI(TreeNode root) {
        Map<TreeNode, Integer> depth = new HashMap<TreeNode, Integer>();

        depth.put(null, -1);

        dfs(root, null, depth);

        int max_depth = -1;

        for (Integer d : depth.values()) {
            max_depth = Math.max(max_depth, d);
        }

        return answer(root, max_depth, depth);
    }

    private void dfs(TreeNode current, TreeNode parent, Map<TreeNode, Integer> depth) {
        if (current != null) {
            depth.put(current, depth.get(parent) + 1);
            dfs(current.left, current, depth);
            dfs(current.right, current, depth);
        }
    }

    private TreeNode answer(TreeNode current, int max_depth, Map<TreeNode, Integer> depth) {
        if (current == null || depth.get(current) == max_depth) {
            return current;
        }

        TreeNode left = answer(current.left, max_depth, depth),
                 right = answer(current.right, max_depth, depth);

        return left == null ? right : right == null ? left : current;
    }

    /* Solution 2 */
    public TreeNode subtreeWithAllDeepestII(TreeNode root) {
        if (root == null) return root;

        return dfs(root).node;
    }

    private Result dfs(TreeNode node) {
        if (node == null) return new Result(null, 0);

        Result left = dfs(node.left),
               right = dfs(node.right);

        if (left.distance > right.distance) return new Result(node.left, left.distance + 1);
        if (right.distance > left.distance) return new Result(node.right, right.distance + 1);

        return new Result(node, left.distance + 1);
    }

    /*
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return deep(root).getValue();
    }

    public Pair<Integer, TreeNode> deep(TreeNode root) {
        if (root == null) return new Pair(0, null);
        Pair<Integer, TreeNode> l = deep(root.left), r = deep(root.right);

        int d1 = l.getKey(), d2 = r.getKey();
        return new Pair(Math.max(d1, d2) + 1, d1 == d2 ? root : d1 > d2 ? l.getValue() : r.getValue());
    }
    */
}