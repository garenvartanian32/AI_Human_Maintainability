#include <algorithm>
#include <iostream>
#include <numeric>
#include <utility>
#include <vector>

namespace {

/// Binary indexed tree tracking both a count and a sum of coordinates.
class FenwickTree {
public:
    explicit FenwickTree(int size)
        : size_(size), counts_(size + 1, 0), coordinateSums_(size + 1, 0) {}

    /// Records one point with the given coordinate at `position`.
    void add(int position, long long coordinate) {
        for (int i = position; i <= size_; i += i & -i) {
            counts_[i]++;
            coordinateSums_[i] += coordinate;
        }
    }

    /// Returns (count, coordinate sum) over ranks 1..`position`.
    std::pair<long long, long long> prefix(int position) const {
        long long count = 0;
        long long coordinateSum = 0;
        for (int i = position; i > 0; i -= i & -i) {
            count += counts_[i];
            coordinateSum += coordinateSums_[i];
        }
        return {count, coordinateSum};
    }

private:
    int size_;
    std::vector<long long> counts_;
    std::vector<long long> coordinateSums_;
};

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int pointCount;
    std::cin >> pointCount;

    std::vector<long long> coordinates(pointCount);
    std::vector<long long> velocities(pointCount);
    for (auto& value : coordinates) std::cin >> value;
    for (auto& value : velocities) std::cin >> value;

    // Two points converge exactly when the left one is not slower than the
    // right one, so we sweep by coordinate and query smaller-or-equal speeds.
    std::vector<long long> distinctVelocities = velocities;
    std::sort(distinctVelocities.begin(), distinctVelocities.end());
    distinctVelocities.erase(
        std::unique(distinctVelocities.begin(), distinctVelocities.end()),
        distinctVelocities.end());

    std::vector<int> byCoordinate(pointCount);
    std::iota(byCoordinate.begin(), byCoordinate.end(), 0);
    std::sort(byCoordinate.begin(), byCoordinate.end(),
              [&coordinates](int a, int b) { return coordinates[a] < coordinates[b]; });

    FenwickTree tree(static_cast<int>(distinctVelocities.size()));
    long long totalDistance = 0;

    for (int index : byCoordinate) {
        const int rank = static_cast<int>(std::lower_bound(distinctVelocities.begin(),
                                                           distinctVelocities.end(),
                                                           velocities[index])
                                          - distinctVelocities.begin()) + 1;
        const auto [earlierCount, earlierSum] = tree.prefix(rank);
        totalDistance += earlierCount * coordinates[index] - earlierSum;
        tree.add(rank, coordinates[index]);
    }

    std::cout << totalDistance << '\n';
    return 0;
}