public class Solution {
    public int findMaximumElegance(int[][] items, int k) {
        int n = items.length;
        int[] category = new int[n];
        for (int i = 0; i < n; i++) {
            category[i] = items[i][1];
        }
        int[] count = new int[n + 1];
        for (int i = 0; i < n; i++) {
            count[category[i]]++;
        }
        for (int i = 1; i <= n; i++) {
            count[i] += count[i - 1];
        }
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int index = count[category[i]];
            dp[index] = Math.max(dp[index], dp[index - 1] + items[i][0]);
        }
        return dp[n];
        
    }

}