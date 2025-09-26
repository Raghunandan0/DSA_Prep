class Solution {
    HashMap<String,HashMap<String,Double>> graph = new HashMap<>();
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Build the graph
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);  // a -> dividend 
            String b = equations.get(i).get(1);  // b -> divisor 
            Double val = values[i];
            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());

            graph.get(a).put(b,val);
            graph.get(b).put(a,1 / val);
        }
        double[] results = new double[queries.size()];
        for (int i = 0; i<queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            HashSet<String> visited = new HashSet<>();  // to avoid duplicates
            results[i] = dfs(start, end, visited, 1.0);
        }
        return results;
     
        }
    private double dfs(String start, String end, HashSet<String> visited, double accProduct) 
        {
            if (!graph.containsKey(start) || !graph.containsKey(end)) return -1.0; // single answer cannot be determined, return -1.0.
            if (start.equals(end)) return accProduct;
            visited.add(start);
            // dict look like {a:{b:val,c:val},x:{y:val,z:val}} so when we loop with entrySet() it returns entry val one by one
            for (Map.Entry<String, Double> neighbor : graph.get(start).entrySet()) {
                if (!visited.contains(neighbor.getKey())) {
                    double result = dfs(neighbor.getKey(), end, visited, accProduct * neighbor.getValue());
                    if (result != -1.0) return result; // if any all neighbor variable determined then return result

                }
            }
            return -1.0;

        }
}