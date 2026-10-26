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
    vector<int> p(n + 1);
    for (int i = 1; i <= n; i++) cin >> p[i];
    parent_.resize(n + 1);
    iota(parent_.begin(), parent_.end(), 0);
    for (int i = 0; i < m; i++) {
        int aj, bj;
        cin >> aj >> bj;
        int ra = find(aj), rb = find(bj);
        if (ra != rb) parent_[ra] = rb;
    }
    vector<vector<int>> groupsByRoot(n + 1);
    for (int i = 1; i <= n; i++) {
        int r = find(i);
        groupsByRoot[r].push_back(i);
    }
    vector<int> ans(n + 1);
    for (int r = 1; r <= n; r++) {
        if (groupsByRoot[r].empty()) continue;
        vector<int> &positions = groupsByRoot[r];
        vector<int> values;
        for (int pos : positions) values.push_back(p[pos]);
        sort(values.rbegin(), values.rend());
        for (size_t k = 0; k < positions.size(); k++) {
            ans[positions[k]] = values[k];
        }
    }
    string out;
    for (int i = 1; i <= n; i++) {
        if (i > 1) out += ' ';
        out += to_string(ans[i]);
    }
    cout << out << "\n";
    return 0;
}