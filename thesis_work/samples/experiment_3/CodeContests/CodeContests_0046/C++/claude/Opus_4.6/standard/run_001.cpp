#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, m;
    cin >> n >> m;
    vector<long long> a(n), b(n), c(m), d(m);
    for (int i = 0; i < n; i++) cin >> a[i] >> b[i];
    for (int j = 0; j < m; j++) cin >> c[j] >> d[j];
    string out;
    for (int i = 0; i < n; i++) {
        int bestJ = -1;
        long long bestDist = LLONG_MAX;
        for (int j = 0; j < m; j++) {
            long long dist = llabs(a[i]-c[j]) + llabs(b[i]-d[j]);
            if (dist < bestDist) {
                bestDist = dist;
                bestJ = j + 1;
            }
        }
        out += to_string(bestJ) + "\n";
    }
    cout << out;
    return 0;
}