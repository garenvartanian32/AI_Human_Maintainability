class Solution {
public:    long long findMaximumElegance(vector<vector<int>>& items, int k) {
        int n = items.size();
        vector<vector<int>> dp(n, vector<int>(2, 0));
        dp[0][0] = items[0][0];
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = max(dp[i - 1][0], items[i][0]);
            dp[i][1] = dp[i - 1][1] + 1;
        }
        long long res = 0;
        for (int i = 0; i < n - k + 1; i++) {
            res = max(res, static_cast<long long>(dp[i][0] + dp[i + k - 1][0] + dp[i][1] * dp[i + k - 1][1]));
        }
        return res;
    }
};