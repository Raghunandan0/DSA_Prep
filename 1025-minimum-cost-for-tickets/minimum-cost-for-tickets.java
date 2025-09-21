class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int lastDay = days[n-1];
        int[] dp = new int[lastDay + 1]; // dp[day] = min cost up to this day
        boolean travelDay[] = new boolean[lastDay + 1];

        for (int d : days) travelDay[d] = true;

        for (int d = 1; d <= lastDay; d++) {
            if (!travelDay[d]) {
                dp[d] = dp[d-1]; // no travel today, cost same as yesterday
            } else {
                int cost1 = dp[d-1] + costs[0];                 // 1-day pass
                int cost7 = dp[Math.max(0, d-7)] + costs[1];    // 7-day pass
                int cost30 = dp[Math.max(0, d-30)] + costs[2];  // 30-day pass
                dp[d] = Math.min(cost1, Math.min(cost7, cost30));
            }
        }

        return dp[lastDay];
    }
}
