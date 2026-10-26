#include <algorithm>
#include <numeric>
#include <vector>

/**
 * Maximum score obtainable by choosing k subarrays greedily by value.
 */
class Solution {
public:
    /**
     * Returns the maximum score achievable with exactly k operations.
     *
     * Each element may be chosen as the dominant element of any subarray where
     * its prime score beats everything to its left and ties-or-beats everything
     * to its right. We spend the k operations on the largest values first.
     */
    int maximumScore(std::vector<int>& nums, int k) {
        const std::vector<int> primeScores = computePrimeScores(nums);
        const std::vector<long long> reach = countDominatedSubarrays(primeScores);

        std::vector<int> byValueDescending(nums.size());
        std::iota(byValueDescending.begin(), byValueDescending.end(), 0);
        std::sort(byValueDescending.begin(), byValueDescending.end(),
                  [&nums](int a, int b) { return nums[a] > nums[b]; });

        long long score = 1;
        long long operationsLeft = k;
        for (int index : byValueDescending) {
            if (operationsLeft == 0) {
                break;
            }
            const long long uses = std::min(reach[index], operationsLeft);
            score = score * modularPower(nums[index], uses) % kModulus;
            operationsLeft -= uses;
        }

        return static_cast<int>(score);
    }

private:
    static constexpr long long kModulus = 1000000007LL;

    /// Number of distinct prime factors for every value, via a sieve.
    static std::vector<int> computePrimeScores(const std::vector<int>& nums) {
        const int limit = *std::max_element(nums.begin(), nums.end()) + 1;

        std::vector<int> distinctPrimeCounts(limit, 0);
        for (int candidate = 2; candidate < limit; ++candidate) {
            if (distinctPrimeCounts[candidate] == 0) {  // candidate is prime
                for (int multiple = candidate; multiple < limit; multiple += candidate) {
                    distinctPrimeCounts[multiple]++;
                }
            }
        }

        std::vector<int> primeScores;
        primeScores.reserve(nums.size());
        for (int value : nums) {
            primeScores.push_back(distinctPrimeCounts[value]);
        }
        return primeScores;
    }

    /// For each index, how many subarrays it dominates by prime score.
    static std::vector<long long> countDominatedSubarrays(const std::vector<int>& primeScores) {
        const int length = static_cast<int>(primeScores.size());

        std::vector<int> previousGreater(length, -1);
        std::vector<int> stack;
        for (int index = 0; index < length; ++index) {
            while (!stack.empty() && primeScores[stack.back()] < primeScores[index]) {
                stack.pop_back();
            }
            previousGreater[index] = stack.empty() ? -1 : stack.back();
            stack.push_back(index);
        }

        std::vector<int> nextGreaterOrEqual(length, length);
        stack.clear();
        for (int index = length - 1; index >= 0; --index) {
            while (!stack.empty() && primeScores[stack.back()] <= primeScores[index]) {
                stack.pop_back();
            }
            nextGreaterOrEqual[index] = stack.empty() ? length : stack.back();
            stack.push_back(index);
        }

        std::vector<long long> reach(length);
        for (int index = 0; index < length; ++index) {
            reach[index] = static_cast<long long>(index - previousGreater[index])
                         * (nextGreaterOrEqual[index] - index);
        }
        return reach;
    }

    /// Computes base^exponent modulo `kModulus`.
    static long long modularPower(long long base, long long exponent) {
        long long result = 1;
        long long factor = base % kModulus;
        for (long long remaining = exponent; remaining > 0; remaining >>= 1) {
            if (remaining & 1LL) {
                result = result * factor % kModulus;
            }
            factor = factor * factor % kModulus;
        }
        return result;
    }
};