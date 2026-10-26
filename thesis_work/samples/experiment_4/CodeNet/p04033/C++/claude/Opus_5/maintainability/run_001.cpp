#include <iostream>

int main() {
    long long low, high;
    std::cin >> low >> high;

    if (low <= 0 && 0 <= high) {
        // Zero is in range, so it annihilates the product.
        std::cout << "Zero\n";
    } else if (low > 0) {
        // Every factor is positive.
        std::cout << "Positive\n";
    } else {
        // Every factor is negative; the sign follows the count's parity.
        const long long factorCount = high - low + 1;
        std::cout << (factorCount % 2 == 0 ? "Positive" : "Negative") << '\n';
    }
    return 0;
}