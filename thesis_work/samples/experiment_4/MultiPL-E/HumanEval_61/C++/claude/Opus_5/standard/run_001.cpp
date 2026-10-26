#include <assert.h>
#include <bits/stdc++.h>

bool correct_bracketing(std::string brackets) {
    int balance = 0;
    for (char c : brackets) {
        balance += c == '(' ? 1 : -1;
        if (balance < 0) return false;
    }
    return balance == 0;
}