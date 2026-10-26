#include <iostream>
#include <sstream>
#include <string>
#include <vector>

namespace {

constexpr int kBitWidth = 64;
constexpr int kFirstPlayerWins = 1;
constexpr int kFirstPlayerLoses = 0;
constexpr char kFirstPlayerMark = '1';

/**
 * True when the first player owns a value that is linearly independent.
 *
 * Values are folded into a linear basis over GF(2) from the back of the list
 * forwards. The first value that survives reduction is the deciding one.
 */
int firstPlayerWins(const std::vector<unsigned long long>& values, const std::string& owners) {
    unsigned long long basis[kBitWidth] = {0};

    for (int index = static_cast<int>(values.size()) - 1; index >= 0; --index) {
        unsigned long long residue = values[index];

        for (int bit = kBitWidth - 1; bit >= 0; --bit) {
            if (!((residue >> bit) & 1ULL)) {
                continue;
            }
            if (basis[bit] == 0) {
                basis[bit] = residue;
                return (owners[index] == kFirstPlayerMark) ? kFirstPlayerWins
                                                           : kFirstPlayerLoses;
            }
            residue ^= basis[bit];
        }
    }

    return kFirstPlayerLoses;
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int testCount;
    std::cin >> testCount;

    std::ostringstream verdicts;
    while (testCount-- > 0) {
        int valueCount;
        std::cin >> valueCount;

        std::vector<unsigned long long> values(valueCount);
        for (auto& value : values) {
            std::cin >> value;
        }

        std::string owners;
        std::cin >> owners;

        verdicts << firstPlayerWins(values, owners) << '\n';
    }

    std::cout << verdicts.str();
    return 0;
}