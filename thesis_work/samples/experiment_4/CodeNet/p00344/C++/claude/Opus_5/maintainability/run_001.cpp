#include <algorithm>
#include <iostream>
#include <vector>

namespace {

constexpr int kUnvisited = 0;
constexpr int kOnCurrentWalk = 1;
constexpr int kFinished = 2;

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int squareCount;
    std::cin >> squareCount;

    // Every square has exactly one successor, so the board is a functional
    // graph: each walk eventually runs into a cycle.
    std::vector<int> successor(squareCount);
    for (int index = 0; index < squareCount; ++index) {
        long long step;
        std::cin >> step;
        successor[index] = static_cast<int>((index + step) % squareCount);
    }

    std::vector<int> state(squareCount, kUnvisited);
    std::vector<char> liesOnCycle(squareCount, 0);

    for (int start = 0; start < squareCount; ++start) {
        if (state[start] != kUnvisited) {
            continue;
        }

        std::vector<int> walk;
        int square = start;
        while (state[square] == kUnvisited) {
            state[square] = kOnCurrentWalk;
            walk.push_back(square);
            square = successor[square];
        }

        // Re-entering the current walk means we closed a fresh cycle.
        if (state[square] == kOnCurrentWalk) {
            const auto cycleStart = std::find(walk.begin(), walk.end(), square);
            for (auto member = cycleStart; member != walk.end(); ++member) {
                liesOnCycle[*member] = 1;
            }
        }

        for (int member : walk) {
            state[member] = kFinished;
        }
    }

    std::cout << std::count(liesOnCycle.begin(), liesOnCycle.end(), 1) << '\n';
    return 0;
}