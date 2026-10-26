#include <iostream>

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    long long gagCount;
    std::cin >> gagCount;

    long long totalValue = 0;
    for (long long gag = 0; gag < gagCount; ++gag) {
        long long value;
        std::cin >> value;
        totalValue += value;
    }

    // The nth gag performed loses n happiness, so ordering never matters: the
    // penalties are always 1, 2, ..., gagCount in some assignment.
    const long long totalPenalty = gagCount * (gagCount + 1) / 2;

    std::cout << totalValue - totalPenalty << '\n';
    return 0;
}