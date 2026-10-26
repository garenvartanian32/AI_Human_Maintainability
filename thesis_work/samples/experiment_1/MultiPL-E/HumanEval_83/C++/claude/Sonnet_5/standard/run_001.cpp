#include<assert.h>
#include<bits/stdc++.h>
// Given a positive integer n, return the count of the numbers of n-digit
// positive integers that start or end with 1.
long starts_one_ends(long n) {

    if (n == 1) return 1;
    return 18L * (long) std::pow(10, n - 2);
}