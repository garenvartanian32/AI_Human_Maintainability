class Solution {
public:
    int minAbsoluteDifference(std::vector<int>& nums, int x) {
        int minDiff = INT_MAX;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + x; j < nums.size(); j++) {
                minDiff = std::min(minDiff, std::abs(nums[i] - nums[j]));
            }
        }
        return minDiff;
    }
};