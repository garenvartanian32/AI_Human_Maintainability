#include <bits/stdc++.h>
using namespace std;
int main() {
    int n, c;
    cin >> n >> c;
    vector<vector<long long>> D(c, vector<long long>(c));
    for (int i = 0; i < c; i++)
        for (int j = 0; j < c; j++) cin >> D[i][j];
    vector<vector<long long>> count(3, vector<long long>(c, 0));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int col; cin >> col;
            int g = (i + j) % 3;
            count[g][col-1]++;
        }
    }
    vector<vector<long long>> cost(3, vector<long long>(c));
    for (int g = 0; g < 3; g++) {
        for (int y = 0; y < c; y++) {
            long long s = 0;
            for (int x = 0; x < c; x++) s += count[g][x] * D[x][y];
            cost[g][y] = s;
        }
    }
    long long best = LLONG_MAX;
    for (int y0 = 0; y0 < c; y0++) {
        for (int y1 = 0; y1 < c; y1++) {
            if (y1 == y0) continue;
            for (int y2 = 0; y2 < c; y2++) {
                if (y2 == y0 || y2 == y1) continue;
                long long total = cost[0][y0] + cost[1][y1] + cost[2][y2];
                if (total < best) best = total;
            }
        }
    }
    cout << best << endl;
    return 0;
}