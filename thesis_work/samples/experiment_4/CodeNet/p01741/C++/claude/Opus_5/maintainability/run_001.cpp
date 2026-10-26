#include <algorithm>
#include <cmath>
#include <cstdio>
#include <iostream>

namespace {

constexpr double kMinimumLeg = 1.0;
constexpr double kFloorTolerance = 1e-9;

/**
 * Returns the greatest total road length within straight-line `limit`.
 *
 * A route is a horizontal leg plus a vertical leg. For an integral number of
 * horizontal blocks the vertical leg stretches to whatever the straight-line
 * budget still allows, but never below one block. The unconstrained diagonal is
 * also a candidate, so it seeds the search.
 */
double longestReachable(double limit) {
    double best = limit * std::sqrt(2.0);

    const int horizontalCeiling = static_cast<int>(std::floor(limit + kFloorTolerance));
    for (int horizontal = 1; horizontal <= horizontalCeiling; ++horizontal) {
        const double remainingSquared =
            std::max(limit * limit - static_cast<double>(horizontal) * horizontal, 0.0);
        const double vertical = std::max(kMinimumLeg, std::sqrt(remainingSquared));
        best = std::max(best, horizontal + vertical);
    }

    return best;
}

}  // namespace

int main() {
    double limit;
    std::cin >> limit;
    std::printf("%.12f\n", longestReachable(limit));
    return 0;
}