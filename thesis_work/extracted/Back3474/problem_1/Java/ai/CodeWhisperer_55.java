public class Solution{
    public int maxSum(int[] nums) {
        int max = -1;
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length;
                    j++) {
                if(nums[i] % 10 == nums[j] % 10) {
                    max = Math.max(max, nums[i] + nums[j]);
                }
            }
            
        }
        return max;
        
    }
    
}