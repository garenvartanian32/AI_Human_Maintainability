class Solution {
public:
    long long findMaximumElegance(vector<vector<int>>& items, int k) {
  int n=items.size();
  vector<vector<int>>dp(n,vector<int>(k,0));
  for(int i=0;i<n;i++)
  {
    dp[i][0]=items[i][0];
  }
  for(int i=1;i<n;i++)
  {
    for(int j=1;j<k;j++)
    {
      dp[i][j]=max(dp[i-1][j],dp[i-1][j-1]);
      if(items[i][1]!=items[i-1][1])
      {
        dp[i][j]=max(dp[i][j],dp[i-1][j]+items[i][0]);
      }
    }
  }
  return dp[n-1][k-1];
    }
};