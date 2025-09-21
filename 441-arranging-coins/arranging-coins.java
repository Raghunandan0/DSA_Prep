class Solution {
    public int arrangeCoins(int n) {
        int row = 0; // Initialize the count of complete rows

        // Keep building rows while there are enough coins for the next row
        while (n >= row + 1) {
            row += 1;       // Move to the next row
            n = n - row;    // Use 'row' coins for the current row
        }

        // Return the total number of complete rows formed
        return row;
    }
}