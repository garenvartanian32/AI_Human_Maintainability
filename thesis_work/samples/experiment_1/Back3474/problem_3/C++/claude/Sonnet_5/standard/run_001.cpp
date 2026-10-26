#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minAbsoluteDifference(vector<int>& nums, int x) {
        int n = nums.size();
        multiset<int> sl;
        int ans = INT_MAX;
        for (int i = x; i < n; i++) {
            sl.insert(nums[i-x]);
            int cur = nums[i];
            auto it = sl.lower_bound(cur);
            if (it != sl.end()) ans = min(ans, abs(*it - cur));
            if (it != sl.begin()) { auto it2 = prev(it); ans = min(ans, abs(*it2 - cur)); }
        }
        return ans;
    }
};