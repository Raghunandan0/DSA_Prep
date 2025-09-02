// class Solution {
//     public void dfs(int src, List<List<Integer>> adjList, boolean visited[]){
//         visited[src] = true;
//         for(int neighbour : adjList.get(src)){
//             if(!visited[neighbour]){
//                 dfs(neighbour, adjList, visited);
//             }
//         }
//     }

//     public int removeStones(int[][] stones) {
//         //Create the adjList
//         int n = stones.length;
//         List<List<Integer>> adjList = new ArrayList<>();
//         for(int i=0; i<n; i++){
//             adjList.add(new ArrayList<>());
//         }

//         for(int i=0; i<n; i++){
//             for(int j=i+1; j<n; j++){
//                 if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]){
//                     adjList.get(i).add(j);
//                     adjList.get(j).add(i);
//                 }
//             }
//         }  

//         int components = 0;
//         boolean visited[] = new boolean[n];
//         for(int i=0; i<n ; i++){
//             if(!visited[i]){
//                 dfs(i, adjList, visited);
//                 components++;
//             }
//         }
//         return n - components;
//     }
// }



// Using DSU

//T.C : O(n^2 * α(n))
//S.C : O(n)
class Solution {
    private int[] parent;
    private int[] rank;
    private int n;

    // Find function with path compression
    private int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]); // Path compression
        }
        return parent[i];
    }

    // Union function with union by rank
    private void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        
        if (rootI != rootJ) {
            if (rank[rootI] > rank[rootJ]) {
                parent[rootJ] = rootI;
            } else if (rank[rootI] < rank[rootJ]) {
                parent[rootI] = rootJ;
            } else {
                parent[rootJ] = rootI;
                rank[rootI]++;
            }
        }
    }

    public int removeStones(int[][] stones) {
        n = stones.length;
        parent = new int[n];
        rank = new int[n];

        // Initialize parent and rank arrays
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        // Union stones that are in the same row or column
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    union(i, j);
                }
            }
        }

        // Count the number of disjoint sets (connected components)
        int groups = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                groups++;
            }
        }

        // The number of stones that can be removed is total stones - number of groups
        return n - groups;
    }
}