import java.util.*;

class DisjointSet {
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            parent.add(i);
            size.add(1);
        }
    }

    public int findUPar(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp); // path compression
        return parent.get(node);
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
}

class Solution {
    private boolean isValid(int r, int c, int n) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n * n);

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};

        // Step 1: Union all 1's
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int newr = row + dr[k];
                    int newc = col + dc[k];
                    if (isValid(newr, newc, n) && grid[newr][newc] == 1) {
                        int nodeNo = row * n + col;
                        int adjNodeNo = newr * n + newc;
                        ds.unionBySize(nodeNo, adjNodeNo);
                    }
                }
            }
        }

        int maxIsland = 0;

        // Step 2: Try converting 0 -> 1 and calculate max island
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) continue;
                HashSet<Integer> components = new HashSet<>();
                for (int k = 0; k < 4; k++) {
                    int newr = row + dr[k];
                    int newc = col + dc[k];
                    if (isValid(newr, newc, n) && grid[newr][newc] == 1) {
                        components.add(ds.findUPar(newr * n + newc));
                    }
                }
                int newSize = 1; // the flipped cell
                for (Integer parent : components) {
                    newSize += ds.size.get(parent);
                }
                maxIsland = Math.max(maxIsland, newSize);
            }
        }

        // Step 3: If grid already full of 1's
        for (int cell = 0; cell < n * n; cell++) {
            if (grid[cell / n][cell % n] == 1) {
                maxIsland = Math.max(maxIsland, ds.size.get(ds.findUPar(cell)));
            }
        }

        return maxIsland;
    }
}
