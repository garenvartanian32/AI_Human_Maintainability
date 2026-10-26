#include <iostream>
#include <vector>

namespace {

constexpr int kDegreeThatBlocks = 2;

/// A vertex of degree exactly two cannot be satisfied.
bool hasBlockingVertex(const std::vector<int>& degrees) {
    for (std::size_t vertex = 1; vertex < degrees.size(); ++vertex) {
        if (degrees[vertex] == kDegreeThatBlocks) {
            return true;
        }
    }
    return false;
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int vertexCount;
    std::cin >> vertexCount;

    std::vector<int> degrees(vertexCount + 1, 0);
    for (int edge = 0; edge < vertexCount - 1; ++edge) {
        int firstEndpoint, secondEndpoint;
        std::cin >> firstEndpoint >> secondEndpoint;
        degrees[firstEndpoint]++;
        degrees[secondEndpoint]++;
    }

    std::cout << (hasBlockingVertex(degrees) ? "NO" : "YES") << '\n';
    return 0;
}