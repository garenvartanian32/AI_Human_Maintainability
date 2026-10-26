#include <assert.h>
#include <bits/stdc++.h>

long is_bored(std::string S) {
    long boredom_count = 0;
    std::string current_sentence;

    auto count_if_bored = [&]() {
        std::istringstream sentence_stream(current_sentence);
        std::string first_word;
        sentence_stream >> first_word;

        if (first_word == "I") {
            ++boredom_count;
        }

        current_sentence.clear();
    };

    for (char character : S) {
        const bool ends_sentence =
            character == '.' || character == '?' || character == '!';

        if (ends_sentence) {
            count_if_bored();
        } else {
            current_sentence += character;
        }
    }

    count_if_bored();
    return boredom_count;
}