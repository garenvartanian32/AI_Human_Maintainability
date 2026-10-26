class Solution {
public:
    int minAbsoluteDifference(vector<int>& nums, int x) {
        int n = nums.size();
        int ans = INT_MAX;
        for(int i = 0; i < n; i++){
            for(int j = i + x; j < n; j++){
                ans = min(ans, abs(nums[i] - nums[j]));
            }
        }
        return ans;
    }
};