#include <bits/stdc++.h>
using namespace std;
int main(){
    int n, m;
    cin >> n >> m;
    vector<array<int,3>> edges(m);
    const long long INF = LLONG_MAX/4;
    vector<vector<long long>> dist(n+1, vector<long long>(n+1, INF));
    for (int i = 1; i <= n; i++) dist[i][i] = 0;
    for (auto &e : edges) {
        cin >> e[0] >> e[1] >> e[2];
        if (e[2] < dist[e[0]][e[1]]) { dist[e[0]][e[1]] = e[2]; dist[e[1]][e[0]] = e[2]; }
    }
    for (int k = 1; k <= n; k++)
        for (int i = 1; i <= n; i++) {
            if (dist[i][k] >= INF) continue;
            for (int j = 1; j <= n; j++)
                if (dist[i][k] + dist[k][j] < dist[i][j]) dist[i][j] = dist[i][k] + dist[k][j];
        }
    int count = 0;
    for (auto &e : edges) if (dist[e[0]][e[1]] != e[2]) count++;
    cout << count << endl;
    return 0;
}