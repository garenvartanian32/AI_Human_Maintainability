#include <iostream>
#include <string>

namespace {

constexpr char kFirstLetter = 'a';

int gLength;
std::string gBuffer;
std::string gResults;

/**
 * Extends the buffer at `position`, given the largest letter index used so far.
 *
 * A string is in normal form when each letter is at most one past the largest
 * letter used before it, so building left to right in alphabetical order yields
 * the strings already sorted.
 */
void extend(int position, int highestUsed) {
    if (position == gLength) {
        gResults += gBuffer;
        gResults += '\n';
        return;
    }

    for (int letterIndex = 0; letterIndex <= highestUsed; ++letterIndex) {
        gBuffer[position] = static_cast<char>(kFirstLetter + letterIndex);
        const int nextHighest = (letterIndex == highestUsed) ? highestUsed + 1 : highestUsed;
        extend(position + 1, nextHighest);
    }
}

}  // namespace

int main() {
    std::cin >> gLength;
    gBuffer.assign(gLength, kFirstLetter);

    extend(0, 0);
    std::cout << gResults;
    return 0;
}