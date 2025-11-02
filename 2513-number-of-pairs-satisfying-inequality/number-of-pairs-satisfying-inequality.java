class SegmentTree {
    private class Node {
        int l, r;
        int sum;
        Node lChild;
        Node rChild;
        
        Node(int l, int r) {
            this.l = l;
            this.r = r;
            this.sum = 0;
            this.lChild = null;
            this.rChild = null;
        }
    }
    
    private Node root;
    
    private Node build(int[] nums, int l, int r) {
        Node node = new Node(l, r);
        
        if (l == r) {
            node.sum = nums[l];
            return node;
        }
        
        int mid = (l + r) / 2;
        node.lChild = build(nums, l, mid);
        node.rChild = build(nums, mid + 1, r);
        node.sum = node.lChild.sum + node.rChild.sum;
        
        return node;
    }
    
    private void pointUpdate(Node node, int index, int value) {
        if (node.l == node.r) {
            node.sum = value;
            return;
        }
        
        int mid = (node.l + node.r) / 2;
        if (index <= mid) {
            pointUpdate(node.lChild, index, value);
        } else {
            pointUpdate(node.rChild, index, value);
        }
        node.sum = node.lChild.sum + node.rChild.sum;
    }
    
    private int rangeQuery(Node node, int l, int r) {
        if (l > node.r || r < node.l) {
            return 0;
        }
        
        if (l <= node.l && r >= node.r) {
            return node.sum;
        }
        
        return rangeQuery(node.lChild, l, r) + rangeQuery(node.rChild, l, r);
    }
    
    public SegmentTree(int[] nums) {
        root = build(nums, 0, nums.length - 1);
    }
    
    public void update(int index, int val) {
        pointUpdate(root, index, val);
    }
    
    public int rangeQuery(int left, int right) {
        return rangeQuery(root, left, right);
    }
}

class Solution {
    public Map<Integer, Integer> valueToIndexMap(List<Integer> input) {
        List<Integer> unique_vals = new ArrayList<>(input);
        Collections.sort(unique_vals);
        unique_vals = unique_vals.stream().distinct().collect(Collectors.toList());
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < unique_vals.size(); i++) {
            map.put(unique_vals.get(i), i);
        }
        return map;
    }
    
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        int[] diff_arr = new int[n];
        for (int i = 0; i < n; i++) {
            diff_arr[i] = nums1[i] - nums2[i];
        }
        
        List<Integer> all_vals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            all_vals.add(diff_arr[i]);
            all_vals.add(diff_arr[i] + diff);
        }
        
        Map<Integer, Integer> valueMap = valueToIndexMap(all_vals);
        int m = valueMap.size();
        int[] tree_vals = new int[m];
        SegmentTree st = new SegmentTree(tree_vals);
        
        long result = 0;
        for (int j = 0; j < n; j++) {
            int target = diff_arr[j] + diff;
            int target_idx = valueMap.get(target);
            result += st.rangeQuery(0, target_idx);
            
            int curr_idx = valueMap.get(diff_arr[j]);
            st.update(curr_idx, st.rangeQuery(curr_idx, curr_idx) + 1);
        }
        
        return result;
    }
}