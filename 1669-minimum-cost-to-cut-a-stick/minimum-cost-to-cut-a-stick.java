class Solution {
    int[][] dp;
    int[] cuts;

    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        this.cuts = cuts;
        dp = new int[cuts.length][cuts.length];
        return cost(0, cuts.length - 1, 0, n);
    }

    private int cost(int i, int j, int start, int end) {
        if (i > j) {
            return 0; // no cuts to perform
        }
        if (dp[i][j] != 0) {
            return dp[i][j]; // already computed
        }
        int minCost = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            int fs = cost(i, k - 1, start, cuts[k]); // left stick
            int ss = cost(k + 1, j, cuts[k], end);   // right stick
            int self = end - start;                  // cost of current cut
            minCost = Math.min(minCost, fs + ss + self);
        }
        return dp[i][j] = minCost;
    }
}