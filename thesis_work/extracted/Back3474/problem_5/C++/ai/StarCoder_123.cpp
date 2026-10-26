class Solution {
public:
    int maximumScore(vector<int>& nums, int k) {
        int MOD=1e9+7;
        int n=nums.size();
        vector<int> dp(n+1,0);
        vector<int> primes;
        for(int i=2;i<=n;i++) {
            if(dp[i]==0) {
                primes.push_back(i);
                for(int j=i;j<=n;j+=i) {
                    dp[j]+=1;
                }
            }
        }
        int ans=0;
        for(int i=0;i<k;i++) {
            int mx=0;
            int mx_idx=-1;
            for(int j=i;j<n;j++) {
                if(mx<dp[j]) {
                    mx=dp[j];
                    mx_idx=j;
                }
            }
            ans=(ans+nums[mx_idx]*primes[mx-1])%MOD;
            dp[mx_idx]=0;
        }
        return ans;
    }
};