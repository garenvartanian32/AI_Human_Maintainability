class Solution {
public:    int maxSum(vector<int>& nums) {
        int n = nums.size();
        int max_sum = -1;
        unordered_map<int, int> mp;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                int max_digit = 0;
                for (int k = 0; k < 10; k++) {
                    if (sum % 10 == k) {
                        max_digit = max(max_digit, k);
                    }
                    sum /= 10;
                }
                if (mp.find(max_digit)!= mp.end()) {
                    max_sum = max(max_sum, sum + mp[max_digit]);
                }
                mp[max_digit] = sum;
            }
        }
        return max_sum;
    }
};