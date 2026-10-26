class Solution {
public:
    long long findMaximumElegance(std::vector<std::vector<int>>& items, int k) {
        const int MOD = 1e9 + 7;
        int n = items.size();
        std::vector<std::unordered_map<int, long long>> dp(n);
        for (int i = 0; i < n; i++) {
            dp[i][1 << items[i][1]] = items[i][0];
            for (int j = 0; j < i; j++) {
                for (auto& [mask, val] : dp[j]) {
                    int newMask = mask | (1 << items[i][1]);
                    dp[i][newMask] = std::max(dp[i][newMask], val + items[i][0]);
                }
            }
        }
        long long maxElegance = 0;
        for (int i = 0; i < n; i++) {
            for (auto& [mask, val] : dp[i]) {
                int cnt = __builtin_popcount(mask);
                if (cnt >= k) {
                    maxElegance = std::max(maxElegance, val + cnt * cnt);
                }
            }
        }
        return maxElegance % MOD;
    }
};