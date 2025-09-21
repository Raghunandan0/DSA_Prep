class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int INF = (int)1e9; // large number
        int dp[][] = new int[n+1][amount+1];

        // base cases
        for (int j = 0; j <= amount; j++) {
            dp[0][j] = INF;   // with 0 coins, can't form any positive amount
        }
        dp[0][0] = 0; // 0 coins needed to form amount 0

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                if (coins[i-1] <= j) {
                    dp[i][j] = Math.min(dp[i-1][j], 1 + dp[i][j - coins[i-1]]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][amount] >= INF ? -1 : dp[n][amount];
    }
}
