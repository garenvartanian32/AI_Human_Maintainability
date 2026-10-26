public class Solution {
    public int findMaximumElegance(int[][] items, int k) {
        int n = items.length;
        int[] dp = new int[n];
        Arrays.sort(items, (a, b) -> a[0] - b[0]);
        int max = 0;
        for (int i = 0; i < n; i++) {
            int profit = items[i][0];
            int category = items[i][1];
            dp[i] = profit + category * category;
            for (int j = 0; j < i; j++) {
                if (items[j][1] != category) {
                    dp[i] = Math.max(dp[i], dp[j] + profit + category * category);
                }
            }
            if (i >= k - 1) {
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}