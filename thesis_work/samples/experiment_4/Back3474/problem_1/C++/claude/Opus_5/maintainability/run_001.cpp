#include <algorithm>
#include <unordered_map>
#include <vector>

/**
 * Finds the maximum sum of a pair of numbers whose largest decimal digit matches.
 */
class Solution {
public:
    int maxSum(std::vector<int>& nums) {
        // For each largest digit, remember the biggest value seen so far.
        std::unordered_map<int, int> bestPerDigit;
        int bestSum = kNoPairFound;

        for (int value : nums) {
            const int digit = largestDigit(value);
            auto existing = bestPerDigit.find(digit);

            if (existing != bestPerDigit.end()) {
                bestSum = std::max(bestSum, existing->second + value);
                existing->second = std::max(existing->second, value);
            } else {
                bestPerDigit.emplace(digit, value);
            }
        }

        return bestSum;
    }

private:
    static constexpr int kNoPairFound = -1;

    /// Returns the greatest decimal digit appearing in `number`.
    static int largestDigit(int number) {
        int largest = 0;
        for (int remaining = number; remaining > 0; remaining /= 10) {
            largest = std::max(largest, remaining % 10);
        }
        return largest;
    }
};