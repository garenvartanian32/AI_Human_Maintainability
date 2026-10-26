#include <assert.h>
#include <bits/stdc++.h>

bool prime_length(std::string string) {
    const long length = static_cast<long>(string.size());

    if (length < 2) {
        return false;
    }

    for (long divisor = 2; divisor * divisor <= length; ++divisor) {
        if (length % divisor == 0) {
            return false;
        }
    }

    return true;
}