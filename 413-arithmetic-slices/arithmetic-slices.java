class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int count = 0;
        long sum = 0;

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] * 2 == nums[i - 1] + nums[i + 1]) {
                if (count == 0)
                    count += 3; 
                else
                    count += 1;
            } else {
                if (count >= 3) { 
                    long totalSubArray = ((long) count * (long) (count + 1)) / 2;
                    totalSubArray -= count;         
                    totalSubArray -= (count - 1);  
                    sum += totalSubArray;
                }
                count = 0;
            }
        }

        if (count >= 3) {
            long totalSubArray = ((long) count * (long) (count + 1)) / 2;
            totalSubArray -= count;
            totalSubArray -= (count - 1);
            sum += totalSubArray;
        }

        return (int) sum;
    }
}