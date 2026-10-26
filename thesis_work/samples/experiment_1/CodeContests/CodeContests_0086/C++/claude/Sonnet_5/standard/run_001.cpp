#include <bits/stdc++.h>
using namespace std;
vector<int> parent_;
int findp(int x) {
    while (parent_[x] != x) { parent_[x] = parent_[parent_[x]]; x = parent_[x]; }
    return x;
}
int main(){
    int n, m;
    scanf("%d %d", &n, &m);
    vector<int> p(n);
    for (auto &v: p) scanf("%d", &v);
    parent_.resize(n+1);
    for (int i=0;i<=n;i++) parent_[i]=i;
    for (int i=0;i<m;i++){
        int a,b;
        scanf("%d %d", &a, &b);
        int ra=findp(a), rb=findp(b);
        if (ra!=rb) parent_[ra]=rb;
    }
    map<int, vector<int>> groups;
    for (int i=1;i<=n;i++) groups[findp(i)].push_back(i);
    vector<int> result = p;
    for (auto &g: groups) {
        vector<int> idxs = g.second;
        vector<int> vals;
        for (auto i: idxs) vals.push_back(p[i-1]);
        sort(vals.rbegin(), vals.rend());
        sort(idxs.begin(), idxs.end());
        for (size_t k=0;k<idxs.size();k++) result[idxs[k]-1] = vals[k];
    }
    string out;
    for (int i=0;i<n;i++){ if(i>0) out+=' '; out += to_string(result[i]); }
    printf("%s\n", out.c_str());
    return 0;
}