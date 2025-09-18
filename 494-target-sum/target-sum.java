class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        for (int num : nums) total += num;

        // Edge case: invalid target
        if ((target + total) % 2 != 0 || target > total || target < -total) {
            return 0;
        }

        int sum = (target + total) / 2;
        int n = nums.length;

        // dp[i][j] = number of ways to form sum j using first i elements
        int[][] dp = new int[n + 1][sum + 1];
        dp[0][0] = 1; // 1 way to form sum 0 with 0 elements

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j]; // exclude nums[i-1]
                if (nums[i - 1] <= j) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]]; // include nums[i-1]
                }
            }
        }

        return dp[n][sum];
    }
}
