#include <assert.h>
#include <bits/stdc++.h>

long starts_one_ends(long n) {
    if (n == 1) return 1;

    long power = 1;
    for (long i = 0; i < n - 2; ++i) power *= 10;
    return 18 * power;
}