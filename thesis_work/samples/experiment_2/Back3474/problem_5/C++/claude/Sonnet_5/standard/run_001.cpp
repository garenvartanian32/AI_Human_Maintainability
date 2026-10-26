#include <vector>
#include <algorithm>
#include <numeric>
using namespace std;

class Solution {
public:
    int maximumScore(vector<int>& nums, int k) {
        const long long MOD = 1'000'000'007LL;
        int n = nums.size();
        int maxVal = *max_element(nums.begin(), nums.end());
        vector<int> spf(maxVal + 1, 0);
        for (int i = 2; i <= maxVal; i++) {
            if (spf[i] == 0) {
                for (int j = i; j <= maxVal; j += i) {
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }
        vector<int> scores(n);
        for (int i = 0; i < n; i++) {
            int x = nums[i], s = 0, last = -1;
            while (x > 1) {
                int p = spf[x];
                if (p != last) { s++; last = p; }
                x /= p;
            }
            scores[i] = s;
        }
        vector<int> left(n), right(n);
        vector<int> stack;
        for (int i = 0; i < n; i++) {
            while (!stack.empty() && scores[stack.back()] < scores[i]) stack.pop_back();
            left[i] = stack.empty() ? -1 : stack.back();
            stack.push_back(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.empty() && scores[stack.back()] <= scores[i]) stack.pop_back();
            right[i] = stack.empty() ? n : stack.back();
            stack.push_back(i);
        }
        vector<int> order(n);
        iota(order.begin(), order.end(), 0);
        sort(order.begin(), order.end(), [&](int a, int b) { return nums[a] > nums[b]; });

        auto power = [&](long long base, long long exp) {
            base %= MOD;
            long long result = 1;
            while (exp > 0) {
                if (exp & 1) result = result * base % MOD;
                base = base * base % MOD;
                exp >>= 1;
            }
            return result;
        };

        long long ans = 1;
        long long remaining = k;
        for (int idx : order) {
            if (remaining <= 0) break;
            long long cnt = (long long)(idx - left[idx]) * (right[idx] - idx);
            cnt = min(cnt, remaining);
            ans = ans * power(nums[idx], cnt) % MOD;
            remaining -= cnt;
        }
        return (int) ans;
    }
};