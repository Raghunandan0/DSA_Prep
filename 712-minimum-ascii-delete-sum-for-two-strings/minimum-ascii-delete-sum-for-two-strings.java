class Solution {
    public int minimumDeleteSum(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + word1.charAt(i-1); // add ASCII value
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int lcsAscii = dp[n][m];  // max ASCII sum of common subsequence
        int totalAscii1 = 0, totalAscii2 = 0;

        for (char c : word1.toCharArray()) totalAscii1 += c;
        for (char c : word2.toCharArray()) totalAscii2 += c;

        return (totalAscii1 + totalAscii2 - 2 * lcsAscii);
    }
}
