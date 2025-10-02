class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length()); // Step 1: sort by word length
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Step 2: each word alone forms a chain of length 1

        for(int i = 1; i < n; i++){
            for(int j = i - 1; j >= 0; j--){
                if(compare(words[i], words[j])){ // Step 3: check if words[j] is predecessor of words[i]
                    dp[i] = Math.max(dp[i], dp[j] + 1); // Step 4: extend the chain
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt(); // Step 5: max chain length
    }

    private boolean compare(String s, String t){
        if(s.length() != t.length() + 1) return false; // only one character longer allowed
        int i = 0, j = 0;
        while(i < s.length()){
            if(j < t.length() && s.charAt(i) == t.charAt(j)){
                i++;
                j++;
            }
            else{
                i++; // simulate insertion in t
            }
        }
        return i == s.length() && j == t.length(); // valid predecessor if t fully traversed
    }
}