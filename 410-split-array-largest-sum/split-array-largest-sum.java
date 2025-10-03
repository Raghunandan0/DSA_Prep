class Solution {
    public int splitArray(int[] nums, int k) {

// setting start s=max and end e=sum 
        int s=-1, e=0, m;
        for(int x: nums){
            if(x>s) s=x;
            e+=x;
        }

        while(s<e){
            m=(s+e)/2;
// checcking for possible subarray and counting no of pieces p.
            int ar=0,p=1;
            for(int x: nums){
                ar+=x;
                if(ar>m){
                    ar=x;
                    p++;
                }
            }
// if piece p is more than allowed, size should be increased
// if piece is less or equal, then trying to reduce size
            if(p>k) s=m+1;
            else e=m;
        }
         return s;
        
    }
}