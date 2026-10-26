class Solution {
    public int maxSum(int[] nums) {
        int max = 0;
        int maxDigit = 0;
        for(int i = 0; i < nums.length; i++){
            int digit = 0;
            int num = nums[i];
            while(num > 0){
                digit = Math.max(digit, num % 10);
                num /= 10;
            }
            if(digit > maxDigit){
                maxDigit = digit;
                max = nums[i];
            }
            else if(digit == maxDigit){
                max = Math.max(max, nums[i]);
            }
        }
        
        int maxSum = 0;
        for(int i = 0; i < nums.length; i++){
            int digit = 0;
            int num = nums[i];
            while(num > 0){
                digit = Math.max(digit, num % 10);
                num /= 10;
            }
            if(digit == maxDigit){
                maxSum = Math.max(maxSum, nums[i]);
            }
        }
        
        if(maxSum == 0){
            return -1;
        }
        
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == max){
                sum += nums[i];
            }
            else if(nums[i] == maxSum){
                sum += nums[i];
            }
        }
        
        return sum;
    }
}