#include <iostream>
#include <sstream>
#include <vector>

namespace {

constexpr int kImpossible = -1;
constexpr int kMinimumExtraEdges = 1;
constexpr int kTooFewTowns = 4;

std::string describePath(int firstHead, int secondHead, const std::vector<int>& middle,
                         int firstTail, int secondTail) {
    std::ostringstream path;
    path << firstHead << ' ' << secondHead;
    for (int town : middle) {
        path << ' ' << town;
    }
    path << ' ' << firstTail << ' ' << secondTail;
    return path.str();
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int townCount, edgeBudget, a, b, c, d;
    std::cin >> townCount >> edgeBudget >> a >> b >> c >> d;

    // With only four towns there is no room for a middle section, and the
    // construction always spends exactly townCount + 1 edges.
    if (townCount == kTooFewTowns || edgeBudget < townCount + kMinimumExtraEdges) {
        std::cout << kImpossible << '\n';
        return 0;
    }

    std::vector<int> middle;
    for (int town = 1; town <= townCount; ++town) {
        if (town != a && town != b && town != c && town != d) {
            middle.push_back(town);
        }
    }

    // Both paths share the same middle spine but enter and leave it in a
    // different order, keeping each forbidden pair non-adjacent.
    std::cout << describePath(a, c, middle, d, b) << '\n';
    std::cout << describePath(c, a, middle, b, d) << '\n';
    return 0;
}