class Solution {
public:
    int maximumScore(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> left(n, -1), right(n, n);
        stack<int> st;
        for(int i = 0; i < n; i++) {
            while(!st.empty() && nums[st.top()] >= nums[i]) {
                st.pop();
            }
            if(!st.empty()) {
                left[i] = st.top();
            }
            st.push(i);
        }
        while(!st.empty()) {
            st.pop();
        }
        for(int i = n - 1; i >= 0; i--) {
            while(!st.empty() && nums[st.top()] >= nums[i]) {
                st.pop();
            }
            if(!st.empty()) {
                right[i] = st.top();
            }
            st.push(i);
        }
        vector<int> primes(100001, 0);
        for(int i = 2; i <= 100000; i++) {
            if(primes[i] == 0) {
                for(int j = i; j <= 100000; j += i) {
                    primes[j]++;
                }
            }
        }
        vector<int> dp(n, 0);
        for(int i = 0; i < n; i++) {
            dp[i] = nums[i] * (primes[nums[i]] + 1);
        }
        for(int i = 1; i < n; i++) {
            dp[i] = max(dp[i], dp[i - 1]);
        }
        int ans = 0;
        for(int i = 0; i < n; i++) {
            int l = left[i] + 1, r = right[i] - 1;
            if(l <= k && k <= r) {
                ans = max(ans, nums[i] * dp[r]);
            }
            if(l <= k - 1 && k - 1 <= r) {
                ans = max(ans, nums[i] * dp[r]);
            }
            if(l <= k && k <= r + 1) {
                ans = max(ans, nums[i] * dp[r]);
            }
            if(l <= k - 1 && k - 1 <= r + 1) {
                ans = max(ans, nums[i] * dp[r]);
            }
        }
        return ans;
    }
};