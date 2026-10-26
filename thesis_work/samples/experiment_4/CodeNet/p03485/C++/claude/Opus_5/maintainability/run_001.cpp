#include <iostream>

int main() {
    long long first, second;
    std::cin >> first >> second;

    // Adding one before the integer division rounds .5 upwards.
    std::cout << (first + second + 1) / 2 << '\n';
    return 0;
}