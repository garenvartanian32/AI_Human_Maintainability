#include <iostream>

namespace {

bool isOdd(long long value) {
    return value % 2 != 0;
}

}  // namespace

int main() {
    long long first, second;
    std::cin >> first >> second;

    // A product is odd only when neither factor contributes a factor of two.
    const bool productIsOdd = isOdd(first) && isOdd(second);
    std::cout << (productIsOdd ? "Odd" : "Even") << '\n';
    return 0;
}