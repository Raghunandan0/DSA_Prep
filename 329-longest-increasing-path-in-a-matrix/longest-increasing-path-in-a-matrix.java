class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int ROW = matrix.length;
        int COL = matrix[0].length;

        int[][] memo = new int[ROW][COL];

        int max = Integer.MIN_VALUE;

        for(int i = 0; i < ROW; i++) {
            for(int j = 0; j < COL; j++) {
               int currMax = dfs(matrix, i, j, -1, memo);
               max = Math.max(max, currMax);
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int previous, int[][] memo) {
        if(!isInBound(matrix, i, j)) return 0;
        if(previous >= matrix[i][j]) return 0;
        if(memo[i][j] != 0) return memo[i][j];


        int curr = matrix[i][j];

        int top = dfs(matrix, i-1, j, curr, memo);
        int bottom = dfs(matrix, i+1, j, curr, memo);
        int left = dfs(matrix, i, j-1, curr, memo);
        int right = dfs(matrix, i, j+1, curr, memo);

        int max = top;
        max = Math.max(max, bottom);
        max = Math.max(max, left);
        max = Math.max(max, right);


        memo[i][j] = max + 1;
        return memo[i][j];
    }

    private boolean isInBound(int[][] matrix, int i, int j) {
        return i >=0 && j >= 0 && i < matrix.length && j < matrix[i].length;
    }
}