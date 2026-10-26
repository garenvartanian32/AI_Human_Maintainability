#include <iostream>
#include <sstream>
#include <vector>

namespace {

constexpr long long kPlaceholder = 0;

bool canReplay(long long entry, long long size) {
    return entry == kPlaceholder || entry <= size;
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int testCount;
    std::cin >> testCount;

    std::ostringstream answers;
    while (testCount-- > 0) {
        long long size;
        int firstLength, secondLength;
        std::cin >> size >> firstLength >> secondLength;

        std::vector<long long> first(firstLength), second(secondLength);
        for (auto& entry : first) std::cin >> entry;
        for (auto& entry : second) std::cin >> entry;

        // An entry may be replayed only if it targets a position that already
        // exists; a placeholder appends a new position. Taking whichever log
        // has a currently-valid head is always safe.
        std::vector<long long> merged;
        int firstIndex = 0, secondIndex = 0;
        bool possible = true;

        while (firstIndex < firstLength || secondIndex < secondLength) {
            long long entry;
            if (firstIndex < firstLength && canReplay(first[firstIndex], size)) {
                entry = first[firstIndex++];
            } else if (secondIndex < secondLength && canReplay(second[secondIndex], size)) {
                entry = second[secondIndex++];
            } else {
                possible = false;
                break;
            }

            if (entry == kPlaceholder) {
                ++size;
            }
            merged.push_back(entry);
        }

        if (!possible) {
            answers << "-1\n";
        } else {
            for (std::size_t i = 0; i < merged.size(); ++i) {
                if (i > 0) answers << ' ';
                answers << merged[i];
            }
            answers << '\n';
        }
    }

    std::cout << answers.str();
    return 0;
}