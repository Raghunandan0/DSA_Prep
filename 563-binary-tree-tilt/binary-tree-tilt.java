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
class Solution {
    int ans = 0;

    public int findTilt(TreeNode root) {
        difference(root);
        return ans;
    }

    public int difference(TreeNode root){
        if(root == null)return 0;

        int left = difference(root.left);
        int right = difference(root.right);

        ans+= Math.abs(left-right);
        return left+right+root.val;
    }
}