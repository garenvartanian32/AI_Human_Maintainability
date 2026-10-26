#include <iostream>
#include <sstream>
#include <string>

/**
 * Returns the least positive multiple of `d` that is not within [l, r].
 *
 * If `d` itself already falls outside the forbidden range it is the answer;
 * otherwise the first free multiple is the next one after `r`.
 */
static long long smallestMultipleOutside(long long l, long long r, long long d) {
    if (d < l || d > r) {
        return d;
    }
    return (r / d + 1) * d;
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int queryCount;
    std::cin >> queryCount;

    std::ostringstream answers;
    for (int query = 0; query < queryCount; ++query) {
        long long l, r, d;
        std::cin >> l >> r >> d;
        answers << smallestMultipleOutside(l, r, d) << '\n';
    }

    std::cout << answers.str();
    return 0;
}