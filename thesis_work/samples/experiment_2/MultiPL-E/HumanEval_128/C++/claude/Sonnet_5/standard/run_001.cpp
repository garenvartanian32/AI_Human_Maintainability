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
    long total = 0;
    int sign = 1;
    for (long x : arr) {
        total += std::abs(x);
        if (x < 0) sign = -sign;
        else if (x == 0) sign = 0;
    }
    return total * sign;
}

int main() {
    auto r1 = prod_signs({1,2,2,-4});
    std::cout << (r1.has_value() ? std::to_string(*r1) : "none") << std::endl;
    auto r2 = prod_signs({0,1});
    std::cout << (r2.has_value() ? std::to_string(*r2) : "none") << std::endl;
    auto r3 = prod_signs({});
    std::cout << (r3.has_value() ? std::to_string(*r3) : "none") << std::endl;
    return 0;
}