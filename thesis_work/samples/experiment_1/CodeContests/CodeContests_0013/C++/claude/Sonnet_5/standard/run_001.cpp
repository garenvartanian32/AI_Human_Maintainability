#include <bits/stdc++.h>
using namespace std;
vector<int> parent_;
int findp(int x) {
    while (parent_[x] != x) { parent_[x] = parent_[parent_[x]]; x = parent_[x]; }
    return x;
}
bool unionp(int x, int y) {
    int rx = findp(x), ry = findp(y);
    if (rx != ry) { parent_[rx] = ry; return true; }
    return false;
}
int main(){
    const long long MOD = 1000000007;
    int n, m;
    cin >> n >> m;
    parent_.resize(m+1);
    for (int i=0;i<=m;i++) parent_[i]=i;
    set<int> singleSeen;
    int rankCount = 0;
    vector<int> chosen;
    for (int i=0;i<n;i++){
        string vec;
        cin >> vec;
        vector<int> ones;
        for (int j=0;j<(int)vec.size();j++) if (vec[j]=='1') ones.push_back(j+1);
        if (ones.empty()) continue;
        else if (ones.size()==1) {
            int v = ones[0];
            if (!singleSeen.count(v)) {
                singleSeen.insert(v);
                rankCount++;
                chosen.push_back(i+1);
            }
        } else {
            int a=ones[0], b=ones[1];
            if (unionp(a,b)) { rankCount++; chosen.push_back(i+1); }
        }
    }
    long long size=1;
    for (int i=0;i<rankCount;i++) size=size*2%MOD;
    cout << size << "\n" << chosen.size() << "\n";
    for (size_t i=0;i<chosen.size();i++){ if(i>0) cout<<' '; cout<<chosen[i]; }
    cout << "\n";
    return 0;
}