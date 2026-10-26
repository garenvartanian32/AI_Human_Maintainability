#include<assert.h>
#include<bits/stdc++.h>
// Everyone knows Fibonacci sequence, it was studied deeply by mathematicians in 
// the last couple centuries. However, what people don't know is Tribonacci sequence.
// Tribonacci sequence is defined by the recurrence:
// tri(1) = 3
// tri(n) = 1 + n / 2, if n is even.
// tri(n) =  tri(n - 1) + tri(n - 2) + tri(n + 1), if n is odd.
// For example:
// tri(2) = 1 + (2 / 2) = 2
// tri(4) = 3
// tri(3) = tri(2) + tri(1) + tri(4)
// = 2 + 3 + 3 = 8 
// You are given a non-negative integer number n, you have to a return a vector of the 
// first n + 1 numbers of the Tribonacci sequence.
// Examples:
// >>> tri((3))
// (std::vector<long>({(long)1, (long)3, (long)2, (long)8}))
std::vector<long> tri(long n) {
    std::vector<long> result(n + 1, 0);
    result[0] = 1;
    for (long i = 1; i <= n; i++) {
        if (i == 1) {
            result[i] = 3;
        } else if (i % 2 == 0) {
            result[i] = 1 + i / 2;
        } else {
            long nextVal = 1 + (i + 1) / 2;
            result[i] = result[i - 1] + result[i - 2] + nextVal;
        }
    }
    return result;
}

int main() {
    for (long v : tri(3)) std::cout << v << " ";
    std::cout << std::endl;
    return 0;
}