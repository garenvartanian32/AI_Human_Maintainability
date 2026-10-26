#include <vector>
#include <algorithm>
#include <unordered_set>
using namespace std;

class Solution {
public:
    long long findMaximumElegance(vector<vector<int>>& items, int k) {
        sort(items.begin(), items.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[0] > b[0];
        });
        int n = items.size();
        long long total = 0;
        for (int i = 0; i < k; i++) total += items[i][0];
        unordered_set<int> seen;
        vector<int> dup;
        for (int i = 0; i < k; i++) {
            int profit = items[i][0], cat = items[i][1];
            if (seen.count(cat)) {
                dup.push_back(profit);
            } else {
                seen.insert(cat);
            }
        }
        long long sz = (long long) seen.size();
        long long ans = total + sz * sz;
        for (int i = k; i < n; i++) {
            if (dup.empty()) break;
            int profit = items[i][0], cat = items[i][1];
            if (seen.count(cat)) continue;
            total += profit - dup.back();
            dup.pop_back();
            seen.insert(cat);
            sz = (long long) seen.size();
            ans = max(ans, total + sz * sz);
        }
        return ans;
    }
};