#include<assert.h>
#include<bits/stdc++.h>
// Complete the function that takes two integers and returns 
// the product of their unit digits.
// Assume the input is always valid.
// Examples:
// >>> multiply((148), (412))
// (16)
// >>> multiply((19), (28))
// (72)
// >>> multiply((2020), (1851))
// (0)
// >>> multiply((14), (-15))
// (20)
long multiply(long a, long b) {

    return (std::labs(a) % 10) * (std::labs(b) % 10);
}