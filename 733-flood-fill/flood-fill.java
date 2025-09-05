class Solution {
    public void helper(int[][] image, int sr, int sc, int color, boolean[][] vis, int orgCol) {
    if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length 
        || vis[sr][sc] || image[sr][sc] != orgCol) {
        return;
    }

    vis[sr][sc] = true; //  Mark as visited
    image[sr][sc] = color;

    // left
    helper(image, sr, sc - 1, color, vis, orgCol);
    // right
    helper(image, sr, sc + 1, color, vis, orgCol);
    // up
    helper(image, sr - 1, sc, color, vis, orgCol);
    // down
    helper(image, sr + 1, sc, color, vis, orgCol);
}

public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    boolean[][] vis = new boolean[image.length][image[0].length];
    int orgCol = image[sr][sc];
    
    // If the new color is same as original, no need to fill
    if (orgCol == color) return image;

    helper(image, sr, sc, color, vis, orgCol);
    return image;
}
}