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

    std::vector<double> res = {1.0, 3.0};
    for (long i = 2; i <= n; i++) {
        if (i % 2 == 0) res.push_back(1 + i / 2.0);
        else res.push_back(res[res.size()-1] + res[res.size()-2] + 1 + (i+1)/2.0);
    }
    std::vector<long> result;
    for (long i = 0; i <= n; i++) result.push_back((long) res[i]);
    return result;
}