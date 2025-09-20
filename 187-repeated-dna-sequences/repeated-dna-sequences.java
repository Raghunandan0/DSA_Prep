class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        // Let's imagine these as two magic notebooks! \U0001f4d2✨
        // 'seenSequences' will remember every DNA sequence we've encountered.
        // 'repeatedSequences' will jot down only those sequences that we've seen more than once.
        Set<String> seenSequences = new HashSet<>(); 
        Set<String> repeatedSequences = new HashSet<>(); 

        // We're going to slide a window of 10 letters at a time across the DNA sequence.
        // Think of it like reading a long book, 10 letters at a glance.
        for (int i = 0; i <= s.length() - 10; i++) {
            // Grabbing a 10-letter chunk from the DNA sequence.
            String subsequence = s.substring(i, i + 10); 

            // Now, we check if we've seen this chunk before.
            // 'add' returns true if the element is new and false if it's already there! \U0001f92f
            if (!seenSequences.add(subsequence)) {
                // Aha! We've seen this sequence before, so it's a repeat offender!
                repeatedSequences.add(subsequence);
            }
        }

        // Finally, turning our notebook of repeated sequences into a neat list to return. \U0001f4dd
        return new ArrayList<>(repeatedSequences);
    }
}