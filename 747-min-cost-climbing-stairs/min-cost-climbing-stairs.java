import java.util.Arrays;

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        
        // We can reach the top from either the last step or the second last step
        return Math.min(solve(n - 1, cost, dp), solve(n - 2, cost, dp));
    }

    private int solve(int i, int[] cost, int[] dp) {
        if (i < 0) return 0;         // no cost if before the start
        if (i == 0) return cost[0];  // base case
        if (i == 1) return cost[1];  // base case
        
        if (dp[i] != -1) return dp[i];
        
        // choose the cheaper path
        dp[i] = cost[i] + Math.min(solve(i - 1, cost, dp), solve(i - 2, cost, dp));
        return dp[i];
    }
}
