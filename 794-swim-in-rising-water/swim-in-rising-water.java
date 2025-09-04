import java.util.*;

public class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];

        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));
        pq.add(new Cell(0, 0, grid[0][0]));

        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            int r = curr.row, c = curr.col, time = curr.time;

            if (visited[r][c]) continue;
            visited[r][c] = true;

            if (r == n - 1 && c == n - 1) return time;

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nc >= 0 && nr < n && nc < n && !visited[nr][nc]) {
                    pq.add(new Cell(nr, nc, Math.max(time, grid[nr][nc])));
                }
            }
        }

        return -1; // Should never reach here
    }

    // Helper class to represent a cell with its time (max elevation so far)
    static class Cell {
        int row, col, time;

        public Cell(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}