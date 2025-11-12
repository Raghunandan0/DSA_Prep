// class Solution {
//        public int[] numsSameConsecDiff(int N, int K) {
//         List<Integer> cur = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//         for (int i = 2; i <= N; ++i) {
//             List<Integer> cur2 = new ArrayList<>();
//             for (int x : cur) {
//                 int y = x % 10;
//                 if (y + K < 10)
//                     cur2.add(x * 10 + y + K);
//                 if (K > 0 && y - K >= 0)
//                     cur2.add(x * 10 + y - K);
//             }
//             cur = cur2;
//         }
//         return cur.stream().mapToInt(j->j).toArray();
//     }
// }



class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        for(int i=1;i<=9;i++){
            helper(i,n-1,k);
        }
        int ans[] = new int[list.size()];
        for(int i=0;i<list.size();i++){
            ans[i]=list.get(i);
        }

        return ans;
        
    }
    List<Integer>list = new ArrayList<>();

    public void helper(int curr, int digitLeft, int k){
        int lastDig = curr%10;
        if(digitLeft==0){
            list.add(curr);
            return;
        }
        if(lastDig+k<=9){
            helper(curr*10+lastDig+k, digitLeft-1, k);
        }
        if(k!=0 && lastDig-k>=0){ // lastDig+k == lastDig-k when k==0; to avoid this duplicacy we will check k!=0 before calling for lastDig-k
            helper(curr*10+lastDig-k, digitLeft-1,k);
        }
    }
}