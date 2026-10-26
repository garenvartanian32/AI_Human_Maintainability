class Solution {
public:
    int maxSum(vector<int>& nums) {
    int n=nums.size();
    int maxi=0;
    int maxj=0;
    int maxsum=0;
    for(int i=0;i<n;i++){
        for(int j=i+1;j<n;j++){
            if(nums[i]>maxi){
                maxi=nums[i];
            }
            if(nums[j]>maxj){
                maxj=nums[j];
            }
            if(maxi==maxj){
                maxsum=max(maxsum,maxi+maxj);
            }
        }
    }
    if(maxsum==0){
        return -1;
    }
    return maxsum;
    }
};