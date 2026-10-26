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

    if (arr.empty()) return std::nullopt;
    long prod = 1, mag = 0;
    for (long x : arr) {
        if (x > 0) prod *= 1;
        else if (x < 0) prod *= -1;
        else prod = 0;
        mag += std::labs(x);
    }
    return prod * mag;
}