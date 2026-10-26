#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int maxSum(vector<int>& nums) {
        vector<int> best(10, -1);
        int ans = -1;
        for (int x : nums) {
            int d = 0, t = x;
            while (t > 0) {
                d = max(d, t % 10);
                t /= 10;
            }
            if (best[d] != -1) ans = max(ans, best[d] + x);
            best[d] = max(best[d], x);
        }
        return ans;
    }
};