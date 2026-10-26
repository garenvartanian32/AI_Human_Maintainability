#include <bits/stdc++.h>
using namespace std;

int main() {
    int n;
    string out;
    while (cin >> n) {
        if (n == 0) break;
        string cards;
        cin >> cards;
        vector<int> hands(n, 0);
        int field = 0;
        for (size_t i = 0; i < cards.size(); i++) {
            char c = cards[i];
            int player = i % n;
            if (c == 'M') {
                hands[player]++;
            } else if (c == 'S') {
                field += hands[player] + 1;
                hands[player] = 0;
            } else if (c == 'L') {
                hands[player] += field + 1;
                field = 0;
            }
        }
        sort(hands.begin(), hands.end());
        string line;
        for (int i = 0; i < n; i++) {
            if (i > 0) line += ' ';
            line += to_string(hands[i]);
        }
        line += ' ' + to_string(field);
        out += line + "\n";
    }
    cout << out;
    return 0;
}