#include <bits/stdc++.h>
using namespace std;

int m;
vector<long long> cnt_, sumx_;

void update(int pos, long long c, long long s) {
    while (pos <= m) {
        cnt_[pos] += c;
        sumx_[pos] += s;
        pos += pos & (-pos);
    }
}

pair<long long,long long> query(int pos) {
    long long c = 0, s = 0;
    while (pos > 0) {
        c += cnt_[pos];
        s += sumx_[pos];
        pos -= pos & (-pos);
    }
    return {c, s};
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int n;
    cin >> n;
    vector<long long> xs(n), vs(n);
    for (auto &x : xs) cin >> x;
    for (auto &v : vs) cin >> v;

    vector<int> order(n);
    iota(order.begin(), order.end(), 0);
    sort(order.begin(), order.end(), [&](int a, int b) { return xs[a] < xs[b]; });

    vector<long long> sortedV = vs;
    sort(sortedV.begin(), sortedV.end());
    sortedV.erase(unique(sortedV.begin(), sortedV.end()), sortedV.end());
    m = sortedV.size();
    cnt_.assign(m + 1, 0);
    sumx_.assign(m + 1, 0);

    long long total = 0;
    for (int idx : order) {
        long long v = vs[idx];
        long long x = xs[idx];
        int r = (int)(lower_bound(sortedV.begin(), sortedV.end(), v) - sortedV.begin()) + 1;
        auto [c, s] = query(r);
        total += c * x - s;
        update(r, 1, x);
    }
    cout << total << endl;
    return 0;
}