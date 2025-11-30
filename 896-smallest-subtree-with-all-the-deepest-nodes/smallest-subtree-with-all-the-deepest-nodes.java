/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Pair {
    TreeNode node;
    int depth;

    Pair(TreeNode node, int depth) {
        this.node = node;
        this.depth = depth;
    }
}

class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Pair LCA_Node = dfs(root);
        return LCA_Node.node;
    }

    Pair dfs(TreeNode node) {
        if (node == null) {
            return new Pair(node, 0);
        }

        Pair left = dfs(node.left);
        Pair right = dfs(node.right);

        if (right.depth == left.depth) {
            return new Pair(node, right.depth + 1);
        } else if (right.depth > left.depth) {
            return new Pair(right.node, right.depth + 1);
        } else {
            return new Pair(left.node, left.depth + 1);
        }
    }
}