public class Solution {
    public int maxSum(int[] nums) {
        int max = -1;
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length;j++){
                if(max < nums[i] + nums[j] && maxDigit(nums[i]) == maxDigit(nums[j])){
                    max = nums[i] + nums[j];
                }
            }
        }
        return max;
    }
    public int maxDigit(int num){
        int max = 0;
        while(num > 0){
            max = Math.max(max, num % 10);
            num /= 10;
        }
        return max;
    }
}