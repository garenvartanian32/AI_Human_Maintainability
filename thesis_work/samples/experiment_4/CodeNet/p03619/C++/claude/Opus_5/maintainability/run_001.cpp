#include <algorithm>
#include <cmath>
#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <utility>
#include <vector>

namespace {

constexpr double kBlockLength = 100.0;
constexpr double kFountainRadius = 10.0;

/// Longest strictly increasing chain by the second coordinate.
int longestIncreasingRun(const std::vector<std::pair<long long, long long>>& points) {
    std::vector<long long> tails;
    for (const auto& point : points) {
        auto slot = std::lower_bound(tails.begin(), tails.end(), point.second);
        if (slot == tails.end()) {
            tails.push_back(point.second);
        } else {
            *slot = point.second;
        }
    }
    return static_cast<int>(tails.size());
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    // Cutting a corner around a fountain replaces two quarter-block legs with a
    // quarter arc; a fountain on a straight stretch forces a detour instead.
    const double cornerSaving = kFountainRadius * (2 - M_PI / 2);
    const double straightDetour = kFountainRadius * (M_PI - 2);

    long long startX, startY, endX, endY;
    std::cin >> startX >> startY >> endX >> endY;

    int fountainCount;
    std::cin >> fountainCount;

    std::vector<long long> fountainX(fountainCount), fountainY(fountainCount);
    for (int i = 0; i < fountainCount; ++i) {
        std::cin >> fountainX[i] >> fountainY[i];
    }

    const double walk =
        (std::llabs(startX - endX) + std::llabs(startY - endY)) * kBlockLength;

    // A purely horizontal or vertical trip has no corners to cut.
    if (startX == endX || startY == endY) {
        bool blocked = false;
        if (startY == endY) {
            const long long low = std::min(startX, endX);
            const long long high = std::max(startX, endX);
            for (int i = 0; i < fountainCount; ++i) {
                if (fountainY[i] == startY && fountainX[i] > low && fountainX[i] < high) {
                    blocked = true;
                    break;
                }
            }
        } else {
            const long long low = std::min(startY, endY);
            const long long high = std::max(startY, endY);
            for (int i = 0; i < fountainCount; ++i) {
                if (fountainX[i] == startX && fountainY[i] > low && fountainY[i] < high) {
                    blocked = true;
                    break;
                }
            }
        }
        std::printf("%.15f\n", walk + (blocked ? straightDetour : 0.0));
        return 0;
    }

    // Reflect coordinates so the trip always runs in the increasing direction,
    // turning "usable fountains" into a plain increasing subsequence.
    const int xSign = (endX > startX) ? 1 : -1;
    const int ySign = (endY > startY) ? 1 : -1;
    const long long lowX = std::min(startX, endX), highX = std::max(startX, endX);
    const long long lowY = std::min(startY, endY), highY = std::max(startY, endY);

    std::vector<std::pair<long long, long long>> inside;
    for (int i = 0; i < fountainCount; ++i) {
        if (fountainX[i] > lowX && fountainX[i] < highX
            && fountainY[i] > lowY && fountainY[i] < highY) {
            inside.emplace_back(fountainX[i] * xSign, fountainY[i] * ySign);
        }
    }
    std::sort(inside.begin(), inside.end());

    std::printf("%.15f\n", walk - longestIncreasingRun(inside) * cornerSaving);
    return 0;
}