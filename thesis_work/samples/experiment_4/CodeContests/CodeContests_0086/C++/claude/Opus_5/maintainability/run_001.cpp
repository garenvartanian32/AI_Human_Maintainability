#include <algorithm>
#include <iostream>
#include <numeric>
#include <vector>

namespace {

/// Union-find over positions, with path compression.
class DisjointSet {
public:
    explicit DisjointSet(int size) : parent_(size + 1) {
        std::iota(parent_.begin(), parent_.end(), 0);
    }

    int find(int position) {
        while (parent_[position] != position) {
            parent_[position] = parent_[parent_[position]];
            position = parent_[position];
        }
        return position;
    }

    void unite(int first, int second) {
        const int firstRoot = find(first);
        const int secondRoot = find(second);
        if (firstRoot != secondRoot) {
            parent_[firstRoot] = secondRoot;
        }
    }

private:
    std::vector<int> parent_;
};

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int size, swapCount;
    std::cin >> size >> swapCount;

    std::vector<int> values(size + 1);
    for (int position = 1; position <= size; ++position) {
        std::cin >> values[position];
    }

    // Swaps are freely composable, so within one connected component the values
    // can be permuted arbitrarily; largest-first at the smallest positions then
    // gives the lexicographic maximum.
    DisjointSet positions(size);
    for (int swap = 0; swap < swapCount; ++swap) {
        int first, second;
        std::cin >> first >> second;
        positions.unite(first, second);
    }

    std::vector<std::vector<int>> componentPositions(size + 1);
    for (int position = 1; position <= size; ++position) {
        componentPositions[positions.find(position)].push_back(position);
    }

    std::vector<int> answer(size + 1, 0);
    for (int root = 1; root <= size; ++root) {
        const std::vector<int>& memberPositions = componentPositions[root];
        if (memberPositions.empty()) {
            continue;
        }

        std::vector<int> available;
        available.reserve(memberPositions.size());
        for (int position : memberPositions) {
            available.push_back(values[position]);
        }
        std::sort(available.rbegin(), available.rend());

        for (std::size_t i = 0; i < memberPositions.size(); ++i) {
            answer[memberPositions[i]] = available[i];
        }
    }

    for (int position = 1; position <= size; ++position) {
        if (position > 1) {
            std::cout << ' ';
        }
        std::cout << answer[position];
    }
    std::cout << '\n';
    return 0;
}