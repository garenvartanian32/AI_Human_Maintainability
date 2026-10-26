#include <assert.h>
#include <bits/stdc++.h>

bool correct_bracketing(std::string brackets) {
    int open_brackets = 0;

    for (char bracket : brackets) {
        if (bracket == '(') {
            ++open_brackets;
        } else {
            --open_brackets;
        }

        if (open_brackets < 0) {
            return false;
        }
    }

    return open_brackets == 0;
}