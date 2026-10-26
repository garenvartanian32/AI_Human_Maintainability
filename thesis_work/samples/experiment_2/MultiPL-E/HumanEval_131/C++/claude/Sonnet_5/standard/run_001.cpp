#include<assert.h>
#include<bits/stdc++.h>
// Given a positive integer n, return the product of the odd digits.
// Return 0 if all digits are even.
// For example:
// >>> digits((1))
// (1)
// >>> digits((4))
// (0)
// >>> digits((235))
// (15)
long digits(long n) {
    long result = 1;
    bool hasOdd = false;
    std::string s = std::to_string(n);
    for (char c : s) {
        int d = c - '0';
        if (d % 2 == 1) {
            result *= d;
            hasOdd = true;
        }
    }
    return hasOdd ? result : 0;
}

int main() {
    std::cout << digits(1) << std::endl;
    std::cout << digits(4) << std::endl;
    std::cout << digits(235) << std::endl;
    return 0;
}