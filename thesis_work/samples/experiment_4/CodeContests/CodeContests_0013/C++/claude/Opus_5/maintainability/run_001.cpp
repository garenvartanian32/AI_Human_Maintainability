#include <iostream>
#include <numeric>
#include <vector>

namespace {

constexpr long long kModulus = 1000000007LL;
constexpr int kGround = 0;  // virtual node for "no second coordinate"

/// Union-find over coordinate nodes, with path compression.
class DisjointSet {
public:
    explicit DisjointSet(int size) : parent_(size + 1) {
        std::iota(parent_.begin(), parent_.end(), 0);
    }

    int find(int node) {
        while (parent_[node] != node) {
            parent_[node] = parent_[parent_[node]];
            node = parent_[node];
        }
        return node;
    }

    /// Merges the two components; returns true when they were distinct.
    bool unite(int first, int second) {
        const int firstRoot = find(first);
        const int secondRoot = find(second);
        if (firstRoot == secondRoot) {
            return false;
        }
        parent_[firstRoot] = secondRoot;
        return true;
    }

private:
    std::vector<int> parent_;
};

long long modularPowerOfTwo(int exponent) {
    long long result = 1;
    long long factor = 2;
    for (int remaining = exponent; remaining > 0; remaining >>= 1) {
        if (remaining & 1) {
            result = result * factor % kModulus;
        }
        factor = factor * factor % kModulus;
    }
    return result;
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int vectorCount, coordinateCount;
    std::cin >> vectorCount >> coordinateCount;

    // Each vector has one or two set coordinates, so it behaves like a graph
    // edge; independence over GF(2) is exactly acyclicity.
    DisjointSet components(coordinateCount);
    std::vector<int> basisIndices;

    for (int vectorIndex = 1; vectorIndex <= vectorCount; ++vectorIndex) {
        int setCoordinates;
        std::cin >> setCoordinates;

        int first, second;
        if (setCoordinates == 1) {
            first = kGround;
            std::cin >> second;
        } else {
            std::cin >> first >> second;
        }

        if (components.unite(first, second)) {
            basisIndices.push_back(vectorIndex);
        }
    }

    std::cout << modularPowerOfTwo(static_cast<int>(basisIndices.size()))
              << ' ' << basisIndices.size() << '\n';
    for (std::size_t i = 0; i < basisIndices.size(); ++i) {
        if (i > 0) {
            std::cout << ' ';
        }
        std::cout << basisIndices[i];
    }
    std::cout << '\n';
    return 0;
}