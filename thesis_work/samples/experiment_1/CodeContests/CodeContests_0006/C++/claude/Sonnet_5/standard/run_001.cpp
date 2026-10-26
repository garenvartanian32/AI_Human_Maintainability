#include <bits/stdc++.h>
using namespace std;
int main(){
    int n;
    cin >> n;
    vector<long long> x(n), v(n);
    for (auto &e: x) cin >> e;
    for (auto &e: v) cin >> e;
    vector<int> idx(n);
    for (int i=0;i<n;i++) idx[i]=i;
    sort(idx.begin(), idx.end(), [&](int a, int b){ return x[a]<x[b]; });
    long long total=0;
    for (int i=0;i<n-1;i++){
        int a=idx[i], b=idx[i+1];
        if (v[a]<=v[b]) total += x[b]-x[a];
    }
    cout << total << endl;
    return 0;
}