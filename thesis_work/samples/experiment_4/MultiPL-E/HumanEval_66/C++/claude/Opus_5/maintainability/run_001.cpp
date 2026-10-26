#include <assert.h>
#include <bits/stdc++.h>

long digitSum(std::string s) {
    long ascii_sum = 0;

    for (char character : s) {
        const bool is_uppercase_letter =
            character >= 'A' && character <= 'Z';

        if (is_uppercase_letter) {
            ascii_sum += static_cast<unsigned char>(character);
        }
    }

    return ascii_sum;
}