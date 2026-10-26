#include <bits/stdc++.h>
using namespace std;

int charToIdx(char c) {
    if (islower(c)) return c - 'a';
    else return 26 + (c - 'A');
}

char idxToChar(int i) {
    i = ((i % 52) + 52) % 52;
    if (i < 26) return (char)('a' + i);
    else return (char)('A' + i - 26);
}

int main() {
    string out;
    int n;
    while (cin >> n) {
        if (n == 0) break;
        vector<int> keys(n);
        for (auto &k : keys) cin >> k;
        string s;
        cin >> s;
        string res;
        for (size_t i = 0; i < s.size(); i++) {
            char c = s[i];
            int k = keys[i % n];
            int origIdx = charToIdx(c);
            int decIdx = origIdx - k;
            res += idxToChar(decIdx);
        }
        out += res + "\n";
    }
    cout << out;
    return 0;
}