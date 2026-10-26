class Solution {
public:
    int maximumScore(vector<int>& nums, int k) { 
       int n = nums.size();
        vector<int> primes;
        for(int i = 2; i <= n; i++){
            bool isPrime = true;
            for(int j = 2; j <= sqrt(i); j++){
                if(i % j == 0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime){
                primes.push_back(i);
            }
        }
        vector<int> dp(n + 1, 0);
        dp[0] = 1;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < primes.size(); j++){
                if(primes[j] <= nums[i - 1]){
                    dp[i] = (dp[i] + dp[i - 1]) % MOD;
                }
            }
        }
        int ans = 0;
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= n; j++){
                if(i + j <= n){
                    ans = (ans + dp[i] * dp[n - i - j] % MOD) % MOD;
                }
            }
        }
        return ans;
    }
    
    int MOD = 1e9 + 7;
};