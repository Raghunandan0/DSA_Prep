// class Solution {
//     public int numberOfArithmeticSlices(int[] nums) {
//         int count = 0;
//         long sum = 0;

//         for (int i = 1; i < nums.length - 1; i++) {
//             if (nums[i] * 2 == nums[i - 1] + nums[i + 1]) {
//                 if (count == 0)
//                     count += 3; 
//                 else
//                     count += 1;
//             } else {
//                 if (count >= 3) { 
//                     long totalSubArray = ((long) count * (long) (count + 1)) / 2;
//                     totalSubArray -= count;         
//                     totalSubArray -= (count - 1);  
//                     sum += totalSubArray;
//                 }
//                 count = 0;
//             }
//         }

//         if (count >= 3) {
//             long totalSubArray = ((long) count * (long) (count + 1)) / 2;
//             totalSubArray -= count;
//             totalSubArray -= (count - 1);
//             sum += totalSubArray;
//         }

//         return (int) sum;
//     }
// }

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) return 0;  
        
        int sum = 0; 
        int count = 0;   
        int diff = nums[1] - nums[0];

        for (int i = 2; i < nums.length; i++) {
            int currDiff = nums[i] - nums[i - 1]; 

            if (currDiff == diff) {
                count++; 
            } else {
                sum += (count * (count + 1)) / 2; 
                count = 0;  
                diff = currDiff; 
            }
        }

        sum += (count * (count + 1)) / 2;

        return sum; 
    }
}