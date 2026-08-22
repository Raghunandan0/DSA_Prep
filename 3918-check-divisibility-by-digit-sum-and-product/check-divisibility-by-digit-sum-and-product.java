class Solution {
    public boolean checkDivisibility(int n) {
        int sumDig = 0;
        int productDig = 1;
        int num = n;

        while(num>0){
            sumDig += num % 10;
            productDig *= num % 10;
            num /= 10;
        }

        return n % (sumDig + productDig) == 0;
    }
}