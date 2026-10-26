class Solution {
public:
    int maxSum(vector<int>& nums) {
       int n = nums.size();
        int max_digit = 0;
        for(int i = 0; i < n; i++){
            int digit = 0;
            int num = nums[i];
            while(num){
                digit = max(digit, num % 10);
                num /= 10;
            }
            max_digit = max(max_digit, digit);
        }
        
        int max_sum = -1;
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                int digit = 0;
                int num = nums[i] + nums[j];
                while(num){
                    digit = max(digit, num % 10);
                    num /= 10;
                }
                if(digit == max_digit){
                    max_sum = max(max_sum, nums[i] + nums[j]);
                }
            }
        }
        
        return max_sum;
    }
};