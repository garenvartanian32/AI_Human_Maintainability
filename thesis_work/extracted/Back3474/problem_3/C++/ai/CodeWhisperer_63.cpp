class Solution {
public:
    int minAbsoluteDifference(vector<int>& nums, int x) {
        int n = nums.size();
        int ans = INT_MAX;
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(abs(nums[i] - nums[j]) >= x){
                    ans = min(ans, abs(nums[i] - nums[j]));
                    break;
                }
            }
        }
        return ans;
    }
};