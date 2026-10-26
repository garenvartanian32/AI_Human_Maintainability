#include <iostream>
#include <string>

namespace {

constexpr char kFirstLetter = 'a';

int gLength;
std::string gBuffer;
std::string gResults;

/**
 * Extends the buffer at `position`, given how many distinct letters are in use.
 *
 * Each letter may be at most one past the largest used so far, which keeps
 * exactly one representative of every partition of the positions and emits the
 * strings already sorted.
 */
void extend(int position, int lettersUsed) {
    if (position == gLength) {
        gResults += gBuffer;
        gResults += '\n';
        return;
    }

    for (int letterIndex = 0; letterIndex <= lettersUsed; ++letterIndex) {
        gBuffer[position] = static_cast<char>(kFirstLetter + letterIndex);
        const int nextUsed = (letterIndex == lettersUsed) ? lettersUsed + 1 : lettersUsed;
        extend(position + 1, nextUsed);
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