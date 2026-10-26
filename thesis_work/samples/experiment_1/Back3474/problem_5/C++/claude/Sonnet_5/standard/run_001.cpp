#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int primeScore(int x) {
        int cnt = 0;
        for (long long d = 2; d*d <= x; d++) {
            if (x % d == 0) {
                cnt++;
                while (x % d == 0) x /= d;
            }
        }
        if (x > 1) cnt++;
        return cnt;
    }
    long long modpow(long long b, long long e, long long m) {
        long long r = 1; b %= m;
        while (e > 0) { if (e&1) r=r*b%m; b=b*b%m; e>>=1; }
        return r;
    }
    int maximumScore(vector<int>& nums, int k) {
        const long long MOD = 1000000007;
        int n = nums.size();
        vector<int> ps(n);
        for (int i = 0; i < n; i++) ps[i] = primeScore(nums[i]);
        vector<int> left(n, -1), right(n, n);
        vector<int> stack_;
        for (int i = 0; i < n; i++) {
            while (!stack_.empty() && ps[stack_.back()] < ps[i]) { right[stack_.back()] = i; stack_.pop_back(); }
            stack_.push_back(i);
        }
        stack_.clear();
        for (int i = n-1; i >= 0; i--) {
            while (!stack_.empty() && ps[stack_.back()] <= ps[i]) { left[stack_.back()] = i; stack_.pop_back(); }
            stack_.push_back(i);
        }
        vector<int> order(n);
        for (int i = 0; i < n; i++) order[i] = i;
        sort(order.begin(), order.end(), [&](int a, int b){ return nums[a] > nums[b]; });
        long long ans = 1;
        long long remaining = k;
        for (int i : order) {
            if (remaining <= 0) break;
            long long cnt = (long long)(i-left[i]) * (right[i]-i);
            long long use = min(cnt, remaining);
            ans = ans * modpow(nums[i], use, MOD) % MOD;
            remaining -= use;
        }
        return (int) ans;
    }
};