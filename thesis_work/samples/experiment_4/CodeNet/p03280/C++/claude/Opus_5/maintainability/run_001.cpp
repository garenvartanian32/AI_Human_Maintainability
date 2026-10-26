#include <iostream>

int main() {
    long long rows, columns;
    std::cin >> rows >> columns;

    // Stripping the outermost row and column from each side leaves the squares
    // that have neighbours on all four sides.
    std::cout << (rows - 1) * (columns - 1) << '\n';
    return 0;
}