#include <bits/stdc++.h>
using namespace std;

vector<int> parent_;

int find(int x) {
    while (parent_[x] != x) {
        parent_[x] = parent_[parent_[x]];
        x = parent_[x];
    }
    return x;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, m;
    cin >> n >> m;
    parent_.resize(m + 1);
    iota(parent_.begin(), parent_.end(), 0);

    vector<int> basis;
    for (int i = 1; i <= n; i++) {
        int k;
        cin >> k;
        int u, v;
        if (k == 1) {
            u = 0;
            cin >> v;
        } else {
            cin >> u >> v;
        }
        int ru = find(u), rv = find(v);
        if (ru != rv) {
            parent_[ru] = rv;
            basis.push_back(i);
        }
    }

    const long long MOD = 1'000'000'007LL;
    int rank = basis.size();
    long long total = 1, base = 2;
    int e = rank;
    while (e > 0) {
        if (e & 1) total = total * base % MOD;
        base = base * base % MOD;
        e >>= 1;
    }
    cout << total << " " << rank << "\n";
    for (size_t i = 0; i < basis.size(); i++) {
        if (i > 0) cout << " ";
        cout << basis[i];
    }
    cout << "\n";
    return 0;
}