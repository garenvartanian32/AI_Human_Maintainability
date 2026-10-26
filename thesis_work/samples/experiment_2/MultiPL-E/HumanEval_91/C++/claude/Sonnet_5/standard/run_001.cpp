#include<assert.h>
#include<bits/stdc++.h>
// You'll be given a string of words, and your task is to count the number
// of boredoms. A boredom is a sentence that starts with the word "I".
// Sentences are delimited by '.', '?' or '!'.
// For example:
// >>> is_bored(("Hello world"))
// (0)
// >>> is_bored(("The sky is blue. The sun is shining. I love this weather"))
// (1)
long is_bored(std::string S) {
    std::vector<std::string> sentences;
    std::string cur;
    for (char c : S) {
        if (c == '.' || c == '?' || c == '!') {
            sentences.push_back(cur);
            cur.clear();
        } else {
            cur += c;
        }
    }
    sentences.push_back(cur);

    long count = 0;
    for (auto &s : sentences) {
        size_t start = s.find_first_not_of(" \t\n\r");
        if (start == std::string::npos) continue;
        size_t end = s.find_last_not_of(" \t\n\r");
        std::string trimmed = s.substr(start, end - start + 1);
        if (trimmed == "I" || (trimmed.size() >= 2 && trimmed[0] == 'I' && trimmed[1] == ' ')) {
            count++;
        }
    }
    return count;
}

int main() {
    std::cout << is_bored("Hello world") << std::endl;
    std::cout << is_bored("The sky is blue. The sun is shining. I love this weather") << std::endl;
}