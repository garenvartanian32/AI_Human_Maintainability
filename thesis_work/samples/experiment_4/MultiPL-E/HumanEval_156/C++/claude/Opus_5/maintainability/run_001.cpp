#include<assert.h>
#include<bits/stdc++.h>
// Given a positive integer, obtain its roman numeral equivalent as a string,
// and return it in lowercase.
// Restrictions: 1 <= num <= 1000
// Examples:
// >>> int_to_mini_roman((19))
// ("xix")
// >>> int_to_mini_roman((152))
// ("clii")
// >>> int_to_mini_roman((426))
// ("cdxxvi")
std::string int_to_mini_roman(long number) {
    // Values run largest to smallest and include the subtractive pairs
    // (cm, cd, xc, ...), so a plain greedy sweep never needs to backtrack.
    static const long numeralValues[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    static const std::string numeralSymbols[] = {
        "m", "cm", "d", "cd", "c", "xc", "l", "xl", "x", "ix", "v", "iv", "i"};
    constexpr int kNumeralCount = 13;

    std::string roman;
    long remaining = number;

    for (int i = 0; i < kNumeralCount; ++i) {
        const long repeats = remaining / numeralValues[i];
        remaining %= numeralValues[i];
        for (long repeat = 0; repeat < repeats; ++repeat) {
            roman += numeralSymbols[i];
        }
    }

    return roman;
}