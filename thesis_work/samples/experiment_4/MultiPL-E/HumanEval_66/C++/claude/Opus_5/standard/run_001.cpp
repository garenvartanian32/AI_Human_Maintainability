#include <assert.h>
#include <bits/stdc++.h>

long digitSum(std::string s) {
    long sum = 0;
    for (char c : s) {
        if (c >= 'A' && c <= 'Z') sum += static_cast<int>(c);
    }
    return sum;
}