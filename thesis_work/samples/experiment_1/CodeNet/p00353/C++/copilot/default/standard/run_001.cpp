#include <bits/stdc++.h>
using namespace std;
using ll = long long;

void solve() {
    // Read input, compute answer, print output.
    // Replace this with problem-specific logic.
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    // Example placeholder: echo input
    string s;
    if (!(cin >> s)) return;
    cout << s << '\n';
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int T = 1;
    if (!(cin >> ws).eof()) {
        // If first token is number of test cases, use it; otherwise reset input.
        cin.clear();
        streampos pos = cin.tellg();
        string tok;
        if (cin >> tok) {
            bool isNumber = all_of(tok.begin(), tok.end(), ::isdigit);
            if (isNumber) {
                T = stoi(tok);
            } else {
                cin.seekg(pos);
            }
        }
    }
    for (int tc = 0; tc < T; ++tc) solve();
    return 0;
}