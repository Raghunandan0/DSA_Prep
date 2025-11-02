class Solution {
    public int reversePairs(int[] nums) {
      int[] ans = {0};
      mergeSort(nums , 0 , nums.length-1 , ans );
        return ans[0] ;
    }
    public void mergeSort(int[] arr , int low , int high , int[] ans ){
        if(low >= high ){
            return ;
        }
        int mid = (low + high )/2;
        mergeSort(arr , low , mid  , ans );
        mergeSort(arr , mid+1 , high , ans);
        merge(arr , low , mid , high , ans );

    }
    void merge(int[] arr , int low ,  int mid , int high , int[] ans ){
        int left = low ;
        int right = mid + 1; 
        while(left <= mid  && right <= high  ){
            if((long) arr[left] > 2L * arr[right]){
                ans[0]+= mid-left + 1 ;
                right++;
            }
            else{
                left++;
            }
        }
        int lefty = low ;
        int righty = mid + 1;
        List<Integer> temp = new ArrayList<>();

        while(lefty <= mid  && righty <= high  ){
            if(arr[lefty] >= arr[righty]){
            temp.add(arr[righty++]);
            }else{
            temp.add(arr[lefty++]);
            }
        }
        while(lefty <= mid){
            temp.add(arr[lefty++]);
        }
        while(righty <= high){
            temp.add(arr[righty++]);
        }

        for(int i = low ; i <= high ; i++){
            arr[i] = temp.get(i-low);
        }
    }
}