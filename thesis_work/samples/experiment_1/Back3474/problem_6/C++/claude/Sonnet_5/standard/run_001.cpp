#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long maximumElegance(vector<vector<int>>& items, int k) {
        sort(items.begin(), items.end(), [](const vector<int>& a, const vector<int>& b){ return a[0] > b[0]; });
        long long total = 0;
        set<int> seen;
        vector<int> stack_;
        for (int i = 0; i < k; i++) {
            int p = items[i][0], c = items[i][1];
            total += p;
            if (seen.count(c)) stack_.push_back(p);
            else seen.insert(c);
        }
        long long best = total + (long long) seen.size() * seen.size();
        for (size_t i = k; i < items.size(); i++) {
            int p = items[i][0], c = items[i][1];
            if (!seen.count(c) && !stack_.empty()) {
                total += p - stack_.back();
                stack_.pop_back();
                seen.insert(c);
                best = max(best, total + (long long) seen.size() * seen.size());
            }
        }
        return best;
    }
};