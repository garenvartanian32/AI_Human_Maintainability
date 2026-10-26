class Solution {
    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int[][] dp = new int[n][k + 1];
        for(int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);
        return dfs(nums, 0, k, dp);
    }
    public int dfs(List<Integer> nums, int i, int k, int[][] dp){
        if(i == nums.size())
            return 1;
        if(k == 0)
            return 0;
        if(dp[i][k] != -1)
            return dp[i][k];
        int ans = 0;
        for(int j = i; j < nums.size(); j++){
            ans = Math.max(ans, nums.get(j) * dfs(nums, j + 1, k - 1, dp));
        }
        return dp[i][k] = ans;
    }
}