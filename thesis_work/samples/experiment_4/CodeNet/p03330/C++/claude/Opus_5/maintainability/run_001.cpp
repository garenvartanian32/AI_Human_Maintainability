#include <algorithm>
#include <climits>
#include <iostream>
#include <vector>

namespace {

constexpr int kGroupCount = 3;

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int gridSize, colourCount;
    std::cin >> gridSize >> colourCount;

    std::vector<std::vector<long long>> repaintCost(
        colourCount, std::vector<long long>(colourCount));
    for (int source = 0; source < colourCount; ++source) {
        for (int target = 0; target < colourCount; ++target) {
            std::cin >> repaintCost[source][target];
        }
    }

    // Cells sharing (row + column) mod 3 must end up the same colour, so only
    // the tally of current colours per group matters.
    std::vector<std::vector<long long>> tally(kGroupCount,
                                              std::vector<long long>(colourCount, 0));
    for (int row = 0; row < gridSize; ++row) {
        for (int column = 0; column < gridSize; ++column) {
            int currentColour;
            std::cin >> currentColour;
            tally[(row + column) % kGroupCount][currentColour - 1]++;
        }
    }

    std::vector<std::vector<long long>> groupCost(kGroupCount,
                                                  std::vector<long long>(colourCount, 0));
    for (int group = 0; group < kGroupCount; ++group) {
        for (int target = 0; target < colourCount; ++target) {
            long long cost = 0;
            for (int source = 0; source < colourCount; ++source) {
                cost += tally[group][source] * repaintCost[source][target];
            }
            groupCost[group][target] = cost;
        }
    }

    // The three groups must receive three distinct colours.
    long long best = LLONG_MAX;
    for (int first = 0; first < colourCount; ++first) {
        for (int second = 0; second < colourCount; ++second) {
            if (second == first) continue;
            for (int third = 0; third < colourCount; ++third) {
                if (third == first || third == second) continue;
                best = std::min(best,
                                groupCost[0][first] + groupCost[1][second] + groupCost[2][third]);
            }
        }
    }

    std::cout << best << '\n';
    return 0;
}