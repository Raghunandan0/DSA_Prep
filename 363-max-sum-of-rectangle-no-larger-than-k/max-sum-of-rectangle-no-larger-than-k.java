import java.util.*;
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int res = Integer.MIN_VALUE;

        // Iterate over left boundary of rectangle
        for (int left = 0; left < n; left++) {
            int[] rowSum = new int[m]; // compress rows between left & right columns
            for (int right = left; right < n; right++) {
                // Update rowSum for this column range
                for (int i = 0; i < m; i++) {
                    rowSum[i] += matrix[i][right];
                }

                // Find max subarray sum ≤ k for rowSum
                res = Math.max(res, maxSubArrayNoLargerThanK(rowSum, k));
                if (res == k) return res; // optimal early exit
            }
        }
        return res;
    }

    private int maxSubArrayNoLargerThanK(int[] arr, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int prefixSum = 0, max = Integer.MIN_VALUE;

        for (int num : arr) {
            prefixSum += num;
            // We need smallest prefix >= prefixSum - k
            Integer target = set.ceiling(prefixSum - k);
            if (target != null) {
                max = Math.max(max, prefixSum - target);
            }
            set.add(prefixSum);
        }
        return max;
    }
}