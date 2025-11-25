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
    public boolean findTarget(TreeNode root, int k) {
        ArrayList<Integer> inorder = new ArrayList<>();
         inorderTraversal(root,inorder);
         int left= 0;
         int right = inorder.size()-1;
         while(left < right){
            int sum = inorder.get(left)+ inorder.get(right);
            if(sum==k){
                return true;
            }
            if(sum<k){
                left++;
            }else{
                right--;
            }
         }
         return false;
    }
    public void inorderTraversal(TreeNode root,ArrayList<Integer> inorder){
        if(root == null){
            return;
        }
         inorderTraversal(root.left,inorder); //left
         inorder.add(root.val);
        inorderTraversal(root.right,inorder); //right
    }
}