class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        int ans = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (check(words[i], words[j])) {
                    ans++;
                }
            }
        }
        return ans;
    }

    boolean check(String one, String two) {
        if (two.length() < one.length()) return false;
        if (two.length() == one.length()) return two.equals(one);
        return two.startsWith(one) && two.endsWith(one);
    }
}