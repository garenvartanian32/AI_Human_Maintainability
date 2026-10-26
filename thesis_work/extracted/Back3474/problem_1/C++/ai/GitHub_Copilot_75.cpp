class Solution {
public:
    int maxSum(vector<int>& nums) {
        int n = nums.size();
        int maxSum = -1;
        unordered_map<int, int> mp;
        for(int i = 0; i < n; i++) {
            int maxDigit = 0;
            int temp = nums[i];
            while(temp) {
                maxDigit = max(maxDigit, temp % 10);
                temp /= 10;
            }
            if(mp.find(maxDigit) != mp.end()) {
                maxSum = max(maxSum, mp[maxDigit] + nums[i]);
                mp[maxDigit] = max(mp[maxDigit], nums[i]);
            } else {
                mp[maxDigit] = nums[i];
            }
        }
        return maxSum;
    }
};