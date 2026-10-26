#include <assert.h>
#include <bits/stdc++.h>

long multiply(long a, long b) {
    const long first_unit_digit = std::labs(a % 10);
    const long second_unit_digit = std::labs(b % 10);

    return first_unit_digit * second_unit_digit;
}