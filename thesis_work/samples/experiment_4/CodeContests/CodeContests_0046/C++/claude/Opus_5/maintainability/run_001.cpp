#include <cstdlib>
#include <iostream>
#include <sstream>
#include <vector>

namespace {

long long manhattanDistance(long long fromX, long long fromY, long long toX, long long toY) {
    return std::llabs(fromX - toX) + std::llabs(fromY - toY);
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int studentCount, checkpointCount;
    std::cin >> studentCount >> checkpointCount;

    std::vector<long long> studentX(studentCount), studentY(studentCount);
    for (int i = 0; i < studentCount; ++i) {
        std::cin >> studentX[i] >> studentY[i];
    }

    std::vector<long long> checkpointX(checkpointCount), checkpointY(checkpointCount);
    for (int j = 0; j < checkpointCount; ++j) {
        std::cin >> checkpointX[j] >> checkpointY[j];
    }

    // Ties are resolved in favour of the smallest index, which falls out of
    // scanning left to right and only replacing on a strict improvement.
    std::ostringstream answers;
    for (int i = 0; i < studentCount; ++i) {
        int bestNumber = 1;
        long long bestDistance =
            manhattanDistance(studentX[i], studentY[i], checkpointX[0], checkpointY[0]);

        for (int j = 1; j < checkpointCount; ++j) {
            const long long distance =
                manhattanDistance(studentX[i], studentY[i], checkpointX[j], checkpointY[j]);
            if (distance < bestDistance) {
                bestDistance = distance;
                bestNumber = j + 1;
            }
        }
        answers << bestNumber << '\n';
    }

    std::cout << answers.str();
    return 0;
}