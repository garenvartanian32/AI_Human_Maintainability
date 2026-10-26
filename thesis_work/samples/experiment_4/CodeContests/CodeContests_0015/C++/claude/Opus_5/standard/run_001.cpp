#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1'000'000'007LL;
const int MAXM = 200001;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    vector<long long> L(MAXM + 1);
    L[0] = 1;
    for (int t = 1; t <= MAXM; t++) {
        long long term1 = (t <= 8) ? 1 : L[t - 9];
        long long term2 = (t <= 9) ? 1 : L[t - 10];
        L[t] = (term1 + term2) % MOD;
    }

    int t;
    cin >> t;
    string out;
    for (int i = 0; i < t; i++) {
        string n;
        long long m;
        cin >> n >> m;
        long long total = 0;
        for (char ch : n) {
            int d = ch - '0';
            long long rem = 9 - d;
            if (m < rem) total += 1;
            else total += L[m - rem];
            total %= MOD;
        }
        out += to_string(total);
        out += '\n';
    }
    cout << out;
    return 0;
}