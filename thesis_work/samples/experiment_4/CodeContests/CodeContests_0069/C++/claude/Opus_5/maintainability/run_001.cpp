#include <iostream>
#include <queue>
#include <unordered_map>
#include <unordered_set>

namespace {

constexpr int kUnreachable = -1;
constexpr long long kColumnRange = 2000000000LL;

constexpr int kRowDeltas[8] = {-1, -1, -1, 0, 0, 1, 1, 1};
constexpr int kColumnDeltas[8] = {-1, 0, 1, -1, 1, -1, 0, 1};

long long pack(long long row, long long column) {
    return row * kColumnRange + column;
}

/// Breadth-first search over the allowed squares only.
int shortestWalk(long long start, long long goal, const std::unordered_set<long long>& allowed) {
    if (allowed.count(start) == 0 || allowed.count(goal) == 0) {
        return kUnreachable;
    }

    std::unordered_map<long long, int> stepsTo;
    stepsTo[start] = 0;
    std::queue<long long> frontier;
    frontier.push(start);

    while (!frontier.empty()) {
        const long long square = frontier.front();
        frontier.pop();
        if (square == goal) {
            return stepsTo[square];
        }

        const long long row = square / kColumnRange;
        const long long column = square - row * kColumnRange;
        const int nextSteps = stepsTo[square] + 1;

        for (int move = 0; move < 8; ++move) {
            const long long neighbour =
                pack(row + kRowDeltas[move], column + kColumnDeltas[move]);
            if (allowed.count(neighbour) > 0 && stepsTo.count(neighbour) == 0) {
                stepsTo[neighbour] = nextSteps;
                frontier.push(neighbour);
            }
        }
    }

    return kUnreachable;
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    long long startRow, startColumn, goalRow, goalColumn;
    std::cin >> startRow >> startColumn >> goalRow >> goalColumn;

    int segmentCount;
    std::cin >> segmentCount;

    // The board arrives as horizontal segments, far smaller than the full
    // coordinate range, so only these squares may be stepped on.
    std::unordered_set<long long> allowed;
    for (int segment = 0; segment < segmentCount; ++segment) {
        long long row, firstColumn, lastColumn;
        std::cin >> row >> firstColumn >> lastColumn;
        for (long long column = firstColumn; column <= lastColumn; ++column) {
            allowed.insert(pack(row, column));
        }
    }

    std::cout << shortestWalk(pack(startRow, startColumn), pack(goalRow, goalColumn), allowed)
              << '\n';
    return 0;
}