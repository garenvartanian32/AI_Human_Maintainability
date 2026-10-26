#include <assert.h>
#include <bits/stdc++.h>

long closest_integer(std::string value) {
    const long double number = std::stold(value);

    if (number >= 0.0L) {
        return static_cast<long>(std::floor(number + 0.5L));
    }

    return static_cast<long>(std::ceil(number - 0.5L));
}