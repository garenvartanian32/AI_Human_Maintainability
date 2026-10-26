class Solution {    
    public long findMaximumElegance(int[][] items, int k) {
        int n = items.length;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (i == 0) {
                    dp[i][j] = 0;
                } else if (j == 0) {
                    dp[i][j] = 0;
                } else if (j == 1) {
                    dp[i][j] = items[i - 1][0];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], items[i - 1][0] + dp[i - 1][j - 1]);
                }
            }
        }
        return dp[n][k];
    }
}