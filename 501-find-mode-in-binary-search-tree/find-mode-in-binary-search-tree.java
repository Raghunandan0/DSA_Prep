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
    public int[] findMode(TreeNode root) {
       if (root == null) return new int[0]; // empty tree case
        
        // Special case: only one node in the tree
        if (root.left == null && root.right == null) {
            return new int[]{root.val};
        }

        Map<Integer, Integer> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // BFS to count frequencies
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            map.put(curr.val, map.getOrDefault(curr.val, 0) + 1);

            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }

        // Find max frequency
        int maxFreq = 0;
        for (int freq : map.values()) {
            if (freq > maxFreq) maxFreq = freq;
        }

        // Collect all keys with max frequency
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxFreq) {
                result.add(entry.getKey());
            }
        }

        // Convert list to array
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}