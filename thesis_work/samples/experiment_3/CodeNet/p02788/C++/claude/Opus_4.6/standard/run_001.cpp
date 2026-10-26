#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    long long n, d, a;
    cin >> n >> d >> a;
    vector<pair<long long,long long>> monsters(n);
    for (auto &m : monsters) cin >> m.first >> m.second;
    sort(monsters.begin(), monsters.end());
    vector<long long> xs(n), hs(n);
    for (int i = 0; i < n; i++) { xs[i] = monsters[i].first; hs[i] = monsters[i].second; }

    vector<long long> diff(n + 1, 0);
    long long current = 0, totalBombs = 0;
    for (int i = 0; i < n; i++) {
        current += diff[i];
        long long remaining = hs[i] - current;
        if (remaining > 0) {
            long long bombs = (remaining + a - 1) / a;
            totalBombs += bombs;
            current += bombs * a;
            long long limit = xs[i] + 2 * d;
            int r = (int)(upper_bound(xs.begin(), xs.end(), limit) - xs.begin()) - 1;
            if (r + 1 <= n) diff[r+1] -= bombs * a;
        }
    }
    cout << totalBombs << endl;
    return 0;
}