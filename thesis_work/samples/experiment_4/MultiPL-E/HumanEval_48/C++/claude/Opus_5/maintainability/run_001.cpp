#include <assert.h>
#include <bits/stdc++.h>

bool is_palindrome(std::string text) {
    const std::size_t length = text.size();
    for (std::size_t left = 0; left < length / 2; ++left) {
        const std::size_t right = length - 1 - left;
        if (text[left] != text[right]) {
            return false;
        }
    }
    return true;
}