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

/// Even positions have a closed form, so no recursion is needed.
static long evenTerm(long index) {
    return 1 + index / 2;
}

std::vector<long> tri(long n) {
    std::vector<long> sequence;
    sequence.push_back(1);

    for (long index = 1; index <= n; ++index) {
        if (index == 1) {
            sequence.push_back(3);
        } else if (index % 2 == 0) {
            sequence.push_back(evenTerm(index));
        } else {
            // The forward reference tri(index + 1) is even, hence known outright.
            sequence.push_back(sequence[index - 1] + sequence[index - 2] + evenTerm(index + 1));
        }
    }

    return sequence;
}