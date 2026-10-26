#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    cin >> t;
    string out;
    for (int tc = 0; tc < t; tc++) {
        long long k;
        int n, m;
        cin >> k >> n >> m;
        vector<long long> a(n), b(m);
        for (auto &x : a) cin >> x;
        for (auto &x : b) cin >> x;
        long long length = k;
        int i = 0, j = 0;
        vector<long long> res;
        bool ok = true;
        while (i < n || j < m) {
            if (i < n && (a[i] == 0 || a[i] <= length)) {
                if (a[i] == 0) length++;
                res.push_back(a[i]);
                i++;
            } else if (j < m && (b[j] == 0 || b[j] <= length)) {
                if (b[j] == 0) length++;
                res.push_back(b[j]);
                j++;
            } else {
                ok = false;
                break;
            }
        }
        if (ok) {
            for (size_t x = 0; x < res.size(); x++) {
                if (x > 0) out += ' ';
                out += to_string(res[x]);
            }
            out += '\n';
        } else {
            out += "-1\n";
        }
    }
    cout << out;
    return 0;
}