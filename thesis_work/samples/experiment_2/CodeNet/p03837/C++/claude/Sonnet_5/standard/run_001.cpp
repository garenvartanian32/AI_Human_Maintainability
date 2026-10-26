#include <bits/stdc++.h>
using namespace std;

int main() {
    int n, m;
    cin >> n >> m;
    const long long INF = LLONG_MAX / 4;
    vector<vector<long long>> dist(n+1, vector<long long>(n+1, INF));
    for (int i = 1; i <= n; i++) dist[i][i] = 0;
    vector<int> ea(m), eb(m);
    vector<long long> ec(m);
    for (int i = 0; i < m; i++) {
        int a, b; long long c;
        cin >> a >> b >> c;
        ea[i] = a; eb[i] = b; ec[i] = c;
        if (c < dist[a][b]) { dist[a][b] = c; dist[b][a] = c; }
    }
    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            if (dist[i][k] >= INF) continue;
            for (int j = 1; j <= n; j++) {
                long long nd = dist[i][k] + dist[k][j];
                if (nd < dist[i][j]) dist[i][j] = nd;
            }
        }
    }
    int count = 0;
    for (int i = 0; i < m; i++) {
        if (dist[ea[i]][eb[i]] < ec[i]) count++;
    }
    cout << count << endl;
    return 0;
}