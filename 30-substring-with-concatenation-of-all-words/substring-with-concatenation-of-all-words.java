import java.util.*;

public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return result;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) return result;

        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Try every possible starting offset (to handle alignment)
        for (int i = 0; i < wordLen; i++) {
            int left = i, count = 0;
            Map<String, Integer> windowFreq = new HashMap<>();

            for (int j = i; j <= s.length() - wordLen; j += wordLen) {
                String word = s.substring(j, j + wordLen);

                if (wordFreq.containsKey(word)) {
                    windowFreq.put(word, windowFreq.getOrDefault(word, 0) + 1);
                    count++;

                    // If the word is overused, shrink from the left
                    while (windowFreq.get(word) > wordFreq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        windowFreq.put(leftWord, windowFreq.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    // If window matches the word count, record start index
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Reset if invalid word
                    windowFreq.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }

        return result;
    }
}