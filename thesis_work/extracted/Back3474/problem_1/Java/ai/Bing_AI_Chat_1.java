public class Solution {
    public int maxSum(int[] nums) {
        int[] maxSum = new int[10];
        for (int num : nums) {
            int maxDigit = 0;
            int temp = num;
            while (temp > 0) {
                maxDigit = Math.max(maxDigit, temp % 10);
                temp /= 10;
            }
            for (int i = 0; i < 10; i++) {
                if (i == maxDigit) {
                    maxSum[i] = Math.max(maxSum[i], num + maxSum[i]);
                } else {
                    maxSum[i] = Math.max(maxSum[i], num);
                }
            }
        }
        int res = -1;
        for (int sum : maxSum) {
            res = Math.max(res, sum);
        }
        return res == 0 ? -1 : res;
    }
}