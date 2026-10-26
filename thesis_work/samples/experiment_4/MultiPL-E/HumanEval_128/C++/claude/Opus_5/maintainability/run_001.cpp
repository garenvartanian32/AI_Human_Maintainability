#include<assert.h>
#include<bits/stdc++.h>
// You are given a vector arr of integers and you need to return
// sum of magnitudes of integers multiplied by product of all signs
// of each number in the vector, represented by 1, -1 or 0.
// Note: return None for empty arr.
// Example:
// >>> prod_signs((std::vector<long>({(long)1, (long)2, (long)2, (long)-4})))
// 9
// >>> prod_signs((std::vector<long>({(long)0, (long)1})))
// 0
// >>> prod_signs((std::vector<long>()))
// std::nullopt
std::optional<long> prod_signs(std::vector<long> arr) {
    if (arr.empty()) {
        return std::nullopt;
    }

    long totalMagnitude = 0;
    int negativeCount = 0;

    for (long value : arr) {
        // A single zero makes the whole sign product zero.
        if (value == 0) {
            return 0L;
        }
        if (value < 0) {
            negativeCount++;
        }
        totalMagnitude += std::labs(value);
    }

    // The sign is negative exactly when an odd number of factors are.
    return (negativeCount % 2 == 0) ? totalMagnitude : -totalMagnitude;
}