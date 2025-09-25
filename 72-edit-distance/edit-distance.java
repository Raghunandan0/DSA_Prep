// class Solution {
//     public int minDistance(String word1, String word2) {
//         int n = word1.length();
//         int m = word2.length();

//         int dp[][] = new int[n+1][m+1];
        
//         //initialize
//         for(int i=0; i<n+1; i++){
//             for(int j=0; j<m+1; j++){
//                 if(i==0){
//                     dp[i][j] = j;
//                 }
//                 if(j==0){
//                     dp[i][j] = i;
//                 }
//             }
//         }
//         //bootom up
//         for(int i=1; i<n+1; i++){
//             for(int j=1; j<m+1; j++){
//                 if(word1.charAt(i-1)==word2.charAt(j-1)){
//                     dp[i][j] = dp[i-1][j-1];
//                 } else {
//                     int add = dp[i][j-1]+1;
//                     int del = dp[i-1][j]+1;
//                     int repl = dp[i-1][j-1]+1;
//                     dp[i][j] = Math.min(add,Math.min(del, repl));
//                 }
//             }
//         }
//         return dp[n][m];
//     }
// }


class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];
        
        // initialize
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i; // delete all chars from word1
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j; // insert all chars into word1
        }

        // bottom up
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // no change
                } else {
                    int add = dp[i][j - 1] + 1;     // insert
                    int del = dp[i - 1][j] + 1;     // delete
                    int repl = dp[i - 1][j - 1] + 1; // replace
                    dp[i][j] = Math.min(add, Math.min(del, repl));
                }
            }
        }

        return dp[n][m];
    }
}
