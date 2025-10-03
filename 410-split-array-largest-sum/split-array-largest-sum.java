class Solution {
    public int splitArray(int[] nums, int k) {
        int s = start(nums);
        int e = end(nums);
        return bs(s,e,nums,k);

    }
    int bs(int s,int e,int[] nums, int k){
        while(s<e){
            int mid= (s+e)/2;
            if(pieces(nums,mid)<=k){
                e=mid;
            }
            else{
                s=mid+1;
            }
        }
        return e;
    }
    int pieces(int[] nums, int sum){
        int cus =0;
        int piece = 1;
        for(int i : nums){
            cus=cus+i;
            if(cus>sum){
                piece+=1;
                cus=i;
            }
        }
        return piece;
    }

    int end(int[] nums){
        int sum = 0;
        for(int i : nums){
            sum+=i;
        }
        return sum;
    }
    int start(int[] nums){
        int max = nums[0];
        for(int i : nums){
            if(i>max){
                max=i;
            }
        }
        return max;
    }
}