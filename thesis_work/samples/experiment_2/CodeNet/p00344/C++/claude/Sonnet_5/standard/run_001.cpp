#include <bits/stdc++.h>
using namespace std;

int main() {
    int n;
    cin >> n;
    vector<long long> a(n);
    for (auto &x : a) cin >> x;
    vector<int> nxt(n);
    for (int i = 0; i < n; i++) nxt[i] = (int)((i + a[i]) % n);
    vector<int> color(n, 0);
    vector<bool> onCycle(n, false);
    for (int i = 0; i < n; i++) {
        if (color[i] != 0) continue;
        vector<int> path;
        int cur = i;
        while (color[cur] == 0) {
            color[cur] = 1;
            path.push_back(cur);
            cur = nxt[cur];
        }
        if (color[cur] == 1) {
            int idx = -1;
            for (size_t k = 0; k < path.size(); k++) if (path[k] == cur) { idx = k; break; }
            for (size_t k = idx; k < path.size(); k++) onCycle[path[k]] = true;
        }
        for (int node : path) color[node] = 2;
    }
    int count = 0;
    for (bool b : onCycle) if (b) count++;
    cout << count << endl;
    return 0;
}