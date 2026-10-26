class Solution {
public:
    int maxSum(vector<int>& nums) {
        int ans = -1;
        int n = nums.size();
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(nums[i] % 10 == nums[j] % 10){
                    ans = max(ans, nums[i] + nums[j]);
                    break;
                }
            }
        }
        return ans;
    }
};