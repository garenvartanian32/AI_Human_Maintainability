#include <algorithm>
#include <array>
#include <iostream>
#include <string>

namespace {

constexpr int kAlphabetSize = 26;
using LetterCounts = std::array<int, kAlphabetSize>;

LetterCounts letterCounts(const std::string& word) {
    LetterCounts counts{};
    for (char letter : word) {
        counts[letter - 'a']++;
    }
    return counts;
}

/// How many whole copies of `needed` fit inside `available`.
int maxCopies(const LetterCounts& available, const LetterCounts& needed, int ceiling) {
    int copies = ceiling;
    for (int letter = 0; letter < kAlphabetSize; ++letter) {
        if (needed[letter] > 0) {
            copies = std::min(copies, available[letter] / needed[letter]);
        }
    }
    return copies;
}

}  // namespace

int main() {
    std::string source, firstWord, secondWord;
    std::getline(std::cin, source);
    std::getline(std::cin, firstWord);
    std::getline(std::cin, secondWord);

    const LetterCounts available = letterCounts(source);
    const LetterCounts needFirst = letterCounts(firstWord);
    const LetterCounts needSecond = letterCounts(secondWord);

    // Copies of the two words compete for the same letters, so we try every
    // feasible count of the first word and keep the best total.
    int bestTotal = -1;
    int bestFirstCount = 0;
    int bestSecondCount = 0;

    const int firstCeiling = maxCopies(available, needFirst, static_cast<int>(source.size()));
    for (int firstCount = 0; firstCount <= firstCeiling; ++firstCount) {
        int secondCount = static_cast<int>(source.size());
        for (int letter = 0; letter < kAlphabetSize; ++letter) {
            if (needSecond[letter] > 0) {
                const int leftover = available[letter] - firstCount * needFirst[letter];
                secondCount = std::min(secondCount, leftover / needSecond[letter]);
            }
        }

        if (firstCount + secondCount > bestTotal) {
            bestTotal = firstCount + secondCount;
            bestFirstCount = firstCount;
            bestSecondCount = secondCount;
        }
    }

    std::string answer;
    for (int i = 0; i < bestFirstCount; ++i) answer += firstWord;
    for (int i = 0; i < bestSecondCount; ++i) answer += secondWord;

    // Any letters not consumed by a copy can be appended in any order.
    for (int letter = 0; letter < kAlphabetSize; ++letter) {
        const int leftover = available[letter]
                           - bestFirstCount * needFirst[letter]
                           - bestSecondCount * needSecond[letter];
        answer += std::string(leftover, static_cast<char>('a' + letter));
    }

    std::cout << answer << '\n';
    return 0;
}