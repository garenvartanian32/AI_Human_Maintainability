#include <cctype>
#include <iostream>
#include <string>

namespace {

/**
 * Upper-cases letters ranked 1..threshold; lower-cases everything else.
 *
 * The original casing carries no information, so each character is first
 * normalised to lower case and then re-cased purely from its rank.
 */
std::string capitaliseLowRanked(std::string text, int threshold) {
    for (char& character : text) {
        const char lowered = static_cast<char>(std::tolower(static_cast<unsigned char>(character)));
        const int rank = lowered - 'a' + 1;
        character = (rank <= threshold)
                        ? static_cast<char>(std::toupper(static_cast<unsigned char>(lowered)))
                        : lowered;
    }
    return text;
}

}  // namespace

int main() {
    std::string text;
    int threshold;
    std::getline(std::cin, text);
    std::cin >> threshold;

    std::cout << capitaliseLowRanked(text, threshold) << '\n';
    return 0;
}