class Solution {
    public int splitArray(int[] nums, int k) {
        
        int high = 0, low = 0;
        for(int num: nums) {
            low = Math.max(low, num);
            high += num;
        }

        while(high > low) {
            int mid = low + (high - low) / 2;
            if(check(nums, mid, k)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean check(int[] nums, int target, int m) {
        int sum = 0;
        int numberOfSubarray = 1;
        for(int num: nums) {
            sum += num;
            if(sum > target) {
                numberOfSubarray++;
                sum = num;
            }
        }
        return numberOfSubarray <= m;
    }
}