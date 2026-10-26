#include <algorithm>
#include <iostream>
#include <vector>

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    long long count;
    std::cin >> count;

    long long total = 0;
    long long largestScore = 0;
    for (long long i = 0; i < count; ++i) {
        long long score;
        std::cin >> score;
        total += score;
        largestScore = std::max(largestScore, score);
    }

    // The answer must be at least the current maximum, and large enough that
    // `count` copies of it strictly exceed twice the current total.
    long long smallestWinning = largestScore;
    while (count * smallestWinning <= 2 * total) {
        ++smallestWinning;
    }

    std::cout << smallestWinning << '\n';
    return 0;
}