class Solution {    
    public int maxSum(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (nums[i] > max) {
                max = nums[i];
            }
            if (max > 0 && nums[i] % max == 0) {
                sum -= nums[i];
            }
        }
        return sum;
    }
}