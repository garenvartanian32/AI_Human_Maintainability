#include <bits/stdc++.h>
using namespace std;
int main(){
    int n, c;
    cin >> n >> c;
    vector<vector<long long>> D(c+1, vector<long long>(c+1));
    for (int i = 1; i <= c; i++) for (int j = 1; j <= c; j++) cin >> D[i][j];
    vector<vector<int>> grid(n, vector<int>(n));
    for (auto &row : grid) for (auto &v : row) cin >> v;
    vector<vector<long long>> hist(3, vector<long long>(c+1, 0));
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++) {
            int g = ((i+1)+(j+1)) % 3;
            hist[g][grid[i][j]]++;
        }
    vector<vector<long long>> cost(3, vector<long long>(c+1, 0));
    for (int g = 0; g < 3; g++)
        for (int k = 1; k <= c; k++) {
            long long s = 0;
            for (int col = 1; col <= c; col++) s += hist[g][col] * D[col][k];
            cost[g][k] = s;
        }
    long long best = LLONG_MAX;
    for (int k0 = 1; k0 <= c; k0++)
        for (int k1 = 1; k1 <= c; k1++) {
            if (k1 == k0) continue;
            for (int k2 = 1; k2 <= c; k2++) {
                if (k2 == k0 || k2 == k1) continue;
                long long total = cost[0][k0] + cost[1][k1] + cost[2][k2];
                best = min(best, total);
            }
        }
    cout << best << endl;
    return 0;
}