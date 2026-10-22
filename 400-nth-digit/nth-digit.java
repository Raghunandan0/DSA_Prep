class Solution {
    public int findNthDigit(int n) {
        long len = 1, c = 9, s = 1;
        while(n > len*c){
            n-=len*c;
            len++;
            c*=10;
            s*=10;
        }
        s+=(n-1)/len;
        return String.valueOf(s).charAt((n-1)%(int)len)-'0';
    }
}