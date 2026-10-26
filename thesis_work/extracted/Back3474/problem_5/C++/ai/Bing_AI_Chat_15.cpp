class Solution {
public:
    int maximumScore(std::vector<int>& nums, int k) {
        const int MOD = 1e9 + 7;
        int n = nums.size();
        std::vector<int> primeScore(n);
        for (int i = 0; i < n; i++) {
            primeScore[i] = getPrimeScore(nums[i]);
        }
        std::vector<std::vector<long long>> dp(n, std::vector<long long>(k + 1));
        for (int i = 0; i < n; i++) {
            dp[i][0] = nums[i];
            for (int j = 1; j <= k; j++) {
                for (int l = 0; l < i; l++) {
                    if (primeScore[l] >= primeScore[i]) {
                        dp[i][j] = std::max(dp[i][j], dp[l][j - 1] * nums[i] % MOD);
                    }
                }
            }
        }
        long long maxScore = 0;
        for (int i = 0; i < n; i++) {
            maxScore = std::max(maxScore, dp[i][k]);
        }
        return maxScore % MOD;
    }

private:
    int getPrimeScore(int num) {
        int score = 0;
        for (int i = 2; i <= std::sqrt(num); i++) {
            if (num % i == 0) {
                score++;
                while (num % i == 0) {
                    num /= i;
                }
            }
        }
        if (num > 1) {
            score++;
        }
        return score;
    }
};