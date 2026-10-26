class Solution {
    public long findMaximumElegance(int[][] items, int k) {
        int n = items.length;
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            int profit = items[i - 1][0];
            int category = items[i - 1][1];
            for (int j = 1; j <= k; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + profit);
                if (category != items[i - 1][1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + profit);
                }
            }
        }
        long max = 0;
        for (int i = 1; i <= k; i++) {
            max = Math.max(max, dp[n][i]);
        }
        return max;
    }
}