#include<assert.h>
#include<bits/stdc++.h>
// Create a function that takes a value (string) representing a number
// and returns the closest integer to it. If the number is equidistant
// from two integers, round it away from zero.
// Examples
// >>> closest_integer(("10"))
// (10)
// >>> closest_integer(("15.3"))
// (15)
// Note:
// Rounding away from zero means that if the given number is equidistant
// from two integers, the one you should return is the one that is the
// farthest from zero. For example closest_integer("14.5") should
// return 15 and closest_integer("-14.5") should return -15.
long closest_integer(std::string value) {
    double f = std::stod(value);
    if (f >= 0) {
        return (long) std::floor(f + 0.5);
    } else {
        return (long) std::ceil(f - 0.5);
    }
}

int main() {
    std::cout << closest_integer("10") << std::endl;
    std::cout << closest_integer("15.3") << std::endl;
    std::cout << closest_integer("14.5") << std::endl;
    std::cout << closest_integer("-14.5") << std::endl;
}