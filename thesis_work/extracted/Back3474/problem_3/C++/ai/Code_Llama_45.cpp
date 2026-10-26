class Solution {
public:
    int minAbsoluteDifference(vector<int>& nums, int x) {
        int n=nums.size();
        int min=INT_MAX;
        int i=0;
        int j=0;
        for(i=0;i<n;i++){
            for(j=i+1;j<n;j++){
                if(abs(nums[i]-nums[j])<min && abs(i-j)>=x){
                    min=abs(nums[i]-nums[j]);
                }
            }
        }
        return min;
    }
};