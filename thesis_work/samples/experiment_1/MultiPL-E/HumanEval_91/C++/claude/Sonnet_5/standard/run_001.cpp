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

    using namespace std;
    vector<string> parts;
    string cur;
    for (char c : S) {
        if (c == '.' || c == '?' || c == '!') {
            parts.push_back(cur);
            cur = "";
        } else cur += c;
    }
    if (!cur.empty()) parts.push_back(cur);
    long count = 0;
    for (auto &p : parts) {
        size_t start = p.find_first_not_of(' ');
        if (start == string::npos) continue;
        string trimmed = p.substr(start);
        if (trimmed.substr(0,2) == "I " || trimmed == "I") count++;
    }
    return count;
}