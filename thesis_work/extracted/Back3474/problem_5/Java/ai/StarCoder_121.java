public class Solution {
    public int maximumScore(List<Integer> nums, int k) {
        
        int n = nums.size();
        int[] dp = new int[n];
        int[] prime = new int[n];
        int[] primeCount = new int[n];
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        
        
        for(int i = 0; i < n; i++){
            dp[i] = nums.get(i);
            prime[i] = getPrime(nums.get(i));
            primeCount[i] = getPrimeCount(nums.get(i));
            
        }
        
        for(int i = 1; i < n; i++){
            prefix[i] = prefix[i-1] + primeCount[i-1];
        }
        
        for(int i = n-2; i >= 0; i--){
            suffix[  i] = suffix[i+1] + primeCount[i+1];
        }
        
        int[] dp2 = new int[n];
        int[] dp3 = new int[n];
        int[] dp4 = new int[n];
        
        for(int i = 0  ; i < n; i++){
            dp2[i] = dp[i];
            dp3[i] = dp[i];
            dp4[i] = dp[i];
        }
        
        for(int i = 0; i < k; i++){
            int max  = -1;
            int maxIndex = -1;
            for(int j = 0; j < n; j++){
                if(j == 0){
                    dp2[j] = dp[j] * prime[j];
                }else{
                    dp2[j  ] = Math.max(dp2[j-1], dp[j] * prime[j]);
                }
                
                if(j == n-1){
                    dp3[j] = dp[j] * prime[j];
                }else{
                    dp3[j]  = Math.max(dp3[j+1], dp[j] * prime[j]);
                }
                
                if(prefix[j] + suffix[j] - primeCount[j] > max){
                    max = prefix[j] + suffix[j] - primeCount  [j];
                    maxIndex = j;
                }
            }
            
            dp4[maxIndex] = 0;
            
            for(int j = 0; j < n; j++){
                if(j == 0){
                    dp4[j] = Math.max(dp4  [j], dp2[j]);
                }else if(j == n-1){
                    dp4[j] = Math.max(dp4[j], dp3[j]);
                }else{
                    dp4[j] = Math.max(dp4[j],  Math.max(dp2[j], dp3[j]));
                }
            }
            
            for(int j = 0; j < n; j++){
                dp[j] = dp4[j];
            }
        }
        
        int sum = 0;
        for(int i  = 0; i < n; i++){
            sum = (sum + dp[i]) % 1000000007;
        }
        
        return sum;
    }
    
    public int getPrime(int num){
        int count = 0;
        for(  int i = 2; i <= num; i++){
            if(num % i == 0){
                count++;
            }
        }
        return count;
    }
    
    public int getPrimeCount(int num){
        int count = 0;
        for(  int i =  2; i <= num; i++){
            if(num % i == 0){
                count++;
            }
        }
        return count - 1;
    }
}