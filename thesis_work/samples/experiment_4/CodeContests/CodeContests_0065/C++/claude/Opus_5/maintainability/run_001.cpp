#include <algorithm>
#include <iostream>
#include <vector>

namespace {

constexpr int kFirstPlayerWins = 1;
constexpr int kSecondPlayerWins = 2;
constexpr int kNoMoveNeeded = 0;

/// Returns every prime factor of `value`, repeated by its multiplicity.
std::vector<long long> primeFactorsWithMultiplicity(long long value) {
    std::vector<long long> factors;
    long long remaining = value;

    for (long long divisor = 2; divisor * divisor <= remaining; ++divisor) {
        while (remaining % divisor == 0) {
            factors.push_back(divisor);
            remaining /= divisor;
        }
    }

    if (remaining > 1) {
        factors.push_back(remaining);
    }
    return factors;
}

}  // namespace

int main() {
    long long number;
    std::cin >> number;

    if (number == 1) {
        // Already a losing position for the opponent; nothing to play.
        std::cout << kFirstPlayerWins << '\n' << kNoMoveNeeded << '\n';
        return 0;
    }

    std::vector<long long> factors = primeFactorsWithMultiplicity(number);

    if (factors.size() == 1) {
        // A prime has no non-trivial divisor, so the opponent is stuck.
        std::cout << kFirstPlayerWins << '\n' << kNoMoveNeeded << '\n';
    } else if (factors.size() == 2) {
        // Any move hands a winning position to the opponent.
        std::cout << kSecondPlayerWins << '\n';
    } else {
        // Move to the product of the two smallest primes, a losing position.
        std::sort(factors.begin(), factors.end());
        std::cout << kFirstPlayerWins << '\n' << factors[0] * factors[1] << '\n';
    }
    return 0;
}