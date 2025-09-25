class Solution {
    public static int minOperations(String word1, String word2) {
        int n = word1.length();
        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();
        
        int[][] dp = new int[n + 1][n + 1];
        
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= n; j++)
                dp[i][j] = Integer.MAX_VALUE;

        dp[0][0] = 0; 

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (dp[i][i] == Integer.MAX_VALUE) continue;
                
                int len = j - i + 1;
                int costNormal = mininumOpr(arr1, arr2, i, j, false);
                int costReverse = mininumOpr(arr1, arr2, i, j, true) + 1;

                int totalMin = Math.min(costNormal, costReverse);
                if (dp[j + 1][j + 1] > dp[i][i] + totalMin) {
                    dp[j + 1][j + 1] = dp[i][i] + totalMin;
                }
            }

            for (int k = i + 1; k <= n; k++) {
                dp[i][k] = Math.min(dp[i][k], dp[i][k - 1]);
            }
        }

        return dp[n][n];
    }

    static int mininumOpr(char[] arr1, char[] arr2, int i, int j, boolean isReversed) {
        int operations = 0;
        int x = i, idx = isReversed ? j : i, mul = isReversed ? -1 : 1;
        int[][] freq = new int[26][26];

        for (int k = i; k <= j; k++) {
            if (arr1[x] != arr2[idx]) {
                char wanted = arr1[x], got = arr2[idx];
                if (freq[got - 'a'][wanted - 'a'] > 0) {
                    freq[got - 'a'][wanted - 'a']--;
                } else {
                    freq[wanted - 'a'][got - 'a']++;
                    operations++;
                }
            }
            x++;
            idx += mul;
        }

        return operations;
    }
}