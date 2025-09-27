class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        long sum = 0;   // total sum of nums
        long f0 = 0;    // initial F(0)
        
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f0 += (long) i * nums[i];
        }
        
        long max = f0;
        long curr = f0;
        
        // Tabulation: compute F(k) using relation
        for (int k = 1; k < n; k++) {
            curr = curr + sum - (long) n * nums[n - k];
            max = Math.max(max, curr);
        }
        
        return (int) max;
    }
}
