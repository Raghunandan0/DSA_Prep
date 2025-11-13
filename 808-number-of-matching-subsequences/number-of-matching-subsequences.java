class Solution {
    private boolean substringCheck(String word, String s) {
        int i  = 0, j = 0;
        while(i<word.length() && j<s.length()) {
            if(word.charAt(i) == s.charAt(j)) i++;
            j++;
        }
        return i==word.length();
    }
    public int numMatchingSubseq(String s, String[] words) {
        HashMap<String,Integer> map = new HashMap<>();
        for(String word : words) {
            map.put(word, map.getOrDefault(word, 0)+1);
        }
        int cnt = 0;
        for(String word: map.keySet()){
            if(substringCheck(word,s)){
                cnt +=map.get(word);
            }
        }
        return cnt;
    }
}