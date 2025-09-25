class Solution {

    // Main function: minimum operations to convert word1 -> word2
    public static int minOperations(String word1, String word2) {
        int n = word1.length();
        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();
        
        // DP table: dp[i][i] = min operations to convert first i chars
        int[][] dp = new int[n + 1][n + 1];
        
        // Initialize DP table with large number
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= n; j++)
                dp[i][j] = Integer.MAX_VALUE;

        dp[0][0] = 0; // base case: empty string → empty string requires 0 operations

        // Iterate over the start index
        for (int i = 0; i < n; i++) {

            // Iterate over the end index of substring
            for (int j = i; j < n; j++) {
                if (dp[i][i] == Integer.MAX_VALUE) continue; // skip impossible states

                // Calculate cost of converting substring normally
                int costNormal = calculateOperations(arr1, arr2, i, j, false);

                // Calculate cost if we reverse the substring (add 1 for reverse operation)
                int costReverse = calculateOperations(arr1, arr2, i, j, true) + 1;

                // Choose the minimum cost
                int totalMin = Math.min(costNormal, costReverse);

                // Update dp for converting first j+1 characters
                dp[j + 1][j + 1] = Math.min(dp[j + 1][j + 1], dp[i][i] + totalMin);
            }

            // Make sure dp[i][k] is minimum for future iterations
            for (int k = i + 1; k <= n; k++) {
                dp[i][k] = Math.min(dp[i][k], dp[i][k - 1]);
            }
        }

        return dp[n][n]; // result: min operations for full strings
    }

    // Function to calculate minimum operations for a substring
    // If isReversed = true, compare arr1[i..j] with arr2[j..i] (reversed)
    static int calculateOperations(char[] arr1, char[] arr2, int i, int j, boolean isReversed) {
        int operations = 0;
        int x = i;
        int idx = isReversed ? j : i;
        int step = isReversed ? -1 : 1;

        // freq matrix helps track swaps
        int[][] freq = new int[26][26];

        for (int k = i; k <= j; k++) {
            if (arr1[x] != arr2[idx]) {
                char from = arr1[x];
                char to = arr2[idx];

                // If we already have a swap available, use it
                if (freq[to - 'a'][from - 'a'] > 0) {
                    freq[to - 'a'][from - 'a']--;
                } else {
                    freq[from - 'a'][to - 'a']++;
                    operations++; // count this as an operation
                }
            }
            x++;
            idx += step;
        }

        return operations;
    }
}
