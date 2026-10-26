#include <bits/stdc++.h>
using namespace std;

int sizeN;
vector<long long> sumTree;
vector<long long> lazy;
vector<bool> hasLazy;

void pushDown(int node, int nodeLen) {
    if (hasLazy[node]) {
        long long val = lazy[node];
        int left = 2*node, right = 2*node+1;
        int half = nodeLen / 2;
        sumTree[left] = val * half;
        sumTree[right] = val * half;
        lazy[left] = val; hasLazy[left] = true;
        lazy[right] = val; hasLazy[right] = true;
        hasLazy[node] = false;
    }
}

void update(int node, int nodeL, int nodeR, int s, int t, long long x) {
    if (t < nodeL || nodeR < s) return;
    if (s <= nodeL && nodeR <= t) {
        sumTree[node] = x * (long long)(nodeR - nodeL + 1);
        lazy[node] = x;
        hasLazy[node] = true;
        return;
    }
    pushDown(node, nodeR - nodeL + 1);
    int mid = (nodeL + nodeR) / 2;
    update(2*node, nodeL, mid, s, t, x);
    update(2*node+1, mid+1, nodeR, s, t, x);
    sumTree[node] = sumTree[2*node] + sumTree[2*node+1];
}

long long query(int node, int nodeL, int nodeR, int s, int t) {
    if (t < nodeL || nodeR < s) return 0;
    if (s <= nodeL && nodeR <= t) return sumTree[node];
    pushDown(node, nodeR - nodeL + 1);
    int mid = (nodeL + nodeR) / 2;
    return query(2*node, nodeL, mid, s, t) + query(2*node+1, mid+1, nodeR, s, t);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, q;
    cin >> n >> q;
    sizeN = 1;
    while (sizeN < n) sizeN *= 2;
    sumTree.assign(2*sizeN, 0);
    lazy.assign(2*sizeN, 0);
    hasLazy.assign(2*sizeN, false);

    string out;
    for (int i = 0; i < q; i++) {
        int typ;
        cin >> typ;
        if (typ == 0) {
            int s, t;
            long long x;
            cin >> s >> t >> x;
            update(1, 0, sizeN-1, s, t, x);
        } else {
            int s, t;
            cin >> s >> t;
            out += to_string(query(1, 0, sizeN-1, s, t)) + "\n";
        }
    }
    cout << out;
    return 0;
}