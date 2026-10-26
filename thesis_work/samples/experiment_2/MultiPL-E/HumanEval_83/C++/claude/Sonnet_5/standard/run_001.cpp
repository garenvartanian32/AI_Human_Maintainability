#include<assert.h>
#include<bits/stdc++.h>
// Given a positive integer n, return the count of the numbers of n-digit
// positive integers that start or end with 1.
long starts_one_ends(long n) {
    if (n == 1) return 1;
    long pow = 1;
    for (long i = 0; i < n - 2; i++) pow *= 10;
    return 18L * pow;
}

int main() {
    std::cout << starts_one_ends(1) << std::endl;
    std::cout << starts_one_ends(2) << std::endl;
    std::cout << starts_one_ends(3) << std::endl;
    std::cout << starts_one_ends(10) << std::endl;
}