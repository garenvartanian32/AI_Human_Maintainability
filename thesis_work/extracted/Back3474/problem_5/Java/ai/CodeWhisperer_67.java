public class Solution {
    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int[] primes = new int[n];
        for (int i = 0; i < n; i++) {
            primes[i] = primeFactors(nums.get(i));
            if (primes[i] == 0) primes[i] = 1;
            //System.out.println(primes[i]);
        }
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                dp[j][i] = Math.max(dp[j][i - 1], dp[j - 1][i - 1] * primes[j]);
            }
        }
        return dp[n - 1][k];
        
    }
    
    public int primeFactors(int n) {
        int count = 0;
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                n /= i;
                count++;
            }
        }
        if (n > 1) count++;
        return count;
    }
    
}