#include <iostream>
#include <string>
#include <vector>

namespace {

constexpr int kHexBits = 4;

int gSize;
std::vector<std::vector<int>> gPrefix;

/// Standard 2-D prefix sums, padded with a zero row and column.
void buildPrefixSums(const std::vector<std::vector<char>>& matrix) {
    gPrefix.assign(gSize + 1, std::vector<int>(gSize + 1, 0));
    for (int row = 1; row <= gSize; ++row) {
        int running = 0;
        for (int column = 1; column <= gSize; ++column) {
            running += matrix[row - 1][column - 1];
            gPrefix[row][column] = gPrefix[row - 1][column] + running;
        }
    }
}

/// True when the side x side block is entirely zeros or entirely ones.
bool blockIsUniform(int top, int left, int side) {
    const int bottom = top + side;
    const int right = left + side;
    const int ones = gPrefix[bottom][right] - gPrefix[top][right]
                   - gPrefix[bottom][left] + gPrefix[top][left];
    return ones == 0 || ones == side * side;
}

bool everyBlockIsUniform(int side) {
    for (int top = 0; top < gSize; top += side) {
        for (int left = 0; left < gSize; left += side) {
            if (!blockIsUniform(top, left, side)) {
                return false;
            }
        }
    }
    return true;
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    std::cin >> gSize;

    std::vector<std::vector<char>> matrix(gSize, std::vector<char>(gSize));
    for (int row = 0; row < gSize; ++row) {
        std::string hexRow;
        std::cin >> hexRow;
        int column = 0;
        for (char character : hexRow) {
            const int value = (character >= '0' && character <= '9')
                                  ? character - '0'
                                  : character - 'A' + 10;
            for (int shift = kHexBits - 1; shift >= 0; --shift) {
                matrix[row][column++] = (value >> shift) & 1;
            }
        }
    }

    buildPrefixSums(matrix);

    // Larger x always implies every smaller divisor works, so scan downwards.
    for (int side = gSize; side >= 1; --side) {
        if (gSize % side == 0 && everyBlockIsUniform(side)) {
            std::cout << side << '\n';
            return 0;
        }
    }
    return 0;
}