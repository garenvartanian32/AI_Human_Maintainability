class Solution {
public:
    int maximumScore(vector<int>& nums, int k) {
        int n = nums.size();
        int l = k, r = k;
        int score = 1;
        int ans = 0;
        while(l >= 0 || r < n){
            if(l < 0){
                score *= nums[r++];
                ans = max(ans, score);
                continue;
            }
            if(r == n){
                score *= nums[l--];
                ans = max(ans, score);
                continue;
            }
            if(nums[l] < nums[r]){
                score *= nums[l--];
                ans = max(ans, score);
            }
            else{
                score *= nums[r++];
                ans = max(ans, score);
            }
        }
        return ans;
    }
};