public class Solution {
    public int maxSum(int[] nums) {
        // Create an array of size 10 to keep track of the maximum values for each digit (0-9)
        int[] maxDigits = new int[10];
        
        // Iterate through the array and update the maxDigits array with the maximum values for each digit
        for (int num : nums) {
            int maxDigit = getMaxDigit(num);
            maxDigits[maxDigit] = Math.max(maxDigits[maxDigit], num);
        }
        
        int maxSum = -1;
        
        // Iterate through the maxDigits array to find pairs with equal maximum digits
        for (int i = 0; i <= 9; i++) {
            for (int j = i; j <= 9; j++) {
                if (maxDigits[i] != 0 && maxDigits[j] != 0 && i == j) {
                    maxSum = Math.max(maxSum, maxDigits[i] + maxDigits[j]);
                }
            }
        }
        
        return maxSum;
    }
    
    // Helper method to get the maximum digit from a number
    private int getMaxDigit(int num) {
        int maxDigit = 0;
        while (num > 0) {
            maxDigit = Math.max(maxDigit, num % 10);
            num /= 10;
        }
        return maxDigit;
    }
    
}