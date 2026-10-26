#include <iostream>
#include <sstream>
#include <string>
#include <vector>

namespace {

constexpr long long kModulus = 1000000007LL;
constexpr int kMaxOperations = 200001;
constexpr int kSplitDelayOne = 9;
constexpr int kSplitDelayTwo = 10;

/// Length a freshly created digit 0 grows to after t further operations.
std::vector<long long> buildLengthTable() {
    std::vector<long long> lengths(kMaxOperations + 1, 0);
    lengths[0] = 1;
    for (int steps = 1; steps <= kMaxOperations; ++steps) {
        const long long first =
            (steps <= kSplitDelayOne - 1) ? 1 : lengths[steps - kSplitDelayOne];
        const long long second =
            (steps <= kSplitDelayTwo - 1) ? 1 : lengths[steps - kSplitDelayTwo];
        lengths[steps] = (first + second) % kModulus;
    }
    return lengths;
}

/// Length that a single `digit` reaches after `operations` steps.
long long grownLength(const std::vector<long long>& lengthAfter, int digit, long long operations) {
    const long long stepsUntilOverflow = 9 - digit;
    if (operations < stepsUntilOverflow) {
        return 1;
    }
    return lengthAfter[operations - stepsUntilOverflow];
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    const std::vector<long long> lengthAfter = buildLengthTable();

    int testCount;
    std::cin >> testCount;

    std::ostringstream answers;
    for (int test = 0; test < testCount; ++test) {
        std::string digits;
        long long operations;
        std::cin >> digits >> operations;

        long long total = 0;
        for (char character : digits) {
            total += grownLength(lengthAfter, character - '0', operations);
            total %= kModulus;
        }
        answers << total << '\n';
    }

    std::cout << answers.str();
    return 0;
}