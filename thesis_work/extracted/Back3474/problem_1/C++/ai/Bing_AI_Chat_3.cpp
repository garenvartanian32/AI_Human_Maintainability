class Solution {
public:
    int maxSum(std::vector<int>& nums) {
        int maxSum = -1;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (maxDigit(nums[i]) == maxDigit(nums[j])) {
                    maxSum = std::max(maxSum, nums[i] + nums[j]);
                }
            }
        }
        return maxSum;
    }

private:
    int maxDigit(int num) {
        int maxDigit = 0;
        while (num > 0) {
            maxDigit = std::max(maxDigit, num % 10);
            num /= 10;
        }
        return maxDigit;
    }
};