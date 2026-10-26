#include <bits/stdc++.h>
using namespace std;

int main() {
    int n;
    cin >> n;
    vector<int> units(n);
    int total = 0;
    for (auto &u : units) {
        int w; cin >> w;
        u = w / 100;
        total += u;
    }
    if (total % 2 != 0) {
        cout << "NO" << endl;
        return 0;
    }
    int half = total / 2;
    vector<char> dp(half + 1, 0);
    dp[0] = 1;
    for (int u : units) {
        for (int s = half; s >= u; s--) {
            if (dp[s - u]) dp[s] = 1;
        }
    }
    cout << (dp[half] ? "YES" : "NO") << endl;
    return 0;
}