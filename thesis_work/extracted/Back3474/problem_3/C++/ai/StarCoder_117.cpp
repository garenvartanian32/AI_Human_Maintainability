class Solution {
public:
    int minAbsoluteDifference(vector<int>& nums, int x) {
    int n=nums.size();
    vector<int>v;
    for(int i=0;i<n;i++)
    {
        v.push_back(abs(nums[i]-x));
    }
    sort(v.begin(),v.end());
    int ans=v[0];
    for(int i=1;i<n;i++)
    {
        ans=min(ans,v[i]-v[i-1]);
    }
    return ans;
    }
};