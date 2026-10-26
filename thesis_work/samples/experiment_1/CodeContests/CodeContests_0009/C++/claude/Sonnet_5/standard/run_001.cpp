#include <bits/stdc++.h>
using namespace std;
int main(){
    int n, k;
    cin >> n >> k;
    vector<long long> both, onlyA, onlyB;
    for (int i=0;i<n;i++){
        long long t; int a,b;
        cin >> t >> a >> b;
        if (a==1 && b==1) both.push_back(t);
        else if (a==1) onlyA.push_back(t);
        else if (b==1) onlyB.push_back(t);
    }
    sort(both.begin(),both.end());
    sort(onlyA.begin(),onlyA.end());
    sort(onlyB.begin(),onlyB.end());
    vector<long long> prefBoth(both.size()+1,0), prefA(onlyA.size()+1,0), prefB(onlyB.size()+1,0);
    for (size_t i=0;i<both.size();i++) prefBoth[i+1]=prefBoth[i]+both[i];
    for (size_t i=0;i<onlyA.size();i++) prefA[i+1]=prefA[i]+onlyA[i];
    for (size_t i=0;i<onlyB.size();i++) prefB[i+1]=prefB[i]+onlyB[i];
    bool has = false;
    long long best = 0;
    for (int x = 0; x <= min((int)both.size(), k); x++) {
        int needA = max(0, k-x);
        int needB = max(0, k-x);
        if (needA > (int)onlyA.size() || needB > (int)onlyB.size()) continue;
        long long total = prefBoth[x] + prefA[needA] + prefB[needB];
        if (!has || total < best) { best = total; has = true; }
    }
    cout << (has ? best : -1) << endl;
    return 0;
}