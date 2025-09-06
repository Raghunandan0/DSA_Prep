class Solution {
    public int makeConnected(int n, int[][] connections) {
        if(connections.length < n-1){
            return -1;
        }
        int wires = 0;
        DisjointSet dsu = new DisjointSet(n);
        for(int connection[] : connections){
            int u = connection[0];
            int v = connection[1];
            if(dsu.union(u,v)){
                wires++;
            }
        }
        return n-1-wires;
    }
}
public class DisjointSet{
    int parent[];
    int size[];
    DisjointSet(int nodes){
        this.parent = new int [nodes];
        this.size = new int [nodes];
        for(int i = 0; i<nodes; i++){
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }
    public int find(int node){
        if(node == parent[node]){
            return node;
        }
        parent[node] = find(parent[node]);
        return parent[node];
    }
    public boolean union(int node1, int node2){
        //1. find the root parent
        int rootParent1 = find(node1);
        int rootParent2 = find(node2);

        if(rootParent1 == rootParent2 ){
            return false;
        }
        //2.Union of components
        if(size[rootParent1] < size[rootParent2]){
            parent[rootParent1] = rootParent2;
            size[rootParent2] += size[rootParent1];
        } else{
            parent[rootParent2] = rootParent1;
            size[rootParent1] += size[rootParent2];
        }
        return true;
    }
}