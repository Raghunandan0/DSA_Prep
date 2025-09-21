class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        for (int i = 1; i <= amount; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }

        for (int idx = 1; idx <= n; idx++) {
            for (int amt = 1; amt <= amount; amt++) {
                int noPick = dp[idx - 1][amt];
                int pick = Integer.MAX_VALUE;
                if (amt >= coins[idx - 1]) {
                    int res = dp[idx][amt - coins[idx - 1]];
                    if (res != Integer.MAX_VALUE) {
                        pick = 1 + res;
                    }
                }
                dp[idx][amt] = Math.min(pick, noPick);
            }
        }
        return dp[n][amount] == Integer.MAX_VALUE ? -1 : dp[n][amount];
    }
}