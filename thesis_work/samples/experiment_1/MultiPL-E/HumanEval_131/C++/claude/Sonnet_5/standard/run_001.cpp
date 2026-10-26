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

    std::string s = std::to_string(n);
    long product = 1;
    bool any = false;
    for (char c : s) {
        int d = c - '0';
        if (d % 2 == 1) { product *= d; any = true; }
    }
    return any ? product : 0;
}