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
    public int pathSum(TreeNode root, int targetSum) {
        List<Long> path = new ArrayList<>();
        return helper(root, targetSum, path);
    }

    int helper(TreeNode node, int targetSum, List<Long> path) {
        if(node == null) return 0;

        path.add((long) node.val);
        long sum = 0;
        int count = 0;

        ListIterator<Long> itr = path.listIterator(path.size());
        while(itr.hasPrevious()) {
            sum += itr.previous();
            if(sum == targetSum) count++;
        }

        count += helper(node.left, targetSum, path);
        count += helper(node.right, targetSum, path);

         path.remove(path.size() - 1); // Backtrack

        return count;
    }
}