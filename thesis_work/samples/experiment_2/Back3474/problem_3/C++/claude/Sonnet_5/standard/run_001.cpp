#include <vector>
#include <set>
#include <climits>
using namespace std;

class Solution {
public:
    int minAbsoluteDifference(vector<int>& nums, int x) {
        int n = nums.size();
        multiset<int> ms;
        int ans = INT_MAX;
        for (int j = x; j < n; j++) {
            ms.insert(nums[j - x]);
            int cur = nums[j];
            auto it = ms.lower_bound(cur);
            if (it != ms.end()) ans = min(ans, *it - cur);
            if (it != ms.begin()) {
                auto it2 = prev(it);
                ans = min(ans, cur - *it2);
            }
        }
        return ans;
    }
};