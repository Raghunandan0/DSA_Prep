class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int windowsum = 0;
        for(int i=0; i<k; i++) {
            windowsum+=nums[i];
        }
        int max = windowsum;
        for(int i=k; i<nums.length; i++) {
            windowsum += nums[i] - nums[i-k];
            max = Math.max(max, windowsum);
        }
        return(double) max/k;
    }
}