#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    cin >> t;
    string out;
    for (int tc = 0; tc < t; tc++) {
        int n;
        cin >> n;
        vector<unsigned long long> a(n);
        for (auto &x : a) cin >> x;
        string s;
        cin >> s;

        unordered_map<int, unsigned long long> basis;
        int result = 0;
        for (int i = n - 1; i >= 0; i--) {
            unsigned long long v = a[i];
            for (int bit = 63; bit >= 0; bit--) {
                if ((v >> bit) & 1ULL) {
                    auto it = basis.find(bit);
                    if (it != basis.end()) {
                        v ^= it->second;
                    } else {
                        break;
                    }
                }
            }
            if (v != 0) {
                int lb = 63 - __builtin_clzll(v);
                basis[lb] = v;
                if (s[i] == '1') {
                    result = 1;
                    break;
                }
            }
        }
        out += to_string(result) + "\n";
    }
    cout << out;
    return 0;
}