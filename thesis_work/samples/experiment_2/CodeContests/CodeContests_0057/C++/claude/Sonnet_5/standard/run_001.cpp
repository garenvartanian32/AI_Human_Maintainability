#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int n;
    cin >> n;
    vector<vector<char>> mat(n, vector<char>(n));
    for (int i = 0; i < n; i++) {
        string row;
        cin >> row;
        int col = 0;
        for (char ch : row) {
            int v = (ch >= '0' && ch <= '9') ? (ch - '0') : (ch - 'A' + 10);
            for (int shift = 3; shift >= 0; shift--) {
                mat[i][col++] = (v >> shift) & 1;
            }
        }
    }
    vector<vector<int>> pre(n+1, vector<int>(n+1, 0));
    for (int i = 1; i <= n; i++) {
        int rowSum = 0;
        for (int j = 1; j <= n; j++) {
            rowSum += mat[i-1][j-1];
            pre[i][j] = pre[i-1][j] + rowSum;
        }
    }
    vector<int> divisors;
    for (int x = 1; x <= n; x++) if (n % x == 0) divisors.push_back(x);
    sort(divisors.rbegin(), divisors.rend());
    for (int x : divisors) {
        bool ok = true;
        for (int bi = 0; bi < n && ok; bi += x) {
            for (int bj = 0; bj < n; bj += x) {
                int r1=bi+1,c1=bj+1,r2=bi+x,c2=bj+x;
                int s = pre[r2][c2]-pre[r1-1][c2]-pre[r2][c1-1]+pre[r1-1][c1-1];
                if (s != 0 && s != x*x) { ok = false; break; }
            }
        }
        if (ok) { cout << x << endl; return 0; }
    }
    return 0;
}