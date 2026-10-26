#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    long long x0, y0, x1, y1;
    cin >> x0 >> y0 >> x1 >> y1;
    int n;
    cin >> n;
    unordered_set<long long> cells;
    auto key = [](long long r, long long c) {
        return r * 2000000000LL + c;
    };
    for (int i = 0; i < n; i++) {
        long long r, a, b;
        cin >> r >> a >> b;
        for (long long c = a; c <= b; c++) {
            cells.insert(key(r, c));
        }
    }
    long long start = key(x0, y0);
    long long end_ = key(x1, y1);
    if (!cells.count(start) || !cells.count(end_)) {
        cout << -1 << endl;
        return 0;
    }
    unordered_map<long long, int> dist;
    dist[start] = 0;
    queue<long long> q;
    q.push(start);
    int dr[8] = {-1,-1,-1,0,0,1,1,1};
    int dc[8] = {-1,0,1,-1,1,-1,0,1};
    while (!q.empty()) {
        long long cur = q.front(); q.pop();
        if (cur == end_) {
            cout << dist[cur] << endl;
            return 0;
        }
        long long cr = cur / 2000000000LL;
        long long cc = cur - cr * 2000000000LL;
        for (int d = 0; d < 8; d++) {
            long long nr = cr + dr[d];
            long long nc = cc + dc[d];
            long long nxt = key(nr, nc);
            if (cells.count(nxt) && !dist.count(nxt)) {
                dist[nxt] = dist[cur] + 1;
                q.push(nxt);
            }
        }
    }
    cout << -1 << endl;
    return 0;
}