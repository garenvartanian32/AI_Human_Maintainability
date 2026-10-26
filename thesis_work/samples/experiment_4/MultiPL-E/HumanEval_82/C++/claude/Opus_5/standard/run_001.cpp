#include <assert.h>
#include <bits/stdc++.h>

bool prime_length(std::string string) {
    long n = static_cast<long>(string.size());
    if (n < 2) return false;

    for (long divisor = 2; divisor * divisor <= n; ++divisor) {
        if (n % divisor == 0) return false;
    }
    return true;
}