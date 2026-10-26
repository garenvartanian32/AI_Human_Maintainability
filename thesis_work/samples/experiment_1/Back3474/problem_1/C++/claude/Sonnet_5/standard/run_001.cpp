#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxSum(vector<int>& nums) {
        unordered_map<int,int> best;
        int ans = -1;
        for (int v : nums) {
            int md = 0, t = v;
            while (t > 0) { md = max(md, t % 10); t /= 10; }
            if (best.count(md)) {
                ans = max(ans, best[md] + v);
                best[md] = max(best[md], v);
            } else {
                best[md] = v;
            }
        }
        return ans;
    }
};