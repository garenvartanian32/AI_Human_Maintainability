public class Solution {
    public int findMaximumElegance(int[][] items, int k) {
        int n = items.length;
        int[][] dp = new int[n][k];
        for(int i=0; i<n; i++) {
            dp[i][0] = items[i][0];
        }
        for(int i=1; i  <n; i++) {
            for(int j=1; j<k; j++) {
                int max = Integer.MIN_VALUE;
                for(int m=0; m<i; m++) {
                    if(items[m][1] != items[i][ 1]) {
                        max = Math.max(max, dp[m][j-1]);
                    }
                }
                dp[i][j] = max + items[i][0];
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i= 0; i<n; i++) {
            max = Math.max(max, dp[i][k-1]);
        }
        return max;
    }
}