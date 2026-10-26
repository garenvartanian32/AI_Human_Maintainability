#include <bitset>
#include <iostream>
#include <vector>

namespace {

constexpr int kWeightUnit = 100;
constexpr int kMaxTotalUnits = 100005;

/**
 * True when some subset of the weights sums to exactly half the total.
 *
 * A bitset tracks every reachable subtotal at once; shifting by an apple's
 * weight adds that apple to all of them simultaneously.
 */
bool canSplitEvenly(const std::vector<int>& unitWeights, int total) {
    if (total % 2 != 0) {
        return false;
    }

    std::bitset<kMaxTotalUnits> reachableSubtotals;
    reachableSubtotals[0] = true;
    for (int weight : unitWeights) {
        reachableSubtotals |= reachableSubtotals << weight;
    }

    return reachableSubtotals[total / 2];
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int appleCount;
    std::cin >> appleCount;

    std::vector<int> unitWeights(appleCount);
    int total = 0;
    for (int& weight : unitWeights) {
        int rawWeight;
        std::cin >> rawWeight;
        weight = rawWeight / kWeightUnit;
        total += weight;
    }

    std::cout << (canSplitEvenly(unitWeights, total) ? "YES" : "NO") << '\n';
    return 0;
}