#include <algorithm>
#include <iostream>
#include <vector>

namespace {

constexpr int kTeamSize = 10;
constexpr int kCountedResults = 3;

/// Sum of the three highest results for the team starting at `offset`.
int teamScore(const std::vector<int>& allResults, int offset) {
    std::vector<int> results(allResults.begin() + offset,
                             allResults.begin() + offset + kTeamSize);
    std::sort(results.rbegin(), results.rend());

    int score = 0;
    for (int i = 0; i < kCountedResults; ++i) {
        score += results[i];
    }
    return score;
}

}  // namespace

int main() {
    std::vector<int> allResults(2 * kTeamSize);
    for (int& result : allResults) {
        std::cin >> result;
    }

    std::cout << teamScore(allResults, 0) << ' '
              << teamScore(allResults, kTeamSize) << '\n';
    return 0;
}