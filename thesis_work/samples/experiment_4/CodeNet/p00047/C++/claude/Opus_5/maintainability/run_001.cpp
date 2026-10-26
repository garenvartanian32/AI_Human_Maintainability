#include <iostream>
#include <string>

namespace {

constexpr char kStartingCup = 'A';
constexpr std::size_t kLeftCupIndex = 0;
constexpr std::size_t kRightCupIndex = 2;  // "X,Y" places the second cup here

}  // namespace

int main() {
    char ballLocation = kStartingCup;

    std::string line;
    while (std::getline(std::cin, line)) {
        if (line.size() <= kRightCupIndex) {
            continue;
        }

        const char leftCup = line[kLeftCupIndex];
        const char rightCup = line[kRightCupIndex];

        // The ball only moves when its own cup is one of the two swapped.
        if (ballLocation == leftCup) {
            ballLocation = rightCup;
        } else if (ballLocation == rightCup) {
            ballLocation = leftCup;
        }
    }

    std::cout << ballLocation << '\n';
    return 0;
}