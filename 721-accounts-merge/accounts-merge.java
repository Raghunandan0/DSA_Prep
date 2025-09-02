// //User function Template for Java
// class DisjointSet {
//     List<Integer> rank = new ArrayList<>();
//     List<Integer> parent = new ArrayList<>();
//     List<Integer> size = new ArrayList<>();
//     public DisjointSet(int n) {
//         for (int i = 0; i <= n; i++) {
//             rank.add(0);
//             parent.add(i);
//             size.add(1);
//         }
//     }

//     public int findUPar(int node) {
//         if (node == parent.get(node)) {
//             return node;
//         }
//         int ulp = findUPar(parent.get(node));
//         parent.set(node, ulp);
//         return parent.get(node);
//     }

//     public void unionByRank(int u, int v) {
//         int ulp_u = findUPar(u);
//         int ulp_v = findUPar(v);
//         if (ulp_u == ulp_v) return;
//         if (rank.get(ulp_u) < rank.get(ulp_v)) {
//             parent.set(ulp_u, ulp_v);
//         } else if (rank.get(ulp_v) < rank.get(ulp_u)) {
//             parent.set(ulp_v, ulp_u);
//         } else {
//             parent.set(ulp_v, ulp_u);
//             int rankU = rank.get(ulp_u);
//             rank.set(ulp_u, rankU + 1);
//         }
//     }

//     public void unionBySize(int u, int v) {
//         int ulp_u = findUPar(u);
//         int ulp_v = findUPar(v);
//         if (ulp_u == ulp_v) return;
//         if (size.get(ulp_u) < size.get(ulp_v)) {
//             parent.set(ulp_u, ulp_v);
//             size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
//         } else {
//             parent.set(ulp_v, ulp_u);
//             size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
//         }
//     }
// }

// public class DisjointSet {
//     int parent[];
//     int size[];

//     DisjointSet(int nodes) {
//         this.parent = new int[nodes];
//         this.size = new int[nodes];
//         for (int i = 0; i < nodes; i++) {
//             this.parent[i] = i;
//             this.size[i] = 1;
//         }
//     }

//     public int find(int node) {
//         if (node == parent[node]) {
//             return node;
//         }
//         parent[node] = find(parent[node]);
//         return parent[node];
//     }

//     public boolean union(int node1, int node2) {
//         //1. find the root parent
//         int rootParent1 = find(node1);
//         int rootParent2 = find(node2);

//         if (rootParent1 == rootParent2) {
//             return false;
//         }
//         //2.Union of components
//         if (size[rootParent1] < size[rootParent2]) {
//             parent[rootParent1] = rootParent2;
//             size[rootParent2] += size[rootParent1];
//         } else {
//             parent[rootParent2] = rootParent1;
//             size[rootParent1] += size[rootParent2];
//         }
//         return true;
//     }
// }
// class Solution {
//     static List<List<String>> accountsMerge(List<List<String>> details) {
//         int n = details.size();
//         DisjointSet ds = new DisjointSet(n);
//         HashMap<String, Integer> mapMailNode = new HashMap<String, Integer>();

//         for (int i = 0; i < n; i++) {
//             for (int j = 1; j < details.get(i).size(); j++) {
//                 String mail = details.get(i).get(j);
//                 if (mapMailNode.containsKey(mail) == false) {
//                     mapMailNode.put(mail, i);
//                 } else {
//                     ds.union(i, mapMailNode.get(mail));
//                 }
//             }
//         }

//         ArrayList<String>[] mergedMail = new ArrayList[n];
//         for (int i = 0; i < n; i++) mergedMail[i] = new ArrayList<String>();
//         for (Map.Entry<String, Integer> it : mapMailNode.entrySet()) {
//             String mail = it.getKey();
//             int node = ds.find(it.getValue());
//             mergedMail[node].add(mail);
//         }

//         List<List<String>> ans = new ArrayList<>();

//         for (int i = 0; i < n; i++) {
//             if (mergedMail[i].size() == 0) continue;
//             Collections.sort(mergedMail[i]);
//             List<String> temp = new ArrayList<>();
//             temp.add(details.get(i).get(0));
//             for (String it : mergedMail[i]) {
//                 temp.add(it);
//             }
//             ans.add(temp);
//         }
//         return ans;

//     }
// }



class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        int[] parent = new int[n]; // parent[i] = parent index for DSU
        int[] size = new int[n];   // size[i] = size of component with root i

        // Initialize DSU arrays
        for (int i = 0; i < n; i++) {
            size[i] = 1;
            parent[i] = i;
        }

        // Map to store email -> account index
        HashMap<String, Integer> map = new HashMap<>();
        // For each account, map all emails to their account representative
        for (int i = 0; i < n; i++) {
            List<String> acc = accounts.get(i);
            // Start from index 1 (skip name at index 0)
            for (int j = 1; j < acc.size(); j++) {
                String email = acc.get(j);
                if (!map.containsKey(email)) {
                    // First time seeing this email, map it to current account
                    map.put(email, i);
                } else {
                    // Email already seen, union current account with previous one
                    unionbysize(i, map.get(email), parent, size);
                }
            }
        }

        // Group emails by their parent account/component
        HashMap<Integer, List<String>> comp = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int i = entry.getValue();
            int u = findupar(i, parent); // Find representative parent
            String email = entry.getKey();
            comp.computeIfAbsent(u, k -> new ArrayList<>()).add(email);
        }

        // Prepare the result list
        List<List<String>> result = new ArrayList<>();
        for (int i : comp.keySet()) {
            List<String> emails = comp.get(i);
            Collections.sort(emails); // Sort emails lexicographically
            // Prepend the account name (from original accounts list)
            emails.add(0, accounts.get(i).get(0));
            result.add(emails);
        }
        return result;
    }

    // Find with path compression
    int findupar(int node, int[] parent) {
        if (node == parent[node]) return node;
        return parent[node] = findupar(parent[node], parent);
    }

    // Union by size to keep tree shallow
    void unionbysize(int u, int v, int[] parent, int[] size) {
        int ulp_u = findupar(u, parent); // ultimate parent of u
        int ulp_v = findupar(v, parent); // ultimate parent of v

        if (ulp_u == ulp_v) return; // already in the same set

        // Attach smaller tree to the larger tree
        if (size[ulp_u] >= size[ulp_v]) {
            size[ulp_u] += size[ulp_v];
            parent[ulp_v] = ulp_u;
        } else {
            size[ulp_v] += size[ulp_u];
            parent[ulp_u] = ulp_v;
        }
    }
}