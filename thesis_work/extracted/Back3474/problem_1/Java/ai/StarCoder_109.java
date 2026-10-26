public class Solution{
    public int maxSum(int[] nums){
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] % 10 == nums[j] % 10) {
                    maxSum = Math.max(maxSum, nums[i] + nums[j]);
                }
            }
        }
        return maxSum == Integer.MIN_VALUE? -1 : maxSum; 
    }
    
}