import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Step 1: Create an adjacency list for the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Step 2: Create indegree array and build the graph
        int[] indegree = new int[numCourses];
        for (int[] pair : prerequisites) {
            int course = pair[0];     // course to take
            int pre = pair[1];        // prerequisite course
            graph.get(pre).add(course); // pre → course
            indegree[course]++;         // increase indegree of course
        }

        // Step 3: Add all courses with 0 indegree (no prerequisite) to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i); // we can start with this course
            }
        }

        // Step 4: Do BFS (Kahn’s Algorithm)
        int[] order = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();     // take the course
            order[index++] = curr;       // add it to our result

            // Reduce indegree of all neighbors (next courses)
            for (int next : graph.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next); // ready to take this course
                }
            }
        }

        // Step 5: If all courses are not included, there is a cycle
        if (index != numCourses) {
            return new int[0]; // not possible to finish all
        }

        return order; // return the valid order of courses
    }
}




/* class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge : prerequisites){
            int course = edge[0];
            int prereq = edge[1];
            graph.get(prereq).add(course); // course depends on prereq
        }

        boolean[] vis = new boolean[numCourses];
        boolean[] path = new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < numCourses; i++){
            if(!vis[i]){
                if(isCyclic(graph, vis, path, i, stack)){
                    return new int[0]; // Cycle detected
                }
            }
        }

        // Build the result from the stack
        int[] order = new int[numCourses];
        int i = 0;
        while(!stack.isEmpty()){
            order[i++] = stack.pop();
        }
        return order;
    }

    public boolean isCyclic(List<List<Integer>> graph, boolean[] vis, boolean[] path, int src, Stack<Integer> stack){
        vis[src] = true;
        path[src] = true;

        for(int neighbor : graph.get(src)){
            if(!vis[neighbor]){
                if(isCyclic(graph, vis, path, neighbor, stack)) return true;
            }
            else if(path[neighbor]){
                return true; // Cycle detected
            }
        }

        path[src] = false;
        stack.push(src); // Add to result after visiting all neighbors
        return false;
    }
}  */