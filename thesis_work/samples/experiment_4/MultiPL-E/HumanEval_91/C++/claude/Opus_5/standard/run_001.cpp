#include <assert.h>
#include <bits/stdc++.h>

long is_bored(std::string S) {
    long count = 0;
    std::string sentence;

    auto process = [&]() {
        std::stringstream stream(sentence);
        std::string first_word;
        stream >> first_word;
        if (first_word == "I") ++count;
        sentence.clear();
    };

    for (char c : S) {
        if (c == '.' || c == '?' || c == '!') {
            process();
        } else {
            sentence += c;
        }
    }
    process();

    return count;
}