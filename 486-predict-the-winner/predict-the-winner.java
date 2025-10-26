class Solution {
    public boolean predictTheWinner(int[] nums) {
       Integer dp[][] = new Integer[nums.length+1][nums.length+1];
        if(fun(nums,0,nums.length-1,dp)>=0) return true;
        return false;
        
    }
    int fun(int a[],int start,int end,Integer dp[][]){
        if( start==end) return a[start];
        if(dp[start][end]!=null) return dp[start][end];

        int pickstarting = a[start] - fun(a,start+1,end,dp);
        int pickending = a[end] - fun(a,start,end-1,dp);

        return dp[start][end]=Math.max(pickstarting,pickending);
    }
}