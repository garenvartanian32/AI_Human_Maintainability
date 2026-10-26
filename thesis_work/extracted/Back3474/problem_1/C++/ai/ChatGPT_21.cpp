class Solution {
public:
    int maxSum(std::vector<int>& nums) {
        std::vector<int> maxDigit(10, -1); // To store the maximum number for each digit (0-9)
        int maxSum = -1; // Initialize maxSum to -1 as the minimum possible answer
        
        for (int num : nums) {
            int maxDig = 0; // To store the maximum digit in the current number
            int temp = num; // Temporary variable to avoid modifying num
            
            while (temp > 0) {
                int digit = temp % 10;
                maxDig = std::max(maxDig, digit); // Update maxDig if a larger digit is found
                temp /= 10;
            }
            
            if (maxDigit[maxDig] != -1) {
                // If we have seen a number with the same maximum digit before, update maxSum
                maxSum = std::max(maxSum, maxDigit[maxDig] + num);
            }
            
            // Update maxDigit[maxDig] with the maximum of the current number and the previous maximum
            maxDigit[maxDig] = std::max(maxDigit[maxDig], num);
        }
        
        return maxSum;
    }
};