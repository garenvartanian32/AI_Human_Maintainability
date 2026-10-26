#include <bits/stdc++.h>
using namespace std;
int main(){
    long long x0,y0,x1,y1;
    int n;
    cin >> x0 >> y0 >> x1 >> y1 >> n;
    vector<long long> r(n), a(n), b(n);
    map<long long, vector<pair<long long,long long>>> rowSegs;
    for (int i=0;i<n;i++){
        cin >> r[i] >> a[i] >> b[i];
        rowSegs[r[i]].push_back({a[i], b[i]});
    }
    auto key = [](long long rr, long long cc){ return rr*2000000000LL + (cc+1000000000LL); };
    set<long long> candidates;
    for (int i=0;i<n;i++){
        for (long long cc : {a[i]-1,a[i],a[i]+1,b[i]-1,b[i],b[i]+1}) candidates.insert(key(r[i], cc));
    }
    candidates.insert(key(x0,y0));
    candidates.insert(key(x1,y1));
    set<long long> validCells;
    for (auto k : candidates) {
        long long rr = k / 2000000000LL;
        long long cc = k - rr*2000000000LL - 1000000000LL;
        auto it = rowSegs.find(rr);
        if (it != rowSegs.end()) {
            for (auto &seg : it->second) if (seg.first <= cc && cc <= seg.second) { validCells.insert(k); break; }
        }
    }
    long long startKey = key(x0,y0), endKey = key(x1,y1);
    if (!validCells.count(startKey) || !validCells.count(endKey)) { cout << -1 << endl; return 0; }
    map<long long,int> dist;
    dist[startKey] = 0;
    queue<long long> q;
    q.push(startKey);
    while (!q.empty()) {
        long long cur = q.front(); q.pop();
        if (cur == endKey) { cout << dist[cur] << endl; return 0; }
        long long cx = cur / 2000000000LL;
        long long cy = cur - cx*2000000000LL - 1000000000LL;
        for (int dx=-1;dx<=1;dx++){
            for (int dy=-1;dy<=1;dy++){
                if (dx==0 && dy==0) continue;
                long long nx=cx+dx, ny=cy+dy;
                long long nk = key(nx,ny);
                if (validCells.count(nk) && !dist.count(nk)) {
                    dist[nk] = dist[cur]+1;
                    q.push(nk);
                }
            }
        }
    }
    cout << -1 << endl;
    return 0;
}