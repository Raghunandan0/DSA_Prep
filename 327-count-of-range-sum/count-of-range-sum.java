import java.util.*;

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
    private Map<Long, Integer> valueToIndexMap(List<Long> input) {
        Collections.sort(input);
        input = new ArrayList<>(new LinkedHashSet<>(input));
        Map<Long, Integer> mp = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            mp.put(input.get(i), i);
        }
        return mp;
    }
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        List<Long> allValues = new ArrayList<>();
        for (long sum : prefixSum) {
            allValues.add(sum);
            allValues.add(sum - upper);
            allValues.add(sum - lower);
        }
        
        Map<Long, Integer> mp = valueToIndexMap(allValues);
        int compressedSize = mp.size();
        
        int[] freq = new int[compressedSize];
        SegmentTree st = new SegmentTree(freq);
        
        int result = 0;
        for (int i = 0; i <= n; i++) {
            if (i > 0) {
                long leftBound = prefixSum[i] - upper;
                long rightBound = prefixSum[i] - lower;
                
                int leftIdx = mp.get(leftBound);
                int rightIdx = mp.get(rightBound);
                
                result += st.rangeQuery(leftIdx, rightIdx);
            }
            
            int currIdx = mp.get(prefixSum[i]);
            int currFreq = st.rangeQuery(currIdx, currIdx);
            st.update(currIdx, currFreq + 1);
        }
        
        return result;
    }
}