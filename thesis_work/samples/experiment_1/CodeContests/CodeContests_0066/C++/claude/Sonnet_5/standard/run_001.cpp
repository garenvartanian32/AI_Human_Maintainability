#include <bits/stdc++.h>
using namespace std;
int main(){
    int n,m,k;
    scanf("%d %d %d", &n, &m, &k);
    vector<int> a(n), b(m);
    for (auto &v: a) scanf("%d", &v);
    for (auto &v: b) scanf("%d", &v);
    vector<int> merged;
    int i=0,j=0;
    while (i<n && j<m) { merged.push_back(a[i++]); merged.push_back(b[j++]); }
    while (i<n) merged.push_back(a[i++]);
    while (j<m) merged.push_back(b[j++]);
    string out;
    for (size_t x=0;x<merged.size();x++){ if(x>0) out+=' '; out += to_string(merged[x]); }
    cout << out << endl;
    return 0;
}