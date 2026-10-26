#include <algorithm>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

namespace {

constexpr char kPlus = '+';

}  // namespace

int main() {
    std::string expression;
    std::cin >> expression;

    // Only the multiset of summands matters, so sorting them is enough.
    std::vector<std::string> summands;
    std::istringstream stream(expression);
    std::string summand;
    while (std::getline(stream, summand, kPlus)) {
        summands.push_back(summand);
    }
    std::sort(summands.begin(), summands.end());

    for (std::size_t i = 0; i < summands.size(); ++i) {
        if (i > 0) {
            std::cout << kPlus;
        }
        std::cout << summands[i];
    }
    std::cout << '\n';
    return 0;
}