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
    long product = 1;
    bool sawOddDigit = false;

    for (long remaining = n; remaining > 0; remaining /= 10) {
        const long digit = remaining % 10;
        if (digit % 2 == 1) {
            product *= digit;
            sawOddDigit = true;
        }
    }

    // An empty product would be 1, but the specification asks for 0 instead.
    return sawOddDigit ? product : 0;
}