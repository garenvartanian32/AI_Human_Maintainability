#include <bits/stdc++.h>
using namespace std;
int size_;
vector<long long> segSum, lazy_;
vector<int> segLen;
vector<bool> hasLazy;

void applyVal(int node, long long val) {
    segSum[node] = val * segLen[node];
    lazy_[node] = val;
    hasLazy[node] = true;
}
void pushDown(int node) {
    if (hasLazy[node]) {
        applyVal(2*node, lazy_[node]);
        applyVal(2*node+1, lazy_[node]);
        hasLazy[node] = false;
    }
}
void update(int node, int nl, int nr, int l, int r, long long val) {
    if (r <= nl || nr <= l) return;
    if (l <= nl && nr <= r) { applyVal(node, val); return; }
    pushDown(node);
    int mid = (nl+nr)/2;
    update(2*node, nl, mid, l, r, val);
    update(2*node+1, mid, nr, l, r, val);
    segSum[node] = segSum[2*node] + segSum[2*node+1];
}
long long query(int node, int nl, int nr, int l, int r) {
    if (r <= nl || nr <= l) return 0;
    if (l <= nl && nr <= r) return segSum[node];
    pushDown(node);
    int mid = (nl+nr)/2;
    return query(2*node, nl, mid, l, r) + query(2*node+1, mid, nr, l, r);
}
int main(){
    int n,q;
    cin >> n >> q;
    size_ = 1;
    while (size_ < n) size_ *= 2;
    segSum.assign(2*size_, 0);
    lazy_.assign(2*size_, 0);
    hasLazy.assign(2*size_, false);
    segLen.assign(2*size_, 0);
    for (int i=0;i<size_;i++) segLen[size_+i]=1;
    for (int i=size_-1;i>=1;i--) segLen[i]=segLen[2*i]+segLen[2*i+1];
    string out;
    for (int i=0;i<q;i++){
        int t; cin >> t;
        if (t==0){
            int s,e; long long x;
            cin >> s >> e >> x;
            update(1,0,size_,s,e+1,x);
        } else {
            int s,e;
            cin >> s >> e;
            out += to_string(query(1,0,size_,s,e+1)) + "\n";
        }
    }
    cout << out;
    return 0;
}