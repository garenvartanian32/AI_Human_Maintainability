#include <assert.h>
#include <bits/stdc++.h>

long starts_one_ends(long n) {
    if (n == 1) {
        return 1;
    }

    long middle_digit_combinations = 1;
    for (long position = 0; position < n - 2; ++position) {
        middle_digit_combinations *= 10;
    }

    const long starts_with_one = 10 * middle_digit_combinations;
    const long ends_with_one = 9 * middle_digit_combinations;
    const long starts_and_ends_with_one = middle_digit_combinations;

    return starts_with_one + ends_with_one - starts_and_ends_with_one;
}