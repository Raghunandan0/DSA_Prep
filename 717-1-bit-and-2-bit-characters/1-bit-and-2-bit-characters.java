// class Solution {
//     public boolean isOneBitCharacter(int[] bits) {
//         int ones = 0;
//         for(int i=bits.length-2; i >= 0 && bits[i] != 0; i--){
//             ones++;
//         }
//         if(ones % 2 > 0) return false;
//         return true;
//     }
// }

class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        while(i < bits.length - 1){
            if(bits[i] == 0) {
                i++;
            }
            else {
                i += 2;
            }
        }
        return i == bits.length-1;
    }
}