#include <assert.h>
#include <bits/stdc++.h>

long closest_integer(std::string value) {
    long double number = std::stold(value);
    return number >= 0
        ? static_cast<long>(std::floor(number + 0.5L))
        : static_cast<long>(std::ceil(number - 0.5L));
}