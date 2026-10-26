#include <bits/stdc++.h>
using namespace std;
int main(){
    long long n; cin >> n;
    long long total=0, v;
    for (int i=0;i<n;i++){ cin >> v; total += v; }
    total -= n*(n+1)/2;
    cout << total << endl;
    return 0;
}