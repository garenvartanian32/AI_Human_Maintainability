#include <assert.h>
#include <bits/stdc++.h>

long multiply(long a, long b) {
    return std::labs(a % 10) * std::labs(b % 10);
}