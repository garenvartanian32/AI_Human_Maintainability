class Solution {
public:    
    int minAbsoluteDifference(vector<int>& nums, int x) {
        int min = INT_MAX;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (abs(nums[i] - nums[j]) >= x)
                    continue;
                min = min < abs(nums[i] - nums[j])? abs(nums[i] - nums[j]) : min;
            }
        }
        return min;
    }
};